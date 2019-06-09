package ui_swing;

import java.awt.Window;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import control.Handler;
import entities.Auto;
import entities.Autoparte;
import entities.Reparacion;
import exception.UserException;
import ui.UI;

public class UISwing extends UI {
	private MiFrame frame;

	public UISwing(Handler handler) {
		super(handler);
		setFrame(new MiFrame("Taller Mecánico", handler));
	}

	// -- autopartes --------------------------------------------------------------
	@Override
	public void mostrarListadoAutopartes() {
		AutoparteTableModel tableModel;
		AutopartePanelListadoEdicion panel;
		try {
			tableModel = new AutoparteTableModel(getHandler().getListaDeAutopartes());
//			panel = new AutopartePanelListadoSeleccion(getFrame(), getHandler(), tableModel);
			panel = new AutopartePanelListadoEdicion(getFrame(), getHandler(), tableModel);
			getFrame().cambiarPanel(panel);
		} catch (UserException e) {
			mostrarError(e.getMessage());
		}
	}

	@Override
	public void ver(Autoparte autoparte) {
		MiPanel panel = new AutopartePanelVista(getFrame(), getHandler(), autoparte);
		getFrame().cambiarPanel(panel);
	}

	@Override
	public void editar(Autoparte autoparte) {
		MiPanel panel = new AutopartePanelEdicion(getFrame(), getHandler(), autoparte);
		getFrame().cambiarPanel(panel);
	}

	// -- autos --------------------------------------------------------------------
	@Override
	public void mostrarListadoAutos() {
		AutoTableModel tableModel;
		MiPanel panel;
		try {
			tableModel = new AutoTableModel(getHandler().getListaDeAutos());
			panel = new AutoPanelListadoEdicion(getFrame(), getHandler(), tableModel);
			getFrame().cambiarPanel(panel);
		} catch (UserException e) {
			mostrarError(e.getMessage());
		}
	}

	@Override
	public void ver(Auto auto) {
		MiPanel panel = new AutoPanelVista(getFrame(), getHandler(), auto);
		getFrame().cambiarPanel(panel);
	}

	@Override
	public void editar(Auto auto) {
		MiPanel panel = new AutoPanelEdicion(getFrame(), getHandler(), auto);
		getFrame().cambiarPanel(panel);
	}

	// -- reparaciones -------------------------------------------------------------
	@Override
	public void mostrarListadoReparaciones() {
		ReparacionTableModel tableModel;
		MiPanel panel;
		try {
			tableModel = new ReparacionTableModel(getHandler().getListaDeReparaciones());
			// FIXME
//			panel = new ReparacionPanelListadoEdicion(getFrame(), getHandler(), tableModel);
//			getFrame().cambiarPanel(panel);
		} catch (UserException e) {
			mostrarError(e.getMessage());
		}

	}

	@Override
	public void ver(Reparacion reparacion) {
		// FIXME codear

	}

	@Override
	public void editar(Reparacion reparacion) {
		// FIXME codear

	}

	// -- basicos ------------------------------------------------------------------
	@Override
	public void initUI() {
		getFrame().initUI();
	}

	@Override
	public void salir() {
		JOptionPane.showMessageDialog(getFrame(), "Adiós!", "Salir", JOptionPane.INFORMATION_MESSAGE);
		WindowEvent we = new WindowEvent((Window) getFrame(), WindowEvent.WINDOW_CLOSING);
		getFrame().dispatchEvent(we);
		System.exit(0);
	}

	@Override
	public void mostrarError(String msg) {
		JOptionPane.showMessageDialog(getFrame(), msg, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void mostrarExito(String msg) {
		JOptionPane.showMessageDialog(getFrame(), msg, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void pantallaInicio() {
		agregarPanelVacio();
	}

	@Override
	public void pantallaLogin() {
		getFrame().cambiarPanel(new LoginPanel(getFrame(), getHandler()));
	}

	// -----------------------------------------------------------------------------
	// -- metodos privados
	// -----------------------------------------------------------------------------
	private void agregarPanelVacio() {
		getFrame().cambiarPanel(new MiPanel(getFrame(), getHandler()));
	}

	// -----------------------------------------------------------------------------
	// -- setters & getters
	// -----------------------------------------------------------------------------
	public MiFrame getFrame() {
		return frame;
	}

	public void setFrame(MiFrame frame) {
		this.frame = frame;
	}

}
