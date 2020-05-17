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
import model.ejb.Sesiones;
import model.ejb.UsuarioEJB;
import model.entidad.Usuario;

/**
 * Servlet implementation class Perfil
 */
@WebServlet("/Perfil")
public class Perfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Perfil.class);
	
	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;
	
	@EJB
	UsuarioEJB usuarioEJB;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);

		if (user == null ||user.getId() == 0 && user.getRol() == 0) {
			RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/login.jsp");
			rs.forward(request, response);
		} else {

			if (user.getRol() == 1) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/perfilUsuario.jsp");
				rs.forward(request, response);
			} else if (user.getRol() == 2 || user.getRol() == 3) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/perfilManager.jsp");
				rs.forward(request, response);
			} else {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/login.jsp");
				rs.forward(request, response);
			}

		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre, apellido, correo, pass, passConfirm;

		HttpSession sesion = request.getSession(true);
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		
		

		if (user == null || user.getId() == 0 && user.getRol() == 0) {
			response.sendRedirect("Main");

		} else {

			if (user.getRol() == 1 || user.getRol() == 2 || user.getRol() == 3) {

				
				nombre = request.getParameter("nombre");
				apellido = request.getParameter("apellido");
				correo = request.getParameter("correo");
				pass = request.getParameter("password");
				passConfirm = request.getParameter("passwordConfirm");


				user.setNombre(nombre);
				user.setApellido(apellido);
				user.setCorreo(correo);
				if(pass.equals(passConfirm) && !pass.equals("") && pass != null) {
					user.setPass(passConfirm);
				}


				usuarioEJB.editaPerfil(user);

				response.sendRedirect("Perfil");
			} else {

				response.sendRedirect("Main");

			}
	}


}}
