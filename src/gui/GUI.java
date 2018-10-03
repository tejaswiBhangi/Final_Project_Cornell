package gui;

import gnomenwald.Nation;
import oopstructures.Gnome;

public class GUI {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Nation<Gnome> gnomenwald = new Nation<Gnome>();
		// gnomenwald.addCity(null);
		GnomeFrame window = new GnomeFrame("GnomenWald", gnomenwald);

		window.setUpComponents();
	}
	// runs the gui
}
// JFrame setup = new JFrame("Gnomenwald");
// setup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// setup.setSize(10000,10000);
// setup.setVisible(true);
// setup.setLayout(new GridLayout(100,100));