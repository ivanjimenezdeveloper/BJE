package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ejb.Sesiones;
import model.ejb.TimerEJB;
import model.ejb.UsuarioEJB;
import model.entidad.Restaurante;
import model.entidad.Timer;
import model.entidad.Usuario;

/**
 * Servlet implementation class MuestraTimers
 */
@WebServlet("/MuestraTimers")
public class MuestraTimers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	TimerEJB timerEJB;
	
	@EJB
	Sesiones sesionEJB;
	
	@EJB
	UsuarioEJB usuarioEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession sesion = request.getSession(true);

		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);

		if (user == null || user.getId() == 0 && user.getRol() == 0) {
			response.sendRedirect("Main");
		} else {

			if (user.getRol() == 1) {
				response.sendRedirect("Main");
			} else if (user.getRol() == 2 || user.getRol() == 3) {

				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/timersActivos.jsp");
				rs.forward(request, response);
			} else {

				response.sendRedirect("Main");
			}

		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
