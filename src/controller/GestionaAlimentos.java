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
import model.entidad.Usuario;

/**
 * Servlet implementation class GestionaAlimentos
 */
@WebServlet("/GestionaAlimentos")
public class GestionaAlimentos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	Sesiones sesionEJB;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);

		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		if (user == null || user.getId() == 0 && user.getRol() == 0) {
			response.sendRedirect("Main");
		} else {

			if (user.getRol() == 1) {
				response.sendRedirect("Main");
			} else if (user.getRol() == 2 || user.getRol() == 3) {

				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/gestionAlimentos.jsp");
				rs.forward(request, response);
			} else {

				response.sendRedirect("Main");
			}

		}
	}

}
