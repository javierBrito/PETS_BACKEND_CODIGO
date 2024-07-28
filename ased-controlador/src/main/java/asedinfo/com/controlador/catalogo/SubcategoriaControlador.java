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
import asedinfo.com.modelo.catalogo.Subcategoria;
import asedinfo.com.modelo.response.ResponseGenerico;
import asedinfo.com.servicio.catalogo.SubcategoriaServicio;
import asedinfo.com.venta.resources.EstadoEnum;

@RestController
@RequestMapping("catalogo/")
public class SubcategoriaControlador {

	@Autowired
	private SubcategoriaServicio subcategoriaServicio;

	@GetMapping(value = "listarTodosSubcategoria")
	public ResponseGenerico<Subcategoria> listarTodosSubcategoria() {
		List<Subcategoria> listaSubcategoria = subcategoriaServicio.listarTodosSubcategoria();
		// Respuesta
		ResponseGenerico<Subcategoria> response = new ResponseGenerico<>();
		response.setListado(listaSubcategoria);
		response.setTotalRegistros((long) listaSubcategoria.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarSubcategoriaActivo")
	public ResponseGenerico<Subcategoria> listarSubcategoriaActivo() {
		List<Subcategoria> listaSubcategoria = subcategoriaServicio.listarSubcategoriaActivo(EstadoEnum.ACTIVO.getDescripcion());
		// Respuesta
		ResponseGenerico<Subcategoria> response = new ResponseGenerico<>();
		response.setListado(listaSubcategoria);
		response.setTotalRegistros((long) listaSubcategoria.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	@GetMapping(value = "listarSubcategoriaPorCategoria/{codCategoria}")
	public ResponseGenerico<Subcategoria> listarSubcategoriaPorCategoria(@PathVariable("codCategoria") Long codCategoria) {
		List<Subcategoria> listaSubcategoria = subcategoriaServicio.listarSubcategoriaPorCategoria(codCategoria);
		// Respuesta
		ResponseGenerico<Subcategoria> response = new ResponseGenerico<>();
		response.setListado(listaSubcategoria);
		response.setTotalRegistros((long) listaSubcategoria.size());
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	/**
	 * REST para obtener Subcategoria
	 * 
	 * @return Subcategoria
	 */
	@GetMapping(value = "buscarSubcategoriaPorCodigo/{codigo}")
	public ResponseGenerico<Subcategoria> buscarSubcategoriaPorCodigo(@PathVariable("codigo") Long codigo) {
		Subcategoria Subcategoria = subcategoriaServicio.buscarSubcategoriaPorCodigo(codigo);
		// Respuesta
		ResponseGenerico<Subcategoria> response = new ResponseGenerico<>();
		response.setObjeto(Subcategoria);
		response.setTotalRegistros((long) (1));
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	/**
	 * REST para obtener Categoria
	 * 
	 * @return Categoria
	 */
	@GetMapping(value = "buscarSubcategoriaPorDenominacion/{denominacion}/{codCategoria}")
	public ResponseGenerico<Subcategoria> buscarCategoriaPorDenominacion(@PathVariable("denominacion") String denominacion, @PathVariable("codCategoria") Long codCategoria) {
		Subcategoria subcategoria = subcategoriaServicio.buscarSubcategoriaPorDenominacion(denominacion, codCategoria);
		// Respuesta
		ResponseGenerico<Subcategoria> response = new ResponseGenerico<>();
		response.setObjeto(subcategoria);
		response.setTotalRegistros((long) (1));
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK);
		return response;
	}

	/**
	 * REST para guardar o actualizar Subcategoria
	 * 
	 * @return guardar
	 */
	@PostMapping(value = "guardarSubcategoria")
	public ResponseGenerico<Subcategoria> guardarSubcategoria(@RequestBody Subcategoria subcategoria) {
		subcategoria = subcategoriaServicio.registrar(subcategoria);
		// Respuesta
		ResponseGenerico<Subcategoria> response = new ResponseGenerico<>();
		response.setObjeto(subcategoria);
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK_CREADO);
		return response;
	}

	/**
	 * Metodo para eliminar (baja lógica) un registro
	 * 
	 * @return objeto response
	 */
	@DeleteMapping(value = "eliminarSubcategoriaPorId/{codigo}")
	public ResponseGenerico<Subcategoria> eliminarSubcategoria(@PathVariable("codigo") Long codigo) {
		Subcategoria subcategoria = subcategoriaServicio.buscarSubcategoriaPorCodigo(codigo);
		subcategoria.setEstado(EstadoEnum.INACTIVO.getDescripcion());
		subcategoriaServicio.registrar(subcategoria);
		// Respuesta
		ResponseGenerico<Subcategoria> response = new ResponseGenerico<>();
		response.setObjeto(subcategoria);
		response.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_OK);
		response.setMensaje(Constantes.MENSAJE_OK_ELIMINADO);
		return response;
	}
}
