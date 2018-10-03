package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JMenuItem;

import gnomenwald.Nation;
import multithreading_test.GnomeIterator;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.OutOfBoundsException;

public class GnomeAdventureActionListener implements ActionListener {
	private Nation<Gnome> gnomenwald;
	private Gnome g;
	private JMenuItem menuItem;
	
	public GnomeAdventureActionListener(Nation<Gnome> gnomenwald, Gnome g, JMenuItem menuItem) {
		this.gnomenwald = gnomenwald;
		this.g = g;
		this.menuItem = menuItem;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Thread adventure = new Thread(new GnomeThread());
		adventure.start();

	}
	private class GnomeThread implements Runnable{
		
		public GnomeThread(){
			super();
		}
		public void run(){
			try {
				GnomeIterator gnomeIterator = new GnomeIterator(g, gnomenwald);
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | OutOfBoundsException
					| NotFoundException | InterruptedException e1) {
				e1.printStackTrace();
			}
		}

	}

}
