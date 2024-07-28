package asedinfo.com.modelo.catalogo;
// default package


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import asedinfo.com.modelo.seguridad.Usuario;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "cat_persona", uniqueConstraints = { @UniqueConstraint(columnNames = "celular"),
		@UniqueConstraint(columnNames = "identificacion") })
public class Persona implements java.io.Serializable {
	private static final long serialVersionUID = -8622196233866959952L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo", unique = true, nullable = false, precision = 10, scale = 0)
	private Long codigo;

	@Column(name = "identificacion", unique = true, length = 10)
	private String identificacion;
	
	@Column(name = "cedula", unique = true, length = 15)
	private String cedula;
	
	@Column(name = "nombres", length = 150)
	private String nombres;
	
	@Column(name = "apellidos", length = 150)
	private String apellidos;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_nacimiento", length = 23)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", locale = "es-EC", timezone = "America/Lima")
	private Date fechaNacimiento;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "celular", unique = true, nullable = false, length = 15)
	private String celular;
	
	@Column(name = "correo", nullable = false, length = 150)
	private String correo;
	
	@Column(name = "estado", nullable = false, length = 1)
	private String estado;

	@Column(name = "prefijo_telefonico")
	private String prefijoTelefonico;

	@JsonIgnore
	@OneToMany(mappedBy = "persona")
	private List<Usuario> listaUsuario = new ArrayList<Usuario>(0);

	public Persona() {
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPrefijoTelefonico() {
		return prefijoTelefonico;
	}

	public void setPrefijoTelefonico(String prefijoTelefonico) {
		this.prefijoTelefonico = prefijoTelefonico;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
}
