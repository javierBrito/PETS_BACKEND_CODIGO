package asedinfo.com.servicio.impl.competencia;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.competencia.Integrante;
import asedinfo.com.modelo.competencia.Participante;
import asedinfo.com.repositorio.competencia.IntegranteRepositorio;
import asedinfo.com.servicio.competencia.IntegranteServicio;
import asedinfo.com.servicio.competencia.ParticipanteServicio;

@Service
public class IntegranteServicioImpl implements IntegranteServicio {

	@Autowired
	private IntegranteRepositorio integranteRepositorio;
	@Autowired
	private ParticipanteServicio participanteServicio;

	@Override
	public List<Integrante> listarTodosIntegrante() {
		return integranteRepositorio.findAll();
	}

	@Override
	public List<Integrante> listarIntegrantePorParticipante(Long codParticipante) {
		return integranteRepositorio.listarIntegrantePorParticipante(codParticipante);
	}
	
	@Override
	public Integer eliminarIntegrantePorCodParticipante(Long codParticipante) {
		return integranteRepositorio.eliminarIntegrantePorCodParticipante(codParticipante);
	}

	@Override
	public List<Integrante> listarIntegranteActivo(String estado) {
		return integranteRepositorio.findByEstadoOrderByCodigo(estado);
	}

	@Override
	public Integrante buscarIntegrantePorCodigo(Long codigo) {
		return integranteRepositorio.findByCodigo(codigo);
	}

	@Override
	public Integrante buscarIntegrantePorNombre(String nombre, Long codParticipante) {
		return integranteRepositorio.buscarIntegrantePorNombre(nombre, codParticipante);
	}

	@Override
	public Integrante registrar(Integrante integrante) {
		if (integrante.getCodParticipante() != 0) {
			Participante participante = participanteServicio.buscarParticipantePorCodigo(integrante.getCodParticipante());
			integrante.setParticipante(participante);
		}
		return integranteRepositorio.save(integrante);
	}

	@Override
	public Integrante crearIntegrante(Integrante integrante) {
		if (integrante.getCodParticipante() != 0) {
			Participante participante = participanteServicio.buscarParticipantePorCodigo(integrante.getCodParticipante());
			integrante.setParticipante(participante);
		}
		return integranteRepositorio.save(integrante);
	}
	
}
