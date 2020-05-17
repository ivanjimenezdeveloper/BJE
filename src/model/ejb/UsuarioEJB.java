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

	public ArrayList<Usuario> busquedaUsuarios() {
		UsuarioDAO u = new UsuarioDAO();

		return u.busquedaUsuarios();
	}

	public void editaUsuario(Usuario user) {

		UsuarioDAO u = new UsuarioDAO();
		int activo = (user.isActivo()) ? 1 : 0;
		u.editaUsuario(user.getNombre(), user.getApellido(), user.getRol(), user.getObservaciones(),
				user.getRestaurante(), activo, user.getId(), user.getCorreo());

	}
	
	public void editaPerfil(Usuario user) {
		UsuarioDAO u = new UsuarioDAO();

		u.editaPerfil(user.getNombre(), user.getApellido(), user.getId(), user.getCorreo(), user.getPass());
	}

	public void creaUsuario(Usuario user) {

		UsuarioDAO u = new UsuarioDAO();
		int activo = (user.isActivo()) ? 1 : 0;
		user.setPass(creaPassword());
		
		if(user.isActivo()) {
			
			String restaurante = restauranteEJB.RestaurantePorId(user.getRestaurante()).getNombre();
			String rol = rolEJB.RolPorId(user.getRol()).getNombre();
			String cuerpo = "Se ha creado un usuario en nuestra plataforma Better Job Environment con los siguientes datos \n";
			cuerpo += "Correo: " + user.getCorreo();
			cuerpo += "\nNombre: " + user.getNombre();
			cuerpo += "\nApellido: " + user.getApellido();
			cuerpo += "\nPassword: " + user.getPass();
			cuerpo += "\nRestaurante: " + restaurante;
			cuerpo += "\nRol: " + rol;
			cuerpo += "\n\nSi ha recibido este correo de forma erronea le pedimos que lo borre.";
			cuerpo += "\nAtentamente, Better Job Environment";
			
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
