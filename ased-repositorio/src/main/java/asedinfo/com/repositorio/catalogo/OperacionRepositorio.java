package asedinfo.com.repositorio.catalogo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asedinfo.com.modelo.catalogo.Operacion;

@Repository
public interface OperacionRepositorio extends JpaRepository<Operacion, Long> {

	List<Operacion> findByEstadoOrderByCodigo(String estado);

	Operacion findByNemonico(String nemonico);
	
	List<Operacion> findByNemonicoAndEstado(String nemonico, String estado);

	Operacion findByCodigo(Long codigo);
}
