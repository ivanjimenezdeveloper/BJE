package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Categoria;
import model.entidad.dao.mapper.CategoriaMapper;

public class CategoriaDAO {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(RolDAO.class);

	public Categoria CategoriaPorId(int id) {
		SqlSession sqlSession = null;
		try {
			 sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			CategoriaMapper categoriaMapper = sqlSession.getMapper(CategoriaMapper.class);
			return categoriaMapper.CategoriaPorId(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			Categoria c = new Categoria();
			return c;
		} finally {
			sqlSession.close();
		}
	}
	
	public ArrayList<Categoria> busquedaCategorias() {
		SqlSession sqlSession = null;
		try {
			 sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			CategoriaMapper categoriaMapper = sqlSession.getMapper(CategoriaMapper.class);
			return categoriaMapper.busquedaCategorias();
		} catch (Exception e) {
			logger.error(e.getMessage());
			ArrayList<Categoria> c = new ArrayList<Categoria>();
			return c;
		} finally {
			sqlSession.close();
		}
	}
}
