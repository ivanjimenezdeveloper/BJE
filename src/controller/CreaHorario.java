package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ejb.DiaEJB;
import model.ejb.Sesiones;
import model.entidad.Dia;
import model.entidad.Usuario;

/**
 * Servlet implementation class CreaHorario
 */
@WebServlet("/CreaHorario")
public class CreaHorario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	Sesiones sesionEJB;
	
	@EJB
	DiaEJB diaEJB;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		
		int anyo = 2018;
		int mes = 4;
		int dia = 9;
		
		sesion.setAttribute("anyo", anyo);
		sesion.setAttribute("mes", mes);
		
		Calendar c = new Calendar.Builder().setCalendarType("iso8601")
				.setDate(anyo, mes, dia).build();
		
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/creaHorario.jsp");
		rs.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sesion = request.getSession(true);
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		Usuario userHorario = null;
		int mes =0, anyo =0; 
		ArrayList<Dia> dia;
		
		
		try {
			userHorario = (Usuario) sesion.getAttribute("usuarioHorario");
			mes = (int) sesion.getAttribute("mesHorario");
			anyo = (int) sesion.getAttribute("anyoHorario");
			
			
		}catch (Exception e) {

		}
		
		if(userHorario != null || userHorario.getId() != 0 || mes != 0 || anyo != 0) {
			dia = diaEJB.horarioUsuarioFecha(userHorario, mes, anyo);
			ArrayList<Dia> arrDia = new ArrayList<Dia>(); 
			for(Dia d : dia) {
				Dia diaAdd = new Dia();
				diaAdd.setEntrada_1(request.getParameter("entrada1"+diaEJB.getDia(d.getFecha())));
				diaAdd.setEntrada_2(request.getParameter("entrada2"+diaEJB.getDia(d.getFecha())));
				diaAdd.setSalida_1(request.getParameter("salida1"+diaEJB.getDia(d.getFecha())));
				diaAdd.setSalida_2(request.getParameter("salida2"+diaEJB.getDia(d.getFecha())));
				
				diaAdd.setFecha(d.getFecha());
				
				diaAdd.setUsuario(userHorario.getId());
				

			}
	
		}
		
		
		
		
	}

}
