package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import genericoop.LList;
import gnomenwald.City;
import gnomenwald.Nation;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.OutOfBoundsException;

public class GnomeShowHistoryActionListener implements ActionListener {

	private Nation<Gnome> gnomenwald;
	private Gnome g;
	private JMenuItem menuItem;
	
	
	public GnomeShowHistoryActionListener(Nation<Gnome> gnomenwald, Gnome g, JMenuItem menuItem) {
		this.gnomenwald = gnomenwald;
		this.g = g;
		this.menuItem = menuItem;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//TODO display gnome travel history
		
		LList<Integer> ll = g.passport;
		int length = ll.length();
		City<Gnome>[] cities = new City[length];
		
			for (int i = 0; i<length; i++) {
				try {
					cities[i] = gnomenwald.getCity(g.passport.get(i));
				} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException
						| NotFoundException | OutOfBoundsException e1) {
					
					e1.printStackTrace();
					continue;
				}
			}
			
			JOptionPane.showInputDialog((Component)e.getSource(), "Passport cities for Gnome "+g.getName(), "Passport", 
					JOptionPane.INFORMATION_MESSAGE, null, cities, null);
			
		
	}

}
