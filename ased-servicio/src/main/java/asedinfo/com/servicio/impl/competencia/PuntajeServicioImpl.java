package asedinfo.com.servicio.impl.competencia;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.catalogo.Instancia;
import asedinfo.com.modelo.catalogo.ModeloPuntaje;
import asedinfo.com.modelo.catalogo.Subcategoria;
import asedinfo.com.modelo.competencia.Participante;
import asedinfo.com.modelo.competencia.Puntaje;
import asedinfo.com.repositorio.competencia.PuntajeRepositorio;
import asedinfo.com.servicio.catalogo.InstanciaServicio;
import asedinfo.com.servicio.catalogo.ModeloPuntajeServicio;
import asedinfo.com.servicio.catalogo.SubcategoriaServicio;
import asedinfo.com.servicio.competencia.ParticipanteServicio;
import asedinfo.com.servicio.competencia.PuntajeServicio;

@Service
public class PuntajeServicioImpl implements PuntajeServicio {

	@Autowired
	private PuntajeRepositorio puntajeRepositorio;
	@Autowired
	private ModeloPuntajeServicio modeloPuntajeServicio;
	@Autowired
	private InstanciaServicio instanciaServicio;
	@Autowired
	private ParticipanteServicio participanteServicio;
	@Autowired
	private SubcategoriaServicio subcategoriaServicio;

	@Override
	public List<Puntaje> listarTodosPuntaje() {
		return puntajeRepositorio.findAll();
	}

	@Override
	public List<Puntaje> listarPuntajeActivo(String estado) {
		return puntajeRepositorio.findByEstadoOrderByCodigo(estado);
	}

	@Override
	public List<Puntaje> listarPuntajePorSubcategoria(Long codSubcategoria, Long codInstancia) {
		return puntajeRepositorio.listarPuntajePorSubcategoria(codSubcategoria, codInstancia);
	}

	@Override
	public List<Puntaje> listarPuntajePorSubcategoriaRegTotal(Long codSubcategoria, Long codInstancia, Long codUsuarioJuez) {
		return puntajeRepositorio.listarPuntajePorSubcategoriaRegTotal(codSubcategoria, codInstancia, codUsuarioJuez);
	}
	
	@Override
	public List<Puntaje> listarPuntajePorParticipanteSubcategoriaInstancia(Long codParticipante, Long codSubcategoria, Long codInstancia, Long codUsuarioJuez) {
		return puntajeRepositorio.listarPuntajePorParticipanteSubcategoriaInstancia(codParticipante, codSubcategoria, codInstancia, codUsuarioJuez);
	}
	
	@Override
	public List<Puntaje> listarPuntajePorParticipanteSubcategoriaInstanciaCriterios(Long codParticipante, Long codSubcategoria, Long codInstancia) {
		return puntajeRepositorio.listarPuntajePorParticipanteSubcategoriaInstanciaCriterios(codParticipante, codSubcategoria, codInstancia);
	}
	
	@Override
	public List<Puntaje> listarPuntajePorParticipanteRegTotal(Long codParticipante, Long codSubcategoria, Long codInstancia, Long codUsuarioJuez, Long codModeloPuntaje) {
		return puntajeRepositorio.listarPuntajePorParticipanteRegTotal(codParticipante, codSubcategoria, codInstancia, codUsuarioJuez, codModeloPuntaje);
	}
	
	@Override
	public Integer eliminarPuntajePorParticipanteInstancia(Long codParticipante, Long codInstancia) {
		return puntajeRepositorio.eliminarPuntajePorParticipanteInstancia(codParticipante, codInstancia);
	}
	
	@Override
	public Integer eliminarPuntajePorParticipanteUsuarioJuezInstancia(Long codParticipante, Long codUsuarioJuez, Long codInstancia) {
		return puntajeRepositorio.eliminarPuntajePorParticipanteUsuarioJuezInstancia(codParticipante, codUsuarioJuez, codInstancia);
	}

	@Override
	public List<Puntaje> listarPuntajePorSubcategoriaInstanciaRegAVG(Long codSubcategoria, Long codInstancia) {
		List<Puntaje> listaPuntaje = new ArrayList<>();
		puntajeRepositorio.listarPuntajePorSubcategoriaInstanciaRegAVG(codSubcategoria, codInstancia).forEach(objects -> {
			Puntaje puntaje = new Puntaje();

			if (objects[0] == null || objects[0] == "") {
				puntaje.setPuntaje(null);
			} else {
				puntaje.setPuntaje(Float.parseFloat(String.valueOf(objects[0])));
			}
			if (objects[1] != null || objects[1] != "") {
				puntaje.setCodSubcategoria(Long.parseLong(String.valueOf(objects[1])));
			}
			if (objects[2] != null || objects[2] != "") {
				puntaje.setCodInstancia(Long.parseLong(String.valueOf(objects[2])));
			}
			if (objects[3] != null || objects[3] != "") {
				puntaje.setCodParticipante(Long.parseLong(String.valueOf(objects[3])));
			}
			if (objects[4] != null || objects[4] != "") {
				puntaje.setNombreParticipante(String.valueOf(objects[4]));
			}
			if (objects[5] != null || objects[5] != "") {
				puntaje.setCodigo(Long.parseLong(String.valueOf(objects[5])));
			}
			if (objects[6] != null || objects[6] != "") {
				puntaje.setCodModeloPuntaje(Long.parseLong(String.valueOf(objects[6])));
			}
			if (objects[7] != null || objects[7] != "") {
				puntaje.setEstado(String.valueOf(objects[7]));
			}

			listaPuntaje.add(puntaje);
		});

		return listaPuntaje;
	}

	@Override
	public List<Puntaje> listarPuntajePorSubcategoriaInstanciaRegSUMA(Long codSubcategoria, Long codInstancia) {
		List<Puntaje> listaPuntaje = new ArrayList<>();
		puntajeRepositorio.listarPuntajePorSubcategoriaInstanciaRegSUMA(codSubcategoria, codInstancia).forEach(objects -> {
			Puntaje puntaje = new Puntaje();

			if (objects[0] == null || objects[0] == "") {
				puntaje.setPuntaje(null);
			} else {
				puntaje.setPuntaje(Float.parseFloat(String.valueOf(objects[0])));
			}
			if (objects[1] != null || objects[1] != "") {
				puntaje.setCodSubcategoria(Long.parseLong(String.valueOf(objects[1])));
			}
			if (objects[2] != null || objects[2] != "") {
				puntaje.setCodInstancia(Long.parseLong(String.valueOf(objects[2])));
			}
			if (objects[3] != null || objects[3] != "") {
				puntaje.setCodParticipante(Long.parseLong(String.valueOf(objects[3])));
			}
			if (objects[4] != null || objects[4] != "") {
				puntaje.setNombreParticipante(String.valueOf(objects[4]));
			}
			if (objects[5] != null || objects[5] != "") {
				puntaje.setCodigo(Long.parseLong(String.valueOf(objects[5])));
			}
			if (objects[6] != null || objects[6] != "") {
				puntaje.setCodModeloPuntaje(Long.parseLong(String.valueOf(objects[6])));
			}
			if (objects[7] != null || objects[7] != "") {
				puntaje.setEstado(String.valueOf(objects[7]));
			}

			listaPuntaje.add(puntaje);
		});

		return listaPuntaje;
	}

	@Override
	public List<Puntaje> listarPuntajePorParticipanteSubcategoriaInstanciaRegSUMA(Long codSubcategoria, Long codInstancia, Long codParticipante) {
		List<Puntaje> listaPuntaje = new ArrayList<>();
		puntajeRepositorio.listarPuntajePorParticipanteSubcategoriaInstanciaRegSUMA(codSubcategoria, codInstancia, codParticipante).forEach(objects -> {
			Puntaje puntaje = new Puntaje();

			if (objects[0] == null || objects[0] == "") {
				puntaje.setPuntaje(null);
			} else {
				puntaje.setPuntaje(Float.parseFloat(String.valueOf(objects[0])));
			}
			if (objects[1] != null || objects[1] != "") {
				puntaje.setCodSubcategoria(Long.parseLong(String.valueOf(objects[1])));
			}
			if (objects[2] != null || objects[2] != "") {
				puntaje.setCodInstancia(Long.parseLong(String.valueOf(objects[2])));
			}
			if (objects[3] != null || objects[3] != "") {
				puntaje.setCodParticipante(Long.parseLong(String.valueOf(objects[3])));
			}
			if (objects[4] != null || objects[4] != "") {
				puntaje.setNombreParticipante(String.valueOf(objects[4]));
			}
			if (objects[5] != null || objects[5] != "") {
				puntaje.setCodigo(Long.parseLong(String.valueOf(objects[5])));
			}
			if (objects[6] != null || objects[6] != "") {
				puntaje.setCodModeloPuntaje(Long.parseLong(String.valueOf(objects[6])));
			}
			if (objects[7] != null || objects[7] != "") {
				puntaje.setEstado(String.valueOf(objects[7]));
			}

			listaPuntaje.add(puntaje);
		});

		return listaPuntaje;
	}
	
	@Override
	public Puntaje buscarPuntajePorCodigo(Long codigo) {
		return puntajeRepositorio.findByCodigo(codigo);
	}

	@Override
	public Puntaje registrar(Puntaje puntaje) {
		if (puntaje.getCodModeloPuntaje() != 0) {
			ModeloPuntaje modeloPuntaje = modeloPuntajeServicio.buscarModeloPuntajePorCodigo(puntaje.getCodModeloPuntaje());
			puntaje.setModeloPuntaje(modeloPuntaje);
		}
		if (puntaje.getCodInstancia() != 0) {
			Instancia instancia = instanciaServicio.buscarInstanciaPorCodigo(puntaje.getCodInstancia());
			puntaje.setInstancia(instancia);
		}
		if (puntaje.getCodParticipante() != 0) {
			Participante participante = participanteServicio.buscarParticipantePorCodigo(puntaje.getCodParticipante());
			puntaje.setParticipante(participante);
		} 
		if (puntaje.getCodSubcategoria() != 0) {
			Subcategoria subcategoria = subcategoriaServicio.buscarSubcategoriaPorCodigo(puntaje.getCodSubcategoria());
			puntaje.setSubcategoria(subcategoria);
		}
		return puntajeRepositorio.save(puntaje);
	}

	@Override
	public Puntaje crearPuntaje(Puntaje puntaje) {
		return puntajeRepositorio.save(puntaje);
	}
	
}
