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
import model.ejb.Sesiones;
import model.entidad.Dia;
import model.entidad.Horario;
import model.entidad.Usuario;

/**
 * Servlet para crear horarios
 * 
 * @author HIBAN
 *
 */
@WebServlet("/CreaHorario")
public class CreaHorario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con sesiones
	 */
	@EJB
	Sesiones sesionEJB;

	/**
	 * EJB para trabajar con dias
	 */
	@EJB
	DiaEJB diaEJB;

	/**
	 * EJB para trabajar con horarios
	 */
	@EJB
	HorarioEJB horarioEJB;

	//____________________________________________________
	//ESTA COMENTADO PORQUE EN UN FUTURO SE VA A USAR
	//___________________________________________________
	
	
	
	
//	/**
//	 * Muestra el form de creacion de horarios generales
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// recuperamos la sesion
//		HttpSession sesion = request.getSession(true);
//		// Obtenemos el usuario de la sesion si existe
//		Usuario user = sesionEJB.usuarioLogeado(sesion);
//
//		int modoTrabajo;
//
//		// recupera si esta o no en modo trabajo
//		try {
//			modoTrabajo = (int) sesion.getAttribute("modoTrabajo");
//
//		} catch (Exception e) {
//			modoTrabajo = 0;
//		}
//
//		// Comprueba que el usurio sea valido y si no redirige al main
//		if (user == null || user.getId() == 0 && user.getRol() == 0) {
//			response.sendRedirect("Main");
//
//		} else {
//
//			// Si el modo trabajo es 1 es que el modo trabajo esta activado y redirige al
//			// jsp del modo trabajo
//			if (modoTrabajo == 1) {
//				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexTrabajo.jsp");
//				rs.forward(request, response);
//			} else {
//				// Segun el rol redirige al main o continua con la operacion
//				if (user.getRol() == 1) {
//
//					response.sendRedirect("Main");
//				} else if (user.getRol() == 2 || user.getRol() == 3) {
//
//					int anyo = 2018;
//					int mes = 4;
//					int dia = 9;
//
//					sesion.setAttribute("anyo", anyo);
//					sesion.setAttribute("mes", mes);
//
//					Calendar c = new Calendar.Builder().setCalendarType("iso8601").setDate(anyo, mes, dia).build();
//
//					RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/creaHorario.jsp");
//					rs.forward(request, response);
//				} else {
//
//					response.sendRedirect("Main");
//
//				}
//			}
//
//		}
//	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		// recuperamos la sesion
//		HttpSession sesion = request.getSession(true);
//		// Obtenemos el usuario de la sesion si existe
//		Usuario user = sesionEJB.usuarioLogeado(sesion);
//		Usuario userHorario = null;
//		int mes = 0, anyo = 0, diasMaximo = 0;
//
//		// Recupero los atributos de creacion de horario
//		try {
//			userHorario = (Usuario) sesion.getAttribute("usuarioHorario");
//			mes = (int) sesion.getAttribute("mesHorario");
//			anyo = (int) sesion.getAttribute("anyoHorario");
//			diasMaximo = (int) sesion.getAttribute("diasMaximo");
//
//		} catch (Exception e) {
//
//			userHorario = null;
//
//		}
//
//		// recupera si esta o no en modo trabajo
//		int modoTrabajo;
//		try {
//			modoTrabajo = (int) sesion.getAttribute("modoTrabajo");
//
//		} catch (Exception e) {
//			modoTrabajo = 0;
//		}
//
//		// Comprueba que el usurio sea valido y si no redirige al main
//		if (user == null || user.getId() == 0 && user.getRol() == 0) {
//			response.sendRedirect("Main");
//
//		} else {
//			// Si el modo trabajo es 1 es que el modo trabajo esta activado y redirige al
//			// jsp del modo trabajo
//			if (modoTrabajo == 1) {
//				RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/indexTrabajo.jsp");
//				rs.forward(request, response);
//			} else {
//				// Segun el rol redirige al main o continua con la operacion
//				if (user.getRol() == 1) {
//
//					response.sendRedirect("Main");
//				} else if (user.getRol() == 2 || user.getRol() == 3) {
//					// comprueba que el usuario, mes y año sean validos
//					if (userHorario != null || userHorario.getId() != 0 || mes != 0 || anyo != 0) {
//
//						String fecha = "";
//						int existe = 0;
//
//						// por cada dia segun el mes dado se hace una pasada
//						for (int i = 1; i <= diasMaximo; i++) {
//							// reinicio el objeto diaAdd
//							Dia diaAdd = new Dia();
//
//							// añade las entradas y salidas con el formato 00, es decir si no hay 2 numeros
//							// añadira un 0 a la izquierda
//							diaAdd.setEntrada_1(request.getParameter("entrada1" + String.format("%02d", i)));
//							diaAdd.setEntrada_2(request.getParameter("entrada2" + String.format("%02d", i)));
//							diaAdd.setSalida_1(request.getParameter("salida1" + String.format("%02d", i)));
//							diaAdd.setSalida_2(request.getParameter("salida2" + String.format("%02d", i)));
//							fecha = String.format("%02d", anyo) + "-" + String.format("%02d", mes) + "-"
//									+ String.format("%02d", i);
//							
//							diaAdd.setFecha(fecha);
//
//							diaAdd.setUsuario(userHorario.getId());
//
//							//comprueba si existe el dia con asociado a este usuario
//							existe = diaEJB.existeDia(userHorario, fecha);
//
//							//si el dia es valido lo inserta si no, no hace nada
//							if (!diaAdd.getEntrada_1().equals("") && !diaAdd.getEntrada_1().equals("") && existe == 0) {
//								Horario h = horarioEJB.horarioIdPorMesAnyo(mes, anyo);
//
//								diaEJB.insertarDia(diaAdd, h.getId());
//							}
//
//						}
//
//						response.sendRedirect("Main");
//
//					}
//				} else {
//
//					response.sendRedirect("Main");
//
//				}
//			}
//
//		}
//
//	}

}
