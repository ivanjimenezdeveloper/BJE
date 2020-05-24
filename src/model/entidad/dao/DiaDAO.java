package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Dia;
import model.entidad.dao.mapper.DiaMapper;

/**
 * DAO de dias
 * @author HIBAN
 *
 */
public class DiaDAO {
	
	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(DiaDAO.class);

	/**
	 * Devuelve un dia segun su id
	 * @param id id del dia
	 * @return Objeto dia
	 */
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
	
	
	/**
	 * Busca el horario en dias de un usuario segun un mes y un año
	 * @param idUsuario id del usuario del que buscamos el horario
	 * @param mes mes del año 
	 * @param anyo año del horario
	 * @return Arraylist de dias
	 */
	public ArrayList<Dia> horarioUsuario(int idUsuario, int mes, int anyo) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			DiaMapper diaMapper = sqlSession.getMapper(DiaMapper.class);
			return diaMapper.horarioUsuario(idUsuario, mes, anyo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<Dia> t = new ArrayList<Dia>();
			return t;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * todos los dias de los usuarios de un restaurante
	 * @param mes mes del año
	 * @param anyo año del horario
	 * @param idRestaurante id del restaurante
	 * @return Arraylist de dias
	 */
	public ArrayList<Dia> horarioRestaurante(int mes, int anyo, int idRestaurante) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			DiaMapper diaMapper = sqlSession.getMapper(DiaMapper.class);
			return diaMapper.horarioRestaurante(mes, anyo, idRestaurante);
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<Dia> t = new ArrayList<Dia>();
			return t;
		} finally {
			sqlSession.close();
		}
	}
	
	
	/**
	 * Busca el horario en dias de un usuario segun un mes y un año
	 * @param idUsuario id del usuario del que buscamos el horario
	 * @param mes mes del año 
	 * @param anyo año del horario
	 * @return Arraylist de dias
	 */
	public ArrayList<Dia> horarioUsuarioFecha(int mes, int anyo, int idUsuario) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			DiaMapper diaMapper = sqlSession.getMapper(DiaMapper.class);
			return diaMapper.horarioUsuarioFecha(mes, anyo, idUsuario);
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<Dia> t = new ArrayList<Dia>();
			return t;
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * Inserta un dia
	 * @param fecha fecha del dia
	 * @param entrada1 primera entrada a trabajar
	 * @param salida1 salida a descanso o a finalizar
	 * @param entrada2 segunda entrada a trabajar
	 * @param salida2 salida del trabajo
	 * @param idUsuario id del usuario
	 * @param idHorario id de horario
	 */
	public void insertarDia(String fecha,String entrada1,String salida1,String entrada2,String salida2,int idUsuario, int idHorario) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			DiaMapper diaMapper = sqlSession.getMapper(DiaMapper.class);
			
			diaMapper.insertaDia(fecha, entrada1, salida1, entrada2, salida2, idUsuario, idHorario);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * Comprueba si existe un dia con esa fecha y si pertenece a un usuario concreto
	 * @param us usuario al que podria pertenecer el dia
	 * @param fecha fecha del dia exacto con formato yyy-M-d
	 * @return 1 o mas si encuentra algo o 0 si no encuentra nada
	 */
	public int existeDia(int idUsuario, String fecha) {
		
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			DiaMapper diaMapper = sqlSession.getMapper(DiaMapper.class);
			return diaMapper.existeDia(idUsuario, fecha);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 0;
		} finally {
			sqlSession.close();
		}
		
	}
	

	/**
	 * edita un dia
	 * @param fecha fecha del dia
	 * @param entrada1 primera entrada a trabajar
	 * @param salida1 salida a descanso o a finalizar
	 * @param entrada2 segunda entrada a trabajar
	 * @param salida2 salida del trabajo
	 * @param idUsuario id del usuario
	 */
	public void editaDia(String fecha, String entrada_1, String salida_1, String entrada_2, String salida_2, int idUsuario) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			DiaMapper diaMapper = sqlSession.getMapper(DiaMapper.class);
			
			diaMapper.editaDia(fecha, idUsuario, entrada_1, salida_1, entrada_2, salida_2);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			sqlSession.close();
		}
	}
	
}
