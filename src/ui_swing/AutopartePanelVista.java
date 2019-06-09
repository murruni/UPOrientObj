package ui_swing;

import java.awt.Frame;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;

import control.Handler;
import entities.Autoparte;

public class AutopartePanelVista extends MiPanel {
	private Autoparte autoparte;

	public AutopartePanelVista(Frame frame, Handler handler, Autoparte autoparte) {
		super(frame, handler);
		setAutoparte(autoparte);
		initUI();
	}

	protected void initUI() {
		Box panelForm = Box.createVerticalBox();
		Box panelBtn = Box.createHorizontalBox();
		panelBtn.add(Box.createHorizontalGlue());

		JTextField tfNombre = new JTextField(20);
		tfNombre.setText(getAutoparte().getNombre());
		tfNombre.setEditable(false);
		panelForm.add(new JLabel("Nombre"));
		panelForm.add(tfNombre);

		JTextField tfDescripcion = new JTextField(30);
		tfDescripcion.setText(getAutoparte().getDescripcion());
		tfDescripcion.setEditable(false);
		panelForm.add(new JLabel("Descripción"));
		panelForm.add(tfDescripcion);

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
