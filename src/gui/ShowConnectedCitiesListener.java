package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import genericoop.GGraph;
import genericoop.LList;
import gnomenwald.City;
import gnomenwald.Nation;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.OutOfBoundsException;

public class ShowConnectedCitiesListener implements ActionListener {

	Nation<Gnome>  gnomenwald;
	int location;
	public ShowConnectedCitiesListener(Nation<Gnome> gnomenwald, int cityId) {
		this.gnomenwald = gnomenwald;
		this.location = cityId;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			City city = gnomenwald.getCity(this.location);
			//TODO get cities connected to the above city
			Color oldColor;
			City[] cities = getConnectedCities(city);
			//dsplay the cities in a dropdown
			JOptionPane.showInputDialog((Component)arg0.getSource(), "Connected Cities from "+city.getId(), "Connected Cities", JOptionPane.YES_OPTION, null, cities, null);
			//flicker the colors of the connected cities
			
			for (int i = 0; i<cities.length; i++) {
				JButton b = GnomeFrame.buttonMap.get(i);
				if (b == null) continue;
				oldColor = b.getForeground();
				b.setOpaque(true);
				b.setForeground(Color.RED);
				b.invalidate();
				//trigger button refresh
				b.setText(b.getText());
				Thread.sleep(500);
				b.setForeground(oldColor);
				b.setText(b.getText());
				b.invalidate();
			}	
			
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NotFoundException | InterruptedException e) {
			e.printStackTrace();
		}
		

	}

	private City[] getConnectedCities(City city) {
		try {
			LList<GGraph<City<Gnome>>.Edge> ll = gnomenwald.getNat().getList(city);
			int length = ll.length();
			City[] cities = new City[length];
			for (int i= 0; i<length; i++) {
				cities[i]= ll.get(i).getData();
			}
			return cities;
		} catch (NotFoundException e) {

			e.printStackTrace();
			
		} catch (OutOfBoundsException e) {

			e.printStackTrace();
		}
		return new City[0];
		
	}

}
