package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import exception.PersistenceException;
import util.Log;

public class DBManager {

	private static DBManager instance;

	private static final String DB_DRIVER = "org.hsqldb.jdbcDriver";
	// shutdown=true persiste la base cuando se cierra la conexion con close()
	// pero en una conexion, no en conexiones anidadas
	// http://hsqldb.org/doc/guide/dbproperties-chapt.html#dpc_connection_props
	private static final String DB_URL =  "jdbc:hsqldb:file:sql/db;shutdown=true;hsqldb.default_table_type=cached";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "";

	// ---------------------------------------------------------------------------------------
	// -- Singleton --
	// ---------------------------------------------------------------------------------------
	private DBManager() {
	}

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	// ---------------------------------------------------------------------------------------
	// -- Métodos públicos --
	// ---------------------------------------------------------------------------------------
	@SuppressWarnings("finally")
	public Connection connect() throws PersistenceException {
		Connection c = null;

		try {
			Class.forName(DB_DRIVER);
			c = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			c.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			String msg = "Error de aplicación al intentar conectar con la base de datos";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		} catch (SQLException e) {
			String msg = "Error de conexión con la base de datos";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		} finally {
			return c;
		}
	}

	/**
	 * El shutdown de la base se ejecuta automaticamente cuando se cierra la
	 * conexion, por el parametro shutdown=true
	 * 
	 * @throws Exception
	 */
	public void shutdown() throws Exception {
		Connection c = getInstance().connect();
		Statement s = c.createStatement();
		s.execute("SHUTDOWN");
		c.close();
	}
}
