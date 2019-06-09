package ui_swing;

import java.awt.Frame;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import control.Handler;
import entities.MiEntity;

public abstract class MiPanelListado<E extends MiEntity, T extends MiTableModel<E>> extends MiPanel {

	protected T tableModel;
	private JTable tabla;

	public MiPanelListado(Frame frame, Handler handler, T tableModel) {
		super(frame, handler);
		setTableModel(tableModel);
		setTabla(new JTable(tableModel));
		initiUI();
	}

	private void initiUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		getTabla().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(getTabla());
		add(scroll);

		agregarBotonera();
	}

	protected E getElementSelected() {
		int selectedRow = getTabla().getSelectedRow();
		E element = null;
		if (selectedRow == -1) {
			getHandler().mostrarError("Seleccione una fila");
		} else {
			element = getTableModel().getElement(selectedRow);
		}
		return element;
	}

	// -----------------------------------------------------------------------------
	// -- botonera -----------------------------------------------------------------
	// -----------------------------------------------------------------------------
	protected abstract ActionListener getActionListenerVer();

	protected abstract void agregarBotones(Box botonera);

	private void agregarBotonera() {
		Box botonera = Box.createHorizontalBox();
		botonera.add(Box.createHorizontalGlue());

		JButton boton = null;

		boton = new JButton("Ver");
		boton.addActionListener(getActionListenerVer());
		botonera.add(boton);

		agregarBotones(botonera);
		add(botonera);
	}

	// -----------------------------------------------------------------------------
	// -- setters & getters
	// -----------------------------------------------------------------------------
	public T getTableModel() {
		return tableModel;
	}

	public void setTableModel(T tableModel) {
		this.tableModel = tableModel;
	}

	public JTable getTabla() {
		return tabla;
	}

	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}
}
