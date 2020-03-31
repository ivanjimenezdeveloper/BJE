package model.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Rol;
import model.entidad.dao.RolDAO;

@Stateless
@LocalBean
public class RolEJB {

	public Rol RolPorId(int id) {
		RolDAO r = new RolDAO();
		
		return r.RolPorId(id);
	}
}
