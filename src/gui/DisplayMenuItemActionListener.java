package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import gnomenwald.Nation;
import oopstructures.Gnome;

public class DisplayMenuItemActionListener implements ActionListener {
	private Gnome g;
	private JMenuItem menuItem;
	
	public DisplayMenuItemActionListener(Gnome g, JMenuItem menuItem) {
		this.g = g;
		this.menuItem = menuItem;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(menuItem, g.toString());

	}

}
