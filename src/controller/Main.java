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
import model.entidad.Usuario;

/**
 * Servlet que muestra la pagina principal
 * 
 * @author HIBAN
 *
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);

	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	/**
	 * Muestra el main o redirige a la pagina necesaria
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

		// Comprueba que el usuario sea valido y si no redirige al a login
		if (user == null || user.getId() == 0 && user.getRol() == 0) {
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/login.jsp");
			rs.forward(request, response);
		} else {

			//segun el tipo de usuario muestra su main o le envia al login
			if (modoTrabajo == 1 && user.getRol() == 2 || modoTrabajo == 1 && user.getRol() == 3) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexTrabajo.jsp");
				rs.forward(request, response);
			} else if (user.getRol() == 1) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexUsuario.jsp");
				rs.forward(request, response);
			} else if (user.getRol() == 2 || user.getRol() == 3) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexManager.jsp");
				rs.forward(request, response);
			} else {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/login.jsp");
				rs.forward(request, response);
			}

		}

	}

}
