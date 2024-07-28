package asedinfo.com.controlador.competencia;

import java.util.ArrayList;
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
import asedinfo.com.modelo.competencia.ParticipanteSeguimiento;
import asedinfo.com.modelo.response.ResponseGenerico;
import asedinfo.com.servicio.competencia.ParticipanteSeguimientoServicio;
import asedinfo.com.venta.resources.EstadoEnum;

@RestController
@RequestMapping("competencia/")
public class ParticipanteSeguimientoControlador {

	@Autowired
	private ParticipanteSeguimientoServicio participanteSeguimientoServicio;

	@GetMapping(value = "listarTodosParticipanteSeguimiento")
	public ResponseGenerico<ParticipanteSeguimiento> listarTodosParticipanteSeguimiento() {
		List<ParticipanteSeguimiento> listaParticipanteSeguimiento = participanteSeguimientoServicio.listarTodosParticipanteSeguimiento();
		// Respuesta
		ResponseGenerico<ParticipanteSeguimiento> response = new ResponseGenerico<>();
		response.setListado(listaParticipanteSeguimiento);
		response.setTotalRegistros((long) listaParticipanteSeguimiento.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarParticipanteSeguimientoActivo")
	public ResponseGenerico<ParticipanteSeguimiento> listarParticipanteSeguimientoActivo() {
		List<ParticipanteSeguimiento> listaParticipanteSeguimiento = participanteSeguimientoServicio.listarParticipanteSeguimientoActivo(EstadoEnum.ACTIVO.getDescripcion());
		// Respuesta
		ResponseGenerico<ParticipanteSeguimiento> response = new ResponseGenerico<>();
		response.setListado(listaParticipanteSeguimiento);
		response.setTotalRegistros((long) listaParticipanteSeguimiento.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	/**
	 * REST para obtener ParticipanteSeguimiento
	 * 
	 * @return ParticipanteSeguimiento
	 */
	@GetMapping(value = "buscarParticipanteSeguimientoPorCodigo/{codigo}")
	public ResponseGenerico<ParticipanteSeguimiento> buscarParticipanteSeguimientoPorCodigo(@PathVariable("codigo") Long codigo) {
		ParticipanteSeguimiento ParticipanteSeguimiento = participanteSeguimientoServicio.buscarParticipanteSeguimientoPorCodigo(codigo);
		// Respuesta
		ResponseGenerico<ParticipanteSeguimiento> response = new ResponseGenerico<>();
		response.setObjeto(ParticipanteSeguimiento);
		response.setTotalRegistros((long) (1));
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	/**
	 * REST para obtener ParticipanteSeguimiento
	 * 
	 * @return ParticipanteSeguimiento
	 */
	@GetMapping(value = "listarParticipanteSeguimientoPorParticipante/{codParticipante}")
	public ResponseGenerico<ParticipanteSeguimiento> listarParticipanteSeguimientoPorParticipante(@PathVariable("codParticipante") Long codParticipante) {
		List<ParticipanteSeguimiento> listaParticipanteSeguimiento = participanteSeguimientoServicio.listarParticipanteSeguimientoPorParticipante(codParticipante);
		// Respuesta
		ResponseGenerico<ParticipanteSeguimiento> response = new ResponseGenerico<>();
		response.setListado(listaParticipanteSeguimiento);
		response.setTotalRegistros((long) listaParticipanteSeguimiento.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	/**
	 * REST para guardar o actualizar ParticipanteSeguimiento
	 * 
	 * @return guardar
	 */
	@PostMapping(value = "guardarListaParticipanteSeguimiento")
	public ResponseGenerico<ParticipanteSeguimiento> guardarListaParticipanteSeguimiento(@RequestBody List<ParticipanteSeguimiento> listaParticipanteSeguimiento) {
		Long codParticipante = 0L;
		ParticipanteSeguimiento participanteSeguimiento = new ParticipanteSeguimiento();
		List<ParticipanteSeguimiento> listaParticipanteSeguimientoEliminar = new ArrayList<>();
		if (listaParticipanteSeguimiento.size() > 0) {
			codParticipante = listaParticipanteSeguimiento.get(0).getCodParticipante();
			listaParticipanteSeguimientoEliminar = participanteSeguimientoServicio.listarParticipanteSeguimientoPorParticipante(codParticipante);
			if (listaParticipanteSeguimientoEliminar != null) {
				for (ParticipanteSeguimiento participanteSeguimientoAux : listaParticipanteSeguimientoEliminar) {
					participanteSeguimientoAux.setEstado(EstadoEnum.INACTIVO.getDescripcion());
					participanteSeguimientoServicio.registrar(participanteSeguimientoAux);
				}	
			}
		}
		if (listaParticipanteSeguimiento.size() > 0) {
			for (ParticipanteSeguimiento participanteSeguimientoAux : listaParticipanteSeguimiento) {
				participanteSeguimiento = participanteSeguimientoServicio.registrar(participanteSeguimientoAux);
			}
		}
		// Respuesta
		ResponseGenerico<ParticipanteSeguimiento> response = new ResponseGenerico<>();
		response.setObjeto(participanteSeguimiento);
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK_CREADO);
		return response;
	}

	/**
	 * REST para guardar o actualizar ParticipanteSeguimiento
	 * 
	 * @return guardar
	 */
	@PostMapping(value = "guardarParticipanteSeguimiento")
	public ResponseGenerico<ParticipanteSeguimiento> guardarParticipanteSeguimiento(@RequestBody ParticipanteSeguimiento participanteSeguimiento) {
		participanteSeguimiento = participanteSeguimientoServicio.registrar(participanteSeguimiento);
		// Respuesta
		ResponseGenerico<ParticipanteSeguimiento> response = new ResponseGenerico<>();
		response.setObjeto(participanteSeguimiento);
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK_CREADO);
		return response;
	}

	/**
	 * Metodo para eliminar (baja lógica) un registro
	 * 
	 * @return objeto response
	 */
	@DeleteMapping(value = "eliminarParticipanteSeguimientoPorId/{codigo}")
	public ResponseGenerico<ParticipanteSeguimiento> eliminarParticipanteSeguimientoPorId(@PathVariable("codigo") Long codigo) {
		ParticipanteSeguimiento participanteSeguimiento = participanteSeguimientoServicio.buscarParticipanteSeguimientoPorCodigo(codigo);
		participanteSeguimiento.setEstado(EstadoEnum.INACTIVO.getDescripcion());
		participanteSeguimientoServicio.registrar(participanteSeguimiento);
		// Respuesta
		ResponseGenerico<ParticipanteSeguimiento> response = new ResponseGenerico<>();
		response.setObjeto(participanteSeguimiento);
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK_ELIMINADO);
		return response;
	}
}
