package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;

import gnomenwald.Nation;
import oopstructures.Gnome;
import throwing.NotFoundException;

public class AddRoadItemActionListener implements ActionListener {
	private int cost;
	private int to;
	private int from;

	private Nation<Gnome> gnomenwald;
	
	public AddRoadItemActionListener(Nation<Gnome> n, int id){
		super();
		this.gnomenwald = n;
		this.from = id;
	}
	public void actionPerformed(ActionEvent e) {
		String capacityString = (String)JOptionPane.showInputDialog("Enter the cityId of the City you want to build to");
		try {
			to = Integer.parseInt(capacityString);
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Error: Not an integer id");
		}
		
		
		String costString = (String)JOptionPane.showInputDialog("Enter cost of the road to this city");
		try {
			cost = Integer.parseInt(costString);
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Error: Not an integer price");
		}

		try {
			gnomenwald.addRoad(from, to, cost);
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotFoundException e1) {
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Error: City Destination not found");
		}

	} //called when an action is performed
	//adds a road
	//checks that input is properly formatted

}
