package ui_swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.Handler;
import entities.Autoparte;

public class AutopartePanelListadoEdicion extends MiPanelListadoEdicion<Autoparte, AutoparteTableModel> {

	public AutopartePanelListadoEdicion(Frame frame, Handler handler, AutoparteTableModel tableModel) {
		super(frame, handler, tableModel);
	}

	@Override
	protected ActionListener getActionListenerNuevo() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getHandler().editar(new Autoparte());
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
				getHandler().listarAutopartes();
			}
		};
	}

	@Override
	protected ActionListener getActionListenerVer() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Autoparte autoparte = getElementSelected();
				if (autoparte != null) {
					getHandler().mostrar(autoparte);
				}
			}
		};
	}

}
