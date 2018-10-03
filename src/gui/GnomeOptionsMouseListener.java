package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gnomenwald.Nation;
import oopstructures.Gnome;

public class GnomeOptionsMouseListener implements MouseListener {
	private Nation<Gnome> gnomenwald;
	private GnomeFrame j;
	private int location;
	private Gnome g;
	private String d1 = "Display";
	private String[] options = {"Display", "Add to Location to Travel List"};
	private JMenu gnomeMenu;
	
	public GnomeOptionsMouseListener(Nation<Gnome> gnomenwald, GnomeFrame j, int location, Gnome g, JMenu gnomeMenu) {
		super();
		this.gnomenwald = gnomenwald;
		this.j = j;
		this.location = location;
		this.g = g;
		this.gnomeMenu = gnomeMenu;
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		gnomeMenu.removeAll();
		JMenuItem menuItem = new JMenuItem("Display Information");
    	menuItem.addActionListener(new DisplayMenuItemActionListener(g, menuItem));
    	gnomeMenu.add(menuItem);
    	
    	JMenuItem menuItem2 = new JMenuItem("Add destination to travel List");
    	menuItem2.addActionListener(new NewTravelActionListener(gnomenwald, g, menuItem));
    	gnomeMenu.add(menuItem2);
    	
    	JMenuItem menuItem3 = new JMenuItem("Begin this gnome's adventure");
    	menuItem3.addActionListener(new GnomeAdventureActionListener(gnomenwald, g, menuItem));
    	gnomeMenu.add(menuItem3);
    	
    	JMenuItem menuItem4 = new JMenuItem("Show Travel History");
    	menuItem4.addActionListener(new GnomeShowHistoryActionListener(gnomenwald, g, menuItem));
    	gnomeMenu.add(menuItem4);
    	
	}


	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	
}

//String selected = (String) JOptionPane.showInputDialog((Component)e.getSource(), "Select an action for Gnome " + g.getName(), 
//		"Gnome Actions", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
//
//if (selected.equals("Display")) {
//	JOptionPane.showMessageDialog((Component)e.getSource(), g.toString(), "Gnome Details", JOptionPane.OK_OPTION);
//	return;
//}
//if (selected.equals("Add to Location to Travel List")) {
//	JOptionPane.showMessageDialog((Component)e.getSource(), g.toString());
//	return;
//}