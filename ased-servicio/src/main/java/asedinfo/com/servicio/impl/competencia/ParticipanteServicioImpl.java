package asedinfo.com.servicio.impl.competencia;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.catalogo.EstadoCompetencia;
import asedinfo.com.modelo.catalogo.Instancia;
import asedinfo.com.modelo.catalogo.Persona;
import asedinfo.com.modelo.catalogo.Subcategoria;
import asedinfo.com.modelo.competencia.Participante;
import asedinfo.com.repositorio.competencia.ParticipanteRepositorio;
import asedinfo.com.servicio.catalogo.EstadoCompetenciaServicio;
import asedinfo.com.servicio.catalogo.InstanciaServicio;
import asedinfo.com.servicio.catalogo.PersonaServicio;
import asedinfo.com.servicio.catalogo.SubcategoriaServicio;
import asedinfo.com.servicio.competencia.ParticipanteServicio;

@Service
public class ParticipanteServicioImpl implements ParticipanteServicio {

	@Autowired
	private ParticipanteRepositorio participanteRepositorio;
	@Autowired
	private SubcategoriaServicio subcategoriaServicio;
	@Autowired
	private InstanciaServicio instanciaServicio;
	@Autowired
	private EstadoCompetenciaServicio estadoCompetenciaServicio;
	@Autowired
	private PersonaServicio personaServicio;
	
	@Override
	public List<Participante> listarTodosParticipante() {
		return participanteRepositorio.findAll();
	}

	@Override
	public Participante buscarParticipantePorCodigo(Long codigo) {
		return participanteRepositorio.findByCodigo(codigo);
	}

	@Override
	public Integer obtenerMaxNumParticipante() {
		return participanteRepositorio.obtenerMaxNumParticipante();
	}

	@Override
	public Integer obtenerNumParticipanteEnEscenario() {
		return participanteRepositorio.obtenerNumParticipanteEnEscenario();
	}

	@Override
	public List<Participante> listarParticipanteEnEscenario() {
		return participanteRepositorio.listarParticipanteEnEscenario();
	}

	@Override
	public List<Participante> listarParticipantePorPersona(Long codPersona) {
		return participanteRepositorio.listarParticipantePorPersona(codPersona);
	}

	@Override
	public List<Participante> listarParticipantePorSubcategoria(Long codSubcategoria) {
		return participanteRepositorio.listarParticipantePorSubcategoria(codSubcategoria);
	}

	@Override
	public List<Participante> listarParticipanteUsuario() {
		return participanteRepositorio.listarParticipanteUsuario();
	}

	@Override
	public List<Participante> listarParticipantePorSubcategoriaInstancia(Long codSubcategoria, Long codInstancia, Long codEstadoComptetencia) {
		if (codEstadoComptetencia == 0) {
			return participanteRepositorio.listarParticipantePorSubcategoriaInstancia(codSubcategoria, codInstancia);
		} else {
			return participanteRepositorio.listarParticipantePorSubcategoriaInstanciaEnEscenario(codSubcategoria, codInstancia, codEstadoComptetencia);
		}
	}

	@Override
	public List<Participante> listarParticipantePorEstadoCompetencia(Long codEstadoComptetencia) {
		return participanteRepositorio.listarParticipantePorEstadoCompetencia(codEstadoComptetencia);
	}

	@Override
	public List<Participante> listarParticipantePorEstado(String estadoPedido) {
		return participanteRepositorio.listarParticipantePorEstado(estadoPedido);
	}

	@Override
	public List<Participante> listarParticipantePorEmail(String email) {
		return participanteRepositorio.listarParticipantePorEmail(email);
	}

	@Override
	public Participante registrar(Participante participante) {
		if (participante.getCodSubcategoria() != 0) {
			Subcategoria subcategoria = subcategoriaServicio.buscarSubcategoriaPorCodigo(participante.getCodSubcategoria());
			participante.setSubcategoria(subcategoria);
		} 
		if (participante.getCodInstancia() != 0) {
			Instancia instancia = instanciaServicio.buscarInstanciaPorCodigo(participante.getCodInstancia());
			participante.setInstancia(instancia);
		}
		if (participante.getCodEstadoCompetencia() != 0) {
			EstadoCompetencia estadoCompetencia = estadoCompetenciaServicio.buscarEstadoCompetenciaPorCodigo(participante.getCodEstadoCompetencia());
			participante.setEstadoCompetencia(estadoCompetencia);
		} 
		if (participante.getCodPersona() != 0) {
			Persona persona = personaServicio.buscarPersonaPorCodigo(participante.getCodPersona());
			participante.setPersona(persona);
		} 
		return participanteRepositorio.save(participante);
	}
	
}
