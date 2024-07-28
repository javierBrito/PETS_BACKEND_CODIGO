package asedinfo.com.servicio.catalogo;

import java.util.List;

import asedinfo.com.modelo.catalogo.Subcategoria;

public interface SubcategoriaServicio {
	/**
	 * Permite listar Subcategoria
	 * 
	 * @param null
	 * @return lista Subcategoria
	 */
	List<Subcategoria> listarTodosSubcategoria();

	/**
	 * Permite listar Subcategoria Activo
	 * 
	 * @param estado
	 * @return lista Subcategoria
	 */
	List<Subcategoria> listarSubcategoriaActivo(String estado);

	/**
	 * Permite listar Subcategoria Activo
	 * 
	 * @param estado
	 * @return lista Subcategoria
	 */
	List<Subcategoria> listarSubcategoriaPorCategoria(Long codCategoria);

	/**
	 * Permite obtener Subcategoria
	 * 
	 * @param codigo
	 * @return Subcategoria
	 */
	Subcategoria buscarSubcategoriaPorCodigo(Long codigo);

	/**
	 * Permite obtener Subcategoria
	 * 
	 * @param denominacion
	 * @return Subcategoria
	 */
	Subcategoria buscarSubcategoriaPorDenominacion(String denominacion, Long codCategoria);

	/**
	 * Permite registrar Subcategoria
	 * 
	 * @param Subcategoria
	 * @return Subcategoria
	 */
	Subcategoria registrar(Subcategoria subcategoria);
	Subcategoria crearSubcategoria(Subcategoria subcategoria);
	
}
