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
 * Servlet que visualiza el horario
 * 
 * @author HIBAN
 *
 */
@WebServlet("/VisualizarHorario")
public class VisualizarHorario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con horarios
	 */
	@EJB
	HorarioEJB horarioEJB;

	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;

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
	 * Muestra el selector de horario
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recuperamos la sesion
		HttpSession sesion = request.getSession(true);

		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		String mes, anyo;
		mes = request.getParameter("mes");
		anyo = request.getParameter("anyo");

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
			if (modoTrabajo == 1) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexTrabajo.jsp");
				rs.forward(request, response);
			} else {

				// Segun el rol redirige al main o continua con la operacion
				if (user.getRol() == 1) {

					if (mes == null && anyo == null || mes.equals("") && anyo.equals("")) {

						// recupera los horarios
						ArrayList<Horario> arrH = horarioEJB.horariosPorRestauranteActivo(user.getRestaurante());

						// guarda los horarios en la sesion
						sesion.setAttribute("horarios", arrH);

						RequestDispatcher rs = getServletContext()
								.getRequestDispatcher("/dist/selectorHorarioUsuario.jsp");
						rs.forward(request, response);
					} else {
						doPost(request, response);
					}
				} else if (user.getRol() == 2 || user.getRol() == 3) {

					// si los datos son correctos envia al selector de horario
					if (mes == null && anyo == null || mes.equals("") && anyo.equals("")) {

						// recupera los horarios
						ArrayList<Horario> arrH = horarioEJB.getHorarios();

						// guarda los horarios en la sesion
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

	/**
	 * Visualiza un horario
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
				String mes, anyo;

				// Segun el rol redirige al main o continua con la operacion
				if (user.getRol() == 1) {

					// recupera los parametros
					mes = request.getParameter("mes");
					anyo = request.getParameter("anyo");
					Restaurante restaurante = restauranteEJB.RestaurantePorId(user.getRestaurante());

					// guarda los atributos
					sesion.setAttribute("usuario", user);
					sesion.setAttribute("restaurante", restaurante);
					sesion.setAttribute("mes", mes);
					sesion.setAttribute("anyo", anyo);

					// redirige
					RequestDispatcher rs = getServletContext()
							.getRequestDispatcher("/dist/visualizarHorarioUsuario.jsp");
					rs.forward(request, response);
				} else if (user.getRol() == 2 || user.getRol() == 3) {

					// recupera los parametros
					mes = request.getParameter("mes");
					anyo = request.getParameter("anyo");
					// guarda los usuarios por restaurante y recupera el resturante
					ArrayList<Usuario> usuarios = usuarioEJB.busquedaUsuarios(user.getRestaurante());
					Restaurante restaurante = restauranteEJB.RestaurantePorId(user.getRestaurante());

					// guarda los atributos
					sesion.setAttribute("usuarios", usuarios);
					sesion.setAttribute("restaurante", restaurante);
					sesion.setAttribute("mes", mes);
					sesion.setAttribute("anyo", anyo);

					// redirige
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/visualizarHorario.jsp");
					rs.forward(request, response);
				} else {

					response.sendRedirect("Main");

				}

			}
		}

	}

}
