package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import genericoop.LList;
import gnomenwald.Nation;
import oopstructures.Gnome;

public class MoveGnomeActionListener implements ActionListener {
	private Nation<Gnome> gnomenwald;
	private int cost;
	private int fromCityId;
	private Gnome thisGnome;
	private int numberOfGnomes;
	GnomeFrame j;

	public MoveGnomeActionListener(Nation<Gnome> gnomenwald, int cityId, GnomeFrame j, Gnome g) {
		super();
		this.gnomenwald = gnomenwald;
		fromCityId = cityId;
		this.j = j;
		this.thisGnome = g;
	}
	//creates a gnome 

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	// does an action

}
