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
 * Servlet implementation class CrearUsuario
 */
@WebServlet("/CrearUsuario")
public class CrearUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		// Obtenemos el usuario de la sesion si existe
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
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/crearUsuario.jsp");
					rs.forward(request, response);
				} else {

					response.sendRedirect("Main");

				}
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
					Usuario userCreate = new Usuario();

					correo = request.getParameter("correo");

					if (usuarioEJB.existeCorreo(correo) == 1) {
						response.sendRedirect("Main");

					} else {

						nombre = request.getParameter("nombre");
						apellido = request.getParameter("apellido");

						try {
							restaurante = Integer.parseInt(request.getParameter("restaurante"));
							rol = Integer.parseInt(request.getParameter("rol"));
						} catch (Exception e) {
							logger.error(e.getMessage());
						}

						observaciones = request.getParameter("observaciones");
						activo = request.getParameter("activo");
						activoParam = (activo != null) ? true : false;

						userCreate.setNombre(nombre);
						userCreate.setApellido(apellido);
						userCreate.setCorreo(correo);
						userCreate.setRestaurante(restaurante);
						userCreate.setRol(rol);
						userCreate.setActivo(activoParam);
						userCreate.setObservaciones(observaciones);
						usuarioEJB.creaUsuario(userCreate);

						response.sendRedirect("GestionUsuario");
					}
				} else {

					response.sendRedirect("Main");

				}
			}

		}

	}

}
