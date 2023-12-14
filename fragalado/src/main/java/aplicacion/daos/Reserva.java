package aplicacion.daos;

import java.util.Calendar;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Entidad Reserva que representa la tabla prestamos en la base de datos
 * @author Puesto8
 * Fecha: 14/12/2023
 */
@Entity
@Table(name = "prestamos", schema = "esqexados")
public class Reserva {
	
	// Atributos
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReserva;
	
	@Column(name = "fchReserva", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fchReserva;
	
	@OneToMany(mappedBy = "reserva")
	List<RelElementoReserva> listaRelacionReserva;
	
	// Constructores
	
	public Reserva(Calendar fchReserva) {
		super();
		this.fchReserva = fchReserva;
	}
	
	public Reserva() {
		super();
	}
	
	// Getter
	
	public int getIdReserva() {
		return idReserva;
	}
	
	public Calendar getFchReserva() {
		return fchReserva;
	}
	
	// Setter

	public void setFchReserva(Calendar fchReserva) {
		this.fchReserva = fchReserva;
	}
	
	// toString
	
	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", fchReserva=" + fchReserva + "]";
	}
	
}
