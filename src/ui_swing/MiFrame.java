package ui_swing;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.Handler;

public class MiFrame extends JFrame {
	
	private Handler handler;

	public MiFrame(String titulo, Handler handler) {
		super(titulo);
		setHandler(handler);
	}

	public void initUI() {
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(new MiMenuBar(getHandler()));
		setVisible(true);
	}

	public void cambiarPanel(JPanel panel) {
		getContentPane().removeAll();
		getContentPane().add(panel);
		getContentPane().validate();
	}

	public void panelVacio() {
		this.cambiarPanel(new JPanel());
	}

	// -----------------------------------------------------------------------------
	// -- getters & setters --------------------------------------------------------
	// -----------------------------------------------------------------------------
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}