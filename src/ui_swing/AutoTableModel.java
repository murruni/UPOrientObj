package ui_swing;

import java.util.List;

import entities.Auto;

public class AutoTableModel extends MiTableModel<Auto> {
	private static final String[] COLUMNAS = { "Dominio", "Dueño" };
	private static final int DOMINIO = 0;
	private static final int DUENIO = 1;

	public AutoTableModel(List<Auto> autos) {
		super(autos, COLUMNAS);
	}

	@Override
	protected Object getValueAtColumn(Auto auto, int columna) {
		switch (columna) {
		case DOMINIO:
			return auto.getDominio();
		case DUENIO:
			return auto.getDuenio();
		}
		return null;
	}

}
