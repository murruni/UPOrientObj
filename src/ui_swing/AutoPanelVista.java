package ui_swing;

import java.awt.Frame;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;

import control.Handler;
import entities.Auto;

public class AutoPanelVista extends MiPanel {
	private Auto auto;

	public AutoPanelVista(Frame frame, Handler handler, Auto auto) {
		super(frame, handler);
		setAuto(auto);
		initUI();
	}

	// FIXME codigo repetido con AutoPanelEdicion
	protected void initUI() {
		Box panelForm = Box.createVerticalBox();
		Box panelBtn = Box.createHorizontalBox();
		panelBtn.add(Box.createHorizontalGlue());

		JTextField tfDominio = new JTextField(10);
		tfDominio.setText(getAuto().getDominio());
		tfDominio.setEditable(false);
		panelForm.add(new JLabel("Dominio"));
		panelForm.add(tfDominio);

		JTextField tfDuenio = new JTextField(30);
		tfDuenio.setText(getAuto().getDuenio());
		tfDuenio.setEditable(false);
		panelForm.add(new JLabel("Dueño"));
		panelForm.add(tfDuenio);

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
