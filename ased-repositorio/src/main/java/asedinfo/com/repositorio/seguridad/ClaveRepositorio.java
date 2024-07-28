package asedinfo.com.repositorio.seguridad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asedinfo.com.modelo.seguridad.ClaveUsuario;

@Repository
public interface ClaveRepositorio extends JpaRepository<ClaveUsuario, Long> {

}
