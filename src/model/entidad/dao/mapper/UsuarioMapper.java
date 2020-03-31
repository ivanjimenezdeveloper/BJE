package model.entidad.dao.mapper;

import org.apache.ibatis.annotations.Param;

import model.entidad.Usuario;

public interface UsuarioMapper {

	public Usuario UsuarioPorId(@Param("id") int id);

	
}
