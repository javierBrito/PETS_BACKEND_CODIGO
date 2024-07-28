package asedinfo.com.repositorio.catalogo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asedinfo.com.modelo.catalogo.Categoria;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {

	List<Categoria> findByEstadoOrderByCodigo(String estado);

	Categoria findByDenominacion(String denominacion);

	Categoria findByCodigo(Long codigo);
}
