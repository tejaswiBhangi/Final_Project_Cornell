package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import genericoop.LList;
import gnomenwald.GnomeStorage;
import gnomenwald.Nation;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.OutOfBoundsException;


public class CityButtonActionListener implements ActionListener {
	Nation<Gnome> gnomenwald;
	int cityId;
	GnomeFrame j;
	GnomeStorage<Gnome> citizens;

	public CityButtonActionListener(Nation<Gnome> gnomenwald, int id, GnomeFrame j) {
		super();
		this.gnomenwald = gnomenwald;
		this.cityId = id;
		this.j = j;
		try {
			this.citizens = gnomenwald.getCityGoers(cityId);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//creates a popup menu
        final JPopupMenu popup = new JPopupMenu();
        
        
        //Creates a city from this city
        JMenuItem menuItem = new JMenuItem("Create City from this City");
        menuItem.addActionListener(new AddCityItemActionListener(gnomenwald, cityId, j));
        popup.add(menuItem);

        //Creates a gnome at this city
        JMenuItem menuItem2 = new JMenuItem("Create Gnome here");
        menuItem2.addActionListener(new AddGnomeItemActionListener(gnomenwald, cityId));
        popup.add(menuItem2);
        
        //creates a road from this city to another
        JMenuItem menuItem3 = new JMenuItem("Add Road");
        menuItem3.addActionListener(new AddRoadItemActionListener(gnomenwald, cityId));
        popup.add(menuItem3);
        
        //Creates a gnome at this city
        JMenu menuItem4 = new JMenu("Gnomes...");
         menuItem4.addMouseListener(new GnomesMouseListener(gnomenwald, cityId, j, gnomesInCity(), menuItem4));
 
        
        popup.add(menuItem4);
        
        //show connections at this city
        JMenuItem menuItem5 = new JMenuItem("Show connected cities...");
        menuItem5.addActionListener(new ShowConnectedCitiesListener(gnomenwald, cityId));
        popup.add(menuItem5);

        //delete a city
        JMenu menuItem6 = new JMenu("Delete this city...");
        
        JMenuItem menuItem61 = new JMenuItem("Delete Roads...");
        menuItem61.addActionListener(new DeleteCityAndRoadsListener(gnomenwald, cityId));
        menuItem6.add(menuItem61);
        
        JMenuItem menuItem62 = new JMenuItem("Combine Roads...");
        menuItem62.addActionListener(new DeleteCityOnlyListener(gnomenwald, cityId));
        menuItem6.add(menuItem62);
        
        popup.add(menuItem6);
        
        //creates the popup menu on the city button
        JButton button = (JButton)e.getSource();
        popup.show(button, 1,button.getHeight());
//        popup.setVisible(true);

		
	}//called when a city is clicked on
	//manages the city

	private Gnome[] gnomesInCity() {
		LList<Gnome> storage;
		Gnome[] gnomesInCity = null;
		try {
			storage = gnomenwald.getCityGoers(cityId).storage;
			int numberOfGnomes = storage.length();
			gnomesInCity = new Gnome[numberOfGnomes];
			for(int i = 0; i<numberOfGnomes; i++){
				gnomesInCity[i] = storage.get(i);
			} 
		}catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NotFoundException | OutOfBoundsException e) {
			e.printStackTrace();
		}

		return gnomesInCity;
	
	} //creates an array containing all the gnomes in the city
	//useful for iterating

}
