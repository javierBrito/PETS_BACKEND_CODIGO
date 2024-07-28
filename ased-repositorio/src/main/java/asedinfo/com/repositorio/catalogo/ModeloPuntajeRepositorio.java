package asedinfo.com.repositorio.catalogo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asedinfo.com.modelo.catalogo.ModeloPuntaje;

@Repository
public interface ModeloPuntajeRepositorio extends JpaRepository<ModeloPuntaje, Long> {

	List<ModeloPuntaje> findByEstadoOrderByCodigo(String estado);

	ModeloPuntaje findByCodigo(Long codigo);
}
