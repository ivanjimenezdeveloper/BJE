package model.entidad;

public class Horario {
	private int id, semana_1, semana_2, semana_3, semana_4, semana_5;

	public Horario() {
		super();
	}

	public Horario(int id, int semana_1, int semana_2, int semana_3, int semana_4, int semana_5) {
		super();
		this.id = id;
		this.semana_1 = semana_1;
		this.semana_2 = semana_2;
		this.semana_3 = semana_3;
		this.semana_4 = semana_4;
		this.semana_5 = semana_5;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSemana_1() {
		return semana_1;
	}

	public void setSemana_1(int semana_1) {
		this.semana_1 = semana_1;
	}

	public int getSemana_2() {
		return semana_2;
	}

	public void setSemana_2(int semana_2) {
		this.semana_2 = semana_2;
	}

	public int getSemana_3() {
		return semana_3;
	}

	public void setSemana_3(int semana_3) {
		this.semana_3 = semana_3;
	}

	public int getSemana_4() {
		return semana_4;
	}

	public void setSemana_4(int semana_4) {
		this.semana_4 = semana_4;
	}

	public int getSemana_5() {
		return semana_5;
	}

	public void setSemana_5(int semana_5) {
		this.semana_5 = semana_5;
	}
	
	
}
