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

import model.ejb.HorarioEJB;
import model.ejb.RestauranteEJB;
import model.ejb.Sesiones;
import model.ejb.UsuarioEJB;
import model.entidad.Horario;
import model.entidad.Restaurante;
import model.entidad.Usuario;

/**
 * Servlet implementation class VisualizarHorario
 */
@WebServlet("/VisualizarHorario")
public class VisualizarHorario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	HorarioEJB horarioEJB;

	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	@EJB
	RestauranteEJB restauranteEJB;

	@EJB
	UsuarioEJB usuarioEJB;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesion = request.getSession(true);
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		String mes, anyo;
		mes = request.getParameter("mes");
		anyo = request.getParameter("anyo");

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

					response.sendRedirect("Main");
				} else if (user.getRol() == 2 || user.getRol() == 3) {

					if (mes == null && anyo == null || mes.equals("") && anyo.equals("")) {
						ArrayList<Horario> arrH = horarioEJB.getHorarios();

						sesion.setAttribute("horarios", arrH);

						RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/selectorHorario.jsp");
						rs.forward(request, response);
					} else {
						doPost(request, response);
					}
				} else {

					response.sendRedirect("Main");

				}

			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		// Obtenemos el usuario de la sesion si existe
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

					response.sendRedirect("Main");
				} else if (user.getRol() == 2 || user.getRol() == 3) {

					String mes, anyo;
					mes = request.getParameter("mes");
					anyo = request.getParameter("anyo");
					ArrayList<Usuario> usuarios = usuarioEJB.busquedaUsuarios(user.getRestaurante());
					Restaurante restaurante = restauranteEJB.RestaurantePorId(user.getRestaurante());

					sesion.setAttribute("usuarios", usuarios);
					sesion.setAttribute("restaurante", restaurante);
					sesion.setAttribute("mes", mes);
					sesion.setAttribute("anyo", anyo);

					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/visualizarHorario.jsp");
					rs.forward(request, response);
				} else {

					response.sendRedirect("Main");

				}

			}
		}

	}

}
