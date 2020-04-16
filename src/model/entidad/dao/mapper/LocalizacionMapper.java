package model.entidad.dao.mapper;

import org.apache.ibatis.annotations.Param;

import model.entidad.Localizacion;

/**
 * Interfaz del mapper de la entidad Localizacion
 * @author HIBAN
 *
 */
public interface LocalizacionMapper {
	public Localizacion LocalizacionPorId(@Param("id") int id);

}
