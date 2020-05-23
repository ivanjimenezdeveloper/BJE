package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.ejb.Sesiones;
import model.ejb.UsuarioEJB;
import model.entidad.Usuario;

/**
 * Servlet que muestra el perfil y lo edita
 * 
 * @author HIBAN
 *
 */
@WebServlet("/Perfil")
public class Perfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Perfil.class);

	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	/**
	 * EJB para trabajar con usuarios
	 */
	@EJB
	UsuarioEJB usuarioEJB;

	/**
	 * Muestra el perfil del usuario
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recuperamos la sesion
		HttpSession sesion = request.getSession(true);
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);

		// recupera si esta o no en modo trabajo
		int modoTrabajo;
		try {
			modoTrabajo = (int) sesion.getAttribute("modoTrabajo");

		} catch (Exception e) {
			modoTrabajo = 0;
		}

		// Comprueba que el usuario sea valido y si no redirige al main
		if (user == null || user.getId() == 0 && user.getRol() == 0) {
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/login.jsp");
			rs.forward(request, response);
		} else {

			// Si el modo trabajo es 1 es que el modo trabajo esta activado y redirige al
			// jsp del modo trabajo
			if (modoTrabajo == 1) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexTrabajo.jsp");
				rs.forward(request, response);
			} else {
				if (user.getRol() == 1) {
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/perfilUsuario.jsp");
					rs.forward(request, response);
				} else if (user.getRol() == 2 || user.getRol() == 3) {
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/perfilManager.jsp");
					rs.forward(request, response);
				} else {
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/login.jsp");
					rs.forward(request, response);
				}
			}

		}
	}

	/**
	 * Cambia el perfil
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombre, apellido, correo, pass, passConfirm;

		// recuperamos la sesion
		HttpSession sesion = request.getSession(true);

		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);

		// recupera si esta o no en modo trabajo
		int modoTrabajo;
		try {
			modoTrabajo = (int) sesion.getAttribute("modoTrabajo");

		} catch (Exception e) {
			modoTrabajo = 0;
		}

		// Comprueba que el usuario sea valido y si no redirige al main
		if (user == null || user.getId() == 0 && user.getRol() == 0) {
			response.sendRedirect("Main");

		} else {

			// Si el modo trabajo es 1 es que el modo trabajo esta activado y redirige al
			// jsp del modo trabajo
			if (modoTrabajo == 1) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexTrabajo.jsp");
				rs.forward(request, response);
			} else {
				
				if (user.getRol() == 1 || user.getRol() == 2 || user.getRol() == 3) {
					//Recoge los parametros
					nombre = request.getParameter("nombre");
					apellido = request.getParameter("apellido");
					correo = request.getParameter("correo");
					pass = request.getParameter("password");
					passConfirm = request.getParameter("passwordConfirm");

					//los guarda en el usuario
					user.setNombre(nombre);
					user.setApellido(apellido);
					user.setCorreo(correo);
					
					//Comprueba que la contraseña sea igual al confirm
					if (pass.equals(passConfirm) && !pass.equals("") && pass != null) {
						user.setPass(passConfirm);
					}

					//edita el perfil
					usuarioEJB.editaPerfil(user);

					response.sendRedirect("Perfil");
				} else {

					response.sendRedirect("Main");

				}
			}
		}

	}
}
