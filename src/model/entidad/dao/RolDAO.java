package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Rol;
import model.entidad.dao.mapper.RolMapper;

public class RolDAO {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(RolDAO.class);

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
