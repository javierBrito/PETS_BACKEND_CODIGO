package asedinfo.com.servicio.impl.seguridad;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.seguridad.Aplicacion;
import asedinfo.com.modelo.seguridad.Recurso;
import asedinfo.com.repositorio.seguridad.RecursoRepositorio;
import asedinfo.com.servicio.seguridad.AplicacionServicio;
import asedinfo.com.servicio.seguridad.RecursoServicio;

@Service
public class RecursoServicioImpl implements RecursoServicio {

	@Autowired
	private RecursoRepositorio recursoRepositorio;
	@Autowired
	private AplicacionServicio aplicacionServicio;
	@Autowired
	private RecursoServicio recursoServicio;

	@Override
	public List<Recurso> listarTodosRecurso() {
		return recursoRepositorio.findAll();
	}

	@Override
	public List<Recurso> listarRecursoActivo(String estado) {
		return recursoRepositorio.findByEstadoOrderByCodigo(estado);
	}

	@Override
	public List<Recurso> listarRecursoPorAplicacion(Long codigoAplicacion) {
		return recursoRepositorio.listarRecursoPorAplicacion(codigoAplicacion);
	}

	@Override
	public List<Recurso> listarRecursoPadre(Long codigoAplicacion) {
		return recursoRepositorio.listarRecursoPadre(codigoAplicacion);
	}

	@Override
	public Recurso buscarRecursoPorCodigo(Long codigo) {
		return recursoRepositorio.findByCodigo(codigo);
	}

	@Override
	public Recurso registrar(Recurso recurso) {
		if (recurso.getCodigoAplicacion() != 0) {
			Aplicacion aplicacion = new Aplicacion();
			aplicacion = aplicacionServicio.buscarAplicacionPorCodigo(recurso.getCodigoAplicacion());
			if (aplicacion != null) {
				recurso.setAplicacion(aplicacion);
			}
		}
		if (recurso.getCodigoPadre() != 0) {
			Recurso padre = new Recurso();
			padre = recursoServicio.buscarRecursoPorCodigo(recurso.getCodigoPadre());
			if (padre != null) {
				recurso.setPadre(padre);
			}
		}

		return recursoRepositorio.save(recurso);
	}

	@Override
	public List<Recurso> obtenerRecursosPorCodigoRol(Long codigoRol) {
		return recursoRepositorio.listaMenu(codigoRol);
	}

	@Override
	public Recurso obtenerRecursoPorCodigo(Long codigoRecurso) {
		return recursoRepositorio.obtenerRecurso(codigoRecurso);
	}

}
