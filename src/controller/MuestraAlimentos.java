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

import model.ejb.AlimentoEJB;
import model.ejb.CategoriaEJB;
import model.ejb.Sesiones;
import model.entidad.Usuario;

/**
 * Muestra los alimentos
 * 
 * @author HIBAN
 *
 */
@WebServlet("/MuestraAlimentos")
public class MuestraAlimentos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con las categorias
	 */
	@EJB
	CategoriaEJB categoriaEJB;

	/**
	 * EJB para trabajar con alimentos
	 */
	@EJB
	AlimentoEJB alimentoEJB;

	/**
	 * EJB para trbajar con las sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	/**
	 * Muestra el JSP de los alimentos
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
			// Segun el rol redirige al main o continua con la operacion
			if (user.getRol() == 1) {
				response.sendRedirect("Main");
			} else if (modoTrabajo == 1 && user.getRol() == 2 || modoTrabajo == 1 && user.getRol() == 3) {

				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/alimentos.jsp");
				rs.forward(request, response);
			} else {

				response.sendRedirect("Main");
			}

		}
	}

}
