package model.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Horario;
import model.entidad.dao.HorarioDAO;

@Stateless
@LocalBean
public class HorarioEJB {
	
	public Horario horarioPorId(int id) {
		HorarioDAO h = new HorarioDAO();
		
		return h.horarioPorDia(id);
	}
	
	public ArrayList<Horario> horariosPorRestaurante(int idRestaurante) {
		HorarioDAO h = new HorarioDAO();
		
		return h.horariosPorRestaurante(idRestaurante);
	}
	
	public ArrayList<Horario> getHorarios() {
		HorarioDAO h = new HorarioDAO();
		
		return h.getHorarios();
	}

	public void creaHorarioGeneral(boolean activo, int mes, int anyo) {
		HorarioDAO h = new HorarioDAO();

		int activoInsert = (activo) ? 1 : 0;
		
		h.creaHorarioGeneral(activoInsert, mes, anyo);
	}
	
	
}
