package model.entidad.dao.mapper;

import org.apache.ibatis.annotations.Param;

import model.entidad.Dia;
import model.entidad.Rol;

public interface DiaMapper {
	public Dia DiaPorId(@Param("id") int id);

}
