package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Usuario;
import model.entidad.dao.mapper.UsuarioMapper;

public class UsuarioDAO {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(UsuarioDAO.class);
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
	
	
	public ArrayList<Usuario> busquedaUsuarios() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper usuarioMapper = sqlSession.getMapper(UsuarioMapper.class);
			return usuarioMapper.busquedaUsuarios();
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<Usuario> u = new ArrayList<Usuario>();
			return u;
		} finally {
			sqlSession.close();
		}		
	}
	
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
}
