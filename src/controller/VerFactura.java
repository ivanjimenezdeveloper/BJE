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

import model.ejb.FacturaEJB;
import model.ejb.HoraVentaEJB;
import model.ejb.Sesiones;
import model.entidad.Factura;
import model.entidad.Usuario;

/**
 * Servlet implementation class VerFactura
 */
@WebServlet("/VerFactura")
public class VerFactura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@EJB
	FacturaEJB facturaEJB;
	
	@EJB
	HoraVentaEJB horaVentaEJB;
	
	@EJB
	Sesiones sesionEJB;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		// recuperamos la sesion
		HttpSession sesion = request.getSession(true);
		
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		String fecha;
		fecha = request.getParameter("anyo");

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
			if (modoTrabajo == 1) {
				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexTrabajo.jsp");
				rs.forward(request, response);
			} else {
				
				// Segun el rol redirige al main o continua con la operacion
				if (user.getRol() == 1) {

					response.sendRedirect("Main");
				} else if (user.getRol() == 2 || user.getRol() == 3) {

					//si los datos son correctos envia al selector de facturas
					if (fecha == null || fecha.equals("")) {
						
						//recupera las facturas
						ArrayList<Factura> arrF = facturaEJB.getFacturasPorRestaurante(user.getRestaurante());

						//guarda las facturas en la sesion
						sesion.setAttribute("facturas", arrF);

						RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/selectorFactura.jsp");
						rs.forward(request, response);
					} else {
						doPost(request, response);
					}
				} else {

					response.sendRedirect("Main");

				}

			}
		}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		// recuperamos la sesion
		HttpSession sesion = request.getSession(true);
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);

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
					
					//recupera los parametros
					String fecha;
					fecha = request.getParameter("fecha");

					//guarda los atributos
					sesion.setAttribute("fecha", fecha);

					//redirige
					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/visualizarFactura.jsp");
					rs.forward(request, response);
				} else {

					response.sendRedirect("Main");

				}

			}
		}
		
	}

}
