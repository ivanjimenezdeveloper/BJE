package model.ejb;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Alimento;
import model.entidad.Timer;
import model.entidad.dao.TimerDAO;

/**
 * EJB de timers
 * 
 * @author HIBAN
 *
 */
@Stateless
@LocalBean
public class TimerEJB {

	/**
	 * EJB para trabajar con alimentos
	 */
	@EJB
	AlimentoEJB alimentoEJB;

	/**
	 * Devuelve todos los timers activos por un restaurante
	 * 
	 * @param idRestaurante
	 * @return
	 */
	public ArrayList<Timer> timersActivos(int idRestaurante) {
		TimerDAO t = new TimerDAO();

		return t.timersActivos(idRestaurante);

	}

	public void eliminaTimer(int id) {
		TimerDAO t = new TimerDAO();
		t.eliminaTimer(id);
	}

	/**
	 * Convierte los segundos en horas, minutos y segundos
	 * 
	 * @param segundosUnix segundos a convertir
	 * @return Arraylist con integers con horas, minutos y segundos
	 */
	public ArrayList<Integer> getHMS(Integer segundosUnix) {

		double minutos, segundo;
		int horasEx, minutosEx, segundosEx;
		segundo = segundosUnix;
		ArrayList<Integer> tiempoHMS = new ArrayList<Integer>();

		// Pasa los segundos a minutos horas y segundos redondeando a lo bajo
		minutos = (segundo / 60);
		horasEx = (int) Math.floor(minutos / 60);
		minutosEx = (int) (minutos - (horasEx * 60));
		segundosEx = (int) ((minutos - (horasEx * 60)) * 60 - minutosEx * 60);

		// se añaden a la arraylist
		tiempoHMS.add(horasEx);
		tiempoHMS.add(minutosEx);
		tiempoHMS.add(segundosEx);

		return tiempoHMS;

	}

	/**
	 * Añade un timer activo al restaurante
	 * 
	 * @param idAlimento    id del alimento del que añadir el timer
	 * @param idRestaurante id del restaurante al que añadir el timer
	 */
	public void addTimer(int idAlimento, int idRestaurante) {

		TimerDAO t = new TimerDAO();

		Alimento a = alimentoEJB.alimentoPorId(idAlimento);

		// pasa el tiempo a segundos
		a.setTiempo(a.getTiempo() * 60);

		// añade el timer
		t.addTimer(a.getId(), a.getTiempo(), idRestaurante);

	}

	/**
	 * A partir de una fecha HH:MM:SS saca los segundos
	 * 
	 * @param fechaHMS fecha en formato HH:MM:SS
	 * @return Integer de los segundos o -1 si falla
	 */
	public Integer getSeconds(String fechaHMS) {

		// divide el string segun :
		String[] string = fechaHMS.split(":");
		int horas, minutos, seconds;

		// Parsea los string a integers o devuelve -1
		try {
			horas = Integer.parseInt(string[0]);
			minutos = Integer.parseInt(string[1]);
			seconds = Integer.parseInt(string[2]);
		} catch (Exception e) {
			return -1;
		}

		// devuelve los segundos
		return (horas * 60 * 60) + (minutos * 60) + seconds;
	}

}
