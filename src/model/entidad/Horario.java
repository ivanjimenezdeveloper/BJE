package model.entidad;

public class Horario {
	private int id, anyo, mes;
	private boolean activo;
	
	public Horario() {
		super();
	}
 
	public Horario(int id, int anyo, int mes, boolean activo) {
		super();
		this.id = id;
		this.anyo = anyo;
		this.mes = mes;
		this.activo = activo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
	
}
