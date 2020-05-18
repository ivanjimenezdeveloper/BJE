package model.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Timer;
import model.entidad.dao.TimerDAO;

@Stateless
@LocalBean
public class TimerEJB {

	public ArrayList<Timer> timersActivos() {
		TimerDAO t = new TimerDAO();

		return t.timersActivos();

	}

	public ArrayList<Integer> getHMS(Integer segundosUnix) {

		double minutos, segundo;
		int horasEx, minutosEx, segundosEx;
		segundo = segundosUnix;
		ArrayList<Integer> tiempoHMS = new ArrayList<Integer>();

		minutos = (segundo / 60);
		horasEx = (int) Math.floor(minutos / 60);
		minutosEx = (int) (minutos - (horasEx * 60));
		segundosEx = (int) ((minutos - (horasEx * 60)) * 60 - minutosEx * 60);
		tiempoHMS.add(horasEx);
		tiempoHMS.add(minutosEx);
		tiempoHMS.add(segundosEx);

		return tiempoHMS;

	}

}
