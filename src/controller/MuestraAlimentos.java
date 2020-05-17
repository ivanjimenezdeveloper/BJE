package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ejb.AlimentoEJB;
import model.ejb.CategoriaEJB;
import model.entidad.Alimento;
import model.entidad.Categoria;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Categoria> arrC = categoriaEJB.busquedaCategorias();
		ArrayList<Alimento> arrA = alimentoEJB.busquedaAlimentos();

		for (Categoria c : arrC) {

			response.getWriter().println(c.getNombre());

		}
		
		for(Alimento a : arrA) {
			response.getWriter().println(a.getNombre());
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
