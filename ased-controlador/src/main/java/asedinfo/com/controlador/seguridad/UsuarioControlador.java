package asedinfo.com.controlador.seguridad;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import asedinfo.com.controlador.util.Constantes;
import asedinfo.com.controlador.util.EncryptUtils;
import asedinfo.com.modelo.response.ResponseGenerico;
import asedinfo.com.modelo.seguridad.ClaveUsuario;
import asedinfo.com.modelo.seguridad.Usuario;
import asedinfo.com.servicio.seguridad.UsuarioServicio;
import asedinfo.com.venta.resources.EstadoEnum;

@RestController
@RequestMapping("seguridad/")
public class UsuarioControlador {

	@Autowired
	private UsuarioServicio usuarioServicio;

	@GetMapping(value = "listarTodosUsuario")
	public ResponseGenerico<Usuario> listarTodosUsuario() {
		List<Usuario> listaUsuario = usuarioServicio.listarTodosUsuario();
		// Respuesta
		ResponseGenerico<Usuario> response = new ResponseGenerico<>();
		response.setListado(listaUsuario);
		response.setTotalRegistros((long) listaUsuario.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarUsuarioActivo")
	public ResponseGenerico<Usuario> listarUsuarioActivo() {
		List<Usuario> listaUsuario = usuarioServicio.listarUsuarioActivo(EstadoEnum.ACTIVO.getDescripcion());
		// Respuesta
		ResponseGenerico<Usuario> response = new ResponseGenerico<>();
		response.setListado(listaUsuario);
		response.setTotalRegistros((long) listaUsuario.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarUsuarioPorPersona/{codPersona}")
	public ResponseGenerico<Usuario> listarUsuarioPorPersona(@PathVariable("codPersona") Long codPersona) {
		List<Usuario> listaUsuario = usuarioServicio.listarUsuarioPorPersona(codPersona);
		// Respuesta
		ResponseGenerico<Usuario> response = new ResponseGenerico<>();
		response.setListado(listaUsuario);
		response.setTotalRegistros((long) listaUsuario.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarUsuarioPorSede/{codigoSede}")
	public ResponseGenerico<Usuario> listarUsuarioPorAplicacion(@PathVariable("codigoSede") Long codigoSede) {
		List<Usuario> listaUsuario = usuarioServicio.listarUsuarioPorSede(codigoSede);
		// Respuesta
		ResponseGenerico<Usuario> response = new ResponseGenerico<>();
		response.setListado(listaUsuario);
		response.setTotalRegistros((long) listaUsuario.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarUsuarioPorIdentificacion/{identificacion}")
	public ResponseGenerico<Usuario> listarUsuarioPorAplicacion(@PathVariable("identificacion") String identificacion) {
		List<Usuario> listaUsuario = usuarioServicio.listarUsuarioPorIdentificacion(identificacion);
		// Respuesta
		ResponseGenerico<Usuario> response = new ResponseGenerico<>();
		response.setListado(listaUsuario);
		response.setTotalRegistros((long) listaUsuario.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	/**
	 * REST para obtener Usuario
	 * 
	 * @return Usuario
	 */
	@GetMapping(value = "buscarUsuarioPorCodigo/{codigo}")
	public ResponseGenerico<Usuario> buscarUsuarioPorCodigo(@PathVariable("codigo") Long codigo) {
		Usuario usuario = usuarioServicio.buscarUsuarioPorCodigo(codigo);
		// Respuesta
		ResponseGenerico<Usuario> response = new ResponseGenerico<>();
		response.setObjeto(usuario);
		response.setTotalRegistros((long) (1));
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	/**
	 * REST para obtener ClaveUsuario
	 * 
	 * @return ClaveUsuario
	 */
	@GetMapping(value = "buscarClaveUsuarioPorCodUsuario/{codigo}")
	public ResponseGenerico<ClaveUsuario> buscarClaveUsuarioPorCodUsuario(@PathVariable("codUsuario") Long codUsuario) {
		ClaveUsuario claveUsuario = usuarioServicio.buscarClaveUsuarioPorCodUsuario(codUsuario);
		// Respuesta
		ResponseGenerico<ClaveUsuario> response = new ResponseGenerico<>();
		response.setObjeto(claveUsuario);
		response.setTotalRegistros((long) (1));
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	/**
	 * REST para guardar o actualizar usuario
	 * 
	 * @return guardar
	 */
	@PostMapping(value = "guardarUsuario")
	public ResponseGenerico<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
		usuario = usuarioServicio.registrar(usuario);
		String claveEncriptada = null;
		try {
			claveEncriptada = EncryptUtils.applyAlgorithm("1512", EncryptUtils.MD5, EncryptUtils.UTF);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// Crear Clave Usuario
		usuarioServicio.crearClaveUsuario(usuario, claveEncriptada);
		// Crear Usuario Detalle Acción
		usuarioServicio.crearUsuarioDetalleAccion(usuario, Constantes.TIPO_ACCION_CREACION);

		// Respuesta
		ResponseGenerico<Usuario> response = new ResponseGenerico<>();
		response.setObjeto(usuario);
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK_CREADO);
		return response;
	}

	/**
	 * REST para guardar o actualizar usuario
	 * 
	 * @return guardar
	 */
	@PostMapping(value = "cambiarClave")
	public ResponseGenerico<Usuario> cambiarClave(@RequestBody Usuario usuarioParametro) {
		// Obtener el Usuario
		Usuario usuario = usuarioServicio.buscarUsuarioPorCodigo(usuarioParametro.getCodigo());
		// Obtener registro de Clave Usuario
		ClaveUsuario claveUsuario = usuarioServicio.buscarClaveUsuarioPorCodUsuario(usuarioParametro.getCodigo());
		String claveEncriptada = null;
		try {
			if (usuarioParametro.getCambioClave() == null) {
				claveEncriptada = EncryptUtils.applyAlgorithm("1512", EncryptUtils.MD5, EncryptUtils.UTF);
			} else {
				claveEncriptada = EncryptUtils.applyAlgorithm(usuarioParametro.getCambioClave(), EncryptUtils.MD5, EncryptUtils.UTF);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		claveUsuario.setClave(claveEncriptada);
		// Actualizar Clave Usuario
		usuarioServicio.actualizarClaveUsuario(claveUsuario);
		// Crear Usuario Detalle Acción
		//usuarioServicio.crearUsuarioDetalleAccion(usuario, Constantes.TIPO_ACCION_CREACION);

		// Respuesta
		ResponseGenerico<Usuario> response = new ResponseGenerico<>();
		response.setObjeto(usuario);
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK_CREADO);
		return response;
	}

	/**
	 * Metodo para eliminar (baja lógica) un registro
	 * 
	 * @return objeto response
	 */
	@DeleteMapping(value = "eliminarUsuarioPorId/{codigo}")
	public ResponseGenerico<Usuario> eliminarUsuario(@PathVariable("codigo") Long codigo) {
		Usuario usuario = usuarioServicio.buscarUsuarioPorCodigo(codigo);
		usuario.setEstado(EstadoEnum.INACTIVO.getDescripcion());
		usuarioServicio.registrar(usuario);
		// Respuesta
		ResponseGenerico<Usuario> response = new ResponseGenerico<>();
		response.setObjeto(usuario);
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK_ELIMINADO);
		return response;
	}
}
