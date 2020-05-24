package model.entidad;

/**
 * Entidad localizacion
 * @author HIBAN
 *
 */
public class Localizacion {
	private int id;
	private String nombre;
	
	//Constructores
	public Localizacion() {
		super();
	}

	public Localizacion(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	//Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
