package model.ejb;

import java.util.ArrayList;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.Mail;
import model.entidad.Usuario;
import model.entidad.dao.UsuarioDAO;

/**
 * EJB de usuarios
 * 
 * @author HIBAN
 *
 */
@Stateless
@LocalBean
public class UsuarioEJB {

	/**
	 * EJB para trabajar con roles
	 */
	@EJB
	RolEJB rolEJB;

	/**
	 * EJB para trabajar con restaurantes
	 */
	@EJB
	RestauranteEJB restauranteEJB;

	/**
	 * EJB para trabajar con usuarios
	 */
	@EJB
	UsuarioEJB usuarioEJB;

	/**
	 * Devuelve un usuario segun su id
	 * 
	 * @param id id del usuario
	 * @return Objeto usuario
	 */
	public Usuario UsuarioPorId(int id) {
		UsuarioDAO u = new UsuarioDAO();

		return u.UsuarioPorId(id);
	}

	/**
	 * Busca un usuario con ese correo y esa contraseña
	 * 
	 * @param correo correo del usuario
	 * @param pass   contraseña del usuario
	 * @return Usuario que coincide con los parametros
	 */
	public Usuario existeUsuario(String correo, String pass) {
		UsuarioDAO u = new UsuarioDAO();

		return u.existeUsuario(correo, pass);
	}

	/**
	 * Busca si existe un correo en la base de datos
	 * 
	 * @param correo correo que buscar
	 * @return un 1 o mas si encuentra algo, 0 si no encuentra nada
	 */
	public int existeCorreo(String correo) {
		UsuarioDAO u = new UsuarioDAO();

		return u.existeCorreo(correo);
	}

	/**
	 * Busca todos los usuarios de un restaurante
	 * 
	 * @param idRestaurante id del restaurante
	 * @return Arraylist con usuarios
	 */
	public ArrayList<Usuario> busquedaUsuarios(int idRestaurante) {
		UsuarioDAO u = new UsuarioDAO();

		return u.busquedaUsuarios(idRestaurante);
	}

	/**
	 * Edita un usuario y envia un correo al usuario correspondiente
	 * 
	 * @param user usuario al que editar
	 */
	public void editaUsuario(Usuario user) {

		UsuarioDAO u = new UsuarioDAO();
		int activo = (user.isActivo()) ? 1 : 0;

		// si el usuario esta activo envia el correo
		if (user.isActivo()) {

			String restaurante = restauranteEJB.RestaurantePorId(user.getRestaurante()).getNombre();
			String rol = rolEJB.RolPorId(user.getRol()).getNombre();
			String cuerpo = "<p>Se ha editado su usuario en nuestra plataforma Better Job Environment con los siguientes datos<p>";
			cuerpo += "<p>Correo: " + user.getCorreo() + "</p>";
			cuerpo += "<p>Nombre: " + user.getNombre() + "</p>";
			cuerpo += "<p>Apellido: " + user.getApellido() + "</p>";
			cuerpo += "<p>Password: " + user.getPass() + "</p>";
			cuerpo += "<p>Restaurante: " + restaurante + "</p>";
			cuerpo += "<p>Rol: " + rol + "</p>";
			cuerpo += "<br><p>Si ha recibido este correo de forma erronea le pedimos que lo borre." + "</p>";
			cuerpo += "<p>Atentamente, el equipo de Better Job Environment" + "</p>";

			enviaCorreo(cuerpo, "Edicion de usuario o activacion", user.getCorreo());

		}

		// edita el usuario
		u.editaUsuario(user.getNombre(), user.getApellido(), user.getRol(), user.getObservaciones(),
				user.getRestaurante(), activo, user.getId(), user.getCorreo());

	}

	/**
	 * edita el perfin de un usuario
	 * 
	 * @param user usuario al que editar el perfil
	 */
	public void editaPerfil(Usuario user) {
		UsuarioDAO u = new UsuarioDAO();

		// si el usuario esta activo envia el correo
		if (user.isActivo()) {

			String restaurante = restauranteEJB.RestaurantePorId(user.getRestaurante()).getNombre();
			String rol = rolEJB.RolPorId(user.getRol()).getNombre();
			String cuerpo = "<p>Se ha editado su usuario en nuestra plataforma Better Job Environment con los siguientes datos<p>";
			cuerpo += "<p>Correo: " + user.getCorreo() + "</p>";
			cuerpo += "<p>Nombre: " + user.getNombre() + "</p>";
			cuerpo += "<p>Apellido: " + user.getApellido() + "</p>";
			cuerpo += "<p>Password: " + user.getPass() + "</p>";
			cuerpo += "<p>Restaurante: " + restaurante + "</p>";
			cuerpo += "<p>Rol: " + rol + "</p>";
			cuerpo += "<br><p>Si ha recibido este correo de forma erronea le pedimos que lo borre." + "</p>";
			cuerpo += "<p>Atentamente, el equipo de Better Job Environment" + "</p>";

			enviaCorreo(cuerpo, "Edicion de perfil", user.getCorreo());

		}

		// edita el perfil
		u.editaPerfil(user.getNombre(), user.getApellido(), user.getId(), user.getCorreo(), user.getPass());

	}

	/**
	 * Crea un usuario
	 * 
	 * @param user usuario que crear
	 */
	public void creaUsuario(Usuario user) {

		UsuarioDAO u = new UsuarioDAO();
		int activo = (user.isActivo()) ? 1 : 0;
		user.setPass(creaPassword());

		// si el usuario esta activo envia el correo
		if (user.isActivo()) {

			String restaurante = restauranteEJB.RestaurantePorId(user.getRestaurante()).getNombre();
			String rol = rolEJB.RolPorId(user.getRol()).getNombre();
			String cuerpo = "<p>Se ha creado un usuario en nuestra plataforma Better Job Environment con los siguientes datos<p>";
			cuerpo += "<p>Correo: " + user.getCorreo() + "</p>";
			cuerpo += "<p>Nombre: " + user.getNombre() + "</p>";
			cuerpo += "<p>Apellido: " + user.getApellido() + "</p>";
			cuerpo += "<p>Password: " + user.getPass() + "</p>";
			cuerpo += "<p>Restaurante: " + restaurante + "</p>";
			cuerpo += "<p>Rol: " + rol + "</p>";
			cuerpo += "<br><p>Si ha recibido este correo de forma erronea le pedimos que lo borre." + "</p>";
			cuerpo += "<p>Atentamente, el equipo de Better Job Environment" + "</p>";

			enviaCorreo(cuerpo, "Bienvenido a BJE", user.getCorreo());

		}
		
		u.creaUsuario(user.getNombre(), user.getApellido(), user.getRol(), user.getObservaciones(),
				user.getRestaurante(), activo, user.getCorreo(), user.getPass());

	}

	/**
	 * Elimina un usuario
	 * 
	 * @param id id del usuario que eliminar
	 */
	public void eliminaUsuario(int id) {

		UsuarioDAO u = new UsuarioDAO();

		u.eliminaUsuario(id);
	}

	/**
	 * Hace un reset de password y envia un correo
	 * 
	 * @param id id del usuario que resetear su password
	 */
	public void resetPassword(int id) {
		UsuarioDAO u = new UsuarioDAO();

		// Crea una password aleatorio
		String pass = creaPassword();

		// Busca el usuario segun su id
		Usuario user = usuarioEJB.UsuarioPorId(id);

		// si el usuario esta activo envia su password
		if (user.isActivo()) {

			String cuerpo = "<p>Se ha reseteado su contraseña <p>";
			cuerpo += "<p>Correo: " + user.getCorreo() + "</p>";
			cuerpo += "<p>Password: " + pass + "</p>";
			cuerpo += "<br><p>Si ha recibido este correo de forma erronea le pedimos que lo borre." + "</p>";
			cuerpo += "<p>Atentamente, el equipo de Better Job Environment" + "</p>";

			enviaCorreo(cuerpo, "Cambio de contraseña BJE", user.getCorreo());

		}

		// edita el usuario
		u.cambiaPass(id, pass);
	}

	/**
	 * Crea una password aleatoria de 8 caracteres con numeros y letras
	 * 
	 * @return String con la password de 8 caracteres
	 */
	private String creaPassword() {

		// parametros de filtrado
		int leftLimit = 48; // numero '0'
		int rightLimit = 122; // letra 'z'

		// tamaño
		int targetStringLength = 8;
		Random random = new Random();

		// crea la password con los random
		String pass = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return pass;
	}

	/**
	 * Envia un correo
	 * 
	 * @param cuerpo cuerpo del correo
	 * @param titulo titulo del correo
	 * @param mail   objetivo del correo
	 */
	private void enviaCorreo(String cuerpo, String titulo, String mail) {

		String remitente = "basiliscoxalligator@gmail.com";
		Mail m = new Mail("smtp.gmail.com", 587, "basiliscoxalligator@gmail.com", "Ageofempires2");
		m.sendMail(mail, remitente, titulo, cuerpo);

	}

}
