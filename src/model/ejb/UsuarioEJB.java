package model.ejb;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.Mail;
import model.entidad.Usuario;
import model.entidad.dao.UsuarioDAO;



@Stateless
@LocalBean
public class UsuarioEJB {
	
	@EJB
	RolEJB rolEJB;
	
	@EJB
	RestauranteEJB restauranteEJB;
	
	@EJB
	UsuarioEJB usuarioEJB;

	public Usuario UsuarioPorId(int id) {
		UsuarioDAO u = new UsuarioDAO();

		return u.UsuarioPorId(id);
	}

	public Usuario existeUsuario(String correo, String pass) {
		UsuarioDAO u = new UsuarioDAO();

		return u.existeUsuario(correo, pass);
	}

	public int existeCorreo(String correo) {
		UsuarioDAO u = new UsuarioDAO();
		

		return u.existeCorreo(correo);
	}

	public ArrayList<Usuario> busquedaUsuarios() {
		UsuarioDAO u = new UsuarioDAO();

		return u.busquedaUsuarios();
	}

	public void editaUsuario(Usuario user) {

		UsuarioDAO u = new UsuarioDAO();
		int activo = (user.isActivo()) ? 1 : 0;
		
		if(user.isActivo()) {
			
			String restaurante = restauranteEJB.RestaurantePorId(user.getRestaurante()).getNombre();
			String rol = rolEJB.RolPorId(user.getRol()).getNombre();
			String cuerpo = "<p>Se ha editado su usuario en nuestra plataforma Better Job Environment con los siguientes datos<p>";
			cuerpo += "<p>Correo: " + user.getCorreo() + "</p>";
			cuerpo += "<p>Nombre: " + user.getNombre()+ "</p>";
			cuerpo += "<p>Apellido: " + user.getApellido()+ "</p>";
			cuerpo += "<p>Password: " + user.getPass()+ "</p>";
			cuerpo += "<p>Restaurante: " + restaurante+ "</p>";
			cuerpo += "<p>Rol: " + rol+ "</p>";
			cuerpo += "<br><p>Si ha recibido este correo de forma erronea le pedimos que lo borre."+ "</p>";
			cuerpo += "<p>Atentamente, el equipo de Better Job Environment"+ "</p>";
			
			enviaCorreo(cuerpo, "Edicion de usuario o activacion", user.getCorreo());


		}
		
		u.editaUsuario(user.getNombre(), user.getApellido(), user.getRol(), user.getObservaciones(),
				user.getRestaurante(), activo, user.getId(), user.getCorreo());

	}
	
	public void editaPerfil(Usuario user) {
		UsuarioDAO u = new UsuarioDAO();

		if(user.isActivo()) {
			
			String restaurante = restauranteEJB.RestaurantePorId(user.getRestaurante()).getNombre();
			String rol = rolEJB.RolPorId(user.getRol()).getNombre();
			String cuerpo = "<p>Se ha editado su usuario en nuestra plataforma Better Job Environment con los siguientes datos<p>";
			cuerpo += "<p>Correo: " + user.getCorreo() + "</p>";
			cuerpo += "<p>Nombre: " + user.getNombre()+ "</p>";
			cuerpo += "<p>Apellido: " + user.getApellido()+ "</p>";
			cuerpo += "<p>Password: " + user.getPass()+ "</p>";
			cuerpo += "<p>Restaurante: " + restaurante+ "</p>";
			cuerpo += "<p>Rol: " + rol+ "</p>";
			cuerpo += "<br><p>Si ha recibido este correo de forma erronea le pedimos que lo borre."+ "</p>";
			cuerpo += "<p>Atentamente, el equipo de Better Job Environment"+ "</p>";
			
			enviaCorreo(cuerpo, "Edicion de perfil", user.getCorreo());


		}
		
		u.editaPerfil(user.getNombre(), user.getApellido(), user.getId(), user.getCorreo(), user.getPass());
		
	}

	public void creaUsuario(Usuario user) {

		UsuarioDAO u = new UsuarioDAO();
		int activo = (user.isActivo()) ? 1 : 0;
		user.setPass(creaPassword());
		
		if(user.isActivo()) {
			
			String restaurante = restauranteEJB.RestaurantePorId(user.getRestaurante()).getNombre();
			String rol = rolEJB.RolPorId(user.getRol()).getNombre();
			String cuerpo = "<p>Se ha creado un usuario en nuestra plataforma Better Job Environment con los siguientes datos<p>";
			cuerpo += "<p>Correo: " + user.getCorreo() + "</p>";
			cuerpo += "<p>Nombre: " + user.getNombre()+ "</p>";
			cuerpo += "<p>Apellido: " + user.getApellido()+ "</p>";
			cuerpo += "<p>Password: " + user.getPass()+ "</p>";
			cuerpo += "<p>Restaurante: " + restaurante+ "</p>";
			cuerpo += "<p>Rol: " + rol+ "</p>";
			cuerpo += "<br><p>Si ha recibido este correo de forma erronea le pedimos que lo borre."+ "</p>";
			cuerpo += "<p>Atentamente, el equipo de Better Job Environment"+ "</p>";
			
			enviaCorreo(cuerpo, "Bienvenido a BJE", user.getCorreo());


		}
		u.creaUsuario(user.getNombre(), user.getApellido(), user.getRol(), user.getObservaciones(),
				user.getRestaurante(), activo, user.getCorreo(), user.getPass());	

	}
	
	public void eliminaUsuario(int id) {
		
		UsuarioDAO u = new UsuarioDAO();
		
		u.eliminaUsuario(id);
	}
	
	public void resetPassword(int id) {
		UsuarioDAO u = new UsuarioDAO();
		String pass = creaPassword();
		Usuario user = usuarioEJB.UsuarioPorId(id);
		if(user.isActivo()) {
			
			String cuerpo = "<p>Se ha reseteado su contraseña <p>";
			cuerpo += "<p>Correo: " + user.getCorreo() + "</p>";
			cuerpo += "<p>Password: " + user.getPass()+ "</p>";
			cuerpo += "<br><p>Si ha recibido este correo de forma erronea le pedimos que lo borre."+ "</p>";
			cuerpo += "<p>Atentamente, el equipo de Better Job Environment"+ "</p>";
			
			enviaCorreo(cuerpo, "Cambio de contraseña BJE", user.getCorreo());


		}
		
		u.cambiaPass(id, pass);
	}

	private String creaPassword() {

		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 8;
		Random random = new Random();

		String pass = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return pass;
	}
	
	private void enviaCorreo(String cuerpo, String titulo, String mail) {
		String remitente = "basiliscoxalligator@gmail.com";
		Mail m = new Mail("smtp.gmail.com", 587, "basiliscoxalligator@gmail.com", "Ageofempires2");
		m.sendMail(mail, remitente, titulo, cuerpo);
		
	}

}
