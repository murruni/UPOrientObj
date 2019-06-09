package ui_swing;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entities.MiEntity;

public abstract class MiTableModel<E extends MiEntity> extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<E> elements;
	private String[] columnNames;

	/**
	 * Arma un TableModel de Swing, con lo elementos dados. Extiende de
	 * javax.swing.table.AbstractTableModel. Swing trabaja con el orden (indice) de
	 * la List columnNames. El objeto que extiende de esta calse debe guardar la
	 * relacion entre el indice de la List columnNames, con lo nombres de los
	 * campos.
	 * 
	 * @param List<E> lista de objetos
	 * @param String[] nombre de columnas
	 */
	public MiTableModel(List<E> elements, String[] columnNames) {
		setElements(elements);
		setColumnNames(columnNames);
	}

	// metodos CRUD
	public void addElement(E e) {
		getElements().add(e);
	}

	public void delElement(E e) {
		getElements().remove(e);
	}

	public E getElement(int row) {
		return getElements().get(row);
	}

	public String getColumnName(int col) {
		return getColumnNames()[col];
	}

	// AbstractTableModel
	@Override
	public int getColumnCount() {
		return getColumnNames().length;
	}

	@Override
	public int getRowCount() {
		return getElements().size();
	}

	@Override // (row, col)
	public Object getValueAt(int arg0, int arg1) {
		return getValueAtColumn(getElements().get(arg0), arg1);
	}

	/**
	 * Para buscar y devolver el atributo dentro del elemento seleccionado. No se
	 * puede inferir sin metaprogramacion.
	 * 
	 * @param E elemento. El elemento seleccionado de la List<E>
	 * @param   int columna. El indice de columnNames.
	 * @return Object. Depende del tipo de objeto de ese atributo.
	 */
	protected abstract Object getValueAtColumn(E elemento, int columna);

	// getters & setters
	public List<E> getElements() {
		return elements;
	}

	public void setElements(List<E> elements) {
		this.elements = elements;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

}
