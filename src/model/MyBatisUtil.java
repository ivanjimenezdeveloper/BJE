package model;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class MyBatisUtil {
	
	private static SqlSessionFactory factory;

	/**
	 * Logger
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(MyBatisUtil.class);
	
	/**
	 * Constructor privado
	 */
	private MyBatisUtil() {
	}

	/**
	 * Estatico para que selo se configure MyBatis una vez
	 */
	static {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		factory = new SqlSessionFactoryBuilder().build(reader);
	}

	/**
	 * Obtiene una SqlSessionFactory
	 * @return La SqlSessionFactory
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		return factory;
	}
}
