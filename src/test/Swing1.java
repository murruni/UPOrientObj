package test;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Swing1 {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test");

//		Container contentPane = frame.getContentPane();
		
		// SpringLayout layout = new SpringLayout();
		//
		// JLabel label = new JLabel("Label: ");
		// layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST,
		// contentPane);
		// layout.putConstraint(SpringLayout.NORTH, label, 5,
		// SpringLayout.NORTH, contentPane);
		//// contentPane.add(label);
		//
		// JTextField textField = new JTextField("Text field", 15);
		// layout.putConstraint(SpringLayout.WEST, textField, 5,
		// SpringLayout.EAST, label);
		// layout.putConstraint(SpringLayout.NORTH, textField, 5,
		// SpringLayout.NORTH, contentPane);
		//// contentPane.add(textField);
		//
		// label.setLabelFor(textField);
		// contentPane.add(textField);
		//
		// contentPane.setLayout(layout);
		// frame.pack();

		// String[] labels = { "Name: ", "Fax: ", "Email: ", "Address: " };
		// int numPairs = labels.length;
		//
		// // Create and populate the panel.
		// JPanel p = new JPanel(new SpringLayout());
		// for (int i = 0; i < numPairs; i++) {
		// JLabel l = new JLabel(labels[i], JLabel.TRAILING);
		// p.add(l);
		// JTextField textField = new JTextField(10);
		// l.setLabelFor(textField);
		// p.add(textField);
		// }
		frame.setLayout(new GridLayout(0, 1));

		JLabel siteName = new JLabel("Site Name: ");
		JLabel siteAddress = new JLabel("Site Address: ");

		JTextField name = new JTextField();
		JTextField address = new JTextField("http://");

		siteName.setLabelFor(name);
		siteAddress.setLabelFor(address);

		frame.add(siteName);
		frame.add(name);
		frame.add(siteAddress);
		frame.add(address);

		frame.setVisible(true);
	}
}
