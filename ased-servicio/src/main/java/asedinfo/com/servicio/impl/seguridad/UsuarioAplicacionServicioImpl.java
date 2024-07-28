package asedinfo.com.servicio.impl.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.seguridad.Aplicacion;
import asedinfo.com.modelo.seguridad.Usuario;
import asedinfo.com.modelo.seguridad.UsuarioAplicacion;
import asedinfo.com.repositorio.seguridad.UsuarioAplicacionRepositorio;
import asedinfo.com.servicio.seguridad.AplicacionServicio;
import asedinfo.com.servicio.seguridad.UsuarioAplicacionServicio;
import asedinfo.com.servicio.seguridad.UsuarioServicio;

@Service
public class UsuarioAplicacionServicioImpl implements UsuarioAplicacionServicio {

	@Autowired
	private UsuarioAplicacionRepositorio usuarioAplicacionRepositorio;
	@Autowired
	private AplicacionServicio aplicacionServicio;
	@Autowired
	private UsuarioServicio usuarioServicio;

	@Override
	public List<UsuarioAplicacion> listarTodosUsuarioAplicacion() {
		return usuarioAplicacionRepositorio.findAll();
	}

	@Override
	public List<UsuarioAplicacion> listarUsuarioAplicacionActivo(String estado) {
		return usuarioAplicacionRepositorio.findByEstadoOrderByCodigo(estado);
	}

	@Override
	public List<UsuarioAplicacion> listarUsuarioAplicacionPorCodAplicacion(Long codAplicacion) {
		return usuarioAplicacionRepositorio.listarUsuarioAplicacionPorCodAplicacion(codAplicacion);
	}

	@Override
	public List<UsuarioAplicacion> listarUsuarioAplicacionPorCodUsuario(Long codUsuario) {
		return usuarioAplicacionRepositorio.listarUsuarioAplicacionPorCodUsuario(codUsuario);
	}

	@Override
	public List<UsuarioAplicacion> listarUsuarioAplicacionPorUsuarioYAplicacion(Long codUsuario, Long codAplicacion) {
		return usuarioAplicacionRepositorio.listarUsuarioAplicacionPorUsuarioYAplicacion(codUsuario, codAplicacion);
	}

	@Override
	public UsuarioAplicacion buscarUsuarioAplicacionPorCodigo(Long codigo) {
		return usuarioAplicacionRepositorio.findByCodigo(codigo);
	}

	@Override
	public UsuarioAplicacion registrar(UsuarioAplicacion usuarioAplicacion) {
		if (usuarioAplicacion.getCodAplicacion() != 0) {
			Aplicacion aplicacion = new Aplicacion();
			aplicacion = aplicacionServicio.buscarAplicacionPorCodigo(usuarioAplicacion.getCodAplicacion());
			if (aplicacion != null) {
				usuarioAplicacion.setAplicacion(aplicacion);
			}
		}
		if (usuarioAplicacion.getCodUsuario() != 0) {
			Usuario usuario = new Usuario();
			usuario = usuarioServicio.buscarUsuarioPorCodigo(usuarioAplicacion.getCodUsuario());
			if (usuario != null) {
				usuarioAplicacion.setUsuario(usuario);
			}
		}

		return usuarioAplicacionRepositorio.save(usuarioAplicacion);
	}
}
