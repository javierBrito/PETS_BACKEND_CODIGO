package asedinfo.com.servicio.impl.catalogo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.catalogo.Instancia;
import asedinfo.com.repositorio.catalogo.InstanciaRepositorio;
import asedinfo.com.servicio.catalogo.InstanciaServicio;

@Service
public class InstanciaServicioImpl implements InstanciaServicio {

	@Autowired
	private InstanciaRepositorio InstanciaRepositorio;

	@Override
	public List<Instancia> listarTodosInstancia() {
		return InstanciaRepositorio.findAll();
	}

	@Override
	public List<Instancia> listarInstanciaActivo(String estado) {
		return InstanciaRepositorio.findByEstadoOrderByCodigo(estado);
	}

	@Override
	public Instancia buscarInstanciaPorCodigo(Long codigo) {
		return InstanciaRepositorio.findByCodigo(codigo);
	}

	@Override
	public Instancia registrar(Instancia instancia) {
		return InstanciaRepositorio.save(instancia);
	}

	@Override
	public Instancia crearInstancia(Instancia instancia) {
		return InstanciaRepositorio.save(instancia);
	}
	
}
