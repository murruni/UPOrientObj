package ui_swing;

import java.util.List;

import entities.Autoparte;

public class AutoparteTableModel extends MiTableModel<Autoparte> {
	private static final String[] COLUMNAS = { "Nombre", "Descripción" };
	private static final int NOMBRE = 0;
	private static final int DESCRIPCION = 1;

	public AutoparteTableModel(List<Autoparte> autopartes) {
		super(autopartes, COLUMNAS);
	}

	@Override
	protected Object getValueAtColumn(Autoparte autoparte, int columna) {
		switch (columna) {
		case NOMBRE:
			return autoparte.getNombre();
		case DESCRIPCION:
			return autoparte.getDescripcion();
		}
		return null;
	}

}
