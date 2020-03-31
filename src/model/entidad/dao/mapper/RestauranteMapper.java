package model.entidad.dao.mapper;

import org.apache.ibatis.annotations.Param;

import model.entidad.Restaurante;

public interface RestauranteMapper {
	public Restaurante RestaurantePorId(@Param("id") int id);

}
