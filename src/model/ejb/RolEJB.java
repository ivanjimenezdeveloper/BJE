package model.ejb;

import java.util.ArrayList;

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
	
	public ArrayList<Rol> busquedaRoles() {
		RolDAO r = new RolDAO();
		
		return r.busquedaRoles();
	}
}
