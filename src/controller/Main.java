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
import model.ejb.DiaEJB;
import model.ejb.HorarioEJB;
import model.ejb.LocalizacionEJB;
import model.ejb.RestauranteEJB;
import model.ejb.RolEJB;
import model.ejb.Sesiones;
import model.ejb.UsuarioEJB;
import model.Mail;
import model.entidad.Usuario;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);

	@EJB
	RolEJB rolEJB;

	@EJB
	LocalizacionEJB localizacionEJB;

	@EJB
	UsuarioEJB usuarioEJB;

	@EJB
	RestauranteEJB restauranteEJB;

	@EJB
	DiaEJB diaEJB;

	@EJB
	HorarioEJB horarioEJB;

	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesion = request.getSession(true);
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);

		if (user == null ||user.getId() == 0 && user.getRol() == 0) {
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/login.jsp");
			rs.forward(request, response);
		} else {

			if (user.getRol() == 1) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexUsuario.jsp");
				rs.forward(request, response);
			} else if (user.getRol() == 2 || user.getRol() == 3) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexManager.jsp");
				rs.forward(request, response);
			} else {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/login.jsp");
				rs.forward(request, response);
			}

		}

	}

}
