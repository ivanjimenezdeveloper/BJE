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
 * Servlet que reseta la contraseña
 * 
 * @author HIBAN
 *
 */
@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con los usuarios
	 */
	@EJB
	UsuarioEJB usuarioEJB;

	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ResetPassword.class);

	/**
	 * Resetea la password
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
			response.sendRedirect("Main");

		} else {

			// Si el modo trabajo es 1 es que el modo trabajo esta activado y redirige al
			// jsp del modo trabajo
			if (modoTrabajo == 1) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexTrabajo.jsp");
				rs.forward(request, response);
			} else {

				// Segun el rol redirige al main o continua con la operacion
				if (user.getRol() == 1) {
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexUsuario.jsp");
					rs.forward(request, response);
				} else if (user.getRol() == 2 || user.getRol() == 3) {

					// recoge la id del reset de password
					int id = 0;
					try {
						id = Integer.parseInt(request.getParameter("id"));
					} catch (Exception e) {
						logger.error(e.getMessage());
					}

					// si la id es valida resetea la password
					if (id != 0) {
						usuarioEJB.resetPassword(id);

						response.sendRedirect("GestionUsuario");
					} else {
						response.sendRedirect("Main");

					}

				} else {

					response.sendRedirect("Main");

				}
			}

		}

	}

}
