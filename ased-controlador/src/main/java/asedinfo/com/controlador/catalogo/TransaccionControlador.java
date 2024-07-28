package asedinfo.com.controlador.catalogo;

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
import asedinfo.com.modelo.catalogo.Producto;
import asedinfo.com.modelo.catalogo.Transaccion;
import asedinfo.com.modelo.response.ResponseGenerico;
import asedinfo.com.servicio.catalogo.ProductoServicio;
import asedinfo.com.servicio.catalogo.TransaccionServicio;
import asedinfo.com.venta.resources.EstadoEnum;

@RestController
@RequestMapping("catalogo/")
public class TransaccionControlador {

	@Autowired
	private TransaccionServicio transaccionServicio;
	@Autowired
	private ProductoServicio productoServicio;

	@GetMapping(value = "listarTodosTransaccion")
	public ResponseGenerico<Transaccion> listarTodosTransaccion() {
		List<Transaccion> listaTransaccion = transaccionServicio.listarTodosTransaccion();
		// Respuesta
		ResponseGenerico<Transaccion> response = new ResponseGenerico<>();
		response.setListado(listaTransaccion);
		response.setTotalRegistros((long) listaTransaccion.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarTransaccionActivo/{nemonicoModulo}")
	public ResponseGenerico<Transaccion> listarTransaccionActivo(@PathVariable("nemonicoModulo") String nemonicoModulo) {
		List<Transaccion> listaTransaccion = transaccionServicio.listarTransaccionActivo(nemonicoModulo);
		// Respuesta
		ResponseGenerico<Transaccion> response = new ResponseGenerico<>();
		response.setListado(listaTransaccion);
		response.setTotalRegistros((long) listaTransaccion.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarTransaccionPorDescripcion/{descripcion}")
	public ResponseGenerico<Transaccion> listarTransaccionPorDescripcion(@PathVariable("descripcion") String descripcion) {
		List<Transaccion> listaTransaccion = transaccionServicio.listarTransaccionPorDescripcion(descripcion);
		// Respuesta
		ResponseGenerico<Transaccion> response = new ResponseGenerico<>();
		response.setListado(listaTransaccion);
		response.setTotalRegistros((long) listaTransaccion.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarTransaccionPorClaveCuenta/{claveCuenta}")
	public ResponseGenerico<Transaccion> listarTransaccionPorClaveCuenta(@PathVariable("claveCuenta") String claveCuenta) {
		List<Transaccion> listaTransaccion = transaccionServicio.listarTransaccionPorClaveCuenta(claveCuenta);
		// Respuesta
		ResponseGenerico<Transaccion> response = new ResponseGenerico<>();
		response.setListado(listaTransaccion);
		response.setTotalRegistros((long) listaTransaccion.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarTransaccionPorCliente/{codCliente}")
	public ResponseGenerico<Transaccion> listarTransaccionPorCliente(@PathVariable("codCliente") Long codCliente) {
		List<Transaccion> listaTransaccion = transaccionServicio.listarTransaccionPorCliente(codCliente);
		// Respuesta
		ResponseGenerico<Transaccion> response = new ResponseGenerico<>();
		response.setListado(listaTransaccion);
		response.setTotalRegistros((long) listaTransaccion.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarTransaccionPorProducto/{codProducto}")
	public ResponseGenerico<Transaccion> listarTransaccionPorProducto(@PathVariable("codProducto") Long codProducto) {
		List<Transaccion> listaTransaccion = transaccionServicio.listarTransaccionPorProducto(codProducto);
		// Respuesta
		ResponseGenerico<Transaccion> response = new ResponseGenerico<>();
		response.setListado(listaTransaccion);
		response.setTotalRegistros((long) listaTransaccion.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarTransaccionPorClienteYProducto/{codCliente}/{codProducto}")
	public ResponseGenerico<Transaccion> listarTransaccionPorClienteYProducto(@PathVariable("codCliente") Long codCliente, @PathVariable("codProducto") Long codProducto) {
		List<Transaccion> listaTransaccion = transaccionServicio.listarTransaccionPorClienteYProducto(codCliente, codProducto);
		// Respuesta
		ResponseGenerico<Transaccion> response = new ResponseGenerico<>();
		response.setListado(listaTransaccion);
		response.setTotalRegistros((long) listaTransaccion.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarTransaccionPorRangoFechas/{fechaInicio}/{fechaFin}")
	public ResponseGenerico<Transaccion> listarTransaccionPorRangoFechas(@PathVariable("fechaInicio") String fechaInicio, @PathVariable("fechaFin") String fechaFin) {
		List<Transaccion> listaTransaccion = transaccionServicio.listarTransaccionPorRangoFechas(fechaInicio, fechaFin);
		// Respuesta
		ResponseGenerico<Transaccion> response = new ResponseGenerico<>();
		response.setListado(listaTransaccion);
		response.setTotalRegistros((long) listaTransaccion.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarTransaccionACaducarse/{numDias}")
	public ResponseGenerico<Transaccion> listarTransaccionACaducarse(@PathVariable("numDias") int numDias) {
		List<Transaccion> listaTransaccion = transaccionServicio.listarTransaccionACaducarse(numDias);
		// Respuesta
		ResponseGenerico<Transaccion> response = new ResponseGenerico<>();
		response.setListado(listaTransaccion);
		response.setTotalRegistros((long) listaTransaccion.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	/**
	 * REST para obtener Transaccion
	 * 
	 * @return Transaccion
	 */
	@GetMapping(value = "buscarTransaccionPorCodigo/{codigo}")
	public ResponseGenerico<Transaccion> buscarTransaccionPorCodigo(@PathVariable("codigo") Long codigo) {
		Transaccion transaccion = transaccionServicio.buscarTransaccionPorCodigo(codigo);
		// Respuesta
		ResponseGenerico<Transaccion> response = new ResponseGenerico<>();
		response.setObjeto(transaccion);
		response.setTotalRegistros((long) (1));
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	/**
	 * REST para guardar o actualizar transaccion
	 * 
	 * @return guardar
	 */
	@PostMapping(value = "guardarTransaccion")
	public ResponseGenerico<Transaccion> guardarTransaccion(@RequestBody Transaccion transaccion) {
		transaccion = transaccionServicio.registrar(transaccion);
		if (transaccion != null) {
			// Restar la cantidad de productos de la transaccion
			Producto producto = productoServicio.buscarProductoPorCodigo(transaccion.getCodProducto());
			if (producto.getNumExistenciaActual() > 0 && producto.getNumExistenciaActual() >= transaccion.getNumProducto()) {
				if (transaccion.getNumProductoAnt() != 0) {
					producto.setNumExistenciaActual(producto.getNumExistenciaActual() + transaccion.getNumProductoAnt());
				}
				producto.setNumExistenciaActual(producto.getNumExistenciaActual() - transaccion.getNumProducto());
				productoServicio.registrar(producto);
			}
		}
		// Respuesta
		ResponseGenerico<Transaccion> response = new ResponseGenerico<>();
		response.setObjeto(transaccion);
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK_CREADO);
		return response;
	}

	/**
	 * Metodo para eliminar (baja lógica) un registro
	 * 
	 * @return objeto response
	 */
	@DeleteMapping(value = "eliminarTransaccionPorId/{codigo}")
	public ResponseGenerico<Transaccion> eliminarTransaccion(@PathVariable("codigo") Long codigo) {
		Transaccion transaccion = transaccionServicio.buscarTransaccionPorCodigo(codigo);
		transaccion.setEstado(EstadoEnum.INACTIVO.getDescripcion());
		transaccionServicio.registrar(transaccion);
		// Respuesta
		ResponseGenerico<Transaccion> response = new ResponseGenerico<>();
		response.setObjeto(transaccion);
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK_ELIMINADO);
		return response;
	}
}
