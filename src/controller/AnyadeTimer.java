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
import model.entidad.dao.RestauranteDAO;

/**
 * Servlet implementation class AnyadeTimer
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
	
	@EJB
	TimerEJB timerEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);

		String timer = request.getParameter("timer");
		int idAlimento = 0;
		try {
			idAlimento = Integer.parseInt(timer);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		timerEJB.addTimer(idAlimento);

		response.sendRedirect("MuestraTimers");
	}

}
