package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import javax.servlet.http.HttpSession;
import model.MyBatisUtil;
import model.ejb.DiaEJB;
import model.ejb.HorarioEJB;
import model.ejb.LocalizacionEJB;
import model.ejb.PlantillaEJB;
import model.ejb.RestauranteEJB;
import model.ejb.RolEJB;
import model.ejb.SemanaEJB;
import model.ejb.Sesiones;
import model.ejb.UsuarioEJB;
import model.entidad.Usuario;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);

	@EJB
	RolEJB rolEJB;
	
	@EJB
	PlantillaEJB plantillaEJB;
	
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

//		response.getWriter().append(rolEJB.RolPorId(1).getNombre());
		
//		response.getWriter().append("\n"+plantillaEJB.PlantillaPorId(1).getId());
		
//		response.getWriter().append("\n"+localizacionEJB.LocalizacionPorId(1).getNombre());

//		response.getWriter().append("\n"+usuarioEJB.UsuarioPorId(3).getNombre());
		
//		response.getWriter().append("\n"+restauranteEJB.RestaurantePorId(1).getLocalizacion());
		
//		response.getWriter().append("\n"+diaEJB.diaPorId(3).getFecha());
		
//		response.getWriter().append("\n"+semanaEJB.semanaPorId(1).getJueves());
		
//		response.getWriter().append("\n"+horarioEJB.horarioPorId(1));

		boolean ver = false;
		HttpSession sesion = request.getSession(true);
		Usuario userprueba = usuarioEJB.UsuarioPorId(3);
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		
		if (user == null) {
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/login.jsp");
			rs.forward(request, response);
		}else {
			response.getWriter().append(user.getNombre());
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
