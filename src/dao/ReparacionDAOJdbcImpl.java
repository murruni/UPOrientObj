package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Auto;
import entities.Autoparte;
import entities.Reparacion;
import exception.PersistenceException;
import util.Log;

public class ReparacionDAOJdbcImpl extends DAOJdbcImpl implements ReparacionDAO {

	AutoDAO autoDAO;
	AutoparteDAO autoparteDAO;

	public ReparacionDAOJdbcImpl() {
		// FIXME esto se hace aca?
		autoDAO = new AutoDAOJdbcImpl();
		autoparteDAO = new AutoparteDAOJdbcImpl();
	}

	@Override
	public Reparacion create(Reparacion reparacion) throws PersistenceException {
		String sql = "INSERT INTO reparacion (titulo, cobrado, terminado, precioManoObra, id_auto) VALUES (?, ?, ?, ?, ?);";
		String sqlAutopartes = "INSERT INTO reparacion_autopartes (id_reparacion, id_autoparte) VALUES (?, ?);";

		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, reparacion.getTitulo());
			preparedStatement.setBoolean(2, reparacion.isCobrado());
			preparedStatement.setBoolean(3, reparacion.isTerminado());
			preparedStatement.setInt(4, reparacion.getPrecioManoObra());
			preparedStatement.setInt(5, reparacion.getAuto().getId());
			preparedStatement.executeUpdate();
			// obtengo la reparacion recien guardada
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				reparacion.setId(rs.getInt(1));
			}

			for (Autoparte autoparte : reparacion.getAutopartes()) {
				preparedStatement = getDBConnection().prepareStatement(sqlAutopartes);
				preparedStatement.setInt(1, reparacion.getId());
				preparedStatement.setInt(2, autoparte.getId());
				preparedStatement.executeUpdate();
			}

			c.commit();
			preparedStatement.close();
			c.close();
		} catch (SQLException e) {
			String msg = "Error en la base de datos";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return get(reparacion);
	}

	@Override
	public List<Reparacion> read() throws PersistenceException {
		List<Reparacion> lista;

		String sql = "SELECT id, titulo, cobrado, terminado, precioManoObra, id_auto FROM reparacion;";
		String sqlAutopartes = "SELECT id_reparacion, id_autoparte FROM reparacion_autopartes WHERE id_reparacion=?;";

		PreparedStatement preparedStatement;
		try {
			lista = new ArrayList<Reparacion>();
			preparedStatement = getDBConnection().prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				preparedStatement = getDBConnection().prepareStatement(sqlAutopartes);
				preparedStatement.setInt(1, rs.getInt("id"));
				ResultSet rsAutopartes = preparedStatement.executeQuery();

				lista.add(rsToReparacion(rs, rsAutopartes, new Reparacion()));
			}
			rs.close();
			preparedStatement.close();
		} catch (SQLException e) {
			String msg = "Error al intentar recuperar la lista de autopartes de la base";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return lista;
	}

	@Override
	public Reparacion update(Reparacion reparacion) throws PersistenceException {
		String sql = "UPDATE reparacion SET cobrado=?, terminado=?, precioManoObra=?, id_auto=? WHERE id=?;";
		String sqlAutopartes = "INSERT INTO reparacion_autopartes (id_reparacion, id_autoparte) VALUES (?, ?);";
		String sqlAutopartesDelete = "DELETE FROM reparacion_autopartes WHERE id_reparacion=?;";

		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setBoolean(1, reparacion.isCobrado());
			preparedStatement.setBoolean(2, reparacion.isTerminado());
			preparedStatement.setInt(3, reparacion.getPrecioManoObra());
			preparedStatement.setInt(4, reparacion.getAuto().getId());
			preparedStatement.setInt(5, reparacion.getId());
			preparedStatement.executeQuery();

			// elimino las autopartes anteriores
			preparedStatement = getDBConnection().prepareStatement(sqlAutopartesDelete);
			preparedStatement.setInt(1, reparacion.getId());
			preparedStatement.executeQuery();

			// agrego las nuevas autopartes
			for (Autoparte autoparte : reparacion.getAutopartes()) {
				preparedStatement = getDBConnection().prepareStatement(sqlAutopartes);
				preparedStatement.setInt(1, reparacion.getId());
				preparedStatement.setInt(2, autoparte.getId());
				preparedStatement.executeQuery();
			}

			c.commit();
			preparedStatement.close();
			c.close();
		} catch (SQLException e) {
			String msg = "Error al intentar recuperar la lista de autopartes de la base";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return get(reparacion);
	}

	@Override
	public void delete(Reparacion reparacion) throws PersistenceException {
		String sql = "DELETE FROM reparacion WHERE id=?;";
		String sqlAutopartes = "DELETE FROM reparacion_autopartes WHERE id_reparacion=?;";

		reparacion = get(reparacion);
		PreparedStatement preparedStatement;

		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setInt(1, reparacion.getId());
			preparedStatement.executeQuery();

			preparedStatement = c.prepareStatement(sqlAutopartes);
			preparedStatement.setInt(1, reparacion.getId());
			preparedStatement.executeQuery();

			c.commit();
			preparedStatement.close();
			c.close();
		} catch (SQLException e) {
			String msg = "Error al intentar eliminar la reparacion de la base";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}
	}

	@Override
	public Reparacion get(Reparacion reparacion) throws PersistenceException {
		Reparacion reparacionFound = null;
		String sql = "SELECT id, titulo, cobrado, terminado, precioManoObra, id_auto FROM reparacion WHERE id=? OR titulo=?;";
		String sqlAutopartes = "SELECT id_reparacion, id_autoparte FROM reparacion_autopartes WHERE id_reparacion=?;";

		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			if (reparacion.getId() != null) {
				preparedStatement.setInt(1, reparacion.getId());
			}
			preparedStatement.setString(2, reparacion.getTitulo());
			ResultSet rs = preparedStatement.executeQuery();

			// autopartes
			preparedStatement = c.prepareStatement(sqlAutopartes);
			preparedStatement.setInt(1, rs.getInt("id"));

			ResultSet rsAutopartes = preparedStatement.executeQuery();

			reparacionFound = rsToReparacion(rs, rsAutopartes, new Reparacion());
			rs.close();
			preparedStatement.close();
		} catch (SQLException e) {
			String msg = "Error al intentar recuperar la lista de autopartes de la base";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return reparacionFound;
	}

	@Override
	public List<Reparacion> search(Reparacion reparacion) throws PersistenceException {
		List<Reparacion> lista = null;
		Reparacion reparacionFound;
		String sql = "SELECT id, titulo, cobrado, terminado, precioManoObra, id_auto FROM reparacion WHERE titulo=? OR id_auto=?;";

		PreparedStatement preparedStatement;
		try {
			lista = new ArrayList<Reparacion>();

			preparedStatement = getDBConnection().prepareStatement(sql);
			if (reparacion.getId() != null) {
				preparedStatement.setInt(1, reparacion.getId());
			}
			preparedStatement.setInt(2, reparacion.getAuto().getId());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				reparacionFound = new Reparacion();
				reparacionFound.setId(rs.getInt("id"));
				reparacionFound.setTitulo(rs.getString("titulo"));
				lista.add(get(reparacionFound));
			}
			rs.close();
			preparedStatement.close();
		} catch (SQLException e) {
			String msg = "Error al intentar recuperar la lista de autopartes de la base";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return lista;
	}

	private Reparacion rsToReparacion(ResultSet rs, ResultSet rsAutopartes, Reparacion reparacion) throws SQLException {
		Auto auto;
		Autoparte autoparte;
		List<Autoparte> listaAutopartes = new ArrayList<Autoparte>();

		try {
			reparacion.setId(rs.getInt("id"));
			reparacion.setTitulo(rs.getString("titulo"));
			reparacion.setCobrado(rs.getBoolean("cobrado"));
			reparacion.setTerminado(rs.getBoolean("terminado"));
			reparacion.setPrecioManoObra(rs.getInt("precioManoObra"));

			auto = new Auto();
			auto.setId(rs.getInt("id_auto"));
			reparacion.setAuto(autoDAO.get(auto));

			while (rsAutopartes.next()) {
				autoparte = new Autoparte();
				autoparte.setId(rsAutopartes.getInt("id_autoparte"));
				listaAutopartes.add(autoparteDAO.get(autoparte));
			}
			reparacion.setAutopartes(listaAutopartes);

		} catch (PersistenceException e) {
			throw new SQLException("Error en base", e);
		}

		return reparacion;
	}
}
