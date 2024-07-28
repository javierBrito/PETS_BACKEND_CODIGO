package asedinfo.com.repositorio.catalogo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asedinfo.com.modelo.catalogo.Modulo;

@Repository
public interface ModuloRepositorio extends JpaRepository<Modulo, Long> {

	List<Modulo> findByEstadoOrderByCodigo(String estado);

	Modulo findByNemonico(String nemonico);
	
	List<Modulo> findByNemonicoAndEstado(String nemonico, String estado);

	Modulo findByCodigo(Long codigo);
}
