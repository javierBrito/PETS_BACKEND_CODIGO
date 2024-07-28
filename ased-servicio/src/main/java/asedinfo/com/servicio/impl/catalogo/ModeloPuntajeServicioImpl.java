package asedinfo.com.servicio.impl.catalogo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.catalogo.ModeloPuntaje;
import asedinfo.com.repositorio.catalogo.ModeloPuntajeRepositorio;
import asedinfo.com.servicio.catalogo.ModeloPuntajeServicio;

@Service
public class ModeloPuntajeServicioImpl implements ModeloPuntajeServicio {

	@Autowired
	private ModeloPuntajeRepositorio modeloPuntajeRepositorio;

	@Override
	public List<ModeloPuntaje> listarTodosModeloPuntaje() {
		return modeloPuntajeRepositorio.findAll();
	}

	@Override
	public List<ModeloPuntaje> listarModeloPuntajeActivo(String estado) {
		return modeloPuntajeRepositorio.findByEstadoOrderByCodigo(estado);
	}

	@Override
	public ModeloPuntaje buscarModeloPuntajePorCodigo(Long codigo) {
		return modeloPuntajeRepositorio.findByCodigo(codigo);
	}

	@Override
	public ModeloPuntaje registrar(ModeloPuntaje modeloPuntaje) {
		return modeloPuntajeRepositorio.save(modeloPuntaje);
	}

	@Override
	public ModeloPuntaje crearModeloPuntaje(ModeloPuntaje modeloPuntaje) {
		return modeloPuntajeRepositorio.save(modeloPuntaje);
	}
	
}
