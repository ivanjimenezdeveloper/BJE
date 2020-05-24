package model.entidad.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Localizacion;
import model.entidad.dao.mapper.LocalizacionMapper;

/**
 * DAO de localizacion
 * @author HIBAN
 *
 */
public class LocalizacionDAO {
	
	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(LocalizacionDAO.class);

	/**
	 * Busca una localizacion segun la id
	 * @param id id de por la que filtrar
	 * @return Objeto localizacion
	 */
	public Localizacion LocalizacionPorId(int id) {
		SqlSession sqlSession = null;
		try {
			 sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			LocalizacionMapper localizacionMapper = sqlSession.getMapper(LocalizacionMapper.class);
			return localizacionMapper.LocalizacionPorId(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			Localizacion t = new Localizacion();
			return t;
		} finally {
			sqlSession.close();
		}
	}
}

