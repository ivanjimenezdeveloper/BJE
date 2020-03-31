package model.entidad.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Dia;
import model.entidad.dao.mapper.DiaMapper;

public class DiaDAO {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(DiaDAO.class);

	public Dia DiaPorId(int id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			DiaMapper diaMapper = sqlSession.getMapper(DiaMapper.class);
			return diaMapper.DiaPorId(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			Dia t = new Dia();
			return t;
		} finally {
			sqlSession.close();
		}
	}
}
