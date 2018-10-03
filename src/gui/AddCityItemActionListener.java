package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

import gnomenwald.City;
import gnomenwald.Nation;
import oopstructures.Gnome;
import throwing.NotFoundException;

public class AddCityItemActionListener implements ActionListener{
	private Nation<Gnome> gnomenwald;
	private int cost;
	private int fromCityId;
	private int capacity;
	GnomeFrame j;
	public AddCityItemActionListener(Nation<Gnome> n, int cityId, GnomeFrame j){
		super();
		gnomenwald = n;
		fromCityId = cityId;
		this.j = j;
		
	}

	public void actionPerformed(ActionEvent e) {
		
		String costString = (String)JOptionPane.showInputDialog("Enter cost of the road to this city");
		try {
			cost = Integer.parseInt(costString);
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Error: Not an integer price");
		}
		String capacityString = (String)JOptionPane.showInputDialog("Enter the capacity of this city");
		try {
			capacity = Integer.parseInt(capacityString);
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Error: Not an integer capacity");
		}

		Integer i = createCity(fromCityId, cost, capacity);
		j.createCityButton(i);
		
		
	} //calls this when an action is performed
	//creates a city
	//makes sure input is correctly formatted
	//
	
	//create city
	public Integer createCity(int fromCityId, int cost, int capacity) {
		Integer id = new Integer(fromCityId);
		Integer[][] pointers = new Integer[][]{{fromCityId, cost}};
		
		City newCity = null;
		try {
			newCity = new City<Gnome>(capacity);
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			gnomenwald.addCity(newCity, pointers);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer i = newCity.getId();
		return i;
	}

}
