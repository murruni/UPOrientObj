package ui_swing;

import java.awt.Frame;

import javax.swing.JPanel;

import control.Handler;

public class MiPanel extends JPanel {

	private Handler handler;
	private Frame frame;

	private static final long serialVersionUID = 1L;

	public MiPanel(Frame frame, Handler handler) {
		setHandler(handler);
		setFrame(frame);
	}

	// -----------------------------------------------------------------------------
	// -- getters & setters
	// -----------------------------------------------------------------------------
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	/**
	 * FIXME volar de aca para abajo queda para que no de error el proyecto
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

//	public MiPanel(Handler handler2) {
//		// TODO Auto-generated constructor stub
//	}
//	protected Box createBox(String label) {
//		Box box = Box.createHorizontalBox();
//		JLabel titulo = new JLabel(label);
//		box.add(titulo);
//		return box;
//	}
//
//	protected Box createBox(String label, Integer value) {
//		Box box = createBox(label);
//		box.add(Box.createHorizontalStrut(10));
////		JTextField textField = new JTextField(30);
////		Integer.parseInt(text);
//		return box;
//	}
//
//	protected Box createBox(String label, String value) {
//		Box box = createBox(label);
//		box.add(Box.createHorizontalStrut(10));
//		JTextField textField = new JTextField(30);
//		textField.setText(value);
//		box.add(textField);
//		return box;
//	}
//
//	protected Box BotoneraGuardarCancelar() {
//		Box botonera = Box.createHorizontalBox();
//		botonera.add(Box.createHorizontalGlue());
//		botonera.add(new JButton("OK"));
//		botonera.add(Box.createHorizontalStrut(10));
//		botonera.add(new JButton("Cancel"));
//		return botonera;
//	}
//
//	protected Box BotoneraVolver() {
//		Box botonera = Box.createHorizontalBox();
//		JButton boton = new JButton("Volver");
//		boton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				getHandler().volverAlInicio();
//			}
//		});
//		botonera.add(boton);
//		return botonera;
//	}

}