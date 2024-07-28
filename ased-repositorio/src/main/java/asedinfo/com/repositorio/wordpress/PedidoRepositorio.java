package asedinfo.com.repositorio.wordpress;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import asedinfo.com.modelo.wordpress.Pedido;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {

	List<Pedido> findByStatusOrderByOrderId(String status);

	@Query(nativeQuery = false, value = "select reg from Pedido reg where reg.status = 'wc-completed'")
	List<Pedido> listarPedidoPorCliente(@Param("customerId") Long customerId);

	Pedido findByOrderId(Long orderId);
}
