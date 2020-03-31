package model.entidad.dao.mapper;

import org.apache.ibatis.annotations.Param;

import model.entidad.Localizacion;

public interface LocalizacionMapper {
	public Localizacion LocalizacionPorId(@Param("id") int id);

}
