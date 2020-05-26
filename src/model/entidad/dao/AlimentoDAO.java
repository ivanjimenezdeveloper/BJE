package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Alimento;
import model.entidad.dao.mapper.AlimentoMapper;

/**
 * DAO de alimentos
 * 
 * @author HIBAN
 *
 */
public class AlimentoDAO {

	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(RolDAO.class);

	/**
	 * Busca un alimento por su id
	 * 
	 * @param id id del alimento
	 * @return objeto alimento
	 */
	public Alimento AlimentoPorId(int id) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			AlimentoMapper alimentoMapper = sqlSession.getMapper(AlimentoMapper.class);
			return alimentoMapper.AlimentoPorId(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			Alimento a = new Alimento();
			return a;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Busca todos los alimentos
	 * 
	 * @return Arraylist de alimentos
	 */
	public ArrayList<Alimento> busquedaAlimentos() {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			AlimentoMapper alimentoMapper = sqlSession.getMapper(AlimentoMapper.class);
			return alimentoMapper.busquedaAlimentos();
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<Alimento> a = new ArrayList<Alimento>();
			return a;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Edita un alimento
	 * @param nombre nombre del alimento
	 * @param idCategoria id de la categoria
	 * @param id id del alimento
	 * @param tiempo tiempo en minutos
	 */
	public void editaAlimento(String nombre, int idCategoria, int id, int tiempo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			AlimentoMapper alimentoMapper = sqlSession.getMapper(AlimentoMapper.class);
			alimentoMapper.editaAlimento(nombre, idCategoria, id, tiempo);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());

		} finally {
			sqlSession.close();
		}
	}


	/**
	 * Crea un alimento
	 * @param nombre nombre del alimento
	 * @param idCategoria id de la categoria
	 * @param tiempo tiempo en minutos
	 */
	public void creaAlimento(String nombre, int idCategoria, int tiempo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			AlimentoMapper alimentoMapper = sqlSession.getMapper(AlimentoMapper.class);
			alimentoMapper.creaAlimento(nombre, idCategoria, tiempo);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());

		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * elimina un alimento
	 * @param idAlimento id del alimento que eliminar
	 */
	public void eliminaAlimento(int idAlimento) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			AlimentoMapper alimentoMapper = sqlSession.getMapper(AlimentoMapper.class);
			alimentoMapper.eliminaAlimento(idAlimento);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());

		} finally {
			sqlSession.close();
		}
	}

}
