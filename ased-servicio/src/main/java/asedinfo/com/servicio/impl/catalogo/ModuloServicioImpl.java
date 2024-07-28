package asedinfo.com.servicio.impl.catalogo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.catalogo.Modulo;
import asedinfo.com.repositorio.catalogo.ModuloRepositorio;
import asedinfo.com.servicio.catalogo.ModuloServicio;
import asedinfo.com.venta.resources.Constantes;

@Service
public class ModuloServicioImpl implements ModuloServicio {

	@Autowired
	private ModuloRepositorio moduloRepositorio;

	@Override
	public List<Modulo> listarTodosModulo() {
		return moduloRepositorio.findAll();
	}

	@Override
	public List<Modulo> listarModuloActivo(String estado) {
		return moduloRepositorio.findByEstadoOrderByCodigo(estado);
	}

	@Override
	public Modulo buscarModuloPorCodigo(Long codigo) {
		return moduloRepositorio.findByCodigo(codigo);
	}

	@Override
	public Modulo buscarModuloPorNemonico(String nemonico) {
		return moduloRepositorio.findByNemonico(nemonico);
	}

	@Override
	public List<Modulo> buscarPorNemonico(String nemonico) {
		return moduloRepositorio.findByNemonicoAndEstado(nemonico, Constantes.REGISTRO_ACTIVO);
	}

	@Override
	public Modulo registrar(Modulo modulo) {
		return moduloRepositorio.save(modulo);
	}

	@Override
	public Modulo crearModulo(Modulo modulo) {
		return moduloRepositorio.save(modulo);
	}
	
}
