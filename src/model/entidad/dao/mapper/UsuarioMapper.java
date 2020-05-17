package model.entidad.dao.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import model.entidad.Usuario;

/**
 * Interfaz del mapper de la entidad Usuario
 * 
 * @author HIBAN
 *
 */
public interface UsuarioMapper {

	public Usuario UsuarioPorId(@Param("id") int id);

	public Usuario existeUsuario(@Param("correo") String correo, @Param("pass") String pass);
	
	public int existeCorreo(@Param("correo") String correo);

	public ArrayList<Usuario> busquedaUsuarios();

	public void editaUsuario(@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("rol") int rol,
			@Param("observaciones") String observaciones, @Param("restaurante") int restaurante,
			@Param("activo") int activo, @Param("id") int id, @Param("correo") String correo);

	public void registraUsuario(@Param("correo") String correo, @Param("nombre") String nombre,
			@Param("apellido") String apellido, @Param("rol") int rol, @Param("observaciones") String observaciones,
			@Param("restaurante") int restaurante, @Param("activo") int activo, @Param("pass")String password);
	
	public void editaPerfil(@Param("nombre")String nombre, @Param("apellido")String apellido, @Param("correo")String correo, @Param("pass")String pass,@Param("id")int id);
	
	public void eliminaUsuario(@Param("id")int id);
	
	public void cambiaPass(@Param("pass")String pass, @Param("id")int id);
}
