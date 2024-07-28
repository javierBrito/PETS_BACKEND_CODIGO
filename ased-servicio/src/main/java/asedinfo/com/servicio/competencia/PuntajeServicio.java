package asedinfo.com.servicio.competencia;

import java.util.List;

import asedinfo.com.modelo.competencia.Puntaje;

public interface PuntajeServicio {
	/**
	 * Permite listar Puntaje
	 * 
	 * @param null
	 * @return lista Puntaje
	 */
	List<Puntaje> listarTodosPuntaje();

	/**
	 * Permite listar Puntaje Activo
	 * 
	 * @param estado
	 * @return lista Puntaje
	 */
	List<Puntaje> listarPuntajeActivo(String estado);

	/**
	 * Permite listar Puntaje Activo
	 * 
	 * @param estado
	 * @return lista Puntaje
	 */
	List<Puntaje> listarPuntajePorSubcategoria(Long codSubcategoria, Long codInstancia);

	/**
	 * Permite listar Puntaje Activo
	 * 
	 * @param estado
	 * @return lista Puntaje
	 */
	List<Puntaje> listarPuntajePorSubcategoriaRegTotal(Long codSubcategoria, Long codInstancia, Long codUsuarioJuez);

	/**
	 * Permite listar Puntaje Activo
	 * 
	 * @param estado
	 * @return lista Puntaje
	 */
	List<Puntaje> listarPuntajePorParticipanteRegTotal(Long codParticipante, Long codSubcategoria, Long codInstancia, Long codUsuarioJuez, Long codModeloPuntaje);

	/**
	 * Permite listar Puntaje Activo
	 * 
	 * @param estado
	 * @return lista Puntaje
	 */
	List<Puntaje> listarPuntajePorSubcategoriaInstanciaRegAVG(Long codSubcategoria, Long codInstancia);

	List<Puntaje> listarPuntajePorSubcategoriaInstanciaRegSUMA(Long codSubcategoria, Long codInstancia);

	List<Puntaje> listarPuntajePorParticipanteSubcategoriaInstanciaRegSUMA(Long codSubcategoria, Long codInstancia, Long codParticipante);

	/**
	 * Permite listar Puntaje Activo
	 * 
	 * @param estado
	 * @return lista Puntaje
	 */
	List<Puntaje> listarPuntajePorParticipanteSubcategoriaInstancia(Long codParticipante, Long codSubcategoria, Long codInstancia, Long codUsuarioJuez);

	List<Puntaje> listarPuntajePorParticipanteSubcategoriaInstanciaCriterios(Long codParticipante, Long codSubcategoria, Long codInstancia);

	Integer eliminarPuntajePorParticipanteInstancia(Long codParticipante, Long codInstancia);

	Integer eliminarPuntajePorParticipanteUsuarioJuezInstancia(Long codParticipante, Long codUsuarioJuez, Long codInstancia);

	/**
	 * Permite obtener Puntaje
	 * 
	 * @param codigo
	 * @return Puntaje
	 */
	Puntaje buscarPuntajePorCodigo(Long codigo);

	/**
	 * Permite registrar Puntaje
	 * 
	 * @param Puntaje
	 * @return Puntaje
	 */
	Puntaje registrar(Puntaje puntaje);
	Puntaje crearPuntaje(Puntaje puntaje);
	
}
