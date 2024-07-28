package asedinfo.com.repositorio.seguridad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asedinfo.com.modelo.seguridad.UsuarioDetalleAccion;

@Repository
public interface UsuarioDetalleAccionRepositorio extends JpaRepository<UsuarioDetalleAccion, Long> {

}
