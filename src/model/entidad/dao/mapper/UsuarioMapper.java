package model.entidad.dao.mapper;

import org.apache.ibatis.annotations.Param;

import model.entidad.Usuario;

/**
 * Interfaz del mapper de la entidad Usuario
 * @author HIBAN
 *
 */
public interface UsuarioMapper {

	public Usuario UsuarioPorId(@Param("id") int id);

	public Usuario existeUsuario(@Param("correo") String correo, @Param("pass") String pass);

}
