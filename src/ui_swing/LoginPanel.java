package ui_swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.Handler;
import entities.Usuario;
import exception.UserException;

public class LoginPanel extends MiPanel {

	public LoginPanel(MiFrame miFrame, Handler handler) {
		super(miFrame, handler);
		initUI();
	}

	private void initUI() {
		int option = -1;
		Usuario userLogin = new Usuario();
		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		label.add(new JLabel("Usuario", SwingConstants.RIGHT));
		label.add(new JLabel("Clave", SwingConstants.RIGHT));
		label.add(new JLabel("", SwingConstants.RIGHT));
		panel.add(label, BorderLayout.WEST);

		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JTextField username = new JTextField();
		controls.add(username);
		JPasswordField password = new JPasswordField();
		controls.add(password);

		JTextField msg = new JTextField();
		msg.setEditable(false);
		msg.setBorder(null);
		controls.add(msg);

		panel.add(controls, BorderLayout.CENTER);

		// FIXME me parece medio una porquería con un while true
		while (true) {
			option = JOptionPane.showConfirmDialog(getFrame(), panel, "Login", JOptionPane.OK_CANCEL_OPTION);
			userLogin.setUsuario(username.getText());
			userLogin.setClave(new String(password.getPassword()));

			if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
				getHandler().salir();
			}
			try {
				getHandler().validarLogin(userLogin);
				break;
			} catch (UserException e) {
				msg.setText(e.getMessage());
			}
		}
	}
}
