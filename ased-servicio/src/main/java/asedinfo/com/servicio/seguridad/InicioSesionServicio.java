package asedinfo.com.servicio.seguridad;

import java.util.List;

import asedinfo.com.modelo.seguridad.InicioSesion;


public interface InicioSesionServicio {
	/**
	 * Permite listar InicioSesion
	 * 
	 * @param null
	 * @return lista InicioSesion
	 */
	List<InicioSesion> listarTodosInicioSesion();

	/**
	 * Permite obtener InicioSesion
	 * 
	 * @param codigo
	 * @return InicioSesion
	 */
	InicioSesion buscarInicioSesionPorCodigo(Long codigo);

	/**
	 * Permite registrar InicioSesion
	 * 
	 * @param InicioSesion
	 * @return InicioSesion
	 */
	InicioSesion registrar(InicioSesion InicioSesion);

}
