package asedinfo.com.servicio.impl.seguridad;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.seguridad.InicioSesion;
import asedinfo.com.repositorio.seguridad.InicioSesionRepositorio;
import asedinfo.com.servicio.seguridad.InicioSesionServicio;

@Service
public class InicioSesionServicioImpl implements InicioSesionServicio {

	@Autowired
	private InicioSesionRepositorio inicioSesionRepositorio;

	@Override
	public List<InicioSesion> listarTodosInicioSesion() {
		return inicioSesionRepositorio.listarTodosInicioSesion();
	}
	
	@Override
	public InicioSesion buscarInicioSesionPorCodigo(Long codigo) {
		return inicioSesionRepositorio.findByCodigo(codigo);
	}
	
	@Override
	public InicioSesion registrar(InicioSesion inicioSesion) {
		return inicioSesionRepositorio.save(inicioSesion);
	}
}
