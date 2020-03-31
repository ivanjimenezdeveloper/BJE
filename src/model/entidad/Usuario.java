package model.entidad;

public class Usuario {
	private int id, rol, plantilla;
	private String correo, nombre, apellido, pass, observaciones;

	public Usuario(int id, int rol, int plantilla, String correo, String nombre, String apellido, String pass,
			String observaciones) {
		super();
		this.id = id;
		this.rol = rol;
		this.plantilla = plantilla;
		this.correo = correo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.pass = pass;
		this.observaciones = observaciones;
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

	public int getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(int plantilla) {
		this.plantilla = plantilla;
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
	
	

}
