package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Alimento;
import model.entidad.dao.mapper.AlimentoMapper;
import model.entidad.dao.mapper.UsuarioMapper;

public class AlimentoDAO {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(RolDAO.class);

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
	
	
}
