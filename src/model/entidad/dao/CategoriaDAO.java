package model.entidad.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import model.MyBatisUtil;
import model.entidad.Categoria;
import model.entidad.dao.mapper.CategoriaMapper;

/**
 * DAO de categoria
 * @author HIBAN
 *
 */
public class CategoriaDAO {
	
	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(RolDAO.class);

	/**
	 * Busca una categoria por su id
	 * @param id id de la categoria
	 * @return Objeto categoria
	 */
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
	
	/**
	 * Busca todas las categorias
	 * @return Arraylist de categorias
	 */
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
