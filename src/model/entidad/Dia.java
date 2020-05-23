package model.entidad;

public class Dia {

	private int id, usuario, horario;
	private String fecha, entrada_1, salida_1, entrada_2, salida_2;
	
	public Dia() {
		super();
	}


	public Dia(int id, int usuario, int horario, String fecha, String entrada_1, String salida_1, String entrada_2,
			String salida_2) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.horario = horario;
		this.fecha = fecha;
		this.entrada_1 = entrada_1;
		this.salida_1 = salida_1;
		this.entrada_2 = entrada_2;
		this.salida_2 = salida_2;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEntrada_1() {
		return entrada_1;
	}

	public void setEntrada_1(String entrada_1) {
		this.entrada_1 = entrada_1;
	}

	public String getSalida_1() {
		return salida_1;
	}

	public void setSalida_1(String salida_1) {
		this.salida_1 = salida_1;
	}

	public String getEntrada_2() {
		return entrada_2;
	}

	public void setEntrada_2(String entrada_2) {
		this.entrada_2 = entrada_2;
	}

	public String getSalida_2() {
		return salida_2;
	}

	public void setSalida_2(String salida_2) {
		this.salida_2 = salida_2;
	}


	public int getHorario() {
		return horario;
	}


	public void setHorario(int horario) {
		this.horario = horario;
	}


	
	
	
	
}
