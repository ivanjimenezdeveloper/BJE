package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Timer;
import model.entidad.dao.mapper.TimerMapper;

public class TimerDAO {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(UsuarioDAO.class);
	public ArrayList<Timer> timersActivos() {
		SqlSession sqlSession = null;
		try {
			 sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			TimerMapper timerMapper = sqlSession.getMapper(TimerMapper.class);
			return timerMapper.timersActivos();
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<Timer> t = new ArrayList<Timer>();
			return t;
		} finally {
			sqlSession.close();
		}
	}
	
}
