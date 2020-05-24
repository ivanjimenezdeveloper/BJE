package model.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Rol;
import model.entidad.dao.RolDAO;

/**
 * EJB de ROL
 * @author HIBAN
 *
 */
@Stateless
@LocalBean
public class RolEJB {

	/**
	 * Busca un rol por su id
	 * @param id id por la que filtrar
	 * @return Objeto rol
	 */
	public Rol RolPorId(int id) {
		RolDAO r = new RolDAO();
		
		return r.RolPorId(id);
	}
	
	/**
	 * Busqueda de roles
	 * @return Arraylist de roles
	 */
	public ArrayList<Rol> busquedaRoles() {
		RolDAO r = new RolDAO();
		
		return r.busquedaRoles();
	}
}
