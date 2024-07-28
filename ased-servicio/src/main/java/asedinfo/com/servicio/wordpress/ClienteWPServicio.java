package asedinfo.com.servicio.wordpress;

import java.util.List;

import asedinfo.com.modelo.DTO.UsuarioWPDTO;
import asedinfo.com.modelo.wordpress.ClienteWP;
import asedinfo.com.modelo.wordpress.PedidoProducto;

public interface ClienteWPServicio {
	/**
	 * Permite listar ClienteWP
	 * 
	 * @param null
	 * @return lista ClienteWP
	 */
	List<ClienteWP> listarTodosClienteWP();

	/**
	 * Permite obtener ClienteWP
	 * 
	 * @param codigo
	 * @return ClienteWP
	 */
	ClienteWP buscarClienteWPPorCustomerId(Long customerId);

	/**
	 * Permite obtener lista ClienteWP
	 * 
	 * @param codPersona
	 * @return listaClienteWP
	 */
	List<ClienteWP> migrarClienteWP();

	/**
	 * Permite obtener lista ClienteWP
	 * 
	 * @param codPersona
	 * @return listaClienteWP
	 */
	List<ClienteWP> migrarClienteWPCategoria();

	List<UsuarioWPDTO> listarUsuarioWP();

	/**
	 * Permite obtener lista ClienteWP
	 * 
	 * @param codPersona
	 * @return listaClienteWP
	 */
	List<PedidoProducto> migrarClienteWPedidoProducto();

	/**
	 * Permite registrar ClienteWP
	 * 
	 * @param ClienteWP
	 * @return ClienteWP
	 */
	ClienteWP registrar(ClienteWP ClienteWP);

}
