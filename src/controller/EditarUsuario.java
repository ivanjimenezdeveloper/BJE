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
import model.entidad.Usuario;

/**
 * Servlet implementation class EditarUsuario
 */
@WebServlet("/EditarUsuario")
public class EditarUsuario extends HttpServlet {
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
		Integer id = 0;
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		try {
			id = Integer.parseInt(request.getParameter("id"));

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		Usuario userEdit = usuarioEJB.UsuarioPorId(id);

		sesion.setAttribute("usuarioEditar", userEdit);
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/editarUsuario.jsp");
		rs.forward(request, response);
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
