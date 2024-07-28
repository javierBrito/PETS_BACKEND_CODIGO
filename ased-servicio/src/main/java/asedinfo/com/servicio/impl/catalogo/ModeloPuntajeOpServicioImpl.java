package asedinfo.com.servicio.impl.catalogo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.catalogo.ModeloPuntajeOp;
import asedinfo.com.repositorio.catalogo.ModeloPuntajeOpRepositorio;
import asedinfo.com.servicio.catalogo.ModeloPuntajeOpServicio;

@Service
public class ModeloPuntajeOpServicioImpl implements ModeloPuntajeOpServicio {

	@Autowired
	private ModeloPuntajeOpRepositorio modeloPuntajeOpRepositorio;

	@Override
	public List<ModeloPuntajeOp> listarTodosModeloPuntajeOp() {
		return modeloPuntajeOpRepositorio.findAll();
	}

	@Override
	public List<ModeloPuntajeOp> listarModeloPuntajeOpActivo(String estado) {
		return modeloPuntajeOpRepositorio.findByEstadoOrderByCodigo(estado);
	}

	@Override
	public ModeloPuntajeOp buscarModeloPuntajeOpPorCodigo(Long codigo) {
		return modeloPuntajeOpRepositorio.findByCodigo(codigo);
	}

	@Override
	public ModeloPuntajeOp registrar(ModeloPuntajeOp modeloPuntajeOp) {
		return modeloPuntajeOpRepositorio.save(modeloPuntajeOp);
	}

	@Override
	public ModeloPuntajeOp crearModeloPuntajeOp(ModeloPuntajeOp modeloPuntajeOp) {
		return modeloPuntajeOpRepositorio.save(modeloPuntajeOp);
	}
	
}
