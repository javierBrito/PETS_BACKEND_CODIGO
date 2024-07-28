package asedinfo.com.repositorio.competencia;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import asedinfo.com.modelo.competencia.Puntaje;

@Repository
public interface PuntajeRepositorio extends JpaRepository<Puntaje, Long> {

	List<Puntaje> findByEstadoOrderByCodigo(String estado);

	Puntaje findByCodigo(Long codigo);

	@Query(nativeQuery = false, value = "select r from Puntaje r where r.subcategoria.codigo = :codSubcategoria and  r.instancia.codigo = :codInstancia")
	List<Puntaje> listarPuntajePorSubcategoria(@Param("codSubcategoria") Long codSubcategoria, @Param("codInstancia") Long codInstancia);
	
	@Query(nativeQuery = false, value = "select r from Puntaje r where r.subcategoria.codigo = :codSubcategoria and r.instancia.codigo = :codInstancia and r.codUsuarioJuez = :codUsuarioJuez and r.modeloPuntaje.codigo = 99")
	List<Puntaje> listarPuntajePorSubcategoriaRegTotal(@Param("codSubcategoria") Long codSubcategoria, @Param("codInstancia") Long codInstancia, @Param("codUsuarioJuez") Long codUsuarioJuez);

	@Query(nativeQuery = false, value = "select r from Puntaje r where r.participante.codigo = :codParticipante and r.instancia.codigo = :codInstancia")
	List<Puntaje> listarPuntajePorParticipante(@Param("codParticipante") Long codParticipante, @Param("codInstancia") Long codInstancia);

	@Query(nativeQuery = false, value = "select r from Puntaje r where r.participante.codigo = :codParticipante and r.subcategoria.codigo = :codSubcategoria and r.instancia.codigo = :codInstancia and r.codUsuarioJuez = :codUsuarioJuez")
	List<Puntaje> listarPuntajePorParticipanteSubcategoriaInstancia(@Param("codParticipante") Long codParticipante, @Param("codSubcategoria") Long codSubcategoria, @Param("codInstancia") Long codInstancia, @Param("codUsuarioJuez") Long codUsuarioJuez);

	@Query(nativeQuery = false, value = "select r from Puntaje r where r.participante.codigo = :codParticipante and r.subcategoria.codigo = :codSubcategoria and r.instancia.codigo = :codInstancia")
	List<Puntaje> listarPuntajePorParticipanteSubcategoriaInstanciaCriterios(@Param("codParticipante") Long codParticipante, @Param("codSubcategoria") Long codSubcategoria, @Param("codInstancia") Long codInstancia);

	@Query(nativeQuery = false, value = "select r from Puntaje r where r.participante.codigo = :codParticipante and r.instancia.codigo = :codInstancia and r.codUsuarioJuez = :codUsuarioJuez and r.modeloPuntaje.codigo = :codModeloPuntaje and r.subcategoria.codigo = :codSubcategoria")
	List<Puntaje> listarPuntajePorParticipanteRegTotal(@Param("codParticipante") Long codParticipante, @Param("codSubcategoria") Long codSubcategoria, @Param("codInstancia") Long codInstancia, @Param("codUsuarioJuez") Long codUsuarioJuez, @Param("codModeloPuntaje") Long codModeloPuntaje);
	
	@Query(nativeQuery = true, value = 
			  " select avg(wpu.puntaje) as puntaje, wpu.cod_subcategoria, wpu.cod_instancia, wpu.cod_participante, concat(wpa.first_name, ' ', wpa.last_name) as nombreParticipante, "
			+ "        wpu.codigo, wpu.cod_modelo_puntaje, wpu.estado " 
			+ "   from wp_puntaje wpu, wp_participante wpa "
			+ "  where wpu.cod_modelo_puntaje = 99 and wpu.cod_participante = wpa.codigo " 
			+ "  group by wpu.cod_subcategoria, wpu.cod_instancia, wpu.cod_participante " ) 
	List<Object[]> listarPuntajePorSubcategoriaInstanciaRegAVG(@Param("codSubcategoria") Long codSubcategoria, @Param("codInstancia") Long codInstancia);;
	
	@Query(nativeQuery = true, value = 
			  " select sum(wpu.puntaje) as puntaje, wpu.cod_subcategoria, wpu.cod_instancia, wpu.cod_participante, concat(wpa.first_name, ' ', wpa.last_name) as nombreParticipante, "
			+ "        wpu.codigo, wpu.cod_modelo_puntaje, wpu.estado " 
			+ "   from wp_puntaje wpu, wp_participante wpa "
			+ "  where wpu.cod_modelo_puntaje = 99 and wpu.cod_participante = wpa.codigo "
			+ "    and wpu.cod_subcategoria = :codSubcategoria and wpu.cod_instancia = :codInstancia "
			+ "    and wpa.cod_estado_competencia in (4, 5) "
			+ "  group by wpu.cod_subcategoria, wpu.cod_instancia, wpu.cod_participante " ) 
	List<Object[]> listarPuntajePorSubcategoriaInstanciaRegSUMA(@Param("codSubcategoria") Long codSubcategoria, @Param("codInstancia") Long codInstancia);
	
	@Query(nativeQuery = true, value = 
			  " select sum(wpu.puntaje) as puntaje, wpu.cod_subcategoria, wpu.cod_instancia, wpu.cod_participante, concat(wpa.first_name, ' ', wpa.last_name) as nombreParticipante, "
			+ "        wpu.codigo, wpu.cod_modelo_puntaje, wpu.estado " 
			+ "   from wp_puntaje wpu, wp_participante wpa "
			+ "  where wpu.cod_modelo_puntaje = 99 and wpu.cod_participante = wpa.codigo "
			+ "    and wpu.cod_subcategoria = :codSubcategoria and wpu.cod_instancia = :codInstancia "
			+ "    and wpu.cod_participante = :codParticipante "
			+ "  group by wpu.cod_subcategoria, wpu.cod_instancia, wpu.cod_participante " ) 
	List<Object[]> listarPuntajePorParticipanteSubcategoriaInstanciaRegSUMA(@Param("codSubcategoria") Long codSubcategoria, @Param("codInstancia") Long codInstancia, @Param("codParticipante") Long codParticipante);

	@Query(nativeQuery = true, value = "delete from wp_puntaje where cod_participante = :codParticipante and cod_instancia = :codInstancia")
	Integer eliminarPuntajePorParticipanteInstancia(@Param("codParticipante") Long codParticipante, @Param("codInstancia") Long codInstancia);

	@Query(nativeQuery = true, value = "delete from wp_puntaje where cod_participante = :codParticipante and cod_usuario_juez = :codUsuarioJuez and cod_instancia = :codInstancia")
	Integer eliminarPuntajePorParticipanteUsuarioJuezInstancia(@Param("codParticipante") Long codParticipante, @Param("codUsuarioJuez") Long codUsuarioJuez, @Param("codInstancia") Long codInstancia);

}
