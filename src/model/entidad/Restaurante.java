package model.entidad;

/**
 * Entidad restaurante
 * @author HIBAN
 *
 */
public class Restaurante {
	private int id, localizacion;
	private String nombre;

	public Restaurante() {
		super();
	}

	public Restaurante(int id, int localizacion, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;

		this.localizacion = localizacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(int localizacion) {
		this.localizacion = localizacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
