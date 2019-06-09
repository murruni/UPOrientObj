package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exception.PersistenceException;
import persistence.DBManager;
import util.Log;

public class DAOJdbcImpl {

	public Connection getDBConnection() throws PersistenceException {
		return DBManager.getInstance().connect();
	}

	public ResultSet executeQuery(String sql) throws PersistenceException {
		Connection c = null;
		try {
			c = getDBConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			String msg = "Error al intentar ejecutar la consulta en la base";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				String msg = "Error al cerrar la conexión";
				Log.logger(msg, e1);
				throw new PersistenceException(msg, e1);
			}
		}
	}
}
