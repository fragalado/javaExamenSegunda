package aplicacion.servicios;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import aplicacion.daos.Elemento;
import aplicacion.daos.RelElementoReserva;
import aplicacion.daos.Reserva;
import aplicacion.dtos.ElementoDTO;
import jakarta.persistence.EntityManager;

/**
 * Implementación de la interfaz RelElementoReservaInterfaz
 */
public class RelElementoReservaImpl implements RelElementoReservaInterfaz {

	@Override
	public void crearReserva(EntityManager em) {
		// Scanner para leer los datos
		Scanner sc = new Scanner(System.in);
		
		try {
			// Pedimos la fecha
			int dia = capturaEntero("Introduzca el día de la fecha: ", 1, 31);
			int mes = capturaEntero("Introduzca el mes de la fecha: ", 1, 12);
			int anyo = capturaEntero("Introduzca el año de la fecha: ", 1, 2023);
			
			// Obtenemos la lista de elementos que hay
			// Para ello primero inicializamos la interfaz Elemento para poder usar sus métodos
			ElementoInterfaz elementoInterfaz = new ElementoImpl();
			List<ElementoDTO> listaElementos = elementoInterfaz.devuelveStock(em);
			
			// Creamos la fecha
			Calendar fecha = new GregorianCalendar();
			fecha.set(anyo, mes - 1, dia);
			
			// Creamos la reserva
			Reserva reserva = new Reserva(fecha);
			
			// Hacemos el persist de la reserva
			em.getTransaction().begin();
			em.persist(reserva);
			em.getTransaction().commit();
			
			String nombre = "";
			do {
				// Mostramos por consola la lista de elementos y la cantidad
				for (ElementoDTO aux : listaElementos) {
					System.out.println("Nombre: " + aux.getNombreElemento() + ", Cantidad: " + aux.getCantidadElemento());
				}
				
				// Pedimos que introduzca una opción
				System.out.print("Introduce el nombre del elemento: [Para salir:0]");
				nombre = sc.nextLine();
				
				// Obtenemos el elemento
				// Si nombre es igual a 0 no haremos nada
				if(!nombre.equals("0")) {
					Elemento elementoObtenido = em.createQuery("SELECT e FROM Elemento e WHERE e.nombreElemento=:nombre", Elemento.class)
							.setParameter("nombre", nombre).getSingleResult();
					
					// Preguntamos la cantidad
					int cantidad = capturaEntero("Introduce la cantidad: ", 0, elementoObtenido.getCantidadElemento());
					
					
					
					// Hacemos el persist de la reserva y de la tabla relacion con la reserva, el elemento y la cantidad
					em.getTransaction().begin();
					em.persist(new RelElementoReserva(reserva, elementoObtenido, cantidad));
					em.getTransaction().commit();
				}
			} while (!nombre.equals("0"));
			
		} catch (Exception e) {
			System.err.println("[ERROR-RelElementoReservaImpl-crearReserva] Error " + e.getMessage());
		}

	}
	
	private int capturaEntero(String mensaje, int minimo, int maximo) {
		// Scanner para leer los datos
		Scanner sc = new Scanner(System.in);
		
		int opcion = -1;
		do {
			// Mostramos el mensaje
			System.out.print(mensaje);
			opcion = sc.nextInt();
			
			if(opcion < minimo || opcion > maximo)
				System.err.println("[ERROR-RelElementoReservaImpl-capturaEntero] La opción introducido no está dentro del rango");
		} while (opcion < minimo || opcion > maximo);
		
		return opcion;
	}

}
