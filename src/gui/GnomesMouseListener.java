package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import gnomenwald.Nation;
import oopstructures.Gnome;

public class GnomesMouseListener implements MouseListener {
	Nation<Gnome> gnomenwald;
	int location;
	GnomeFrame j;
	Gnome[] gnomeList;
	JMenu subMenu;

	public GnomesMouseListener(Nation<Gnome> gnomenwald, int location, GnomeFrame j, Gnome[] gnomes, JMenu subMenu) {
		this.gnomenwald = gnomenwald;
		this.location = location;
		this.j = j;
		this.gnomeList = gnomes;
		this.subMenu = subMenu;
	}
	// constructor
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {
		subMenu.removeAll();
		for(Gnome g: gnomeList){
        	JMenu gnomeMenu = new JMenu(g.getName() + "...");
        	gnomeMenu.addMouseListener(new GnomeOptionsMouseListener(gnomenwald, j, location, g, gnomeMenu));
        	subMenu.add(gnomeMenu);
        }

	}
}