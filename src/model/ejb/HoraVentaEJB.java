package model.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.HoraVenta;
import model.entidad.dao.HoraVentaDAO;

/**
 * EJB de HoraVenta
 * 
 * @author HIBAN
 *
 */
@Stateless
@LocalBean
public class HoraVentaEJB {

	public HoraVenta HoraVentaPorId(int id, int hora) {
		HoraVentaDAO f = new HoraVentaDAO();

		return f.HoraVentaPorId(id, hora);

	}

	public ArrayList<HoraVenta> getFacturas() {
		HoraVentaDAO f = new HoraVentaDAO();

		return f.getVentas();

	}

	public ArrayList<HoraVenta> getVentasPorRestauranteFecha(int idRestaurante, String fecha) {
		HoraVentaDAO f = new HoraVentaDAO();

		return f.getVentasPorRestauranteFecha(idRestaurante, fecha);

	}

	public HoraVenta existeVenta(int idFactura, int idRestaurante, int hora) {
		HoraVentaDAO f = new HoraVentaDAO();

		return f.existeVenta(idFactura, idRestaurante, hora);

	}

	public void insertaHoraVenta(HoraVenta h, int idRestaurante) {
		HoraVentaDAO f = new HoraVentaDAO();

		f.insertaHoraVenta(h.getVenta(), h.getIdFactura(), idRestaurante, h.getHora());

	}

	public void editaHoraVenta(HoraVenta h, int idRestaurante) {
		HoraVentaDAO f = new HoraVentaDAO();

		f.editaHoraVenta(h.getVenta(), h.getIdFactura(), idRestaurante, h.getHora());

	}

}
