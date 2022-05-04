package gui;

import javax.swing.JPanel;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JTable;

public class Tiempo_gui extends JPanel {
	
	private JTable table;
//Examen 11, Ejercicio 1, eval 3
	/**
	 * Create the panel.
	 */
	public Tiempo_gui() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JList list = new JList();
		add(list);
		
		table = new JTable();
		add(table);
		
	}

}
