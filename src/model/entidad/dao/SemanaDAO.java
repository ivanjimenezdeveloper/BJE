package model.entidad.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Semana;
import model.entidad.Usuario;
import model.entidad.dao.mapper.SemanaMapper;
import model.entidad.dao.mapper.UsuarioMapper;

public class SemanaDAO {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(SemanaDAO.class);
	public Semana semanaPorId(int id) {
		SqlSession sqlSession = null;
		try {
			 sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			SemanaMapper semanaMapper = sqlSession.getMapper(SemanaMapper.class);
			return semanaMapper.SemanaPorId(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			Semana t = new Semana();
			return t;
		} finally {
			sqlSession.close();
		}
	}
}
