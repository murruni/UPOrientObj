package ui_swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import control.Handler;
import entities.Auto;

public class AutoPanelEdicion extends MiPanel {
	private Auto auto;

	public AutoPanelEdicion(Frame frame, Handler handler, Auto auto) {
		super(frame, handler);
		setAuto(auto);
		initUI();
	}

	// FIXME codigo repetido con AutoPanelVer
	protected void initUI() {
		Box panelForm = Box.createVerticalBox();
		Box panelBtn = Box.createHorizontalBox();
		panelBtn.add(Box.createHorizontalGlue());
		JButton boton;

		JTextField tfDominio = new JTextField(10);
		tfDominio.setText(getAuto().getDominio());
		tfDominio.setEditable(true);
		panelForm.add(new JLabel("Dominio"));
		panelForm.add(tfDominio);

		JTextField tfDuenio = new JTextField(30);
		tfDuenio.setText(getAuto().getDuenio());
		tfDuenio.setEditable(true);
		panelForm.add(new JLabel("Dueño"));
		panelForm.add(tfDuenio);

		boton = new JButton("Guardar");
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getAuto().setDominio(tfDominio.getText());
				getAuto().setDuenio(tfDuenio.getText());
				getHandler().createUpdate(auto);
			}
		});
		panelBtn.add(boton);

		boton = new JButton("Cancelar");
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getHandler().listarAutos();
			}
		});
		panelBtn.add(boton);

		panelForm.add(Box.createVerticalStrut(10));
		panelForm.add(panelBtn);

		add(panelForm);
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

}
