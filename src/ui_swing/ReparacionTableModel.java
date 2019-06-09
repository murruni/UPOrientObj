package ui_swing;

import java.util.List;

import entities.Reparacion;

public class ReparacionTableModel extends MiTableModel<Reparacion> {
	// FIXME modificar a Reparacion
	private static final String[] COLUMNAS = { "Nombre", "Descripción" };
	private static final int NOMBRE = 0;
	private static final int DESCRIPCION = 1;

	public ReparacionTableModel(List<Reparacion> reparaciones) {
		super(reparaciones, COLUMNAS);
	}

	@Override
	protected Object getValueAtColumn(Reparacion reparacion, int columna) {
		// FIXME codear
		switch (columna) {
		case NOMBRE:
			return "";
//			return reparacion.getNombre();
		case DESCRIPCION:
			return "";
//			return reparacion.getDescripcion();
		}
		return null;
	}

}
