package model.entidad.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Plantilla;
import model.entidad.dao.mapper.PlantillaMapper;

public class PlantillaDAO {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(PlantillaDAO.class);

	public Plantilla PlantillaPorId(int id) {
		SqlSession sqlSession = null;
		try {
			 sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			PlantillaMapper plantillaMapper = sqlSession.getMapper(PlantillaMapper.class);
			return plantillaMapper.PlantillaPorId(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			Plantilla t = new Plantilla();
			return t;
		} finally {
			sqlSession.close();
		}
	}
}
