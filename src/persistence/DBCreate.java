package persistence;

import java.sql.Connection;
import java.sql.Statement;

import util.Log;

public class DBCreate {

	public static void main(String[] args) {
		crearTablas();
	}

	private static void crearTablas() {
		// autos
		execute("CREATE TABLE auto (id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY, dominio VARCHAR(10), duenio VARCHAR(50));");

		// autoparte
		execute("CREATE TABLE autoparte (id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY, nombre VARCHAR(30), descripcion VARCHAR(50));");

		// reparacion
		execute("CREATE TABLE reparacion (id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY, titulo VARCHAR(30),cobrado BOOLEAN, terminado BOOLEAN, precioManoObra INTEGER, id_auto INTEGER, FOREIGN KEY (id_auto) REFERENCES auto(id));");

		// reparacion - autopartes
		execute("CREATE TABLE reparacion_autopartes (id_reparacion INTEGER, id_autoparte INTEGER, FOREIGN KEY (id_reparacion) REFERENCES reparacion(id), FOREIGN KEY (id_autoparte)  REFERENCES autoparte(id));");

		// usuario
		execute("CREATE TABLE usuario (id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY, nombre VARCHAR(50), usuario VARCHAR(30), clave VARCHAR(30));");
	}

	private static void execute(String sql) {
		Connection c = null;
		try {
			c = DBManager.getInstance().connect();
			Statement s = c.createStatement();
			s.execute(sql);
			c.commit();
		} catch (Exception e) {
			try {
				c.rollback();
				Log.logger(e.getMessage(), e);
			} catch (Exception e1) {
				Log.logger(e1.getMessage(), e1);
			}
		} finally {
			try {
				c.close();
			} catch (Exception e) {
				Log.logger(e.getMessage(), e);
			}
		}
	}

}
