package test;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TestLogin {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Demo application");
		frame.setSize(300, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);

		frame.setVisible(true);

		// login
		Hashtable<String, String> loginInformation = login(frame);
		System.out.println(loginInformation.get("user"));
		System.out.println(loginInformation);
	}

	static public Hashtable<String, String> login(JFrame frame) {

		Hashtable<String, String> logininformation = new Hashtable<String, String>();

		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		label.add(new JLabel("E-Mail", SwingConstants.RIGHT));
		label.add(new JLabel("Password", SwingConstants.RIGHT));
		panel.add(label, BorderLayout.WEST);

		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JTextField username = new JTextField();
		controls.add(username);
		JPasswordField password = new JPasswordField();
		controls.add(password);
		panel.add(controls, BorderLayout.CENTER);

		JOptionPane.showConfirmDialog(frame, panel, "login", JOptionPane.OK_CANCEL_OPTION);
		
		logininformation.put("user", username.getText());
		logininformation.put("pass", new String(password.getPassword()));
		return logininformation;
	}

}
