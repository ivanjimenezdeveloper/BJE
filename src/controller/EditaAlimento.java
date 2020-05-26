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
import model.ejb.AlimentoEJB;
import model.ejb.Sesiones;
import model.entidad.Alimento;
import model.entidad.Usuario;

/**
 * Edita un alimento
 * 
 * @author HIBAN
 *
 */
@WebServlet("/EditaAlimento")
public class EditaAlimento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);

	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	/**
	 * EJB para trabajar con alimentos
	 */
	@EJB
	AlimentoEJB alimentoEJB;

	/**
	 * Muestra el JSP para editar un alimento
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

					// recupera el parametro de id del alimento
					try {
						id = Integer.parseInt(request.getParameter("timer"));

					} catch (Exception e) {
						logger.error(e.getMessage());
					}

					// si la id es valida reenvia al formulario del alimento esperado
					if (id != 0) {
						Alimento alimentoEdit = alimentoEJB.alimentoPorId(id);

						sesion.setAttribute("alimentoEditar", alimentoEdit);
						RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/editarAlimento.jsp");
						rs.forward(request, response);
					} else {
						response.sendRedirect("Main");

					}

				} else {

					response.sendRedirect("Main");

				}
			}

		}
	}

	/**
	 * Edita el alimento
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recuperamos la sesion
		HttpSession sesion = request.getSession(true);

		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);

		String nombre, categoria, tiempo;

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

					// recupera los parametros necesarios del alimento
					Alimento alimento = (Alimento) sesion.getAttribute("alimentoEditar");

					nombre = request.getParameter("nombre");
					categoria = request.getParameter("categoria");
					tiempo = request.getParameter("tiempo");

					alimento.setNombre(nombre);

					try {
						alimento.setIdCategoria(Integer.parseInt(categoria));

						// con el tiempo dado lo formatea al formato esperado
						alimento.setTiempo(alimentoEJB.desglosaTiempoFormulario(tiempo));
					} catch (Exception e) {
						logger.error(e.getMessage());
					}

					// comprueba que sea valido
					if (alimento.getNombre() == null || alimento.getNombre().equals("") || alimento.getTiempo() == 0
							|| alimento.getCategoria() == 0) {
						response.sendRedirect("GestionaAlimentos");

					} else {

						// edita el alimento
						alimentoEJB.editaAlimento(alimento);
						response.sendRedirect("GestionaAlimentos");
					}
				} else {

					response.sendRedirect("Main");

				}

			}
		}
	}

}
