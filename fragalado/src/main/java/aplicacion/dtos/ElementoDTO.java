package aplicacion.dtos;

/**
 * Clase ElementoDTO
 * @author Puesto8
 * Fecha: 14/12/2023
 */
public class ElementoDTO {
	// Atributos
	
	private int idElemento = 0;
	private String codigoElemento = "aaaaa";
	private String nombreElemento = "aaaaa";
	private String descripcionElemento = "aaaaa";
	private int cantidadElemento = 0;
	
	// Constructores
	
	public ElementoDTO(String nombreElemento, String descripcionElemento, int cantidadElemento) {
		super();
		this.nombreElemento = nombreElemento;
		this.descripcionElemento = descripcionElemento;
		this.cantidadElemento = cantidadElemento;
	}
	
	// Getter
	
	public int getIdElemento() {
		return idElemento;
	}

	public String getCodigoElemento() {
		return codigoElemento;
	}

	public String getNombreElemento() {
		return nombreElemento;
	}

	public String getDescripcionElemento() {
		return descripcionElemento;
	}

	public int getCantidadElemento() {
		return cantidadElemento;
	}
	
	// Setter
	
	public void setCodigoElemento(String codigoElemento) {
		this.codigoElemento = codigoElemento;
	}
	
	// toString
	
	@Override
	public String toString() {
		return "ElementoDTO [codigoElemento=" + codigoElemento + ", nombreElemento="
				+ nombreElemento + ", descripcionElemento=" + descripcionElemento + ", cantidadElemento="
				+ cantidadElemento + "]";
	}
	
}
