package model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;


/**
 * Hace la conexion a la base de datos
 * @author horabaixa
 *
 */
public class Conexion {
	private static Conexion instancia = null;
	private static Context initContext;
	private static Context envContext;
	private static DataSource DS;
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Conexion.class);

	private Conexion() {

		try {
			
			 //Inicio el los contextos para usar la base de datos
			 
			initContext = new InitialContext();
			envContext = (Context) initContext.lookup("java:/comp/env");
			DS = (DataSource) envContext.lookup("jdbc/BJE");

		} catch (NamingException e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * Inicia la instancia de la conexion si no esta iniciada
	 * @return Objeto de conexion instanciado
	 */
	public static Conexion getInstance() {
		if (instancia == null) {
			synchronized (Conexion.class) {

				if (instancia == null) {
					instancia = new Conexion();
				}

			}

		}
		return instancia;
	}
	
	/**
	 * Devuelve la conexion del DataSource
	 * @return Devuelve la conexion del DataSource
	 * @throws SQLException
	 */

	public Connection getConnection() throws SQLException {
		return DS.getConnection();
	}
}
