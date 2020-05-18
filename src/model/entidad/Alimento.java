package model.entidad;

public class Alimento {
	private int id, categoria, tiempo;
	private String nombre;

	public Alimento() {
		super();
	}

	public Alimento(int id, int categoria, int tiempo, String nombre) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.tiempo = tiempo;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setIdCategoria(int categoria) {
		this.categoria = categoria;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTiempoHMS() {
		
		String tiempoHMS = "";
		
		int horas = 0;
		
		int min = tiempo;
		
		horas = min / 60;
		
		min = min - (horas*60);
		
		return tiempoHMS + horas + ":"+ min + ":00";
		
	}



}
