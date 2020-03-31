package model.entidad.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Horario;
import model.entidad.dao.mapper.HorarioMapper;

public class HorarioDAO {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(HorarioDAO.class);

	public Horario horarioPorId(int id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			HorarioMapper horarioMapper = sqlSession.getMapper(HorarioMapper.class);
			return horarioMapper.HorarioPorId(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			Horario t = new Horario();
			return t;
		} finally {
			sqlSession.close();
		}
	}
}
