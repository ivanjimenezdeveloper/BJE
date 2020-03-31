package model.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Localizacion;
import model.entidad.dao.LocalizacionDAO;

@Stateless
@LocalBean
public class LocalizacionEJB {

	public Localizacion LocalizacionPorId(int id) {
		LocalizacionDAO l = new LocalizacionDAO();
		
		return l.LocalizacionPorId(id);
	}
}
