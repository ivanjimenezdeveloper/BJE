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
import model.ejb.Sesiones;
import model.ejb.TimerEJB;
import model.entidad.Usuario;

/**
 * Servlet que sirve para añadir timers activos
 * 
 * @author HIBAN
 *
 */
@WebServlet("/AnyadeTimer")
public class AnyadeTimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = (Logger) LoggerFactory.getLogger(AnyadeTimer.class);

	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	/**
	 * EJB de timers
	 */
	@EJB
	TimerEJB timerEJB;

	/**
	 * Añade un timer y redirecciona a Muestra Timers
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recuperamos la sesion
		HttpSession sesion = request.getSession(true);
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);

		int modoTrabajo;

		// recupera si esta o no en modo trabajo
		try {
			modoTrabajo = (int) sesion.getAttribute("modoTrabajo");

		} catch (Exception e) {
			modoTrabajo = 0;
		}

		// Comprueba que el usurio sea valido y si no redirige al main
		if (user == null || user.getId() == 0 && user.getRol() == 0) {
			response.sendRedirect("Main");
		} else {


				// Segun el rol redirige al main o continua con la operacion
				if (user.getRol() == 1) {
					response.sendRedirect("Main");
				} else if (user.getRol() == 2 && modoTrabajo == 1 || user.getRol() == 3 && modoTrabajo == 1) {

					// recupera la id del alimento para crear un timer

					String timer = request.getParameter("timer");
					int idAlimento = 0;
					try {
						idAlimento = Integer.parseInt(timer);
					} catch (Exception e) {
						logger.error(e.getMessage());
					}

					// Si la id de l alimento es correcta hara el insert
					if (idAlimento != 0) {
						timerEJB.addTimer(idAlimento, user.getRestaurante());

					}

					response.sendRedirect("MuestraTimers");
				} else {

					response.sendRedirect("Main");
				}
			}

		

	}

}
