package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ejb.DiaEJB;
import model.ejb.LocalizacionEJB;
import model.ejb.RestauranteEJB;
import model.ejb.RolEJB;
import model.ejb.Sesiones;
import model.ejb.UsuarioEJB;
import model.entidad.Restaurante;
import model.entidad.Usuario;

/**
 * Muestra la gestion de usuarios
 * @author HIBAN
 *
 */
@WebServlet("/GestionUsuario")
public class GestionUsuario extends HttpServlet {
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

	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	/**
	 * Muestra la gestion de usuarios
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recuperamos la sesion
		HttpSession sesion = request.getSession(true);

		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		
		//recupera los usuarios del restaurante
		ArrayList<Usuario> usuarios = usuarioEJB.busquedaUsuarios(user.getRestaurante());

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
					
					//recupera el restaurante del usuario
					Restaurante restaurante = restauranteEJB.RestaurantePorId(user.getRestaurante());

					//guarda los atributos en la sesion
					sesion.setAttribute("usuarios", usuarios);
					sesion.setAttribute("restaurante", restaurante);

					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/muestraUsuarios.jsp");
					rs.forward(request, response);
				} else {

					response.sendRedirect("Main");
				}
			}

		}

	}

}
