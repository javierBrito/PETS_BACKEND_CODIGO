package asedinfo.com.servicio.impl.catalogo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.catalogo.Modulo;
import asedinfo.com.modelo.catalogo.Operacion;
import asedinfo.com.repositorio.catalogo.OperacionRepositorio;
import asedinfo.com.servicio.catalogo.ModuloServicio;
import asedinfo.com.servicio.catalogo.OperacionServicio;
import asedinfo.com.venta.resources.Constantes;

@Service
public class OperacionServicioImpl implements OperacionServicio {

	@Autowired
	private OperacionRepositorio operacionRepositorio;
	@Autowired
	private ModuloServicio moduloServicio;

	@Override
	public List<Operacion> listarTodosOperacion() {
		return operacionRepositorio.findAll();
	}

	@Override
	public List<Operacion> listarOperacionActivo(String estado) {
		return operacionRepositorio.findByEstadoOrderByCodigo(estado);
	}

	@Override
	public Operacion buscarOperacionPorCodigo(Long codigo) {
		return operacionRepositorio.findByCodigo(codigo);
	}

	@Override
	public Operacion buscarOperacionPorNemonico(String nemonico) {
		return operacionRepositorio.findByNemonico(nemonico);
	}

	@Override
	public List<Operacion> buscarPorNemonico(String nemonico) {
		return operacionRepositorio.findByNemonicoAndEstado(nemonico, Constantes.REGISTRO_ACTIVO);
	}

	@Override
	public Operacion registrar(Operacion operacion) {
		if (operacion.getCodModulo() != 0) {
			Modulo modulo = new Modulo();
			modulo = moduloServicio.buscarModuloPorCodigo(operacion.getCodModulo());
			if (modulo != null) {
				operacion.setModulo(modulo);
			}
		}
		return operacionRepositorio.save(operacion);
	}

	@Override
	public Operacion crearOperacion(Operacion operacion) {
		return operacionRepositorio.save(operacion);
	}
	
}
