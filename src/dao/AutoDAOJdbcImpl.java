package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Auto;
import exception.PersistenceException;
import util.Log;

public class AutoDAOJdbcImpl extends DAOJdbcImpl implements AutoDAO {

	@Override
	public Auto create(Auto auto) throws PersistenceException {

		Connection c = null;
		String sql = "INSERT INTO auto (dominio, duenio) VALUES (? ,? ); ";
		PreparedStatement preparedStatement;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, auto.getDominio());
			preparedStatement.setString(2, auto.getDuenio());
			preparedStatement.executeUpdate();
			c.commit();
			preparedStatement.close();
			c.close();
		} catch (SQLException e) {
			String msg = "Error en la base de datos";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return get(auto);
	}

	@Override
	public List<Auto> read() throws PersistenceException {
		String sql = "SELECT id, dominio, duenio FROM auto;";
		List<Auto> lista = new ArrayList<Auto>();

		try {
			ResultSet rs = executeQuery(sql);
			while (rs.next()) {
				lista.add(rsToAuto(rs, new Auto()));
			}
		} catch (SQLException e) {
			String msg = "Error al intentar recuperar la lista de autos de la base";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return lista;
	}

	@Override
	public Auto update(Auto auto) throws PersistenceException {

		String sql = "UPDATE auto SET dominio=?, duenio=? WHERE id =?;";

		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, auto.getDominio());
			preparedStatement.setString(2, auto.getDuenio());
			preparedStatement.setInt(3, auto.getId());

			preparedStatement.executeUpdate();
			c.commit();
			preparedStatement.close();
			c.close();
		} catch (SQLException e) {
			String msg = "Error en la base de datos";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return get(auto);
	}

	@Override
	public void delete(Auto auto) throws PersistenceException {
		String sql = "DELETE FROM auto WHERE id=?;";

		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setInt(1, auto.getId());
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
	public Auto get(Auto auto) throws PersistenceException {
		Auto autoFound = null;
		String sql = "SELECT id, dominio, duenio FROM auto WHERE id=? OR dominio=?;";

		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			if (auto.getId() != null) {
				preparedStatement.setInt(1, auto.getId());
			}
			preparedStatement.setString(2, auto.getDominio());

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				autoFound = rsToAuto(rs, new Auto());
			}
			rs.close();
			preparedStatement.close();
			c.close();
		} catch (SQLException e) {
			String msg = "Error al recuperar un auto de la base de datos";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return autoFound;
	}

	@Override
	public List<Auto> search(Auto auto) throws PersistenceException {
		String sql = "SELECT id, dominio, duenio FROM auto WHERE dominio=? ;";
		List<Auto> lista = new ArrayList<Auto>();

		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, auto.getDominio());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				lista.add(rsToAuto(rs, new Auto()));
			}
			rs.close();
			preparedStatement.close();
			c.close();
		} catch (SQLException e) {
			String msg = "Error al intentar recuperar la lista de autos de la base";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return lista;
	}

	private Auto rsToAuto(ResultSet rs, Auto auto) throws SQLException {
		auto.setId(rs.getInt("id"));
		auto.setDominio(rs.getString("dominio"));
		auto.setDuenio(rs.getString("duenio"));
		return auto;
	}

}
