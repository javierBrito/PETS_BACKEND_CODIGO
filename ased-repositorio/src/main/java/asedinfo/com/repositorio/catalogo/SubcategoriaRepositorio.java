package asedinfo.com.repositorio.catalogo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import asedinfo.com.modelo.catalogo.Subcategoria;

@Repository
public interface SubcategoriaRepositorio extends JpaRepository<Subcategoria, Long> {

	List<Subcategoria> findByEstadoOrderByDenominacion(String estado);

	@Query(nativeQuery = false, value = "select subc from Subcategoria subc where subc.categoria.codigo = :codCategoria and subc.estado = 'A'")
	List<Subcategoria> listarSubcategoriaPorCategoria(@Param("codCategoria") Long codCategoria);

	@Query(nativeQuery = false, value = "select subc from Subcategoria subc where subc.denominacion = :denominacion and subc.categoria.codigo = :codCategoria and subc.estado = 'A'")
	Subcategoria buscarSubcategoriaPorDenominacion(String denominacion, Long codCategoria);

	Subcategoria findByCodigo(Long codigo);
}
