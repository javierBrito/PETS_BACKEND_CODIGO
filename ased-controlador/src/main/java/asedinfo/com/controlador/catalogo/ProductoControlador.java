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
import asedinfo.com.modelo.response.ResponseGenerico;
import asedinfo.com.servicio.catalogo.ProductoServicio;
import asedinfo.com.venta.resources.EstadoEnum;

@RestController
@RequestMapping("catalogo/")
public class ProductoControlador {

	@Autowired
	private ProductoServicio productoServicio;

	@GetMapping(value = "listarTodosProducto")
	public ResponseGenerico<Producto> listarTodosProducto() {
		List<Producto> listaProducto = productoServicio.listarTodosProducto();
		// Respuesta
		ResponseGenerico<Producto> response = new ResponseGenerico<>();
		response.setListado(listaProducto);
		response.setTotalRegistros((long) listaProducto.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarProductoActivo/{nemonicoModulo}")
	public ResponseGenerico<Producto> listarProductoActivo(@PathVariable("nemonicoModulo") String nemonicoModulo) {
		List<Producto> listaProducto = productoServicio.listarProductoActivo(nemonicoModulo);
		// Respuesta
		ResponseGenerico<Producto> response = new ResponseGenerico<>();
		response.setListado(listaProducto);
		response.setTotalRegistros((long) listaProducto.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarProductoPorDescripcion/{descripcion}/{nemonicoModulo}")
	public ResponseGenerico<Producto> listarProductoPorDescripcion(@PathVariable("descripcion") String descripcion, @PathVariable("nemonicoModulo") String nemonicoModulo) {
		List<Producto> listaProducto = productoServicio.listarProductoPorDescripcion(descripcion, nemonicoModulo);
		// Respuesta
		ResponseGenerico<Producto> response = new ResponseGenerico<>();
		response.setListado(listaProducto);
		response.setTotalRegistros((long) listaProducto.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	/**
	 * REST para obtener Transaccion
	 * 
	 * @return Transaccion
	 */
	@GetMapping(value = "buscarProductoPorCodigo/{codigo}")
	public ResponseGenerico<Producto> buscarProductoPorCodigo(@PathVariable("codigo") Long codigo) {
		Producto producto = productoServicio.buscarProductoPorCodigo(codigo);
		// Respuesta
		ResponseGenerico<Producto> response = new ResponseGenerico<>();
		response.setObjeto(producto);
		response.setTotalRegistros((long) (1));
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	/**
	 * REST para guardar o actualizar producto
	 * 
	 * @return guardar
	 */
	@PostMapping(value = "guardarProducto")
	public ResponseGenerico<Producto> guardarProducto(@RequestBody Producto producto) {
		producto = productoServicio.registrar(producto);
		// Respuesta
		ResponseGenerico<Producto> response = new ResponseGenerico<>();
		response.setObjeto(producto);
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK_CREADO);
		return response;
	}

	/**
	 * Metodo para eliminar (baja lógica) un registro
	 * 
	 * @return objeto response
	 */
	@DeleteMapping(value = "eliminarProductoPorId/{codigo}")
	public ResponseGenerico<Producto> eliminarProducto(@PathVariable("codigo") Long codigo) {
		Producto producto = productoServicio.buscarProductoPorCodigo(codigo);
		producto.setEstado(EstadoEnum.INACTIVO.getDescripcion());
		productoServicio.registrar(producto);
		// Respuesta
		ResponseGenerico<Producto> response = new ResponseGenerico<>();
		response.setObjeto(producto);
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK_ELIMINADO);
		return response;
	}
}
