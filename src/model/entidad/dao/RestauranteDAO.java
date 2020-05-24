package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Restaurante;
import model.entidad.dao.mapper.RestauranteMapper;
/**
 * DAO de restaurante
 * @author HIBAN
 *
 */
public class RestauranteDAO {
	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(RestauranteDAO.class);

	/**
	 * Busca un restaurante por su id
	 * @param id id del restaurante
	 * @return Objeto del restaurante
	 */
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
	
	/**
	 * Busca todos los restaurantes de la base de datos
	 * @return Arraylist del objeto restaurante
	 */
	public ArrayList<Restaurante> busquedaRestaurantes() {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			RestauranteMapper restauranteMapper = sqlSession.getMapper(RestauranteMapper.class);
			return restauranteMapper.busquedaRestaurantes();
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<Restaurante> t = new ArrayList<Restaurante>();
			return t;
		} finally {
			sqlSession.close();
		}
	}
	
	
}
