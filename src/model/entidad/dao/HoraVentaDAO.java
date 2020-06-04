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

	/**
	 * Devuelve todas las HoraVentas de un restaurante en una fecha
	 * 
	 * @return arraylist de HoraVenta
	 */
	public ArrayList<HoraVenta> getVentasPorRestauranteFecha(int idRestaurante, String fecha) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			HoraVentaMapper horaVentaMapper = sqlSession.getMapper(HoraVentaMapper.class);
			return horaVentaMapper.getVentasPorRestauranteFecha(idRestaurante, fecha);
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<HoraVenta> t = new ArrayList<HoraVenta>();
			return t;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Comprueba que exista una venta
	 * @param idFactura id de la factura
	 * @param idRestaurante id del restaurante
	 * @param hora hora por la que filtrar
	 * @return objeto HoraVenta
	 */
	public HoraVenta existeVenta(int idFactura, int idRestaurante, int hora) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			HoraVentaMapper horaVentaMapper = sqlSession.getMapper(HoraVentaMapper.class);
			return horaVentaMapper.existeVenta(idFactura, idRestaurante, hora);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new HoraVenta();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Inserta una venta
	 * @param h objeto HoraVenta que introducir
	 * @param idRestaurante id del restaurante
	 */
	public void insertaHoraVenta(Double venta, int idFactura, int idRestaurante, int hora) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			HoraVentaMapper horaVentaMapper = sqlSession.getMapper(HoraVentaMapper.class);
			horaVentaMapper.insertaHoraVenta(venta, hora, idFactura, idRestaurante);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());

		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Edita una venta
	 * @param h objeto que editar
	 * @param idRestaurante id del restaurante
	 */
	public void editaHoraVenta(Double venta, int idFactura, int idRestaurante, int hora) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			HoraVentaMapper horaVentaMapper = sqlSession.getMapper(HoraVentaMapper.class);
			horaVentaMapper.editaHoraVenta(venta, hora, idFactura, idRestaurante);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());

		} finally {
			sqlSession.close();
		}
	}
}
