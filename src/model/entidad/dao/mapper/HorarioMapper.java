package model.entidad.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import model.entidad.Horario;

/**
 * Interfaz del mapper de la entidad Horario
 * @author HIBAN
 *
 */
public interface HorarioMapper {
	public ArrayList<Horario> HorarioPorId(@Param("id") int id);

}
