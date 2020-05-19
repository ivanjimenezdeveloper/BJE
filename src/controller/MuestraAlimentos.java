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

import model.ejb.AlimentoEJB;
import model.ejb.CategoriaEJB;
import model.ejb.Sesiones;
import model.entidad.Alimento;
import model.entidad.Categoria;
import model.entidad.Usuario;

/**
 * Servlet implementation class MuestraAlimentos
 */
@WebServlet("/MuestraAlimentos")
public class MuestraAlimentos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CategoriaEJB categoriaEJB;
	
	@EJB
	AlimentoEJB alimentoEJB;
	
	@EJB
	Sesiones sesionEJB;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesion = request.getSession(true);

		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		if (user == null || user.getId() == 0 && user.getRol() == 0) {
			response.sendRedirect("Main");
		} else {

			if (user.getRol() == 1) {
				response.sendRedirect("Main");
			} else if (user.getRol() == 2 || user.getRol() == 3) {

				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/alimentos.jsp");
				rs.forward(request, response);
			} else {

				response.sendRedirect("Main");
			}

		}
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
