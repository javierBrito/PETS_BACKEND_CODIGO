package asedinfo.com.servicio.impl.catalogo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asedinfo.com.modelo.catalogo.Modulo;
import asedinfo.com.modelo.catalogo.Producto;
import asedinfo.com.modelo.catalogo.Transaccion;
import asedinfo.com.modelo.venta.Cliente;
import asedinfo.com.repositorio.catalogo.TransaccionRepositorio;
import asedinfo.com.servicio.catalogo.ModuloServicio;
import asedinfo.com.servicio.catalogo.ProductoServicio;
import asedinfo.com.servicio.catalogo.TransaccionServicio;
import asedinfo.com.servicio.venta.ClienteServicio;
import asedinfo.com.venta.resources.Constantes;

@Service
public class TransaccionServicioImpl implements TransaccionServicio {

	@Autowired
	private TransaccionRepositorio transaccionRepositorio;
	@Autowired
	private ModuloServicio moduloServicio;
	@Autowired
	private ClienteServicio clienteServicio;
	@Autowired
	private ProductoServicio productoServicio;

	private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public List<Transaccion> listarTodosTransaccion() {
		return transaccionRepositorio.findAll();
	}

	@Override
	public List<Transaccion> listarTransaccionActivo(String nemonicoModulo) {
		return transaccionRepositorio.listarTransaccionActivo(nemonicoModulo);
	}

	@Override
	public Transaccion buscarTransaccionPorCodigo(Long codigo) {
		return transaccionRepositorio.findByCodigo(codigo);
	}

	@Override
	public List<Transaccion> listarTransaccionPorDescripcion(String descripcion) {
		return transaccionRepositorio.listarTransaccionPorDescripcion(descripcion);
	}

	@Override
	public List<Transaccion> listarTransaccionPorClaveCuenta(String claveCuenta) {
		return transaccionRepositorio.listarTransaccionPorClaveCuenta(claveCuenta);
	}

	@Override
	public List<Transaccion> listarTransaccionPorCliente(Long codCliente) {
		return transaccionRepositorio.listarTransaccionPorCliente(codCliente);
	}

	@Override
	public List<Transaccion> listarTransaccionPorProducto(Long codProducto) {
		return transaccionRepositorio.listarTransaccionPorProducto(codProducto);
	}

	@Override
	public List<Transaccion> listarTransaccionPorClienteYProducto(Long codCliente, Long codProducto) {
		return transaccionRepositorio.listarTransaccionPorClienteYProducto(codCliente, codProducto);
	}

	@Override
	public List<Transaccion> buscarPorDescripcion(String descripcion) {
		return transaccionRepositorio.findByDescripcionAndEstado(descripcion, Constantes.REGISTRO_ACTIVO);
	}

	@Override
	public List<Transaccion> listarTransaccionPorRangoFechas(String fechaInicio, String fechaFin) {
		try {
			// Transformar fecha tipo String a fecha tipo Date
			Date fechaInicioDate = formatoFecha.parse(fechaInicio);
			Date fechaFinDate = formatoFecha.parse(fechaFin);
			return transaccionRepositorio.listarTransaccionPorRangoFechas(fechaInicioDate, fechaFinDate);
		} catch (ParseException e) {
			System.out.println("Problemas en el servicio, error = "+e.getMessage());
			return null;
		}
	}

	@Override
	public List<Transaccion> listarTransaccionACaducarse(int numDias) {
		return transaccionRepositorio.listarTransaccionACaducarse(numDias);
	}

	@Override
	public Transaccion registrar(Transaccion transaccion) {
		if (transaccion.getCodModulo() != 0) {
			Modulo modulo = new Modulo();
			modulo = moduloServicio.buscarModuloPorCodigo(transaccion.getCodModulo());
			if (modulo != null) {
				transaccion.setModulo(modulo);
			}
		}
		if (transaccion.getCodCliente() != 0) {
			Cliente cliente = new Cliente();
			cliente = clienteServicio.buscarClientePorCodigo(transaccion.getCodCliente());
			if (cliente != null) {
				transaccion.setCliente(cliente);
			}
		}
		if (transaccion.getCodProducto() != 0) {
			Producto producto = new Producto();
			producto = productoServicio.buscarProductoPorCodigo(transaccion.getCodProducto());
			if (producto != null) {
				transaccion.setProducto(producto);
			}
		}

		return transaccionRepositorio.save(transaccion);
	}

	@Override
	public Transaccion crearTransaccion(Transaccion transaccion) {
		return transaccionRepositorio.save(transaccion);
	}
	
}
