package asedinfo.com.modelo.seguridad;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Aplicacion generated by hbm2java
 */
@Entity
@Table(name = "seg_aplicacion", uniqueConstraints = @UniqueConstraint(columnNames = "prefijo"))
public class Aplicacion implements java.io.Serializable {
	private static final long serialVersionUID = 8685080160184303374L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo", unique = true, nullable = false)
	private Long codigo;

	@Column(name = "prefijo", unique = true, nullable = false, length = 3)
	private String prefijo;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "descripcion", length = 500)
	private String descripcion;

	@Column(name = "tipo", nullable = false, length = 3)
	private String tipo;

	@Column(name = "url", length = 150)
	private String url;

	@Column(name = "estado", nullable = false, length = 1)
	private String estado;

	@JsonIgnore
	@Column(name = "est_visualizacion_rpr", nullable = false, length = 1)
	private String estVisualizacionRpr;
	@JsonIgnore
	@Column(name = "est_visualizacion_rec", nullable = false, length = 1)
	private String estVisualizacionRec;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "aplicacion")
	private List<Recurso> recursos = new ArrayList<Recurso>(0);

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "aplicacion")
	private List<RolAplicacion> rolAplicacions = new ArrayList<RolAplicacion>(0);

	@Transient
	private boolean seleccionada;

	public Aplicacion() {
	}

	public Aplicacion(Long codigo, String prefijo, String nombre, String tipo, String estado) {
		this.codigo = codigo;
		this.prefijo = prefijo;
		this.nombre = nombre;
		this.tipo = tipo;
		this.estado = estado;
	}

	public Aplicacion(Long codigo, String prefijo, String nombre, String descripcion, String tipo, String url,
			String estado, List<Recurso> recursos, List<RolAplicacion> rolAplicacions) {
		this.codigo = codigo;
		this.prefijo = prefijo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.url = url;
		this.estado = estado;
		this.recursos = recursos;
		this.rolAplicacions = rolAplicacions;
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getPrefijo() {
		return this.prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Recurso> getRecursos() {
		return this.recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

	public List<RolAplicacion> getRolAplicacions() {
		return this.rolAplicacions;
	}

	public void setRolAplicacions(List<RolAplicacion> rolAplicacions) {
		this.rolAplicacions = rolAplicacions;
	}

	public boolean isSeleccionada() {
		return seleccionada;
	}

	public void setSeleccionada(boolean seleccionada) {
		this.seleccionada = seleccionada;
	}

	public String getEstVisualizacionRpr() {
		return estVisualizacionRpr;
	}

	public void setEstVisualizacionRpr(String estVisualizacionRpr) {
		this.estVisualizacionRpr = estVisualizacionRpr;
	}

	public String getEstVisualizacionRec() {
		return estVisualizacionRec;
	}

	public void setEstVisualizacionRec(String estVisualizacionRec) {
		this.estVisualizacionRec = estVisualizacionRec;
	}

}
