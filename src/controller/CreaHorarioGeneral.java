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


@WebServlet("/CreaHorarioGeneral")
public class CreaHorarioGeneral extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	Sesiones sesionEJB;

	@EJB
	HorarioEJB horarioEJB;

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

					response.sendRedirect("Main");
				} else if (user.getRol() == 2 || user.getRol() == 3) {

					int mes = 0, anyo = 0;
					
					String activo = request.getParameter("activo");
					boolean activoBol = false;

					try {
						mes = Integer.parseInt(request.getParameter("mes"));
						anyo = Integer.parseInt(request.getParameter("anyo"));
						
					} catch (Exception e) {
						// TODO: handle exception
					}

					if (activo != null) {

						activoBol = true;

					}

					horarioEJB.creaHorarioGeneral(activoBol, mes, anyo);
					
					response.sendRedirect("VisualizarHorario");

				} else {

					response.sendRedirect("Main");

				}
			}

		}
	}

}
