package aplicacion.servicios;

import java.util.List;

import aplicacion.dtos.ElementoDTO;
import jakarta.persistence.EntityManager;

/**
 * Interfaz que define los métodos que darán servicio a Elemento
 * @author Puesto8
 * Fecha: 14/12/2023
 */
public interface ElementoInterfaz {
	
	/**
	 * Método que dará de alta un elemento en la base de datos
	 * Pedirá nombre, descripcion y cantidad del elemento, y el código se completará por la concatenación del id con nombre.
	 * @author Puesto8
	 * Fecha: 14/12/2023
	 * 
	 * @param em
	 */
	public void darDeAltaElemento(EntityManager em);
	
	/**
	 * Método que elimina un elemento de la base de datos según su codigo de elemento
	 * @author Puesto8
	 * Fecha: 14/12/2023
	 * 
	 * @param em
	 */
	public void eliminaElemento(EntityManager em);
	
	/**
	 * Método que pide el codigo del elemento y si existe en la base de datos pedirá la cantidad a disminuir.
	 * Se controlará que el stock no sea negativo
	 * @author Puesto8
	 * Fecha: 14/12/2023
	 * 
	 * @param em
	 */
	public void modificarCantidadElemento(EntityManager em);
	
	/**
	 * Método que devuelve una lista de elementos DTO.
	 * Hace un select a la base de datos de todos los elementos y los devuelve convertido a DTO
	 * @author Puesto8
	 * Fecha: 14/12/2023
	 * 
	 * @param em
	 * @return Lista de elementos DTO
	 */
	public List<ElementoDTO> devuelveStock(EntityManager em);
}
