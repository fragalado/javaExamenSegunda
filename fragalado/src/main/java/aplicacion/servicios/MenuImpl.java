package aplicacion.servicios;

import java.util.Scanner;

/**
 * Implementación de la interfaz Menu
 * @author Puesto8
 * Fecha: 14/12/2023
 */
public class MenuImpl implements MenuInterfaz {

	@Override
	public int menu() {
		// Scanner para leer los datos
		Scanner sc = new Scanner(System.in);
		
		int opcion=-1;
		do {
			// Mostramos el menu
			System.out.println("1. Alta elemento");
			System.out.println("2. Eliminar elemento");
			System.out.println("3. Modificar cantidad elemento");
			System.out.println("4. Mostrar stock");
			System.out.println("5. Crear reserva");
			System.out.println("0. Salir");
			System.out.print("Introduzca una opción: ");
			opcion = sc.nextInt();
			
			if(opcion < 0 || opcion > 5)
				System.err.println("[ERROR-MenuImpl-menu] Error no se ha introducido una opción correcta");
		} while (opcion < 0 || opcion > 5);
		
		
		return opcion;
	}
	
}
