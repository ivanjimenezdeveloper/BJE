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
 * Edita un horario
 * @author HIBAN
 *
 */
@WebServlet("/EditaHorario")
public class EditaHorario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * EJB para trabajar con las sesiones
	 */
	@EJB
	Sesiones sesionEJB;
	
	/**
	 * EJB para trabajar con los dias
	 */
	@EJB
	DiaEJB diaEJB;
	
	/**
	 * EJB para trabajar con los usuarios
	 */
	@EJB
	UsuarioEJB usuarioEJB;
	
	/**
	 * EJB para trabajar con los horarios
	 */
	@EJB
	HorarioEJB horarioEJB;
	
	/**
	 * Muestra el formulario de edicion de horarios
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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
					
					//recupera los usuarios del restaurante y lo guarda en la sesion
					ArrayList<Usuario> usuarios = usuarioEJB.busquedaUsuarios(user.getRestaurante());
					sesion.setAttribute("usuarios", usuarios);
					int idUsuario = 0;
					int mes =0, anyo =0; 
					
					//recupera los parametros y los guarda en la sesion
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
				} else {

					response.sendRedirect("Main");

				}
			}

		}

	}

	/**
	 * Edita el horario
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
					
					Usuario userHorario = null;
					int mes =0, anyo =0; 
					ArrayList<Dia> dia;
					
					//recupera los parametros
					try {
						userHorario = (Usuario) sesion.getAttribute("usuarioHorario");
						mes = (int) sesion.getAttribute("mesHorario");
						anyo = (int) sesion.getAttribute("anyoHorario");
						
						
					}catch (Exception e) {

					}
					
					String cuerpo;
					
					
					cuerpo = "<p>Se ha creado o editado un horario en nuestra plataforma BJE <p>";
					cuerpo += "<p>Correo: " + user.getCorreo() + "</p>";
					cuerpo += "<p>Fecha " +mes +" de " + anyo + "</p>";
					cuerpo += "<br><p>Si ha recibido este correo de forma erronea le pedimos que lo borre." + "</p>";
					cuerpo += "<p>Atentamente, el equipo de Better Job Environment" + "</p>";
					
					//si los parametros son validos sigue
					if(userHorario != null || userHorario.getId() != 0 || mes != 0 || anyo != 0) {
						
						//a partir de la fecha dada saco la cantidad de dias que tiene ese mes en concreto
						Calendar c = new Calendar.Builder().setCalendarType("iso8601")
								.setDate(anyo, mes-1, 1).build();
						
						int diaMaximo = c.getActualMaximum(Calendar.DATE);
						
						//bucle que edita el horario segun la cantidad de dias del mes
						for(int i =1; i<= diaMaximo; i++) {
							
							//reinicia el objeto Dia
							Dia diaAdd = new Dia();
							
							//recupera los parametros
							diaAdd.setEntrada_1(request.getParameter("entrada1"+String.format("%02d",i)));
							diaAdd.setEntrada_2(request.getParameter("entrada2"+String.format("%02d",i)));
							diaAdd.setSalida_1(request.getParameter("salida1"+String.format("%02d",i)));
							diaAdd.setSalida_2(request.getParameter("salida2"+String.format("%02d",i)));
							
							diaAdd.setFecha(anyo+"-"+mes+"-"+i);
							
							diaAdd.setUsuario(userHorario.getId());
							
							
							//si no existe un dia para ese usuario con esa fecha en lugar de editar inserta
							if(diaEJB.existeDia(userHorario, diaAdd.getFecha()) == 0) {
								//saca el horario general al que debe pertenecer este dia
								Horario h =horarioEJB.horarioIdPorMesAnyo(mes, anyo);
								
								
								diaEJB.insertarDia(diaAdd, h.getId());
							}else {
								//edita el dia
								diaEJB.editaDia(diaAdd);
							}
							

						}
						
						diaEJB.enviaCorreo(cuerpo, "Se ha creado o editado un horario suyo", userHorario.getCorreo());

						
						response.sendRedirect("VisualizarHorario");
				
					}else {
						response.sendRedirect("Main");
					}
				} else {

					response.sendRedirect("Main");

				}
			}

		}
		

	}

}
