package model.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import controller.Main;
import model.entidad.Alimento;
import model.entidad.dao.AlimentoDAO;

/**
 * EJB de alimentos
 * @author HIBAN
 *
 */
@Stateless
@LocalBean
public class AlimentoEJB {
	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);

	/**
	 * Busca un alimento por su id
	 * @param id id del alimento
	 * @return objeto alimento
	 */
	public Alimento alimentoPorId(int id) {
		AlimentoDAO a = new AlimentoDAO();

		return a.AlimentoPorId(id);
	}

	/**
	 * Busca todos los alimentos
	 * @return Arraylist de alimentos
	 */
	public ArrayList<Alimento> busquedaAlimentos() {
		AlimentoDAO a = new AlimentoDAO();

		return a.busquedaAlimentos();
	}

	/**
	 * Edita un alimento
	 * @param alimento objeto alimento que editar
	 */
	public void editaAlimento(Alimento alimento) {
		AlimentoDAO a = new AlimentoDAO();

		//desglosa el alimento en sus campos
		a.editaAlimento(alimento.getNombre(), alimento.getCategoria(), alimento.getId(), alimento.getTiempo());
	}

	/**
	 * Desglosa una string de tiempo con el formato 00:00 de horas y minutos
	 * @param tiempo String con el formato 00:00 que guarda minutos y horas
	 * @return el tiempo devuelve el tiempo en minutos
	 */
	public int desglosaTiempoFormulario(String tiempo) {
		int minutos = 0, horas = 0, resultado;

		try {
			//divide el string y lo convierte a integer
			horas = Integer.parseInt(tiempo.substring(0, 2));
			minutos = Integer.parseInt(tiempo.substring(3));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		//pasa horas a minutos y los suma
		resultado = (horas *60)+ minutos;
		return resultado;
	}

	/**
	 * Crea un alimento
	 * @param alimento alimento a crear
	 */
	public void creaAlimento(Alimento alimento) {
		AlimentoDAO a = new AlimentoDAO();

		a.creaAlimento(alimento.getNombre(), alimento.getCategoria(), alimento.getTiempo());		
	}

}
