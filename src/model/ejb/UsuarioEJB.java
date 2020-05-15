package model.ejb;

import java.util.ArrayList;

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
	
	public Usuario existeUsuario(String correo, String pass) {
		UsuarioDAO u = new UsuarioDAO();
		
		return u.existeUsuario(correo, pass);
	}
	
	public boolean existeUsuario2(String correo, String pass) {
		UsuarioDAO u = new UsuarioDAO();
		
		return true;
	}
	
	public ArrayList<Usuario> busquedaUsuarios(){
		UsuarioDAO u = new UsuarioDAO();
		
		return u.busquedaUsuarios();
	}
	
	public void editaUsuario (Usuario user) {
		
		UsuarioDAO u = new UsuarioDAO();
		int activo = (user.isActivo())?1:0;
		u.editaUsuario(user.getNombre(), user.getApellido(), user.getRol(), user.getObservaciones(), user.getRestaurante(), activo, user.getId(), user.getCorreo());
		
	}
}
