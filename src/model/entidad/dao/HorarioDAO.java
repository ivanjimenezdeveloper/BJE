package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Dia;
import model.entidad.Horario;
import model.entidad.dao.mapper.DiaMapper;
import model.entidad.dao.mapper.HorarioMapper;

public class HorarioDAO {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(DiaDAO.class);

	public Horario horarioPorDia(int id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			HorarioMapper horarioMapper = sqlSession.getMapper(HorarioMapper.class);
			return horarioMapper.horarioPorId(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			Horario t = new Horario();
			return t;
		} finally {
			sqlSession.close();
		}
	}

	public ArrayList<Horario> horariosPorRestaurante(int idRestaurante) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			HorarioMapper horarioMapper = sqlSession.getMapper(HorarioMapper.class);
			return horarioMapper.horariosPorRestaurante(idRestaurante);
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<Horario> t = new ArrayList<Horario>();
			return t;
		} finally {
			sqlSession.close();
		}
	}
	
	public ArrayList<Horario> getHorarios() {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			HorarioMapper horarioMapper = sqlSession.getMapper(HorarioMapper.class);
			return horarioMapper.getHorarios();
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<Horario> t = new ArrayList<Horario>();
			return t;
		} finally {
			sqlSession.close();
		}
	}
	

	public void creaHorarioGeneral(int activo, int mes, int anyo) {

		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			HorarioMapper horarioMapper = sqlSession.getMapper(HorarioMapper.class);
			horarioMapper.creaHorarioGeneral(activo, mes, anyo);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			sqlSession.close();
		}

	}
}
