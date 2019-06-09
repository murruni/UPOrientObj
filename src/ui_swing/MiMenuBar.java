package ui_swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import control.Handler;

public class MiMenuBar extends JMenuBar {
	private Handler handler;

	public MiMenuBar(Handler handler) {
		this.handler = handler;
		initUI();
	}

	public void initUI() {
		JMenu menu;
		JMenuItem menuItem;

		// ---------------------------------------------------------------------------------------
		// -- APP
		// ---------------------------------------------------------------------------------------
		menu = new JMenu("Home");

		// inicio
		menuItem = new JMenuItem("Inicio");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getHandler().pantallaInicio();
			}
		});
		menu.add(menuItem);

		// exit
		menuItem = new JMenuItem("Salir");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getHandler().salir();
			}
		});
		menu.add(menuItem);
		add(menu);

		// ---------------------------------------------------------------------------------------
		// -- Inventory --
		// ---------------------------------------------------------------------------------------
		menu = new JMenu("Inventario");

		menuItem = new JMenuItem("Autos");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getHandler().listarAutos();
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Autopartes");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getHandler().listarAutopartes();
			}
		});
		menu.add(menuItem);
		add(menu);

		// ---------------------------------------------------------------------------------------
		// -- Repairs --
		// ---------------------------------------------------------------------------------------
		menu = new JMenu("Reparaciones");

		menuItem = new JMenuItem("Reparaciones");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//FIXME falta todo el codigo
//				getHandler().listarAutos();
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Histórico");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//FIXME falta todo el codigo
//				getHandler().listarAutos();
			}
		});
		menu.add(menuItem);

		add(menu);
	}

	// ---------------------------------------------------------------------------------------
	// -- Getters & Setters --
	// ---------------------------------------------------------------------------------------
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
