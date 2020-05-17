package model.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Alimento;
import model.entidad.dao.AlimentoDAO;

@Stateless
@LocalBean
public class AlimentoEJB {

	public Alimento alimentoPorId(int id) {
		AlimentoDAO a = new AlimentoDAO();
		
		
		return a.AlimentoPorId(id);
	}
	
	public ArrayList<Alimento> busquedaAlimentos(){
		AlimentoDAO a = new AlimentoDAO();

		return a.busquedaAlimentos();
	}
}
