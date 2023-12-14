package aplicacion.daos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entidad Elemento que representa la tabla vajillas en la base de datos
 * @author Puesto8
 * Fecha: 14/12/2023
 */
@Entity
@Table(name = "vajillas", schema = "esqexados")
public class Elemento {
	
	// Atributos
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idElemento;
	
	@Column(name = "codigoElemento", nullable = false)
	private String codigoElemento;
	
	@Column(name = "nombreElemento", nullable = false)
	private String nombreElemento;
	
	@Column(name = "descripcionElemento", nullable = false)
	private String descripcionElemento;
	
	@Column(name = "cantidadElemento", nullable = false)
	private int cantidadElemento;
	
	@OneToMany(mappedBy = "elemento")
	List<RelElementoReserva> listaRelacionElemento;
	
	// Constructores
	
	public Elemento(String codigoElemento, String nombreElemento, String descripcionElemento, int cantidadElemento) {
		super();
		this.codigoElemento = codigoElemento;
		this.nombreElemento = nombreElemento;
		this.descripcionElemento = descripcionElemento;
		this.cantidadElemento = cantidadElemento;
	}
	
	public Elemento() {
		super();
	}

	// Getter
	
	public int getIdElemento() {
		return idElemento;
	}

	public int getCantidadElemento() {
		return cantidadElemento;
	}
	
	public String getNombreElemento() {
		return nombreElemento;
	}
	
	public String getCodigoElemento() {
		return codigoElemento;
	}

	public String getDescripcionElemento() {
		return descripcionElemento;
	}
	
	// Setter

	public void setCodigoElemento(String codigoElemento) {
		this.codigoElemento = codigoElemento;
	}

	public void setCantidadElemento(int cantidadElemento) {
		this.cantidadElemento = cantidadElemento;
	}
}
