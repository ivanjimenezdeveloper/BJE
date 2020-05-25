package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Factura;
import model.entidad.dao.mapper.FacturaMapper;

public class FacturaDAO {
	
	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(FacturaDAO.class);
	
	
	/**
	 * Devuelve una factura segun su id
	 * @param id id de la factura
	 * @return Objeto facura
	 */
	public Factura FacturaPorId(int id) {
		SqlSession sqlSession = null;
		try {
			 sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			FacturaMapper facturaMapper = sqlSession.getMapper(FacturaMapper.class);
			return facturaMapper.facturaPorId(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			Factura t = new Factura();
			return t;
		} finally {
			sqlSession.close();
		}
	}
	
	

	/**
	 * Devuelve todas las facturas
	 * @return arraylist de facturas
	 */
	public ArrayList<Factura> getFactura() {
		SqlSession sqlSession = null;
		try {
			 sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			FacturaMapper facturaMapper = sqlSession.getMapper(FacturaMapper.class);
			return facturaMapper.getFacturas();
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<Factura> t = new  ArrayList<Factura>();
			return t;
		} finally {
			sqlSession.close();
		}
	}
	

	/**
	 * Devuelve facturas por restaurante
	 * @param restaurante id de restaurante
	 * @return arraylist de facturas
	 */
	public ArrayList<Factura> getFacturasPorRestaurante(int restaurante) {
		SqlSession sqlSession = null;
		try {
			 sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			FacturaMapper facturaMapper = sqlSession.getMapper(FacturaMapper.class);
			return facturaMapper.FacturasPorRestaurante(restaurante);
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<Factura> t = new  ArrayList<Factura>();
			return t;
		} finally {
			sqlSession.close();
		}
	}
	
	public void creaFactura(String fecha) {
		SqlSession sqlSession = null;
		try {
			 sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			FacturaMapper facturaMapper = sqlSession.getMapper(FacturaMapper.class);
			facturaMapper.creaFactura(fecha);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());

		} finally {
			sqlSession.close();
		}
	}
	
	

}
