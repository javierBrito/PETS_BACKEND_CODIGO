package asedinfo.com.modelo.DTO;

import asedinfo.com.modelo.seguridad.Sede;

public class LoginDTO {

	private Long codigoUsuario;
	private String identificacion;
	private String cedula;
	private String nombre;
	private boolean accesoConcedido;
	private String observacion;
	private String token;
	private String correo;
	private Sede sede;
	private String prefijoTelefonico;
	private String celular;

	public Long getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isAccesoConcedido() {
		return accesoConcedido;
	}

	public void setAccesoConcedido(boolean accesoConcedido) {
		this.accesoConcedido = accesoConcedido;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPrefijoTelefonico() {
		return prefijoTelefonico;
	}

	public void setPrefijoTelefonico(String prefijoTelefonico) {
		this.prefijoTelefonico = prefijoTelefonico;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
}
