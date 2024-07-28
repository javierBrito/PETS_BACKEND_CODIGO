package asedinfo.com.servicio.impl.ahorro;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.ahorro.Socio;
import asedinfo.com.modelo.catalogo.Persona;
import asedinfo.com.repositorio.ahorro.SocioRepositorio;
import asedinfo.com.servicio.ahorro.SocioServicio;
import asedinfo.com.servicio.catalogo.PersonaServicio;

@Service
public class SocioServicioImpl implements SocioServicio {

	@Autowired
	private SocioRepositorio socioRepositorio;
	@Autowired
	private PersonaServicio personaServicio;
	
	@Override
	public List<Socio> listarTodosSocio() {
		return socioRepositorio.findAll();
	}

	@Override
	public List<Socio> listarSocioActivo(String estado) {
		return socioRepositorio.findByEstadoOrderByCodigo(estado);
	}

	@Override
	public Socio buscarSocioPorCodigo(Long codigo) {
		return socioRepositorio.findByCodigo(codigo);
	}

	@Override
	public List<Socio> listarSocioPorPersona(Long codPersona) {
		return socioRepositorio.listarSocioPorPersona(codPersona);
	}

	@Override
	public Socio registrar(Socio socio) {
		if (socio.getCodPersona() != null && socio.getCodPersona() != 0) {
			Persona persona = new Persona();
			persona = personaServicio.buscarPersonaPorCodigo(socio.getCodPersona());
			if (persona != null) {
				socio.setPersona(persona);
			}
		}

		return socioRepositorio.save(socio);
	}
	
}
