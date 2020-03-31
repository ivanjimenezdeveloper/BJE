package model.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.entidad.Restaurante;
import model.entidad.dao.RestauranteDAO;

@Stateless
@LocalBean
public class RestauranteEJB {
	public Restaurante RestaurantePorId(int id) {
		
		RestauranteDAO r = new RestauranteDAO();
		
		return r.restaurantePorId(id);
		
	}
}
