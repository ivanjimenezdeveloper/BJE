package model.entidad.dao.mapper;

import org.apache.ibatis.annotations.Param;

import model.entidad.Plantilla;

/**
 * Interfaz del mapper de la entidad Plantilla
 * @author HIBAN
 *
 */
public interface PlantillaMapper {
	
	public Plantilla PlantillaPorId(@Param("id") int id);

}
