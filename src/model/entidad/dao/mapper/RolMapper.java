package model.entidad.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import model.entidad.Rol;

/**
 * Interfaz del mapper de la entidad Rol
 * @author HIBAN
 *
 */
public interface RolMapper {
	
	public Rol RolPorId(@Param("id") int id);
	
	public ArrayList<Rol> busquedaRoles();

}
