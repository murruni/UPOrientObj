package ui_swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import control.Handler;
import entities.Autoparte;

public class AutopartePanelEdicion extends MiPanel {

	private Autoparte autoparte;

	public AutopartePanelEdicion(Frame frame, Handler handler, Autoparte autoparte) {
		super(frame, handler);
		setAutoparte(autoparte);
		initUI();
	}

	// FIXME codigo repetido
	private void initUI() {
		Box panelForm = Box.createVerticalBox();
		Box panelBtn = Box.createHorizontalBox();
		panelBtn.add(Box.createHorizontalGlue());
		JButton boton;

		JTextField tfNombre = new JTextField(20);
		tfNombre.setText(getAutoparte().getNombre());
		tfNombre.setEditable(true);
		panelForm.add(new JLabel("Nombre"));
		panelForm.add(tfNombre);

		JTextField tfDescripcion = new JTextField(30);
		tfDescripcion.setText(getAutoparte().getDescripcion());
		tfDescripcion.setEditable(true);
		panelForm.add(new JLabel("Descripción"));
		panelForm.add(tfDescripcion);

		panelForm.add(Box.createVerticalStrut(10));
		panelForm.add(panelBtn);

		boton = new JButton("Guardar");
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getAutoparte().setNombre(tfNombre.getText());
				getAutoparte().setDescripcion(tfDescripcion.getText());
				getHandler().createUpdate(autoparte);
			}
		});
		panelBtn.add(boton);
		
		boton = new JButton("Cancelar");
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getHandler().listarAutopartes();
			}
		});
		panelBtn.add(boton);

		panelForm.add(Box.createVerticalStrut(10));
		panelForm.add(panelBtn);

		add(panelForm);

	}

	public Autoparte getAutoparte() {
		return autoparte;
	}

	public void setAutoparte(Autoparte autoparte) {
		this.autoparte = autoparte;
	}

}
