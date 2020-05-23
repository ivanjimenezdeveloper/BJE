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
import model.ejb.UsuarioEJB;
import model.entidad.Dia;
import model.entidad.Horario;
import model.entidad.Usuario;

/**
 * Servlet implementation class EditaHorario
 */
@WebServlet("/EditaHorario")
public class EditaHorario extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@EJB
	Sesiones sesionEJB;
	
	@EJB
	DiaEJB diaEJB;
	
	@EJB
	UsuarioEJB usuarioEJB;
	
	@EJB
	HorarioEJB horarioEJB;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		HttpSession sesion = request.getSession(true);
		// Obtenemos el usuario de la sesion si existe
		Usuario user = sesionEJB.usuarioLogeado(sesion);
		ArrayList<Usuario> usuarios = usuarioEJB.busquedaUsuarios(user.getRestaurante());
		sesion.setAttribute("usuarios", usuarios);
		int idUsuario = 0;
		int mes =0, anyo =0; 
		try {
			idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
			mes = Integer.parseInt( sesion.getAttribute("mes").toString());
			anyo = Integer.parseInt(sesion.getAttribute("anyo").toString());
			
			sesion.setAttribute("idUsuario", idUsuario);
			sesion.setAttribute("mes", mes);
			sesion.setAttribute("anyo", anyo);

		}catch (Exception e) {
			
			idUsuario = 0;
			mes = 0;
			anyo = 0;

		}

		
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/dist/editaHorario.jsp");
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
			ArrayList<Dia> arrDia = new ArrayList<Dia>(); 
			Calendar c = new Calendar.Builder().setCalendarType("iso8601")
					.setDate(anyo, mes-1, 1).build();
			
			int diaMaximo = c.getActualMaximum(Calendar.DATE);
			for(int i =1; i<= diaMaximo; i++) {
				Dia diaAdd = new Dia();
				diaAdd.setEntrada_1(request.getParameter("entrada1"+i));
				diaAdd.setEntrada_2(request.getParameter("entrada2"+i));
				diaAdd.setSalida_1(request.getParameter("salida1"+i));
				diaAdd.setSalida_2(request.getParameter("salida2"+i));
				
				diaAdd.setFecha(anyo+"-"+mes+"-"+i);
				
				diaAdd.setUsuario(userHorario.getId());
				
				if(diaEJB.existeDia(userHorario, diaAdd.getFecha()) == 0) {
					
					Horario h =horarioEJB.horarioIdPorMesAnyo(mes, anyo);
					
					diaEJB.insertarDia(diaAdd, h.getId());
				}else {
					diaEJB.editaDia(diaAdd);
				}
				

			}
			
			response.sendRedirect("VisualizarHorario");
	
		}
	}

}
