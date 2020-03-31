package model.entidad.dao.mapper;

import org.apache.ibatis.annotations.Param;

import model.entidad.Semana;

public interface SemanaMapper {
	public Semana SemanaPorId(@Param("id") int id);

}
