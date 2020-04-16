package model.entidad.dao.mapper;

import org.apache.ibatis.annotations.Param;

import model.entidad.Restaurante;

/**
 * Interfaz del mapper de la entidad Restaurante
 * @author HIBAN
 *
 */
public interface RestauranteMapper {
	public Restaurante RestaurantePorId(@Param("id") int id);

}
