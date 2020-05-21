package model.entidad.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import model.entidad.Timer;

public interface TimerMapper {
	
	public ArrayList<Timer> timersActivos (@Param("idRestaurante")int idRestaurante);
	
	public void addTimer(@Param("idAlimento") int idAlimento, @Param("fecha") int fecha, @Param("idRestaurante")int idRestaurante);

}
