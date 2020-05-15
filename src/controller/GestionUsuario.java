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
import model.ejb.HorarioEJB;
import model.ejb.LocalizacionEJB;
import model.ejb.RestauranteEJB;
import model.ejb.RolEJB;
import model.ejb.Sesiones;
import model.ejb.UsuarioEJB;
import model.entidad.Restaurante;
import model.entidad.Rol;
import model.entidad.Usuario;

/**
 * Servlet implementation class GestionUsuario
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
	

	@EJB
	HorarioEJB horarioEJB;
	
	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		ArrayList<Usuario> usuarios = usuarioEJB.busquedaUsuarios();
		HttpSession sesion = request.getSession(true);

		
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		Restaurante restaurante = restauranteEJB.RestaurantePorId(user.getRestaurante());
		
		sesion.setAttribute("usuarios", usuarios);
		sesion.setAttribute("restaurante", restaurante);

		RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/muestraUsuarios.jsp");
		rs.forward(request, response);
		

	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
