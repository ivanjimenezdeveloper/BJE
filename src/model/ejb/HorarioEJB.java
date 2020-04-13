package model.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Horario;
import model.entidad.dao.HorarioDAO;

@Stateless
@LocalBean
public class HorarioEJB {

	public ArrayList<Horario> horarioPorId(int id) {
		HorarioDAO h = new HorarioDAO();
		
		return h.horarioPorId(id);
	}
}
