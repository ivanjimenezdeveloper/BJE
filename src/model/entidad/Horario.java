package model.entidad;

public class Horario {
	private int id, dia;

	public Horario() {
		super();
	}

	public Horario(int id, int dia) {
		super();
		this.id = id;
		this.dia = dia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}


	
}
