package model.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Categoria;
import model.entidad.dao.CategoriaDAO;

/**
 * EJB de categoria
 * @author HIBAN
 *
 */
@Stateless
@LocalBean
public class CategoriaEJB {
	
	/**
	 * Saca una categoria a partir de una id
	 * @param id id de la categoria
	 * @return objeto de Categoria
	 */
	public Categoria categoriaPorId(int id) {
		CategoriaDAO c = new CategoriaDAO();
		
		
		return c.CategoriaPorId(id);
	}
	
	/**
	 * Busca todas las categorias
	 * @return Arraylist de categorias
	 */
	public ArrayList<Categoria> busquedaCategorias(){
		CategoriaDAO c = new CategoriaDAO();
		
		
		return c.busquedaCategorias();
	}

}
