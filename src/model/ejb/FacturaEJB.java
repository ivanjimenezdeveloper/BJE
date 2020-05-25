package model.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Factura;
import model.entidad.dao.FacturaDAO;

/**
 * EJB de factura
 * 
 * @author HIBAN
 *
 */
@Stateless
@LocalBean
public class FacturaEJB {

	
	public Factura facturaPorId(int id) {
		FacturaDAO f = new FacturaDAO();

		return f.FacturaPorId(id);

	}

	public ArrayList<Factura> getFacturas() {
		FacturaDAO f = new FacturaDAO();

		return f.getFactura();

	}
	
	public ArrayList<Factura> getFacturasPorRestaurante(int idRestaurante) {
		FacturaDAO f = new FacturaDAO();

		return f.getFacturasPorRestaurante(idRestaurante);
	}

}
