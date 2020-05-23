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
import model.ejb.HorarioEJB;
import model.ejb.Sesiones;
import model.entidad.Dia;
import model.entidad.Horario;
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
	
	@EJB
	HorarioEJB horarioEJB;
	
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
		int mes =0, anyo =0, diasMaximo = 0; 
		ArrayList<Dia> dia;
		
		
		try {
			userHorario = (Usuario) sesion.getAttribute("usuarioHorario");
			mes = (int) sesion.getAttribute("mesHorario");
			anyo = (int) sesion.getAttribute("anyoHorario");
			diasMaximo = (int) sesion.getAttribute("diasMaximo");
			
			
		}catch (Exception e) {

		}
		
		if(userHorario != null || userHorario.getId() != 0 || mes != 0 || anyo != 0) {
			dia = diaEJB.horarioUsuarioFecha(userHorario, mes, anyo);
			String fecha ="";
			int existe =0;
			ArrayList<Dia> arrDia = new ArrayList<Dia>(); 
			for(int i =1; i<= diasMaximo; i++) {
				Dia diaAdd = new Dia();
				diaAdd.setEntrada_1(request.getParameter("entrada1"+i));
				diaAdd.setEntrada_2(request.getParameter("entrada2"+i));
				diaAdd.setSalida_1(request.getParameter("salida1"+i));
				diaAdd.setSalida_2(request.getParameter("salida2"+i));
				fecha = String.format("%02d", anyo)+"-"+String.format("%02d", mes)+"-"+String.format("%02d", i);
				diaAdd.setFecha(fecha);
				
				diaAdd.setUsuario(userHorario.getId());
				
				existe = diaEJB.existeDia(userHorario, fecha);
				
				if(!diaAdd.getEntrada_1().equals("") && !diaAdd.getEntrada_1().equals("") && existe == 0) {
					Horario h =horarioEJB.horarioIdPorMesAnyo(mes, anyo);

					
					diaEJB.insertarDia(diaAdd, h.getId());
				}
				
			}
			
			response.sendRedirect("Main");
	
		}
		
		
		
		
	}

}
