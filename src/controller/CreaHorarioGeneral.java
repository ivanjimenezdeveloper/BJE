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

import model.ejb.HorarioEJB;
import model.ejb.Sesiones;
import model.entidad.Usuario;

/**
 * Servlet que muestra el formulario para crear un horario general y lo inserta
 * @author HIBAN
 *
 */
@WebServlet("/CreaHorarioGeneral")
public class CreaHorarioGeneral extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con lase sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	/**
	 * EJB para trabajar con el horarios
	 */
	@EJB
	HorarioEJB horarioEJB;

	/**
	 * Muestra el formulario de creacion de horario
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

		// Comprueba que el usurio sea valido y si no redirige al main
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

					response.sendRedirect("Main");
				} else if (user.getRol() == 2 || user.getRol() == 3) {
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/crearHorarioGeneral.jsp");
					rs.forward(request, response);
				} else {

					response.sendRedirect("Main");

				}
			}

		}
	}

	/**
	 * Inserta el horario general
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

		// Comprueba que el usurio sea valido y si no redirige al main
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

					response.sendRedirect("Main");
				} else if (user.getRol() == 2 || user.getRol() == 3) {

					int mes = 0, anyo = 0;

					// recupera los parametros para insertar
					String activo = request.getParameter("activo");
					boolean activoBol = false;

					try {
						mes = Integer.parseInt(request.getParameter("mes"));
						anyo = Integer.parseInt(request.getParameter("anyo"));

					} catch (Exception e) {
						mes = 0;
						anyo = 0;
					}

					//si activo no tiene nada dentro pasara a ser true
					if (activo != null) {

						activoBol = true;

					}

					// TODO: comprobar horario

					//Inserta el horario general
					horarioEJB.creaHorarioGeneral(activoBol, mes, anyo);

					response.sendRedirect("VisualizarHorario");

				} else {

					response.sendRedirect("Main");

				}
			}

		}
	}

}
