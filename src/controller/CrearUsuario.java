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
import model.ejb.LocalizacionEJB;
import model.ejb.RestauranteEJB;
import model.ejb.RolEJB;
import model.ejb.Sesiones;
import model.ejb.UsuarioEJB;
import model.entidad.Usuario;

/**
 * Servlet que muestra el formulario para crear un usuario y crea un usuario
 * @author HIBAN
 *
 */
@WebServlet("/CrearUsuario")
public class CrearUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con roles
	 */
	@EJB
	RolEJB rolEJB;

	/**
	 * EJB para trabajar con localizaciones
	 */
	@EJB
	LocalizacionEJB localizacionEJB;

	/**
	 * EJB para trabajar con los usuarios
	 */
	@EJB
	UsuarioEJB usuarioEJB;

	/**
	 * EJB para trabajar con restaurantes
	 */
	@EJB
	RestauranteEJB restauranteEJB;

	/**
	 * EJB para trabajar con dias
	 */
	@EJB
	DiaEJB diaEJB;


	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(CrearUsuario.class);

	/**
	 * Muestra el formulario de creacion de usuario
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
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/crearUsuario.jsp");
					rs.forward(request, response);
				} else {

					response.sendRedirect("Main");

				}
			}

		}

	}

	/**
	 * Inserta el usuario nuevo
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nombre, apellido, correo, observaciones, activo;
		boolean activoParam;

		int rol = 0, restaurante = 0;

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
					
					Usuario userCreate = new Usuario();

					//recoge los el correo
					correo = request.getParameter("correo");

					//si ya existe el correo redirige al main
					if (usuarioEJB.existeCorreo(correo) == 1) {
						response.sendRedirect("Main");

					} else {
						
						//recoge el resto de parametros
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
						
						//si guarda algo en activo entonces guarda true si no, false
						activoParam = (activo != null) ? true : false;

						//guarda todos los parametros en un objeto usuario
						userCreate.setNombre(nombre);
						userCreate.setApellido(apellido);
						userCreate.setCorreo(correo);
						userCreate.setRestaurante(restaurante);
						userCreate.setRol(rol);
						userCreate.setActivo(activoParam);
						userCreate.setObservaciones(observaciones);
						
						//inserta un usuario
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
