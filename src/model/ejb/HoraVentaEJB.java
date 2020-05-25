package model.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Factura;
import model.entidad.HoraVenta;
import model.entidad.dao.FacturaDAO;
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

		return f.HoraVentaPorId(id,hora);

	}

	public ArrayList<HoraVenta> getFacturas() {
		HoraVentaDAO f = new HoraVentaDAO();

		return f.getVentas();

	}
}
