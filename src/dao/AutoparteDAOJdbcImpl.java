package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Autoparte;
import exception.PersistenceException;
import util.Log;

public class AutoparteDAOJdbcImpl extends DAOJdbcImpl implements AutoparteDAO {

	@Override
	public Autoparte create(Autoparte autoparte) throws PersistenceException {
		String sql = "INSERT INTO autoparte (nombre, descripcion) VALUES (?, ?);";

		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, autoparte.getNombre());
			preparedStatement.setString(2, autoparte.getDescripcion());
			preparedStatement.executeUpdate();
			c.commit();
			preparedStatement.close();
			c.close();
		} catch (SQLException e) {
			String msg = "Error en la base de datos";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return get(autoparte);
	}

	@Override
	public List<Autoparte> read() throws PersistenceException {
		String sql = "SELECT id, nombre, descripcion FROM autoparte;";
		List<Autoparte> lista = new ArrayList<Autoparte>();

		try {
			ResultSet rs = executeQuery(sql);
			while (rs.next()) {
				lista.add(rsToAutoparte(rs, new Autoparte()));
			}
		} catch (SQLException e) {
			String msg = "Error al intentar recuperar la lista de autopartes de la base";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return lista;
	}

	@Override
	public Autoparte update(Autoparte autoparte) throws PersistenceException {
		String sql = "UPDATE autoparte SET nombre=?, descripcion=? WHERE id =?;";

		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, autoparte.getNombre());
			preparedStatement.setString(2, autoparte.getDescripcion());
			preparedStatement.setInt(3, autoparte.getId());
			preparedStatement.executeUpdate();
			c.commit();
			preparedStatement.close();
			c.close();
		} catch (SQLException e) {
			String msg = "Error en la base de datos";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return get(autoparte);
	}

	@Override
	public void delete(Autoparte autoparte) throws PersistenceException {
		String sql = "DELETE FROM autoparte WHERE id=?;";

		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setInt(1, autoparte.getId());
			preparedStatement.executeUpdate();
			c.commit();
			preparedStatement.close();
			c.close();
		} catch (SQLException e) {
			String msg = "Error en la base de datos";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}
	}

	@Override
	public Autoparte get(Autoparte autoparte) throws PersistenceException {
		Autoparte autoparteFound = null;

		String sql = "SELECT id, nombre, descripcion FROM autoparte WHERE id=? OR nombre=?;";

		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			if (autoparte.getId() != null) {
				preparedStatement.setInt(1, autoparte.getId());
			}
			preparedStatement.setString(2, autoparte.getNombre());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				autoparteFound = rsToAutoparte(rs, new Autoparte());
			}
			rs.close();
			preparedStatement.close();
			c.close();
		} catch (SQLException e) {
			String msg = "Error al recuperar un autoparte de la base de datos";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return autoparteFound;
	}

	@Override
	public List<Autoparte> search(Autoparte autoparte) throws PersistenceException {
		String sql = "SELECT id, nombre, descripcion FROM autoparte WHERE nombre=? ;";
		List<Autoparte> lista = new ArrayList<Autoparte>();

		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, autoparte.getNombre());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				lista.add(rsToAutoparte(rs, new Autoparte()));
			}
			rs.close();
			preparedStatement.close();
			c.close();
		} catch (SQLException e) {
			String msg = "Error al intentar recuperar la lista de autopartes de la base";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return lista;
	}

	private Autoparte rsToAutoparte(ResultSet rs, Autoparte autoparte) throws SQLException {
		autoparte.setId(rs.getInt("id"));
		autoparte.setNombre(rs.getString("nombre"));
		autoparte.setDescripcion(rs.getString("descripcion"));
		return autoparte;
	}

}
