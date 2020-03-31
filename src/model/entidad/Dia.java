package model.entidad;

public class Dia {

	private int id;
	private String fecha, entrada_1, salida_1, entrada_2, salida_2;
	
	public Dia() {
		super();
	}

	public Dia(int id, String fecha, String entrada_1, String salida_1, String entrada_2, String salida_2) {
		super();
		this.id = id;
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
	
	
	
	
	
}
