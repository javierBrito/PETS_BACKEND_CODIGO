package asedinfo.com.repositorio.catalogo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import asedinfo.com.modelo.catalogo.Persona;

@Repository
public interface PersonaRepositorio extends JpaRepository<Persona, Long> {

	List<Persona> findByEstadoOrderByCodigo(String estado);

	List<Persona> findByIdentificacion(String identificacion);
	
	List<Persona> findByIdentificacionAndEstado(String identificacion, String estado);

	Persona findByCodigo(Long codigo);

	@Query(nativeQuery = true, value = 
			  " select nombre_pais, codigo "
			+ "   from cat_prefijo_telefonico" ) 
	List<Object[]> listarPrefijoTelefonico();;

}
