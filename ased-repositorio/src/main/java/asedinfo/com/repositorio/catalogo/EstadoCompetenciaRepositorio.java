package asedinfo.com.repositorio.catalogo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asedinfo.com.modelo.catalogo.EstadoCompetencia;

@Repository
public interface EstadoCompetenciaRepositorio extends JpaRepository<EstadoCompetencia, Long> {

	List<EstadoCompetencia> findByEstadoOrderByCodigo(String estado);

	EstadoCompetencia findByCodigo(Long codigo);
}
