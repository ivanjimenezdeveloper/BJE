package model.entidad.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import model.entidad.Timer;

public interface TimerMapper {
	
	public ArrayList<Timer> timersActivos (@Param("idRestaurante")int idRestaurante);
	
	public void addTimer(@Param("idAlimento") int idAlimento, @Param("fecha") int fecha, @Param("idRestaurante")int idRestaurante);

	public void eliminaTimer(@Param("timer")int id);
}
