package model.entidad.dao.mapper;

import org.apache.ibatis.annotations.Param;

import model.entidad.Horario;

public interface HorarioMapper {
	public Horario HorarioPorId(@Param("id") int id);

}
