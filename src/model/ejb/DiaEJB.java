package model.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Dia;
import model.entidad.dao.DiaDAO;

@Stateless
@LocalBean
public class DiaEJB {

	public Dia diaPorId(int id) {
		DiaDAO d = new DiaDAO();
		
		return d.DiaPorId(id);
	}
}
