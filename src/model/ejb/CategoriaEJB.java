package model.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Categoria;
import model.entidad.dao.CategoriaDAO;

@Stateless
@LocalBean
public class CategoriaEJB {
	
	public Categoria categoriaPorId(int id) {
		CategoriaDAO c = new CategoriaDAO();
		
		
		return c.CategoriaPorId(id);
	}
	
	public ArrayList<Categoria> busquedaCategorias(){
		CategoriaDAO c = new CategoriaDAO();
		
		
		return c.busquedaCategorias();
	}

}
