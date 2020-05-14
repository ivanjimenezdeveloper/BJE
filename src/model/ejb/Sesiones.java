package model.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

import model.entidad.Usuario;

/**
 * Clase que gestiona la informacion de un usuario a traves de la sesion
 * 
 * @author horabaixa
 *
 */
@Stateless
@LocalBean
public class Sesiones {

	/**
	 * Obtiene un usuario de la sesion
	 * 
	 * @param sesion Sesion donde buscamos al usuario
	 * @return Usuario que esta en la sesion o null si no hay nada
	 */
	public Usuario usuarioLogeado(HttpSession sesion) {
		Usuario user = null;

		if (sesion != null) {
			user = (Usuario) sesion.getAttribute("user");
		}

		return user;
	}

	
	/**
	 * Añade un usuario a la sesion
	 * @param sesion Sesion donde añadir el usuario
	 * @param user Usuario a añadir a la sesion
	 */
	public void loginUsuario(HttpSession sesion, Usuario user) {
		
		if (sesion != null) {
			sesion.setAttribute("user", user);
		}
	}
	
	
	/**
	 * Cierra la sesion
	 * @param sesion Sesion a cerrar
	 */
	public void logoutUsuario(HttpSession sesion) {
		if (sesion != null) {
			
			sesion.invalidate();
			
		}
	}
	

}
