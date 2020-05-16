package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.ejb.Sesiones;
import model.ejb.UsuarioEJB;
import model.entidad.Usuario;

/**
 * Servlet implementation class BorrarUsuario
 */
@WebServlet("/BorrarUsuario")
public class BorrarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	UsuarioEJB usuarioEJB;
	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		int id= 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		usuarioEJB.eliminaUsuario(id);

		response.sendRedirect("GestionUsuario");

		
	}



}
