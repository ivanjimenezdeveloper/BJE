package model.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Dia;
import model.entidad.Usuario;
import model.entidad.dao.DiaDAO;

@Stateless
@LocalBean
public class DiaEJB {

	public Dia diaPorId(int id) {
		DiaDAO d = new DiaDAO();
		
		return d.DiaPorId(id);
	}
	
	
	public ArrayList<Dia> horarioUsuario(Usuario u, int mes, int anyo){
		DiaDAO d = new DiaDAO();
		
		
		return d.horarioUsuario(u.getId(), mes, anyo);
		
	}
}
