package asedinfo.com.servicio.seguridad;

import java.util.List;

import asedinfo.com.modelo.DTO.AplicacionDTO;
import asedinfo.com.modelo.seguridad.Aplicacion;


public interface AplicacionServicio {
	/**
	 * Permite listar TitAplicacion
	 * 
	 * @param null
	 * @return lista TitAplicacion
	 */
	List<Aplicacion> listarTodosAplicacion();

	/**
	 * Permite listar TitAplicacion Activo
	 * 
	 * @param null
	 * @return lista TitAplicacion
	 */
	List<Aplicacion> listarAplicacionActivo(String estado);

	/**
	 * Permite buscar TitAplicacion Activo
	 * 
	 * @param null
	 * @return TitAplicacion
	 */
	Aplicacion buscarAplicacionPorPrefijo(String prefijo, String estado);
	
	/**
	 * Permite obtener Aplicacion
	 * 
	 * @param codigo
	 * @return Aplicacion
	 */
	Aplicacion buscarAplicacionPorCodigo(Long codigo);

	/**
	 * Permite registrar TitAplicacion
	 * 
	 * @param TitAplicacion
	 * @return TitAplicacion
	 */
	Aplicacion registrar(Aplicacion Aplicacion);

	Aplicacion buscarAplicacionPorPrefijo(String prefijo);

	AplicacionDTO listaAplicaciones(Long codUsuario, String prefijoApp, Long codigoAplicacionRol,
			Long codigoAplicacion);

	AplicacionDTO listarAplicacion(Long codUsuario, Long codigoAplicacionRol, Long codigoAplicacion);

}
