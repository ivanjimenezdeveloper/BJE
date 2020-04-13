package model.entidad.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import model.entidad.Horario;

public interface HorarioMapper {
	public ArrayList<Horario> HorarioPorId(@Param("id") int id);

}
