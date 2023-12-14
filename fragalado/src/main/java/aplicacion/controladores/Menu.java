package aplicacion.controladores;

import aplicacion.dtos.ElementoDTO;
import aplicacion.servicios.ElementoImpl;
import aplicacion.servicios.ElementoInterfaz;
import aplicacion.servicios.MenuImpl;
import aplicacion.servicios.MenuInterfaz;
import aplicacion.servicios.RelElementoReservaImpl;
import aplicacion.servicios.RelElementoReservaInterfaz;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Clase Menu que contendrá el main
 * @author Puesto8
 * Fecha: 14/12/2023
 */
public class Menu {

	public static void main(String[] args) {
		
		// EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
		// EntityManager
		EntityManager em = emf.createEntityManager();
		
		// Inicializamos la interfaz Menu
		MenuInterfaz menuInterfaz = new MenuImpl();
		
		// Inicializamos la interfaz Elemento
		ElementoInterfaz elementoInterfaz = new ElementoImpl();
		
		// Inicializamos la interfaz RelElementoReserva
		RelElementoReservaInterfaz relInterfaz = new RelElementoReservaImpl();
		
		int opcion;
		do {
			opcion = -1;
			// Mostramos el menu
			try {
				opcion = menuInterfaz.menu();
			} catch (Exception e) {
				System.err.println("[ERROR-Menu-main] Error no se ha introducido el formato correcto");
			}
			
			switch (opcion) {
			case 1:
				// Dar de alta elemento
				try {
					elementoInterfaz.darDeAltaElemento(em);
				} catch (Exception e) {
					System.err.println("[ERROR-Menu-main] Error no se ha podido dar de alta el elemento");
				}
				break;

			case 2:
				// Eliminar elemento
				try {
					elementoInterfaz.eliminaElemento(em);
				} catch (Exception e) {
					System.err.println("[ERROR-Menu-main] Error no se ha podido eliminar el elemento");
				}
				break;
			case 3:
				// Modifica cantidad elemento
				try {
					elementoInterfaz.modificarCantidadElemento(em);
				} catch (Exception e) {
					System.err.println("[ERROR-Menu-main] Error no se ha podido modificar el elemento");
				}
				break;
			case 4:
				// Mostrar stock
				try {
					for (ElementoDTO aux : elementoInterfaz.devuelveStock(em)) {
						System.out.println(aux.toString());
					}
				} catch (Exception e) {
					System.err.println("[ERROR-Menu-main] Error no se ha podido mostrar el stock de elementos");
				}
				break;
			case 5:
				// Crear reserva
				try {
					relInterfaz.crearReserva(em);
				} catch (Exception e) {
					System.err.println("[ERROR-Menu-main] Error no se ha podido crear la reserva");
				}
				break;
			}
		} while (opcion != 0);
	}

}
