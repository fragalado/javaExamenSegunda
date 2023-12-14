package aplicacion.daos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad RelElementoReserva que representa la tabla de relacion entre Prestamos y Vajillas
 * @author Puesto8
 * Fecha: 14/12/2023
 */
@Entity
@Table(name = "rel_elemento_reserva", schema = "esqexados")
public class RelElementoReserva {
	
	// Atributos
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idRelacion;
	
	@ManyToOne
	@JoinColumn(name = "idReserva")
	private Reserva reserva;
	
	@ManyToOne
	@JoinColumn(name = "idElemento")
	private Elemento elemento;
	
	@Column(name = "cantidadElemento")
	private int cantidadElemento;
	
	// Constructores
	
	public RelElementoReserva(Reserva reserva, Elemento elemento, int cantidadElemento) {
		super();
		this.reserva = reserva;
		this.elemento = elemento;
		this.cantidadElemento = cantidadElemento;
	}
	
	public RelElementoReserva() {
		super();
	}
	
	// Getter
	// Setter
	// toString
}
