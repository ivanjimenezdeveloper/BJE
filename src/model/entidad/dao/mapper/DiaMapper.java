package model.entidad.dao.mapper;

import org.apache.ibatis.annotations.Param;

import model.entidad.Dia;
/**
 * Interfaz del mapper de la entidad Dia
 * @author HIBAN
 *
 */
public interface DiaMapper {
	public Dia DiaPorId(@Param("id") int id);

}
