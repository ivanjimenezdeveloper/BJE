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
 * Edita un usuario
 * @author HIBAN
 *
 */
@WebServlet("/EditarUsuario")
public class EditarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);

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
	 * EJB para trabajar con usuarios
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
	 * Muestra el JSP de edicion de usuario
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recuperamos la sesion
		HttpSession sesion = request.getSession(true);
		Integer id = 0;
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
					
					//recoge los parametros
					try {
						id = Integer.parseInt(request.getParameter("id"));

					} catch (Exception e) {
						logger.error(e.getMessage());
					}
					
					//si la id es valida recogera el usuario y lo mostrara para editar
					if(id != 0) {
						Usuario userEdit = usuarioEJB.UsuarioPorId(id);

						sesion.setAttribute("usuarioEditar", userEdit);
						RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/editarUsuario.jsp");
						rs.forward(request, response);
					}else {
						response.sendRedirect("Main");

					}
					
				} else {

					response.sendRedirect("Main");

				}

			}
		}

	}

	/**
	 * Edita el usuario
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

				//Recupera los parametros
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
				
				//si guarda algo en activo, activo param pasara a true
				activoParam = (activo != null) ? true : false;

				//guarda los parametros en un objeto usuario
				userEdit.setNombre(nombre);
				userEdit.setApellido(apellido);
				userEdit.setCorreo(correo);
				userEdit.setRestaurante(restaurante);
				userEdit.setRol(rol);
				userEdit.setActivo(activoParam);
				userEdit.setObservaciones(observaciones);

				//inserta el usuario
				usuarioEJB.editaUsuario(userEdit);

				response.sendRedirect("GestionUsuario");
			} else {

				response.sendRedirect("Main");

			}

		}}

	}
}
