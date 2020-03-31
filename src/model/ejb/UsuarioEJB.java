package model.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Usuario;
import model.entidad.dao.UsuarioDAO;

@Stateless
@LocalBean
public class UsuarioEJB {

	public Usuario UsuarioPorId(int id) {
		UsuarioDAO u = new UsuarioDAO();
		
		return u.UsuarioPorId(id);
	}
}
