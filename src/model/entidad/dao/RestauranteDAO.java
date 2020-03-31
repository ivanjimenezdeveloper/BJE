package model.entidad.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Restaurante;
import model.entidad.dao.mapper.RestauranteMapper;

public class RestauranteDAO {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(RestauranteDAO.class);

	public Restaurante restaurantePorId(int id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			RestauranteMapper restauranteMapper = sqlSession.getMapper(RestauranteMapper.class);
			return restauranteMapper.RestaurantePorId(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			Restaurante t = new Restaurante();
			return t;
		} finally {
			sqlSession.close();
		}
	}
}
