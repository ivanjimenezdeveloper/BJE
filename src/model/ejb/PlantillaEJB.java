package model.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Plantilla;
import model.entidad.dao.PlantillaDAO;

@Stateless
@LocalBean
public class PlantillaEJB {

	public Plantilla PlantillaPorId(int id) {
		
		PlantillaDAO p = new PlantillaDAO();
		
		return p.PlantillaPorId(id);
		
	}
}
