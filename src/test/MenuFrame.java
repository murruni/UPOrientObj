package test;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MenuFrame {
	@SuppressWarnings("static-access")
	public MenuFrame() {
		JFrame frmMain = new JFrame("Test");
		JPanel pTop, pBottom;
		JMenuBar mnubTop, mnubBottom;
		JMenu mnuMenu1, mnuMenu2, mnuMenu3, mnuMenu4;
		JMenuItem mnuiMenuItem1, mnuiMenuItem2, mnuiMenuItem3, mnuiMenuItem4;
		pTop = new JPanel();
		pBottom = new JPanel();
		mnubTop = new JMenuBar();
		mnubBottom = new JMenuBar();
		mnuMenu1 = new JMenu("File");
		mnuMenu2 = new JMenu("Edit");
		mnuMenu3 = new JMenu("View");
		mnuMenu4 = new JMenu("Help");
		mnuiMenuItem1 = new JMenuItem("Menu");
		mnuiMenuItem2 = new JMenuItem("Menu");
		mnuiMenuItem3 = new JMenuItem("Menu");
		mnuiMenuItem4 = new JMenuItem("Menu");
		mnuMenu1.add(mnuiMenuItem1);
		mnuMenu2.add(mnuiMenuItem2);
		mnuMenu3.add(mnuiMenuItem3);
		mnuMenu4.add(mnuiMenuItem4);
		mnubTop.add(mnuMenu1);
		mnubTop.add(mnuMenu2);
		mnubBottom.add(mnuMenu3);
		mnubBottom.add(mnuMenu4);

		frmMain.setLayout(new BorderLayout());
		pTop.setLayout(new BorderLayout());
		pBottom.setLayout(new BorderLayout());
		pTop.add(mnubTop, new BorderLayout().CENTER);
		pBottom.add(mnubBottom, new BorderLayout().CENTER);
		frmMain.add(pTop, new BorderLayout().NORTH);
		frmMain.add(pBottom, new BorderLayout().SOUTH);
		frmMain.setSize(150, 220);
		frmMain.setVisible(true);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		new MenuFrame();
	}
}
