package model.entidad;

public class HoraVenta {

	private int venta, hora, idFactura;

	public HoraVenta() {
		super();
	}

	public HoraVenta(int venta, int hora, int idFactura) {
		super();
		this.venta = venta;
		this.hora = hora;
		this.idFactura = idFactura;
	}

	public int getVenta() {
		return venta;
	}

	public void setVenta(int venta) {
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
	
	
	
}
