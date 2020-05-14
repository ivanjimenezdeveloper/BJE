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

import model.ejb.Sesiones;
import model.ejb.UsuarioEJB;
import model.entidad.Usuario;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * EJB para trabajar con usuarios
	 */
	@EJB
	UsuarioEJB userEJB;

	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	/**
	 * Logea al usuario si es correcto o deslogea
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);

		Usuario user = new Usuario();
		
		String error = request.getParameter("error");
		String logout = request.getParameter("logout");
		
		user = sesionEJB.usuarioLogeado(sesion);
		
		
		//Si hay un parametro de logout hara logout
		if (logout != null) {
			sesionEJB.logoutUsuario(sesion);
			response.sendRedirect("Main");	
		} else {
			//Comprueba que haya error y en caso positivo redirecciona a login con error
			if (error != null) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/loginERROR.jsp");
				rs.forward(request, response);
			} else {
				//si hay un usuario redirige a main
				if (user != null) {
					response.sendRedirect("Main");	
				} else {
					//En caso de no haber usuario muestra la pagina de login
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/login.jsp");
					rs.forward(request, response);
				}
			}
		}

	}

	/**
	 * Hace las operaciones de login
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Coge los parametros de usuario y password
		String usuario = request.getParameter("user");
		String pass = request.getParameter("pass");

		Usuario user = new Usuario();
		HttpSession sesion = request.getSession(true);

		user = sesionEJB.usuarioLogeado(sesion);

		//si el usuario es nulo procedera a hacer login, en caso contrario redirigira a main
		if (user == null) {
			//comprueba que exista el usuario
			user = userEJB.existeUsuario(usuario, pass);

			//comprueba que no haya errores
			if (user == null || user.getCorreo() == null) {
				response.sendRedirect("Login?error=1");
			} else {

				//Lo guarda en la sesion
				sesionEJB.loginUsuario(sesion, user);
				response.sendRedirect("Main");
			}
		} else {
			response.sendRedirect("Main");
		}
	}

}
