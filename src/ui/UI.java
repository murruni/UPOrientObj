package ui;

import control.Handler;
import entities.Auto;
import entities.Autoparte;
import entities.Reparacion;

public abstract class UI {
	// esta clase es para diferenciar UIs
	private Handler handler;

	public UI(Handler handler) {
		setHandler(handler);
	}

	public abstract void initUI();

	// -----------------------------------------------------------------------------
	// -- getters & setters
	// -----------------------------------------------------------------------------
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	// -----------------------------------------------------------------------------
	// -- metodos abstractos
	// -----------------------------------------------------------------------------

	public abstract void pantallaInicio();

	public abstract void pantallaLogin();

	public abstract void mostrarError(String msg);

	public abstract void mostrarExito(String msg);

	public abstract void salir();

	public abstract void mostrarListadoAutopartes();

	public abstract void mostrarListadoAutos();

	public abstract void mostrarListadoReparaciones();

	public abstract void ver(Auto auto);

	public abstract void ver(Autoparte autoparte);

	public abstract void ver(Reparacion reparacion);

	public abstract void editar(Auto auto);

	public abstract void editar(Autoparte autoparte);

	public abstract void editar(Reparacion reparacion);

}
