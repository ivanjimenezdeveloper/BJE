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

	/**
	 * Muestra una venta segun su id de factura y su hora
	 * @param id id de factura
	 * @param hora hora de la venta
	 * @return Objeto HoraVenta
	 */
	public HoraVenta HoraVentaPorId(int id, int hora) {
		HoraVentaDAO f = new HoraVentaDAO();

		return f.HoraVentaPorId(id, hora);

	}

	/**
	 * Devuelve todas las ventas
	 * @return Arraylist de HoraVenta
	 */
	public ArrayList<HoraVenta> getFacturas() {
		HoraVentaDAO f = new HoraVentaDAO();

		return f.getVentas();

	}
	
	
	/**
	 * Devuelve todas las ventas de un restaurante segun la fecha
	 * @param idRestaurante id del restaurante
	 * @param fecha fecha por la que filtrar
	 * @return Arraylist de HoraVenta
	 */
	public ArrayList<HoraVenta> getVentasPorRestauranteFecha(int idRestaurante, String fecha) {
		HoraVentaDAO f = new HoraVentaDAO();

		return f.getVentasPorRestauranteFecha(idRestaurante, fecha);

	}

	/**
	 * Comprueba que exista una venta
	 * @param idFactura id de la factura
	 * @param idRestaurante id del restaurante
	 * @param hora hora por la que filtrar
	 * @return objeto HoraVenta
	 */
	public HoraVenta existeVenta(int idFactura, int idRestaurante, int hora) {
		HoraVentaDAO f = new HoraVentaDAO();

		return f.existeVenta(idFactura, idRestaurante, hora);

	}

	/**
	 * Inserta una venta
	 * @param h objeto HoraVenta que introducir
	 * @param idRestaurante id del restaurante
	 */
	public void insertaHoraVenta(HoraVenta h, int idRestaurante) {
		HoraVentaDAO f = new HoraVentaDAO();

		f.insertaHoraVenta(h.getVenta(), h.getIdFactura(), idRestaurante, h.getHora());

	}

	/**
	 * Edita una venta
	 * @param h objeto que editar
	 * @param idRestaurante id del restaurante
	 */
	public void editaHoraVenta(HoraVenta h, int idRestaurante) {
		HoraVentaDAO f = new HoraVentaDAO();

		f.editaHoraVenta(h.getVenta(), h.getIdFactura(), idRestaurante, h.getHora());

	}

}
