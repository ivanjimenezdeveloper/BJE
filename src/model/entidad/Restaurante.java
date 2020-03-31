package model.entidad;

public class Restaurante {
	private int id, gerente, plantilla, localizacion;

	public Restaurante() {
		super();
	}

	public Restaurante(int id, int gerente, int plantilla, int localizacion) {
		super();
		this.id = id;
		this.gerente = gerente;
		this.plantilla = plantilla;
		this.localizacion = localizacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGerente() {
		return gerente;
	}

	public void setGerente(int gerente) {
		this.gerente = gerente;
	}

	public int getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(int plantilla) {
		this.plantilla = plantilla;
	}

	public int getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(int localizacion) {
		this.localizacion = localizacion;
	}
	
	

}
