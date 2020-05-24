package model.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Localizacion;
import model.entidad.dao.LocalizacionDAO;

/**
 * EJB de localizacion
 * @author HIBAN
 *
 */
@Stateless
@LocalBean
public class LocalizacionEJB {

	/**
	 * Busca una localizacion segun la id
	 * @param id id de por la que filtrar
	 * @return Objeto localizacion
	 */
	public Localizacion LocalizacionPorId(int id) {
		LocalizacionDAO l = new LocalizacionDAO();
		
		return l.LocalizacionPorId(id);
	}
}
