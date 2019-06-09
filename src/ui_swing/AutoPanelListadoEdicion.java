package ui_swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.Handler;
import entities.Auto;

public class AutoPanelListadoEdicion extends MiPanelListadoEdicion<Auto, AutoTableModel> {

	public AutoPanelListadoEdicion(Frame frame, Handler handler, AutoTableModel tableModel) {
		super(frame, handler, tableModel);
	}

	@Override
	protected ActionListener getActionListenerNuevo() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getHandler().editar(new Auto());
			}
		};
	}

	@Override
	protected ActionListener getActionListenerEditar() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getHandler().editar(getElementSelected());
			}
		};
	}

	@Override
	protected ActionListener getActionListenerEliminar() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getHandler().eliminar(getElementSelected());
				getHandler().listarAutos();
			}
		};
	}

	@Override
	protected ActionListener getActionListenerVer() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Auto auto = getElementSelected();
				if (auto != null) {
					getHandler().mostrar(auto);
				}
			}
		};
	}

}
