package model.entidad;

/**
 * Entidad de usuario
 * @author HIBAN
 *
 */
public class Usuario {
	private int id, rol, restaurante;
	private String correo, nombre, apellido, pass, observaciones;
	private boolean activo;
	public Usuario(int id, int rol, int plantilla, String correo, String nombre, String apellido, String pass,
			String observaciones, boolean activo, int restaurante) {
		super();
		this.id = id;
		this.rol = rol;
		this.correo = correo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.pass = pass;
		this.observaciones = observaciones;
		this.activo = activo;
		this.restaurante = restaurante;
	}

	public Usuario() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(int restaurante) {
		this.restaurante = restaurante;
	}
	
	
	
	
	

}
