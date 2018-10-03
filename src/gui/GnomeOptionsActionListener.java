package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gnomenwald.Nation;
import oopstructures.Gnome;

public class GnomeOptionsActionListener implements ActionListener {
	private Nation<Gnome> gnomenwald;
	private GnomeFrame j;
	private int location;
	private Gnome g;
	private String d1 = "Display";
	private String[] options = {"Display", "Add to Location to Travel List"};
	
	public GnomeOptionsActionListener(Nation<Gnome> gnomenwald, GnomeFrame j, int location, Gnome g) {
		super();
		this.gnomenwald = gnomenwald;
		this.j = j;
		this.location = location;
		this.g = g;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		String selected = (String) JOptionPane.showInputDialog((Component)e.getSource(), "Select an action for Gnome " + g.getName(), 
				"Gnome Actions", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		if (selected.equals("Display")) {
			JOptionPane.showMessageDialog((Component)e.getSource(), g.toString(), "Gnome Details", JOptionPane.OK_OPTION);
			return;
		}
		if (selected.equals("Add to Location to Travel List")) {
			JOptionPane.showMessageDialog((Component)e.getSource(), g.toString());
			return;
		}
	}

	
}
