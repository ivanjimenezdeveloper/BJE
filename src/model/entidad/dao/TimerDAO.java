package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Timer;
import model.entidad.dao.mapper.TimerMapper;

/**
 * DAO de timers
 * @author HIBAN
 *
 */
public class TimerDAO {

	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(UsuarioDAO.class);
	
	/**
	 * Devuelve todos los timers activos por un restaurante
	 * @param idRestaurante
	 * @return
	 */
	public ArrayList<Timer> timersActivos(int idRestaurante) {
		SqlSession sqlSession = null;
		try {
			 sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			TimerMapper timerMapper = sqlSession.getMapper(TimerMapper.class);
			return timerMapper.timersActivos(idRestaurante);
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<Timer> t = new ArrayList<Timer>();
			return t;
		} finally {
			sqlSession.close();
		}
	}
	
	
	/**
	 * Añade un timer
	 * @param idAlimento id del alimento
	 * @param fecha fecha limite en segundos 
	 * @param idRestaurante id del restaurante
	 */
	public void addTimer(int idAlimento, int fecha, int idRestaurante) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			TimerMapper timerMapper = sqlSession.getMapper(TimerMapper.class);
			timerMapper.addTimer(idAlimento, fecha, idRestaurante);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());

		} finally {
			sqlSession.close();
		}		
	}
	
}
