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
 * Activa horario
 * @author HIBAN
 *
 */
@WebServlet("/ActivarHorario")
public class ActivarHorario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;
	
	/**
	 * EJB para trabajar con horarios
	 */
	@EJB
	HorarioEJB horarioEJB;

	/**
	 * Activa horario
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
					response.sendRedirect("Main");

				} else if (user.getRol() == 2 || user.getRol() == 3) {

					// si los datos son correctos envia al selector de horario
					int id, desactivar;

					try {
						desactivar = Integer.parseInt(request.getParameter("desactivar").toString());

					} catch (Exception e) {
						desactivar = 0;

					}
					try {
						id = Integer.parseInt(request.getParameter("id").toString());
					} catch (Exception e) {
						id = 0;
					}

					if (desactivar != 0) {
						horarioEJB.desactivaHorario(id);

					} else {
						horarioEJB.activaHorario(id);
					}

					response.sendRedirect("Main");

				} else {

					response.sendRedirect("Main");

				}

			}
		}
	}

}
