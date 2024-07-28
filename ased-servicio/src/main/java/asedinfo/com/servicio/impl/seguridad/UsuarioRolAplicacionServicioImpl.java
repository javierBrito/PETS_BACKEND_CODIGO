package asedinfo.com.servicio.impl.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.seguridad.RolAplicacion;
import asedinfo.com.modelo.seguridad.Usuario;
import asedinfo.com.modelo.seguridad.UsuarioRolAplicacion;
import asedinfo.com.repositorio.seguridad.UsuarioRolAplicacionRepositorio;
import asedinfo.com.servicio.seguridad.RolAplicacionServicio;
import asedinfo.com.servicio.seguridad.UsuarioRolAplicacionServicio;
import asedinfo.com.servicio.seguridad.UsuarioServicio;

@Service
public class UsuarioRolAplicacionServicioImpl implements UsuarioRolAplicacionServicio {

	@Autowired
	private UsuarioRolAplicacionRepositorio usuarioRolAplicacionRepositorio;
	@Autowired
	private RolAplicacionServicio rolAplicacionServicio;
	@Autowired
	private UsuarioServicio usuarioServicio;

	@Override
	public List<UsuarioRolAplicacion> listarTodosUsuarioRolAplicacion() {
		return usuarioRolAplicacionRepositorio.findAll();
	}

	@Override
	public List<UsuarioRolAplicacion> listarUsuarioRolAplicacionActivo(String estado) {
		return usuarioRolAplicacionRepositorio.findByEstadoOrderByCodigo(estado);
	}

	@Override
	public List<UsuarioRolAplicacion> listarUsuarioRolAplicacionPorCodRolAplicacion(Long codRolAplicacion) {
		return usuarioRolAplicacionRepositorio.listarUsuarioRolAplicacionPorCodRolAplicacion(codRolAplicacion);
	}

	@Override
	public List<UsuarioRolAplicacion> listarUsuarioRolAplicacionPorCodUsuario(Long codUsuario) {
		return usuarioRolAplicacionRepositorio.listarUsuarioRolAplicacionPorCodUsuario(codUsuario);
	}

	@Override
	public List<UsuarioRolAplicacion> listarUsuarioRolAplicacionPorCodUsuarioYCodAplicacion(Long codUsuario, Long codAplicacion) {
		return usuarioRolAplicacionRepositorio.listarUsuarioRolAplicacionPorCodUsuarioYCodAplicacion(codUsuario, codAplicacion);
	}

	@Override
	public List<UsuarioRolAplicacion> listarUsuarioRolAplicacionPorCodUsuarioYCodRolAplicacion(Long codUsuario, Long codRolAplicacion) {
		return usuarioRolAplicacionRepositorio.listarUsuarioRolAplicacionPorCodUsuarioYCodRolAplicacion(codUsuario, codRolAplicacion);
	}

	@Override
	public UsuarioRolAplicacion buscarUsuarioRolAplicacionPorCodigo(Long codigo) {
		return usuarioRolAplicacionRepositorio.findByCodigo(codigo);
	}

	@Override
	public UsuarioRolAplicacion registrar(UsuarioRolAplicacion usuarioRolAplicacion) {
		if (usuarioRolAplicacion.getCodRolAplicacion() != 0) {
			RolAplicacion rolAplicacion = new RolAplicacion();
			rolAplicacion = rolAplicacionServicio.buscarRolAplicacionPorCodigo(usuarioRolAplicacion.getCodRolAplicacion());
			if (rolAplicacion != null) {
				usuarioRolAplicacion.setRolAplicacion(rolAplicacion);
			}
		}
		if (usuarioRolAplicacion.getCodUsuario() != 0) {
			Usuario usuario = new Usuario();
			usuario = usuarioServicio.buscarUsuarioPorCodigo(usuarioRolAplicacion.getCodUsuario());
			if (usuario != null) {
				usuarioRolAplicacion.setUsuario(usuario);
			}
		}

		return usuarioRolAplicacionRepositorio.save(usuarioRolAplicacion);
	}

	@Override
	public UsuarioRolAplicacion crearUsuarioRolAplicacion(UsuarioRolAplicacion usuarioRolAplicacion) {
		return usuarioRolAplicacionRepositorio.save(usuarioRolAplicacion);
	}

}
