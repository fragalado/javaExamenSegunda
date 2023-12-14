package aplicacion.servicios;

import jakarta.persistence.EntityManager;

/**
 * Interfaz que define los métodos que darán servicio a la tabla RelElementoReserva
 */
public interface RelElementoReservaInterfaz {
	
	/**
	 * Método que crea una reserva.
	 * Pide la fecha de Reserva, los elementos necesarios y la cantidad de estos
	 * @param em (EntityManager)
	 */
	public void crearReserva(EntityManager em);
}
