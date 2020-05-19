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
 * Servlet implementation class ResetPassword
 */
@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		int modoTrabajo;
		try {
			modoTrabajo = (int) sesion.getAttribute("modoTrabajo");

		} catch (Exception e) {
			modoTrabajo = 0;
		}
		
		if (user == null || user.getId() == 0 && user.getRol() == 0) {
			response.sendRedirect("Main");

		} else {
			if (modoTrabajo == 1) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexTrabajo.jsp");
				rs.forward(request, response);
			} else {
			if (user.getRol() == 1) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexUsuario.jsp");
				rs.forward(request, response);
			} else if (user.getRol() == 2 || user.getRol() == 3) {
				int id = 0;
				try {
					id = Integer.parseInt(request.getParameter("id"));
				} catch (Exception e) {
					logger.error(e.getMessage());
				}

				usuarioEJB.resetPassword(id);

				response.sendRedirect("GestionUsuario");
			} else {

				response.sendRedirect("Main");

			}}

		}

	}

}
