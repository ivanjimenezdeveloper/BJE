package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Usuario;
import model.entidad.dao.mapper.UsuarioMapper;

/**
 * DAO de usuario
 * @author HIBAN
 *
 */
public class UsuarioDAO {
	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(UsuarioDAO.class);
	
	/**
	 * Devuelve un usuario segun su id
	 * @param id id del usuario
	 * @return Objeto usuario
	 */
	public Usuario UsuarioPorId(int id) {
		SqlSession sqlSession = null;
		try {
			 sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			return usuarioMapper.UsuarioPorId(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			Usuario t = new Usuario();
			return t;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * Busca un usuario con ese correo y esa contraseña
	 * @param correo correo del usuario
	 * @param pass contraseña del usuario
	 * @return Usuario que coincide con los parametros
	 */
	public Usuario existeUsuario(String correo, String pass) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			return usuarioMapper.existeUsuario(correo, pass);
		} catch (Exception e) {
			logger.error(e.getMessage());
			Usuario u = new Usuario();
			return u;
		} finally {
			sqlSession.close();
		}		
	}
	
	/**
	 * Busca si existe un correo en la base de datos
	 * @param correo correo que buscar
	 * @return un 1 o mas si encuentra algo, 0 si no encuentra nada
	 */
	public int existeCorreo(String correo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			return usuarioMapper.existeCorreo(correo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 0;
		} finally {
			sqlSession.close();
		}		
	}
	
	/**
	 * Busca todos los usuarios de un restaurante
	 * @param idRestaurante id del restaurante
	 * @return Arraylist con usuarios
	 */
	public ArrayList<Usuario> busquedaUsuarios(int idRestaurante) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			return usuarioMapper.busquedaUsuarios(idRestaurante);
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<Usuario> u = new ArrayList<Usuario>();
			return u;
		} finally {
			sqlSession.close();
		}		
	}
	
	/**
	 * Edita un usuario 
	 * @param user usuario al que editar
	 */
	public void editaUsuario(String nombre, String apellido,
			int rol, String observaciones, int restaurante, int activo, int id, String correo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			usuarioMapper.editaUsuario(nombre, apellido, rol, observaciones, restaurante, activo, id, correo);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());

		} finally {
			sqlSession.close();
		}		
	}
	
	/**
	 * edita el perfin de un usuario
	 * @param user usuario al que editar el perfil
	 */
	public void editaPerfil(String nombre, String apellido, int id, String correo, String pass) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			usuarioMapper.editaPerfil(nombre, apellido, correo, pass, id);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());

		} finally {
			sqlSession.close();
		}		
	}
	
	/**
	 * Crea un usuario
	 * @param user usuario que crear
	 */
	public void creaUsuario(String nombre, String apellido,
			int rol, String observaciones, int restaurante, int activo, String correo, String password) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			usuarioMapper.registraUsuario(correo, nombre, apellido, rol, observaciones, restaurante, activo, password);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());

		} finally {
			sqlSession.close();
		}		
	}
	
	/**
	 * Elimina un usuario
	 * 
	 * @param id id del usuario que eliminar
	 */
	public void eliminaUsuario(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			usuarioMapper.eliminaUsuario(id);;
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());

		} finally {
			sqlSession.close();
		}		
	}
	
	/**
	 * Cambia la pass
	 * @param id id del usuario al que cambiar la pass
	 * @param pass password nueva
	 */
	public void cambiaPass(int id, String pass) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			usuarioMapper.cambiaPass(pass, id);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());

		} finally {
			sqlSession.close();
		}		
	}
}
