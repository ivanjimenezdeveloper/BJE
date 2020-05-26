package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Horario;
import model.entidad.dao.mapper.HorarioMapper;

/**
 * DAO de horarios
 * @author HIBAN
 *
 */
public class HorarioDAO {
	
	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(HorarioDAO.class);

	/**
	 * Busca un horario general segun una id
	 * @param id id del horario general
	 * @return Objeto horario general
	 */
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

	/**
	 * Devuelve los horarios que estan conectados a un restaurante
	 * @param idRestaurante id del restaurante por el que filtrar
	 * @return Arraylist de horarios generales
	 */
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
	
	
	/**
	 * Devuelve los horarios que estan conectados a un restaurante y estan activos
	 * @param idRestaurante id del restaurante por el que filtrar
	 * @return Arraylist de horarios generales
	 */
	public ArrayList<Horario> horariosPorRestauranteActivo(int idRestaurante) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			HorarioMapper horarioMapper = sqlSession.getMapper(HorarioMapper.class);
			return horarioMapper.horariosPorRestauranteActivo(idRestaurante);
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<Horario> t = new ArrayList<Horario>();
			return t;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * Devuelve todos los horarios generales
	 * @return Arraylist de horarios generales
	 */
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
	

	/**
	 * Inserta un horario general a la base de datos
	 * @param activo boolean para determinar si esta activo o no
	 * @param mes mes del año 
	 * @param anyo año por el que filtrar
	 */
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
	
	/**
	 * Devuelve la id de un horario segun su mes y su año
	 * @param mes mes del año
	 * @param anyo año por el que filtrar
	 * @return Objeto horario
	 */
	public Horario horarioIdPorMesAnyo(int mes, int anyo) {
		
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			HorarioMapper horarioMapper = sqlSession.getMapper(HorarioMapper.class);
			return horarioMapper.horarioIdPorMesAnyo(mes, anyo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			Horario t = new Horario();
			return t;
		} finally {
			sqlSession.close();
		}
		
	}
}
