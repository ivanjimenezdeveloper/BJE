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

import model.ejb.Sesiones;
import model.ejb.UsuarioEJB;
import model.entidad.Usuario;

/**
 * Servlet implementation class ModoTrabajo
 */
@WebServlet("/ModoTrabajo")
public class ModoTrabajo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con usuarios
	 */
	@EJB
	UsuarioEJB userEJB;
	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		int modoTrabajo = 0;
		String logout = "1";
		try {
			modoTrabajo = (int) sesion.getAttribute("modoTrabajo");
			logout = request.getParameter("logoutTrabajo");

		} catch (Exception e) {
			modoTrabajo = 0;
		}

		if (user == null || user.getId() == 0 && user.getRol() == 0) {
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/login.jsp");
			rs.forward(request, response);
		} else {
			if (modoTrabajo == 0) {

				if (user.getRol() == 1) {
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexUsuario.jsp");
					rs.forward(request, response);
				} else if (user.getRol() == 2 || user.getRol() == 3) {
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/activarModoTrabajo.jsp");
					rs.forward(request, response);
				} else {
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/login.jsp");
					rs.forward(request, response);
				}
			} else if (logout.equals("1")) {

				sesion.setAttribute("modoTrabajo", 0);
				response.sendRedirect("Main");
			} else {

				response.sendRedirect("Main");
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario user = new Usuario();
		HttpSession sesion = request.getSession(true);
		String pass = request.getParameter("pass");

		user = sesionEJB.usuarioLogeado(sesion);

		user = userEJB.existeUsuario(user.getCorreo(), pass);

		if (user == null || user.getCorreo() == null) {
			response.sendRedirect("Main");
		} else {
			sesion.setAttribute("modoTrabajo", 1);
			response.sendRedirect("Main");
		}

	}

}
