package aplicacion.servicios;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import aplicacion.daos.Elemento;
import aplicacion.dtos.ElementoDTO;
import aplicacion.utiles.ADto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockTimeoutException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.PessimisticLockException;
import jakarta.persistence.QueryTimeoutException;
import jakarta.persistence.RollbackException;
import jakarta.persistence.TransactionRequiredException;

/**
 * Implementación de la interfaz Elemento
 * 
 * @author Puesto8
 * Fecha: 14/12/2023
 */
public class ElementoImpl implements ElementoInterfaz {

	@Override
	public void darDeAltaElemento(EntityManager em) {
		// Scanner para leer los datos
		Scanner sc = new Scanner(System.in);

		try {
			// Pedimos el nombre
			System.out.print("Introduzca el nombre del elemento: ");
			String nombre = sc.nextLine();

			// Pedimos la descripcion
			System.out.print("Introduzca la descripcion del elemento: ");
			String descripcion = sc.nextLine();

			// Pedimos la cantidad
			System.out.print("Introduzca la cantidad del elemento: ");
			int cantidad = sc.nextInt();

			// Creamos el elemento
			// En el codigo le pondremos la descripcion de momento
			Elemento elemento = new Elemento(descripcion, nombre, descripcion, cantidad);

			// Hacemos el insert a la base de datos
			em.getTransaction().begin();
			em.persist(elemento);
			em.getTransaction().commit();

			// Ahora traemos el elemento
			Elemento elementoObtenido = obtieneElementoPorDescripcion(em, descripcion);

			// Ahora modificamos el elemento que insertamos
			elemento.setCodigoElemento(elementoObtenido.getIdElemento() + "" + elementoObtenido.getNombreElemento());

			// Y ahora hacemos el update
			em.getTransaction().begin();
			em.merge(elemento);
			em.getTransaction().commit();
			
			// Si llega aqui es porque se ha dado de alta el elemento
			System.out.println("[INFO-ElementoImpl-darDeAltaElemento] Se ha dado de alta el elemento");

		} catch (InputMismatchException e) {
			System.err.println("[ERROR-ElementoImpl-darDeAltaElemento] Error " + e.getMessage());
		} catch (NoSuchElementException e) {
			System.err.println("[ERROR-ElementoImpl-darDeAltaElemento] Error " + e.getMessage());
		} catch (IllegalStateException e) {
			System.err.println("[ERROR-ElementoImpl-darDeAltaElemento] Error " + e.getMessage());
		} catch (TransactionRequiredException e) {
			System.err.println("[ERROR-ElementoImpl-darDeAltaElemento] Error " + e.getMessage());
		} catch (EntityExistsException e) {
			System.err.println("[ERROR-ElementoImpl-darDeAltaElemento] Error " + e.getMessage());
		}

	}

	/**
	 * Método que obtiene un elemento de la base de datos según la descripcion.
	 * 
	 * @author Puesto8 
	 * Fecha: 14/12/2023
	 * 
	 * @param em
	 * @param descripcion
	 * @return
	 */
	private Elemento obtieneElementoPorDescripcion(EntityManager em, String descripcion) {

		// Creamos una lista auxiliar donde almacenaremos la lista de elementosDAO
		List<Elemento> listaAuxiliar = em
				.createQuery("SELECT e FROM Elemento e WHERE e.descripcionElemento=:descripcion", Elemento.class)
				.setParameter("descripcion", descripcion).getResultList();

		// Devolvemos el elemento convertido
		return listaAuxiliar.get(0);
	}

	@Override
	public void eliminaElemento(EntityManager em) {
		// Scanner para leer los datos
		Scanner sc = new Scanner(System.in);

		Elemento elementoObtenido = null;
		try {
			// Pedimos el codigo del elemento
			System.out.print("Itroduzca el codigo del elemento a borrar: ");
			String codigo = sc.nextLine();

			// Buscamos el elemento en la base de datos
			elementoObtenido = em.createQuery("SELECT e FROM Elemento e WHERE e.codigoElemento=:codigo", Elemento.class)
					.setParameter("codigo", codigo).getSingleResult();

		} catch (NoSuchElementException e) {
			System.err.println("[ERROR-ElementoImpl-eliminaElemento] Error " + e.getMessage());
		} catch (IllegalStateException e) {
			System.err.println("[ERROR-ElementoImpl-eliminaElemento] Error " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.err.println("[ERROR-ElementoImpl-eliminaElemento] Error " + e.getMessage());
		} catch (Exception e) {
			System.err.println("[ERROR-ElementoImpl-eliminaElemento] Error " + e.getMessage());
		}

		// Si el elementoObtenido es distinto de null, es decir, existe en la base de
		// datos
		if (elementoObtenido != null) {
			try {
				// Borramos el elemento de la base de datos
				em.getTransaction().begin();
				em.remove(elementoObtenido);
				em.getTransaction().commit();
				
				// Si llega aqui es porque el elemento se ha eliminado
				System.out.println("[INFO-ElementoImpl-eliminaElemento] Se ha eliminado el elemento");

			} catch (TransactionRequiredException e) {
				System.err.println("[ERROR-ElementoImpl-eliminaElemento] Error " + e.getMessage());
			} catch (IllegalStateException e) {
				System.err.println("[ERROR-ElementoImpl-eliminaElemento] Error " + e.getMessage());
			} catch (RollbackException e) {
				System.err.println("[ERROR-ElementoImpl-eliminaElemento] Error " + e.getMessage());
			}
		} else
			System.err.println(
					"[ERROR-ElementoImpl-eliminaElemento] Error no existe ningún elemento con el codigo introducido");

	}

	@Override
	public void modificarCantidadElemento(EntityManager em) {
		// Scanner para leer los datos
		Scanner sc = new Scanner(System.in);

		try {
			// Pedimos el codigo del elemento a modificar
			System.out.print("Introduzca el código del elemento a modificar: ");
			String codigo = sc.nextLine();

			// Buscamos el elemento en la base de datos
			Elemento elementoObtenido = em
					.createQuery("SELECT e FROM Elemento e WHERE e.codigoElemento=:codigo", Elemento.class)
					.setParameter("codigo", codigo).getSingleResult();

			// Ahora preguntamos la cantidad que quiere disminuir
			int cantidadDisminuir;
			do {
				System.out.print("Introduzca la cantidad a disminuir: ");
				cantidadDisminuir = sc.nextInt();

				if (elementoObtenido.getCantidadElemento() - cantidadDisminuir < 0)
					System.err.println(
							"[ERROR-ElementoImpl-modificarCantidadElemento] Error la cantidad a disminuir no puede dejar el stock en negativo");
			} while (elementoObtenido.getCantidadElemento() - cantidadDisminuir < 0);

			// Ahora que tenemos la cantidad a disminuir actualizamos el elemento y hacemos
			// el update a la base de datos
			elementoObtenido.setCantidadElemento(elementoObtenido.getCantidadElemento() - cantidadDisminuir);
			em.getTransaction().begin();
			em.merge(elementoObtenido);
			em.getTransaction().commit();
			
			// Si llega aqui es porque el elemento se ha modificado
			System.out.println("[INFO-ElementoImpl-modificarCantidadElemento] Se ha modificado el elemento");
		} catch (Exception e) {
			System.err.println("[ERROR-ElementoImpl-modificarCantidadElemento] Error " + e.getMessage());
		}

	}

	@Override
	public List<ElementoDTO> devuelveStock(EntityManager em) {
		// Lista auxiliar donde guardaremos los elementos obtenidos en la base de datos
		List<Elemento> listaAuxiliar = new ArrayList<Elemento>();

		try {
			listaAuxiliar = em.createQuery("SELECT e FROM Elemento e", Elemento.class).getResultList();
		} catch (IllegalArgumentException e) {
			System.err.println("[ERROR-ElementoImpl-mostrarStock] Error " + e.getMessage());
		} catch (IllegalStateException e) {
			System.err.println("[ERROR-ElementoImpl-mostrarStock] Error " + e.getMessage());
		} catch (QueryTimeoutException e) {
			System.err.println("[ERROR-ElementoImpl-mostrarStock] Error " + e.getMessage());
		} catch (TransactionRequiredException e) {
			System.err.println("[ERROR-ElementoImpl-mostrarStock] Error " + e.getMessage());
		} catch (PessimisticLockException e) {
			System.err.println("[ERROR-ElementoImpl-mostrarStock] Error " + e.getMessage());
		} catch (LockTimeoutException e) {
			System.err.println("[ERROR-ElementoImpl-mostrarStock] Error " + e.getMessage());
		} catch (PersistenceException e) {
			System.err.println("[ERROR-ElementoImpl-mostrarStock] Error " + e.getMessage());
		}

		// Mensaje INFO
		System.out.println("[INFO-ElementoImpl-mostrarStock] Número de elementos: " + listaAuxiliar.size());

		// Inicializamos la clase ADto para poder usar sus métodos
		ADto adto = new ADto();

		// Devolvemos la lista convertida a objetos de tipo ElementoDTO
		return adto.elementoADto(listaAuxiliar);
	}

}
