package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Dia;
import model.entidad.dao.mapper.DiaMapper;

public class DiaDAO {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(DiaDAO.class);

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
	
	public void insertarDia(String fecha,String entrada1,String salida1,String entrada2,String salida2,int idUsuario) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			DiaMapper diaMapper = sqlSession.getMapper(DiaMapper.class);
			
			diaMapper.insertaDia(fecha, entrada1, salida1, entrada2, salida2, idUsuario);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			sqlSession.close();
		}
	}
	
}
