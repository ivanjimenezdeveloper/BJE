package model.entidad;

public class Restaurante {
	private int id, localizacion;

	public Restaurante() {
		super();
	}

	public Restaurante(int id, int localizacion) {
		super();
		this.id = id;

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
	
	

}
