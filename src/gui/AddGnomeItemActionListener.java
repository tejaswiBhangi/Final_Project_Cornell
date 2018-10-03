package gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;


import javax.swing.Icon;
import javax.swing.JOptionPane;

import gnomenwald.Nation;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.OutOfBoundsException;

public class AddGnomeItemActionListener implements ActionListener {
	Nation<Gnome> gnomenwald;
	int location;
	int age;
	String name;
	int vipstat;
	String color;
	Gnome newGnome;
	int delay;
	public AddGnomeItemActionListener(Nation<Gnome> gnomenwald, int cityId) {
		// TODO Auto-generated constructor stub
		this.gnomenwald = gnomenwald;
		this.location = cityId;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		name = (String)JOptionPane.showInputDialog("Enter the Gnome's name");
		color = (String)JOptionPane.showInputDialog("Enter the Gnome's favorite color");
		
		String vipString = (String)JOptionPane.showInputDialog("Enter the Gnome's VIP Status");
		String ageString = (String)JOptionPane.showInputDialog("(Optional) Enter the Gnome's age");
		String delayString = (String)JOptionPane.showInputDialog("(Option) Enter the Gnome's sleep requirements");
		
		try {
			vipstat = Integer.parseInt(vipString);
			newGnome = new Gnome(name, this.location, color, vipstat);
			
			if(!ageString.equals(null)){
				age = Integer.parseInt(ageString);
				newGnome.setAge(age);
			}
			if(!delayString.equals(null)){
				delay = Integer.parseInt(delayString);
				newGnome.setDelay(delay);
			}
			
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Error: Not an integer ");
			return;
		}	
		try {
			gnomenwald.addCitizen(newGnome, this.location);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | OutOfBoundsException
				| NotFoundException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	} //called when action is performed
	//creates a gnome
	//makes sure parameters are properly formatted


}
