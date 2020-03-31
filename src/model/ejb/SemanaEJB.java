package model.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Semana;
import model.entidad.dao.SemanaDAO;

@Stateless
@LocalBean
public class SemanaEJB {
	public Semana semanaPorId(int id) {
		SemanaDAO s = new SemanaDAO();

		return s.semanaPorId(id);
	}

}
