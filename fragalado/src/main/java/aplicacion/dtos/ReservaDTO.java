package aplicacion.dtos;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase ReservaDTO
 * @author Puesto8
 * Fecha: 14/12/2023
 */
public class ReservaDTO {
	
	// Atributos
	
	private int idReserva = 0;
	private Calendar fchReserva = new GregorianCalendar(9999, 12, 31, 00, 00, 00);
	
	// Constructores
	
	public ReservaDTO(Calendar fchReserva) {
		super();
		this.fchReserva = fchReserva;
	}
	
	// Getter
	
	public int getIdReserva() {
		return idReserva;
	}

	public Calendar getFchReserva() {
		return fchReserva;
	}
	
	// Setter
	
	
	
	// toString
	
	@Override
	public String toString() {
		return "ReservaDTO [idReserva=" + idReserva + ", fchReserva=" + fchReserva + "]";
	}
	
}
