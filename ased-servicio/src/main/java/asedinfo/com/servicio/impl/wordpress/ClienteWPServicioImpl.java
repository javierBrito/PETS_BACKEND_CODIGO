package asedinfo.com.servicio.impl.wordpress;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.DTO.UsuarioWPDTO;
import asedinfo.com.modelo.wordpress.ClienteWP;
import asedinfo.com.modelo.wordpress.PedidoProducto;
import asedinfo.com.repositorio.wordpress.ClienteWPRepositorio;
import asedinfo.com.servicio.wordpress.ClienteWPServicio;

@Service
public class ClienteWPServicioImpl implements ClienteWPServicio {

	@Autowired
	private ClienteWPRepositorio clienteWPRepositorio;
	
	@Override
	public List<ClienteWP> listarTodosClienteWP() {
		return clienteWPRepositorio.findAll();
	}

	@Override
	public ClienteWP buscarClienteWPPorCustomerId(Long customerId) {
		return clienteWPRepositorio.findByCustomerId(customerId);
	}

	@Override
	public List<ClienteWP> migrarClienteWP() {
		return clienteWPRepositorio.migrarClienteWP();
	}

	@Override
	public List<ClienteWP> migrarClienteWPCategoria() {
		return clienteWPRepositorio.migrarClienteWPCategoria();
	}

	@Override
	public List<UsuarioWPDTO> listarUsuarioWP() {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
		//Date fecha = formato.parse("23/11/2015");
		List<UsuarioWPDTO> listaUsuarioWPDTO = new ArrayList<>();
		clienteWPRepositorio.listarUsuarioWP().forEach(objects -> {
			UsuarioWPDTO usuarioWPDTO = new UsuarioWPDTO();

			if (objects[0] == null || objects[0] == "") {
				usuarioWPDTO.setEmail(null);
			} else {
				usuarioWPDTO.setEmail(String.valueOf(objects[0]));
			}
			if (objects[1] == null || objects[1] == "") {
				usuarioWPDTO.setUsername(null);
			} else {
				usuarioWPDTO.setUsername(String.valueOf(objects[1]));
			}
			if (objects[2] != null || objects[2] != "") {
				try {
					usuarioWPDTO.setDateLastActive(formato.parse(String.valueOf(objects[2])));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (objects[3] != null || objects[3] != "") {
				usuarioWPDTO.setFirstName(String.valueOf(objects[3]));
			}
			if (objects[4] != null || objects[4] != "") {
				usuarioWPDTO.setLastName(String.valueOf(objects[4]));
			}

			listaUsuarioWPDTO.add(usuarioWPDTO);
		});

		return listaUsuarioWPDTO;
	}

	@Override
	public List<PedidoProducto> migrarClienteWPedidoProducto() {
		return clienteWPRepositorio.migrarClienteWPedidoProducto();
	}

	@Override
	public ClienteWP registrar(ClienteWP ClienteWP) {
		return clienteWPRepositorio.save(ClienteWP);
	}
	
}
