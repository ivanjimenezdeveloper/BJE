package model.entidad.dao.mapper;

import org.apache.ibatis.annotations.Param;

import model.entidad.Rol;

public interface RolMapper {
	
	public Rol RolPorId(@Param("id") int id);

}
