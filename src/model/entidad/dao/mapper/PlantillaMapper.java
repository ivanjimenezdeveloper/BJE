package model.entidad.dao.mapper;

import org.apache.ibatis.annotations.Param;

import model.entidad.Plantilla;

public interface PlantillaMapper {
	
	public Plantilla PlantillaPorId(@Param("id") int id);

}
