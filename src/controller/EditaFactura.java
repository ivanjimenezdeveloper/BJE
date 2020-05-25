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

import model.ejb.FacturaEJB;
import model.ejb.HoraVentaEJB;
import model.ejb.Sesiones;
import model.entidad.Factura;
import model.entidad.HoraVenta;
import model.entidad.Horario;
import model.entidad.Usuario;

/**
 * Servlet implementation class EditaFactura
 */
@WebServlet("/EditaFactura")
public class EditaFactura extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con las sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	@EJB
	HoraVentaEJB horaVentaEJB;
	
	@EJB
	FacturaEJB facturaEJB;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

					String fecha;

					try {
						fecha = request.getParameter("fecha");
					} catch (Exception e) {
						fecha = "";
					}

					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/editaFactura.jsp");
					rs.forward(request, response);
				} else {

					response.sendRedirect("Main");

				}
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

					String fecha = "";

					// recupera los parametros
					try {
						fecha = sesion.getAttribute("fecha").toString();

					} catch (Exception e) {

					}

					Factura f = new Factura();
					
					f = facturaEJB.facturaIdFecha(fecha);
					for (int i = 0; i <= 30; i++) {
						Double venta;
						HoraVenta h = new HoraVenta();

						try {
							venta = Double.parseDouble(request.getParameter("venta" + i));

						} catch (Exception e) {
							venta = 0.0;
						}
						h.setHora(i);
						h.setVenta(venta);
						h.setIdFactura(f.getId());
						
						HoraVenta horaComparation = horaVentaEJB.existeVenta(h.getIdFactura(), user.getRestaurante(), h.getHora());
						// si no existe un dia para ese usuario con esa fecha en lugar de editar inserta
						if ( horaComparation == null || horaComparation.getIdFactura() == 0 ) {

							horaVentaEJB.insertaHoraVenta(h, user.getRestaurante());
						} else {

							horaVentaEJB.editaHoraVenta(h, user.getRestaurante());
						}

					}

					response.sendRedirect("VerFactura");

				} else {

					response.sendRedirect("Main");

				}
			}

		}
	}

}
