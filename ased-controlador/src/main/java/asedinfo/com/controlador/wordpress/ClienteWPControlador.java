package asedinfo.com.controlador.wordpress;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
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
import asedinfo.com.modelo.DTO.UsuarioWPDTO;
import asedinfo.com.modelo.catalogo.Categoria;
import asedinfo.com.modelo.catalogo.Persona;
import asedinfo.com.modelo.catalogo.Subcategoria;
import asedinfo.com.modelo.competencia.Participante;
import asedinfo.com.modelo.response.ResponseGenerico;
import asedinfo.com.modelo.seguridad.Usuario;
import asedinfo.com.modelo.wordpress.ClienteWP;
import asedinfo.com.modelo.wordpress.PedidoProducto;
import asedinfo.com.servicio.catalogo.CategoriaServicio;
import asedinfo.com.servicio.catalogo.PersonaServicio;
import asedinfo.com.servicio.catalogo.SubcategoriaServicio;
import asedinfo.com.servicio.competencia.ParticipanteServicio;
import asedinfo.com.servicio.seguridad.UsuarioServicio;
import asedinfo.com.servicio.wordpress.ClienteWPServicio;

@RestController
@RequestMapping("wordpress/")
public class ClienteWPControlador {

	@Autowired
	private ClienteWPServicio clienteWPServicio;
	@Autowired
	private ParticipanteServicio participanteServicio;
	@Autowired
	private PersonaServicio personaServicio;
	@Autowired
	private UsuarioServicio usuarioServicio;
	@Autowired
	private CategoriaServicio categoriaServicio;
	@Autowired
	private SubcategoriaServicio subcategoriaServicio;

	@GetMapping(value = "listarTodosClienteWP")
	public ResponseGenerico<ClienteWP> listarTodosClienteWP() {
		List<ClienteWP> listaClienteWP = clienteWPServicio.listarTodosClienteWP();
		// Respuesta
		ResponseGenerico<ClienteWP> response = new ResponseGenerico<>();
		response.setListado(listaClienteWP);
		response.setTotalRegistros((long) listaClienteWP.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "migrarClienteWP")
	public ResponseGenerico<PedidoProducto> migrarClienteWP() {
		Long codSubcategoria = 0L;
		List<PedidoProducto> listaPedidoProducto = clienteWPServicio.migrarClienteWPedidoProducto();
		if (listaPedidoProducto.size() > 0) {
			ClienteWP clienteWP = new ClienteWP();
			Categoria categoria = new Categoria(); 
			Subcategoria subcategoria = new Subcategoria(); 
			
			for (PedidoProducto pedidoProducto : listaPedidoProducto) {
				clienteWP = pedidoProducto.getClienteWP();
				// Mover datos desde ClienteWP a Persona
				Persona persona = new Persona();
				// Verificar si ya existe Persona
				List<Persona> listaPersona = personaServicio.buscarPorIdentificacion(clienteWP.getEmail().trim());
				if (listaPersona.size() > 0) {
					persona = listaPersona.get(0);
				}
				// Registro nuevo
				persona.setIdentificacion(clienteWP.getEmail());
				persona.setCedula("Suscriptor");
				persona.setNombres(clienteWP.getFirstName().toUpperCase() + " " +clienteWP.getLastName().toUpperCase());
				//persona.setApellidos(clienteWP.getLastName().toUpperCase());
				persona.setApellidos("");
				//persona.setFechaNacimiento(clienteWP.getBirthday())
				persona.setCorreo(clienteWP.getEmail());
				//persona.setCelular(clienteWP.getCelular);
				persona.setEstado("A");
				// Guardar la Persona
				persona = personaServicio.registrar(persona);
				
				// Mover datos desde Persona a Usuario
				Usuario usuario = new Usuario();
				// Verificar si ya existe usuario
				List<Usuario> listaUsuario = usuarioServicio.listarUsuarioPorPersona(persona.getCodigo());
				if (listaUsuario.size() > 0) {
					usuario = listaUsuario.get(0);
				}
				usuario.setCambioClave("NO");
				usuario.setActualizacionDatos("NO");
				usuario.setFechaSolicitudClave(new Date());
				usuario.setPersona(persona);
				usuario.setEstado("A");
				// Guardar el usuario
				usuarioServicio.registrar(usuario);

				// Mover datos desde Usuario a ClaveUsuario y UsuarioDetalleAccion 
				String claveEncriptada = null;
				try {
					claveEncriptada = EncryptUtils.applyAlgorithm("1512", EncryptUtils.MD5, EncryptUtils.UTF);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				// Guardar Clave Usuario
				usuarioServicio.crearClaveUsuario(usuario, claveEncriptada);
				// Guardar Usuario Detalle Acción
				usuarioServicio.crearUsuarioDetalleAccion(usuario, Constantes.TIPO_ACCION_CREACION);
				
				// Obtener la Categoria por denominacion desdes el dato de pedidoProducto.getPostExcerpt()
				categoria = categoriaServicio.buscarCategoriaPorDenominacion(pedidoProducto.getPostExcerpt());
				//categoria = categoriaServicio.buscarCategoriaPorDenominacion("Infantil (9-12 años)");
				if (categoria != null) {
					// Obtener la Subcategoria desde su categoria
					subcategoria = subcategoriaServicio.buscarSubcategoriaPorDenominacion(pedidoProducto.getPostTitle(), categoria.getCodigo());
					if (subcategoria != null) {
						codSubcategoria = subcategoria.getCodigo();
					}
				}
				
				// Mover datos desde ClienteWP a Participante
				Participante participante = new Participante();
				// Verificar si ya existe Persona
				List<Participante> listaParticipante = participanteServicio.listarParticipantePorPersona(persona.getCodigo());
				if (listaParticipante.size() > 0) {
					// Verificar si ya existe el participante con la Subcategoria
					for (Participante participanteAux : listaParticipante) {
						if (participanteAux.getCodSubcategoria() == codSubcategoria) {
							participante = participanteAux;
							break;
						}
					}
				}
				participante.setCustomerId(clienteWP.getCustomerId());
				participante.setDateLastActive(clienteWP.getDateLastActive());
				participante.setDateRegistered(clienteWP.getDateRegistered());
				participante.setEmail(clienteWP.getEmail());
				participante.setFirstName(clienteWP.getFirstName().toUpperCase() + " " + clienteWP.getLastName().toUpperCase());
				//participante.setLastName(clienteWP.getLastName().toUpperCase());
				participante.setUserId(clienteWP.getUserId());
				participante.setUsername(clienteWP.getUsername());
				// Datos por default al migrar
				participante.setCodInstancia(1L);
				participante.setCodEstadoCompetencia(1L);
				// Datos de la persona relacionada
				participante.setCodPersona(persona.getCodigo());
				participante.setPersona(persona);

				// Valor por default de la Subcategoria
				participante.setCodSubcategoria(1L);
				if (codSubcategoria != 0) {
					participante.setCodSubcategoria(codSubcategoria);
				}
				
				// Guardar el registro
				participanteServicio.registrar(participante);
			}
		}
		// Respuesta
		ResponseGenerico<PedidoProducto> response = new ResponseGenerico<>();
		response.setListado(listaPedidoProducto);
		response.setTotalRegistros((long) listaPedidoProducto.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarUsuarioWP")
	public ResponseGenerico<UsuarioWPDTO> listarUsuarioWP() {
		List<UsuarioWPDTO> listaUsuarioWPDTO = clienteWPServicio.listarUsuarioWP();
		// Respuesta
		ResponseGenerico<UsuarioWPDTO> response = new ResponseGenerico<>();
		response.setListado(listaUsuarioWPDTO);
		response.setTotalRegistros((long) listaUsuarioWPDTO.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}
	
	@GetMapping(value = "migrarUsuarioWP")
	public ResponseGenerico<UsuarioWPDTO> migrarUsuarioWP() {
		List<UsuarioWPDTO> listaUsuarioWPDTO = clienteWPServicio.listarUsuarioWP();
		if (listaUsuarioWPDTO.size() > 0) {
			for (UsuarioWPDTO usuarioWPDTO : listaUsuarioWPDTO) {
				// Mover datos desde ClienteWP a Persona
				Persona persona = new Persona();
				// Verificar si ya existe Persona
				List<Persona> listaPersona = personaServicio.buscarPorIdentificacion(usuarioWPDTO.getEmail().trim());
				if (listaPersona.size() > 0) {
					persona = listaPersona.get(0);
				}
				// Registro nuevo
				persona.setIdentificacion(usuarioWPDTO.getEmail());
				persona.setCedula("Suscriptor");
				persona.setNombres(usuarioWPDTO.getFirstName().toUpperCase() + " " +usuarioWPDTO.getLastName().toUpperCase());
				//persona.setApellidos(clienteWP.getLastName().toUpperCase());
				persona.setApellidos(" ");
				//persona.setFechaNacimiento(clienteWP.getBirthday())
				persona.setCorreo(usuarioWPDTO.getEmail());
				persona.setEstado("A");
				// Guardar la Persona
				persona = personaServicio.registrar(persona);
				
				// Mover datos desde Persona a Usuario
				Usuario usuario = new Usuario();
				// Verificar si ya existe usuario
				List<Usuario> listaUsuario = usuarioServicio.listarUsuarioPorPersona(persona.getCodigo());
				if (listaUsuario.size() > 0) {
					usuario = listaUsuario.get(0);
				}
				usuario.setCambioClave("NO");
				usuario.setActualizacionDatos("NO");
				usuario.setFechaSolicitudClave(new Date());
				usuario.setPersona(persona);
				usuario.setEstado("A");
				// Guardar el usuario
				usuarioServicio.registrar(usuario);

				// Mover datos desde Usuario a ClaveUsuario y UsuarioDetalleAccion 
				String claveEncriptada = null;
				try {
					claveEncriptada = EncryptUtils.applyAlgorithm("1512", EncryptUtils.MD5, EncryptUtils.UTF);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				// Guardar Clave Usuario
				usuarioServicio.crearClaveUsuario(usuario, claveEncriptada);
				// Guardar Usuario Detalle Acción
				usuarioServicio.crearUsuarioDetalleAccion(usuario, Constantes.TIPO_ACCION_CREACION);

				// Mover datos desde UsuarioWP a Participante
				Participante participante = new Participante();
				// Verificar si ya existe Persona
				List<Participante> listaParticipante = participanteServicio.listarParticipantePorPersona(persona.getCodigo());
				if (listaParticipante.size() > 0) {
					participante = listaParticipante.get(0);
				}
				//participante.setCustomerId(clienteWP.getCustomerId());
				participante.setDateLastActive(usuarioWPDTO.getDateLastActive());
				//participante.setDateRegistered(clienteWP.getDateRegistered());
				participante.setEmail(usuarioWPDTO.getEmail());
				participante.setFirstName(usuarioWPDTO.getFirstName().toUpperCase() + " " + usuarioWPDTO.getLastName().toUpperCase());
				//participante.setLastName(clienteWP.getLastName().toUpperCase());
				participante.setLastName(" ");
				//participante.setUserId(clienteWP.getUserId());
				participante.setUsername(usuarioWPDTO.getUsername());
				if (participante.getCodigo() == null) {
					// Datos por default al migrar
					participante.setCodInstancia(1L);
					participante.setCodEstadoCompetencia(1L);
					participante.setCodSubcategoria(82L);
				}

				// Datos de la persona relacionada
				participante.setCodPersona(persona.getCodigo());
				participante.setPersona(persona);

				// Guardar el registro
				participanteServicio.registrar(participante);
			}
		}
		// Respuesta
		ResponseGenerico<UsuarioWPDTO> response = new ResponseGenerico<>();
		response.setListado(listaUsuarioWPDTO);
		response.setTotalRegistros((long) listaUsuarioWPDTO.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	/**
	 * REST para obtener ClienteWP
	 * 
	 * @return ClienteWP
	 */
	@GetMapping(value = "buscarClienteWPPorCodigo/{customerId}")
	public ResponseGenerico<ClienteWP> buscarClienteWPPorCodigo(@PathVariable("customerId") Long customerId) {
		ClienteWP ClienteWP = clienteWPServicio.buscarClienteWPPorCustomerId(customerId);
		// Respuesta
		ResponseGenerico<ClienteWP> response = new ResponseGenerico<>();
		response.setObjeto(ClienteWP);
		response.setTotalRegistros((long) (1));
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	/**
	 * REST para guardar o actualizar ClienteWP
	 * 
	 * @return guardar
	 */
	@PostMapping(value = "guardarClienteWP")
	public ResponseGenerico<ClienteWP> guardarClienteWP(@RequestBody ClienteWP ClienteWP) {
		ClienteWP = clienteWPServicio.registrar(ClienteWP);
		// Respuesta
		ResponseGenerico<ClienteWP> response = new ResponseGenerico<>();
		response.setObjeto(ClienteWP);
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK_CREADO);
		return response;
	}

	/**
	 * Metodo para eliminar (baja lógica) un registro
	 * 
	 * @return objeto response
	 */
	@DeleteMapping(value = "eliminarClienteWPPorId/{customerId}")
	public ResponseGenerico<ClienteWP> eliminarClienteWP(@PathVariable("customerId") Long customerId) {
		ClienteWP clienteWP = clienteWPServicio.buscarClienteWPPorCustomerId(customerId);
		//ClienteWP.setStatus("Eliminar");
		clienteWPServicio.registrar(clienteWP);
		// Respuesta
		ResponseGenerico<ClienteWP> response = new ResponseGenerico<>();
		response.setObjeto(clienteWP);
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK_ELIMINADO);
		return response;
	}
}
