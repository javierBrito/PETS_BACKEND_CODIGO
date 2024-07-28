package asedinfo.com.servicio.impl.seguridad;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.DTO.AplicacionDTO;
import asedinfo.com.modelo.DTO.RolesAplicacionDTO;
import asedinfo.com.modelo.seguridad.Aplicacion;
import asedinfo.com.modelo.seguridad.RolAplicacion;
import asedinfo.com.repositorio.seguridad.RolAplicacionRepositorio;
import asedinfo.com.servicio.seguridad.AplicacionServicio;
import asedinfo.com.servicio.seguridad.RolAplicacionServicio;
import asedinfo.com.venta.resources.Constantes;

@Service
public class RolAplicacionServicioImpl implements RolAplicacionServicio {

	@Autowired
	private RolAplicacionRepositorio rolAplicacionRepositorio;
	@Autowired
	private AplicacionServicio aplicacionServicio;

	private AplicacionDTO aplicacionDTO = new AplicacionDTO();

	@Override
	public List<RolAplicacion> listarTodosRolAplicacion() {
		return rolAplicacionRepositorio.findAll();
	}

	@Override
	public List<RolAplicacion> listarRolAplicacionActivo(String estadoRol) {
		return rolAplicacionRepositorio.findByEstadoRolOrderByCodigo(estadoRol);
	}

	@Override
	public List<RolAplicacion> listarRolAplicacionPorCodAplicacion(Long codAplicacion) {
		return rolAplicacionRepositorio.listarRolAplicacionPorAplicacion(codAplicacion);
	}

	@Override
	public RolAplicacion buscarRolAplicacionPorCodigo(Long codigo) {
		return rolAplicacionRepositorio.findByCodigo(codigo);
	}

	@Override
	public RolAplicacion registrar(RolAplicacion rolAplicacion) {
		if (rolAplicacion.getCodAplicacion() != 0) {
			Aplicacion aplicacion = new Aplicacion();
			aplicacion = aplicacionServicio.buscarAplicacionPorCodigo(rolAplicacion.getCodAplicacion());
			if (aplicacion != null) {
				rolAplicacion.setAplicacion(aplicacion);
			}
		}

		return rolAplicacionRepositorio.save(rolAplicacion);
	}

	@Override
	public RolAplicacion buscarRolAplicacionNombre(Aplicacion aplicacion, String nombre) {
		return rolAplicacionRepositorio.findByAplicacionAndNombreRolAndEstadoRol(aplicacion, nombre,
				Constantes.REGISTRO_ACTIVO);
	}

	@Override
	public List<RolesAplicacionDTO> listaRolesAplicaciones(Long codUsuario, String prefijoApp) {

		List<RolesAplicacionDTO> rolAplicacion = new ArrayList<>();

		rolAplicacionRepositorio.listaRolesAplicaciones(codUsuario, prefijoApp).forEach(objects -> {
			RolesAplicacionDTO rolAplicacionDTO = new RolesAplicacionDTO();

			if (objects[0] == null || objects[0] == "") {

			} else {
				rolAplicacionDTO.setCodigo(Long.parseLong(String.valueOf(objects[0])));
			}

			if (objects[1] == null || objects[1] == "") {
			} else {
				rolAplicacionDTO.setCod_aplicacion(Long.parseLong(String.valueOf(objects[1])));
				aplicacionDTO = (AplicacionDTO) aplicacionServicio.listaAplicaciones(codUsuario, prefijoApp,
						rolAplicacionDTO.getCodigo(), rolAplicacionDTO.getCod_aplicacion());
				rolAplicacionDTO.setAplicacion(aplicacionDTO);
			}
			if (objects[2] == null || objects[2] == "") {
			} else {
				rolAplicacionDTO.setNombre(String.valueOf(objects[2]));
			}
			if (objects[3] == null || objects[3] == "") {
			} else {
				rolAplicacionDTO.setDescripcion(String.valueOf(objects[3]));
			}
			if (objects[4] == null || objects[4] == "") {

			} else {
				rolAplicacionDTO.setEstado(String.valueOf(objects[4]));
			}

			rolAplicacion.add(rolAplicacionDTO);
		});

		return rolAplicacion;
	}

	@Override
	public List<RolesAplicacionDTO> listarRolAplicacion(Long codUsuario) {
		List<RolesAplicacionDTO> rolAplicacion = new ArrayList<>();
		rolAplicacionRepositorio.listarRolAplicacion(codUsuario).forEach(objects -> {
			RolesAplicacionDTO rolAplicacionDTO = new RolesAplicacionDTO();

			if (objects[0] == null || objects[0] == "") {

			} else {
				rolAplicacionDTO.setCodigo(Long.parseLong(String.valueOf(objects[0])));
			}

			if (objects[1] == null || objects[1] == "") {
			} else {
				rolAplicacionDTO.setCod_aplicacion(Long.parseLong(String.valueOf(objects[1])));
				aplicacionDTO = (AplicacionDTO) aplicacionServicio.listarAplicacion(codUsuario,
						rolAplicacionDTO.getCodigo(), rolAplicacionDTO.getCod_aplicacion());
				rolAplicacionDTO.setAplicacion(aplicacionDTO);
			}
			if (objects[2] == null || objects[2] == "") {
			} else {
				rolAplicacionDTO.setNombre(String.valueOf(objects[2]));
			}
			if (objects[3] == null || objects[3] == "") {
			} else {
				rolAplicacionDTO.setDescripcion(String.valueOf(objects[3]));
			}
			if (objects[4] == null || objects[4] == "") {

			} else {
				rolAplicacionDTO.setEstado(String.valueOf(objects[4]));
			}

			rolAplicacion.add(rolAplicacionDTO);
		});

		return rolAplicacion;
	}

}
