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
import model.ejb.AlimentoEJB;
import model.ejb.Sesiones;
import model.entidad.Alimento;
import model.entidad.Usuario;

/**
 * Servlet que crea un alimento y muestra el formulario de creacion de alimento
 * 
 * @author HIBAN
 *
 */
@WebServlet("/CreaAlimento")
public class CreaAlimento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);

	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	/**
	 * EJB para trabajar con alimentos
	 */
	@EJB
	AlimentoEJB alimentoEJB;

	/**
	 * Muestra el formulario de creacion de alimento
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
				// Segun el rol redirige al main o continua con la operacion
				if (user.getRol() == 1) {

					response.sendRedirect("Main");
				} else if (user.getRol() == 2 || user.getRol() == 3) {

					// Muestra el JSP de crear alimento

					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/crearAlimento.jsp");
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
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		String nombre, categoria, tiempo;

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

					Alimento alimento = new Alimento();

					nombre = request.getParameter("nombre");
					categoria = request.getParameter("categoria");
					tiempo = request.getParameter("tiempo");

					alimento.setNombre(nombre);

					try {
						alimento.setIdCategoria(Integer.parseInt(categoria));
						alimento.setTiempo(alimentoEJB.desglosaTiempoFormulario(tiempo));
					} catch (Exception e) {
						logger.error(e.getMessage());
					}

					alimentoEJB.creaAlimento(alimento);
					response.sendRedirect("GestionaAlimentos");
				} else {

					response.sendRedirect("Main");

				}
			}

		}
	}

}
