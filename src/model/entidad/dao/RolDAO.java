package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Rol;
import model.entidad.dao.mapper.RolMapper;

/**
 * DAO de rol
 * @author HIBAN
 *
 */
public class RolDAO {
	
	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(RolDAO.class);

	/**
	 * Busca un rol por su id
	 * @param id id por la que filtrar
	 * @return Objeto rol
	 */
	public Rol RolPorId(int id) {
		SqlSession sqlSession = null;
		try {
			 sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			RolMapper rolMapper = sqlSession.getMapper(RolMapper.class);
			return rolMapper.RolPorId(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			Rol t = new Rol();
			return t;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * Busqueda de roles
	 * @return Arraylist de roles
	 */
	public ArrayList<Rol> busquedaRoles() {
		SqlSession sqlSession = null;
		try {
			 sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			RolMapper rolMapper = sqlSession.getMapper(RolMapper.class);
			return rolMapper.busquedaRoles();
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<Rol> t = new ArrayList<Rol>();
			return t;
		} finally {
			sqlSession.close();
		}
	}
}
