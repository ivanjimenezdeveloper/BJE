package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.ejb.DiaEJB;
import model.ejb.HorarioEJB;
import model.ejb.LocalizacionEJB;
import model.ejb.PlantillaEJB;
import model.ejb.RestauranteEJB;
import model.ejb.RolEJB;
import model.ejb.SemanaEJB;
import model.ejb.UsuarioEJB;

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
	SemanaEJB semanaEJB;
	
	@EJB
	HorarioEJB horarioEJB;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append(rolEJB.RolPorId(1).getNombre());
		
		response.getWriter().append("\n"+plantillaEJB.PlantillaPorId(1).getId());
		
		response.getWriter().append("\n"+localizacionEJB.LocalizacionPorId(1).getNombre());

		response.getWriter().append("\n"+usuarioEJB.UsuarioPorId(1).getNombre());
		
		response.getWriter().append("\n"+restauranteEJB.RestaurantePorId(1).getGerente());
		
		response.getWriter().append("\n"+diaEJB.diaPorId(1).getFecha());
		
		response.getWriter().append("\n"+semanaEJB.semanaPorId(1).getJueves());
		
		response.getWriter().append("\n"+horarioEJB.horarioPorId(1).getSemana_1());





		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
