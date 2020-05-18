package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ejb.TimerEJB;
import model.entidad.Timer;

/**
 * Servlet implementation class MuestraTimers
 */
@WebServlet("/MuestraTimers")
public class MuestraTimers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	TimerEJB timerEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Timer> timers = timerEJB.timersActivos();

		for (Timer t : timers) {

			response.getWriter().println(t.getId());
			response.getWriter().println(t.getIdAlimento());
			response.getWriter().println(timerEJB.getHMS(10000));
			response.getWriter().println(t.getIdcategoria());
			response.getWriter().println("==================");

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
