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
 * Servlet que cambia el modo trabajo
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

	/**
	 * Cambia a modo trabajo o deslogea segun el parametro logout
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recuperamos la sesion
		HttpSession sesion = request.getSession(true);

		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		int modoTrabajo = 0;
		String logout = "";

		// recupera si esta en modo trabajo o si hay un logout de modo trabajo
		try {
			modoTrabajo = (int) sesion.getAttribute("modoTrabajo");
			logout = request.getParameter("logoutTrabajo");

		} catch (Exception e) {
			modoTrabajo = 0;
		}

		// Comprueba que el usuario sea valido y si no redirige al main
		if (user == null || user.getId() == 0 && user.getRol() == 0) {
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/login.jsp");
			rs.forward(request, response);
		} else {

			// si logout es positivo envia a desactivar el modo trabajo
			if (logout != null && logout.equals("1")) {

				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/desactivarModoTrabajo.jsp");
				rs.forward(request, response);
			}

			// Si el modo trabajo es 1 es que el modo trabajo esta activado y redirige al
			// jsp del modo trabajo
			else if (modoTrabajo == 1) {

				if (user.getRol() == 1) {
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexUsuario.jsp");
					rs.forward(request, response);
				} else if (user.getRol() == 2 || user.getRol() == 3) {
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexTrabajo.jsp");
					rs.forward(request, response);
				} else {
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/login.jsp");
					rs.forward(request, response);
				}
			} else if (user.getRol() == 2 || user.getRol() == 3) {

				sesion.setAttribute("modoTrabajo", 1);
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexTrabajo.jsp");
				rs.forward(request, response);

			} else {

				response.sendRedirect("Main");
			}

		}
	}

	/**
	 * Quita el modo trabajo
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario user = new Usuario();
		//recupera la sesion
		HttpSession sesion = request.getSession(true);
		
		//recupera la password
		String pass = request.getParameter("pass");

		//recupera el usuario de la sesion
		user = sesionEJB.usuarioLogeado(sesion);

		//si la password es correcta quita el modo trabajo
		user = userEJB.existeUsuario(user.getCorreo(), pass);

		if (user == null || user.getCorreo() == null) {
			response.sendRedirect("Main");
		} else {
			sesion.setAttribute("modoTrabajo", 0);
			response.sendRedirect("Main");
		}

	}

}
