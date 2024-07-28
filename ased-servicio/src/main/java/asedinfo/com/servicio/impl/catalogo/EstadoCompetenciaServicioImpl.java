package asedinfo.com.servicio.impl.catalogo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.catalogo.EstadoCompetencia;
import asedinfo.com.repositorio.catalogo.EstadoCompetenciaRepositorio;
import asedinfo.com.servicio.catalogo.EstadoCompetenciaServicio;

@Service
public class EstadoCompetenciaServicioImpl implements EstadoCompetenciaServicio {

	@Autowired
	private EstadoCompetenciaRepositorio EstadoCompetenciaRepositorio;

	@Override
	public List<EstadoCompetencia> listarTodosEstadoCompetencia() {
		return EstadoCompetenciaRepositorio.findAll();
	}

	@Override
	public List<EstadoCompetencia> listarEstadoCompetenciaActivo(String estado) {
		return EstadoCompetenciaRepositorio.findByEstadoOrderByCodigo(estado);
	}

	@Override
	public EstadoCompetencia buscarEstadoCompetenciaPorCodigo(Long codigo) {
		return EstadoCompetenciaRepositorio.findByCodigo(codigo);
	}

	@Override
	public EstadoCompetencia registrar(EstadoCompetencia estadoCompetencia) {
		return EstadoCompetenciaRepositorio.save(estadoCompetencia);
	}

	@Override
	public EstadoCompetencia crearEstadoCompetencia(EstadoCompetencia estadoCompetencia) {
		return EstadoCompetenciaRepositorio.save(estadoCompetencia);
	}
	
}
