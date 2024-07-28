package asedinfo.com.servicio.util;

import asedinfo.com.modelo.DTO.ReporteDTO;

/**
 * Interfaz 
 * 
 * @author Gabriel Avalos
 *
 */
public interface EnviarCorreoService {

	String enviarCorreoAux(ReporteDTO reporteDTO);
	
	String enviarCorreo(ReporteDTO reporteDTO);
}
