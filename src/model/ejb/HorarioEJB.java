package model.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Horario;
import model.entidad.dao.HorarioDAO;

@Stateless
@LocalBean
public class HorarioEJB {

	public Horario horarioPorId(int id) {
		HorarioDAO h = new HorarioDAO();
		
		return h.horarioPorId(id);
	}
}
