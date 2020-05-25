package model.entidad;

public class HoraVenta {

	private int  hora, idFactura;
	private Double venta;

	public HoraVenta() {
		super();
	}

	public HoraVenta(int hora, int idFactura, Double venta) {
		super();
		this.hora = hora;
		this.idFactura = idFactura;
		this.venta = venta;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public Double getVenta() {
		return venta;
	}

	public void setVenta(Double venta) {
		this.venta = venta;
	}


	
	
}
