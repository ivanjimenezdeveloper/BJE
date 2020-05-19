package model.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import controller.Main;
import model.entidad.Alimento;
import model.entidad.dao.AlimentoDAO;

@Stateless
@LocalBean
public class AlimentoEJB {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);

	public Alimento alimentoPorId(int id) {
		AlimentoDAO a = new AlimentoDAO();

		return a.AlimentoPorId(id);
	}

	public ArrayList<Alimento> busquedaAlimentos() {
		AlimentoDAO a = new AlimentoDAO();

		return a.busquedaAlimentos();
	}

	public void editaAlimento(Alimento alimento) {
		AlimentoDAO a = new AlimentoDAO();

		a.editaAlimento(alimento.getNombre(), alimento.getCategoria(), alimento.getId(), alimento.getTiempo());
	}

	public int desglosaTiempoFormulario(String tiempo) {
		int minutos = 0, horas = 0, resultado;

		try {
			horas = Integer.parseInt(tiempo.substring(0, 2));
			minutos = Integer.parseInt(tiempo.substring(3));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		resultado = (horas *60)+ minutos;
		return resultado;
	}

	public void creaAlimento(Alimento alimento) {
		AlimentoDAO a = new AlimentoDAO();

		a.creaAlimento(alimento.getNombre(), alimento.getCategoria(), alimento.getTiempo());		
	}

}
