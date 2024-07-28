package asedinfo.com.servicio.impl.seguridad;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.seguridad.Recurso;
import asedinfo.com.modelo.seguridad.RolAplicacion;
import asedinfo.com.modelo.seguridad.RolAplicacionRecurso;
import asedinfo.com.repositorio.seguridad.RolAplicacionRecursoRepositorio;
import asedinfo.com.servicio.seguridad.RecursoServicio;
import asedinfo.com.servicio.seguridad.RolAplicacionRecursoServicio;
import asedinfo.com.servicio.seguridad.RolAplicacionServicio;

@Service
public class RolAplicacionRecursoServicioImpl implements RolAplicacionRecursoServicio {

	@Autowired
	private RolAplicacionRecursoRepositorio rolAplicacionRecursoRepositorio;
	@Autowired
	private RolAplicacionServicio rolAplicacionServicio;
	@Autowired
	private RecursoServicio recursoServicio;

	@Override
	public List<RolAplicacionRecurso> listarTodosRolAplicacionRecurso() {
		return rolAplicacionRecursoRepositorio.findAll();
	}

	@Override
	public List<RolAplicacionRecurso> listarRolAplicacionRecursoActivo(String estado) {
		return rolAplicacionRecursoRepositorio.findByEstadoOrderByCodigo(estado);
	}

	@Override
	public List<RolAplicacionRecurso> listarRolAplicacionRecursoPorRolAplicacion(Long codigoRolAplicacion) {
		return rolAplicacionRecursoRepositorio.listarRolAplicacionRecursoPorRolAplicacion(codigoRolAplicacion);
	}

	@Override
	public List<RolAplicacionRecurso> listarRolAplicacionRecursoPorAplicacion(Long codigoAplicacion) {
		return rolAplicacionRecursoRepositorio.listarRolAplicacionRecursoPorAplicacion(codigoAplicacion);
	}

	@Override
	public RolAplicacionRecurso buscarRolAplicacionRecursoPorCodigo(Long codigo) {
		return rolAplicacionRecursoRepositorio.findByCodigo(codigo);
	}

	@Override
	public RolAplicacionRecurso registrar(RolAplicacionRecurso rolAplicacionRecurso) {
		if (rolAplicacionRecurso.getCodigoRolAplicacion() != 0) {
			RolAplicacion rolAplicacion = new RolAplicacion();
			rolAplicacion = rolAplicacionServicio.buscarRolAplicacionPorCodigo(rolAplicacionRecurso.getCodigoRolAplicacion());
			if (rolAplicacion != null) {
				rolAplicacionRecurso.setRolAplicacion(rolAplicacion);
			}
		}

		if (rolAplicacionRecurso.getCodigoRecurso() != 0) {
			Recurso recurso = new Recurso();
			recurso = recursoServicio.buscarRecursoPorCodigo(rolAplicacionRecurso.getCodigoRecurso());
			if (recurso != null) {
				rolAplicacionRecurso.setRecurso(recurso);
			}
		}

		return rolAplicacionRecursoRepositorio.save(rolAplicacionRecurso);
	}
}
