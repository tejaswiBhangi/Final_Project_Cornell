package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import gnomenwald.Nation;
import oopstructures.Gnome;

public class NewTravelActionListener implements ActionListener {
	private Nation<Gnome> gnomenwald;
	private Gnome g;
	private JMenuItem menuItem;
	private int dest;
	public NewTravelActionListener(Nation<Gnome> gnomenwald, Gnome g, JMenuItem menuItem) {
		this.gnomenwald = gnomenwald;
		this.g = g;
		this.menuItem = menuItem;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String destString = JOptionPane.showInputDialog("Enter a city id destination");
		try {
			dest = Integer.parseInt(destString);
			gnomenwald.travelList(g, dest);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(menuItem, "Error: not a valid int id");
			return;
		}
	}

}
