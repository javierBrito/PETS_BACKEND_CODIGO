package asedinfo.com.controlador.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import asedinfo.com.modelo.DTO.ReporteDTO;
import asedinfo.com.modelo.response.ResponseGenerico;
import asedinfo.com.servicio.util.EnviarCorreoService;

/**
 * Controlador envío de correo
 * 
 * @author jbrito - 202230418
 */

@RestController
@RequestMapping("private/")
public class EnviarCorreoControlador {
	@Autowired
	private EnviarCorreoService enviarCorreoService;

	/**
	 * REST para enviar correo con archivo adjunto
	 * 
	 * @param datos del correo: destinatario, asunto, el mensaje, el archivo en
	 *              base64
	 */
	@RequestMapping(value = "enviarCorreo", method = RequestMethod.POST)
	public ResponseGenerico<ReporteDTO> enviarCorreo(@RequestBody @Validated ReporteDTO reporteDTO) {
		ResponseGenerico<ReporteDTO> response = new ResponseGenerico<>();

		String respuesta = enviarCorreoService.enviarCorreoAux(reporteDTO);
		if (respuesta == "OK") {
			response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
			response.setMensaje(respuesta + "Se envío el mensaje al correo => " + reporteDTO.getTo());
			response.setObjeto(null);
		} else {
			response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_ERR);
			response.setMensaje(respuesta);
		}

		return response;
	}
}
