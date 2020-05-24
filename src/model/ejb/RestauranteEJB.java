package model.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Restaurante;
import model.entidad.dao.RestauranteDAO;

/**
 * EJB de restaurante
 * @author HIBAN
 *
 */
@Stateless
@LocalBean
public class RestauranteEJB {
	
	/**
	 * Busca un restaurante por su id
	 * @param id id del restaurante
	 * @return Objeto del restaurante
	 */
	public Restaurante RestaurantePorId(int id) {
		
		RestauranteDAO r = new RestauranteDAO();
		
		return r.restaurantePorId(id);
		
	}
	
	/**
	 * Busca todos los restaurantes de la base de datos
	 * @return Arraylist del objeto restaurante
	 */
	public ArrayList<Restaurante> busquedaRestaurantes(){
		RestauranteDAO r = new RestauranteDAO();
		
		
		return r.busquedaRestaurantes();
	}
}
