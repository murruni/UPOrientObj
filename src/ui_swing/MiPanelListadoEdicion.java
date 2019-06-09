package ui_swing;

import java.awt.Frame;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;

import control.Handler;
import entities.MiEntity;

public abstract class MiPanelListadoEdicion<E extends MiEntity, T extends MiTableModel<E>>
		extends MiPanelListado<E, T> {

	public MiPanelListadoEdicion(Frame frame, Handler handler, T tableModel) {
		super(frame, handler, tableModel);
	}

	protected abstract ActionListener getActionListenerNuevo();

	protected abstract ActionListener getActionListenerEditar();

	protected abstract ActionListener getActionListenerEliminar();

	@Override
	protected void agregarBotones(Box botonera) {

		JButton boton = null;

		boton = new JButton("Nuevo");
		botonera.add(Box.createHorizontalStrut(10));
		boton.addActionListener(getActionListenerNuevo());
		botonera.add(boton);

		botonera.add(Box.createHorizontalStrut(10));
		boton = new JButton("Editar");
		boton.addActionListener(getActionListenerEditar());
		botonera.add(boton);

		botonera.add(Box.createHorizontalStrut(10));
		boton = new JButton("Eliminar");
		boton.addActionListener(getActionListenerEliminar());
		botonera.add(boton);

	}

}
