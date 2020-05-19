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
 * Servlet implementation class EditaAlimento
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

	@EJB
	AlimentoEJB alimentoEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		Integer id = 0;
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
					try {
						id = Integer.parseInt(request.getParameter("timer"));

					} catch (Exception e) {
						logger.error(e.getMessage());
					}
					
					if(id != 0) {
						Alimento alimentoEdit = alimentoEJB.alimentoPorId(id);

						sesion.setAttribute("alimentoEditar", alimentoEdit);
						RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/editarAlimento.jsp");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		String nombre, categoria, tiempo;
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

					Alimento alimento = (Alimento) sesion.getAttribute("alimentoEditar");

					nombre = request.getParameter("nombre");
					categoria = request.getParameter("categoria");
					tiempo = request.getParameter("tiempo");

					alimento.setNombre(nombre);

					try {
						alimento.setIdCategoria(Integer.parseInt(categoria));
						alimento.setTiempo(alimentoEJB.desglosaTiempoFormulario(tiempo));
					} catch (Exception e) {
						logger.error(e.getMessage());
					}

					alimentoEJB.editaAlimento(alimento);
					response.sendRedirect("GestionaAlimentos");
				} else {

					response.sendRedirect("Main");

				}

			}
		}
	}

}
