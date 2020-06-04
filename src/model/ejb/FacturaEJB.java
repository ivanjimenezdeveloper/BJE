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

	/**
	 * Devuelve una factura segun su id
	 * 
	 * @param id id de la factura
	 * @return Objeto facura
	 */
	public Factura facturaPorId(int id) {
		FacturaDAO f = new FacturaDAO();

		return f.FacturaPorId(id);

	}

	/**
	 * Devuelve todas las facturas
	 * 
	 * @return arraylist de facturas
	 */
	public ArrayList<Factura> getFacturas() {
		FacturaDAO f = new FacturaDAO();

		return f.getFactura();

	}

	/**
	 * Devuelve facturas por restaurante
	 * 
	 * @param restaurante id de restaurante
	 * @return arraylist de facturas
	 */
	public ArrayList<Factura> getFacturasPorRestaurante(int idRestaurante) {
		FacturaDAO f = new FacturaDAO();

		return f.getFacturasPorRestaurante(idRestaurante);
	}

	/**
	 * Crea una factura
	 * @param fecha fecha de la factura
	 */
	public void insertaFecha(String fecha) {
		FacturaDAO f = new FacturaDAO();

		f.creaFactura(fecha);

	}
	
	/**
	 * Busca una factura por su fecha
	 * @param fecha fecha de la factura
	 * @return Objeto factura
	 */
	public Factura facturaIdFecha(String fecha) {
		FacturaDAO f = new FacturaDAO();

		return f.facturaIdFecha(fecha);

	}
	
	

}
