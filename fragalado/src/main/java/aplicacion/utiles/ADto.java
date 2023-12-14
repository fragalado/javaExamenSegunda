package aplicacion.utiles;

import java.util.ArrayList;
import java.util.List;

import aplicacion.daos.Elemento;
import aplicacion.daos.Reserva;
import aplicacion.dtos.ElementoDTO;
import aplicacion.dtos.ReservaDTO;

/**
 * Clase que se utilizará para pasar de DAO a DTO
 * @author Puesto8
 * Fecha: 14/12/2023
 */
public class ADto {
	
	/**
	 * Método que pasa una lista de elementoDAO a elementoDTO
	 * @author Puesto8
	 * Fecha: 14/12/2023
	 * 
	 * @return
	 */
	public List<ElementoDTO> elementoADto(List<Elemento> listaElementosDAO){
		// Creamos una lista donde guardaremos los elementosDTO
		List<ElementoDTO> listaElementosDTO = new ArrayList<ElementoDTO>();
		
		// Recorremos la lista de elementos DAO
		for (Elemento aux : listaElementosDAO) {
			// Vamos creando elementoDTO con los datos del elementoDAO
			ElementoDTO elementoDTO = new ElementoDTO(aux.getNombreElemento(), aux.getDescripcionElemento(), aux.getCantidadElemento());
			elementoDTO.setCodigoElemento(aux.getCodigoElemento());
			listaElementosDTO.add(elementoDTO);
		}
		
		// Devolvemos la lista de elementosDTO
		return listaElementosDTO;
	}
}
