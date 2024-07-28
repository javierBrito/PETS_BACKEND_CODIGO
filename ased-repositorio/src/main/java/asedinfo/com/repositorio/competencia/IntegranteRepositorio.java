package asedinfo.com.repositorio.competencia;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import asedinfo.com.modelo.competencia.*;

@Repository
public interface IntegranteRepositorio extends JpaRepository<Integrante, Long> {

	List<Integrante> findByEstadoOrderByCodigo(String estado);

	@Query(nativeQuery = false, value = "select i from Integrante i where i.participante.codigo = :codParticipante and i.estado = 'A'")
	List<Integrante> listarIntegrantePorParticipante(@Param("codParticipante") Long codParticipante);

	@Query(nativeQuery = false, value = "select i from Integrante i where i.nombre = :nombre and i.participante.codigo = :codParticipante and i.estado = 'A'")
	Integrante buscarIntegrantePorNombre(@Param("nombre") String nombre, @Param("codParticipante") Long codParticipante);

	@Query(nativeQuery = true, value = "delete from wp_integrante where cod_participante = :codParticipante")
	Integer eliminarIntegrantePorCodParticipante(@Param("codParticipante") Long codParticipante);

	Integrante findByCodigo(Long codigo);
}
