package model.entidad;

public class Factura {
	private int id;
	private String fecha;
	
	public Factura(int id, String fecha) {
		super();
		this.id = id;
		this.fecha = fecha;
	}

	public Factura() {
		super();
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
	
	
	
}
