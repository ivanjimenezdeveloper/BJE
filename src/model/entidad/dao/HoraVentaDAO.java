package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.HoraVenta;
import model.entidad.dao.mapper.HoraVentaMapper;

public class HoraVentaDAO {

	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(HoraVentaDAO.class);

	/**
	 * Devuelve una hora de venta segun su id
	 * 
	 * @param id id de la horaVenta
	 * @return Objeto HoraVenta
	 */
	public HoraVenta HoraVentaPorId(int id, int hora) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			HoraVentaMapper horaVentaMapper = sqlSession.getMapper(HoraVentaMapper.class);
			return horaVentaMapper.ventaPorId(id, hora);
		} catch (Exception e) {
			logger.error(e.getMessage());
			HoraVenta t = new HoraVenta();
			return t;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Devuelve todas las HoraVentas
	 * 
	 * @return arraylist de HoraVenta
	 */
	public ArrayList<HoraVenta> getVentas() {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			HoraVentaMapper horaVentaMapper = sqlSession.getMapper(HoraVentaMapper.class);
			return horaVentaMapper.getVentas();
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<HoraVenta> t = new ArrayList<HoraVenta>();
			return t;
		} finally {
			sqlSession.close();
		}
	}

}
