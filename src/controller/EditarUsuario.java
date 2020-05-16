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
import model.entidad.Restaurante;
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

		if (user == null || user.getId() == 0 && user.getRol() == 0) {
			response.sendRedirect("Main");

		} else {

			if (user.getRol() == 1) {

				response.sendRedirect("Main");
			} else if (user.getRol() == 2 || user.getRol() == 3) {
				try {
					id = Integer.parseInt(request.getParameter("id"));

				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				Usuario userEdit = usuarioEJB.UsuarioPorId(id);

				sesion.setAttribute("usuarioEditar", userEdit);
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/editarUsuario.jsp");
				rs.forward(request, response);
			} else {

				response.sendRedirect("Main");

			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre, apellido, correo, observaciones, activo;
		boolean activoParam;

		int rol = 0, restaurante = 0;

		HttpSession sesion = request.getSession(true);
		Usuario user = sesionEJB.usuarioLogeado(sesion);

		if (user == null || user.getId() == 0 && user.getRol() == 0) {
			response.sendRedirect("Main");

		} else {

			if (user.getRol() == 1) {

				response.sendRedirect("Main");
			} else if (user.getRol() == 2 || user.getRol() == 3) {
				
				Usuario userEdit = (Usuario) sesion.getAttribute("usuarioEditar");

				nombre = request.getParameter("nombre");
				apellido = request.getParameter("apellido");
				correo = request.getParameter("correo");

				try {
					restaurante = Integer.parseInt(request.getParameter("restaurante"));
					rol = Integer.parseInt(request.getParameter("rol"));
				} catch (Exception e) {
					logger.error(e.getMessage());
				}

				observaciones = request.getParameter("observaciones");
				activo = request.getParameter("activo");
				activoParam = (activo != null) ? true : false;

				userEdit.setNombre(nombre);
				userEdit.setApellido(apellido);
				userEdit.setCorreo(correo);
				userEdit.setRestaurante(restaurante);
				userEdit.setRol(rol);
				userEdit.setActivo(activoParam);
				userEdit.setObservaciones(observaciones);

				usuarioEJB.editaUsuario(userEdit);

				response.sendRedirect("GestionUsuario");
			} else {

				response.sendRedirect("Main");

			}

		}

	}
}
