package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Usuario;
import exception.PersistenceException;
import util.Log;

public class UsuarioDAOJdbcImpl extends DAOJdbcImpl implements UsuarioDAO {

	@Override
	public Usuario create(Usuario usuario) throws PersistenceException {
		String sql = "INSERT INTO usuario (nombre, usuario, clave) VALUES (?, ?, ?);";
		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getNombre());
			preparedStatement.setString(2, usuario.getUsuario());
			preparedStatement.setString(3, usuario.getClave());
			preparedStatement.executeUpdate();
			c.commit();
			preparedStatement.close();
			c.close();
		} catch (SQLException e) {
			String msg = "Error en la base de datos";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		} catch (Exception e1) {
			throw new PersistenceException(e1);
		}

		return get(usuario);
	}

	@Override
	public List<Usuario> read() throws PersistenceException {
		String sql = "SELECT id, nombre, usuario, clave FROM usuario;";
		List<Usuario> lista = new ArrayList<Usuario>();

		try {
			ResultSet rs = executeQuery(sql);
			while (rs.next()) {
				lista.add(rsToUsuario(rs, new Usuario()));
			}
		} catch (SQLException e) {
			String msg = "Error al intentar recuperar la lista de usuarios de la base";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return lista;
	}

	@Override
	public Usuario update(Usuario usuario) throws PersistenceException {
		String sql = "UPDATE usuario SET nombre=?, usuario=?, clave=? WHERE id=?;";

		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getNombre());
			preparedStatement.setString(2, usuario.getUsuario());
			preparedStatement.setString(3, usuario.getClave());
			preparedStatement.setInt(4, usuario.getId());
			preparedStatement.executeUpdate();
			c.commit();
			preparedStatement.close();
			c.close();
		} catch (SQLException e) {
			String msg = "Error en la base de datos";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return get(usuario);
	}

	@Override
	public void delete(Usuario usuario) throws PersistenceException {
		String sql = "DELETE FROM usuario WHERE id=?;";

		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setInt(1, usuario.getId());
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
	public Usuario get(Usuario usuario) throws PersistenceException {
		Usuario usuarioFound = null;
		String sql = "SELECT id, nombre, usuario, clave FROM usuario WHERE id=? OR usuario=? ";

		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setInt(1, -1);// borrar
			if (usuario.getId() != null) {
				preparedStatement.setInt(1, usuario.getId());
			}
			preparedStatement.setString(2, usuario.getUsuario());

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				usuarioFound = rsToUsuario(rs, new Usuario());
			}
			rs.close();
			preparedStatement.close();
			c.close();
		} catch (SQLException e) {
			String msg = "Error al recuperar un usuario de la base de datos";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return usuarioFound;

	}

	@Override
	public List<Usuario> search(Usuario usuario) throws PersistenceException {
		String sql = "SELECT id, nombre, usuario, clave FROM usuario WHERE usuario=? ;";
		List<Usuario> lista = new ArrayList<Usuario>();

		PreparedStatement preparedStatement;
		Connection c = null;
		try {
			c = getDBConnection();
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getUsuario());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				lista.add(rsToUsuario(rs, new Usuario()));
			}
			rs.close();
			preparedStatement.close();
			c.close();
		} catch (SQLException e) {
			String msg = "Error al intentar recuperar la lista de usuarios de la base";
			Log.logger(msg, e);
			throw new PersistenceException(msg, e);
		}

		return lista;
	}

	private Usuario rsToUsuario(ResultSet rs, Usuario usuario) throws SQLException {
		usuario.setNombre(rs.getString("nombre"));
		usuario.setUsuario(rs.getString("usuario"));
		usuario.setClave(rs.getString("clave"));
		if (rs.getInt("id") > -1) {
			usuario.setId(rs.getInt("id"));
		}

		return usuario;
	}
}
