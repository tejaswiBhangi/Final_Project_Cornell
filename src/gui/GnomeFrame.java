package gui;

import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import gnomenwald.City;
import gnomenwald.Nation;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.OutOfBoundsException;

public class GnomeFrame extends JFrame {
	private Nation<Gnome> gnomenwald;

	public static HashMap<Integer, JButton> buttonMap= new HashMap<Integer, JButton>();
	
	// creates a GridLayout frame
	public GnomeFrame(String s, Nation<Gnome> n) {
		super(s);
		gnomenwald = n;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setLayout(new GridLayout(5, 5));
		
	}

	// sets up components and first city
	public void setUpComponents() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("Gnomenwald");

		JMenuItem BeginMultiThreadItem = new JMenuItem("MultiThread");
		BeginMultiThreadItem.addActionListener(new MultiThreadActionListener(gnomenwald));
		fileMenu.add(BeginMultiThreadItem);	
		
		JMenuItem ShowAllGnomesItem = new JMenuItem("Show all Gnomes");
		ShowAllGnomesItem.addActionListener(new ShowAllGnomesActionListener(gnomenwald));
		fileMenu.add(ShowAllGnomesItem);
		
		JMenuItem MinimalSpanningTreeItem = new JMenuItem("Update Infrastructure!");
		MinimalSpanningTreeItem.addActionListener(new InfrastructureActionListener(gnomenwald));
		fileMenu.add(MinimalSpanningTreeItem);
		JMenuItem generateTestData = new JMenuItem("Generate Test Data");
		generateTestData.addActionListener(new GenerateTestDataListener(gnomenwald, this));

		fileMenu.add(generateTestData);
		menuBar.add(fileMenu);

		this.setJMenuBar(menuBar);
		City newCity;
		try {
			newCity = new City<Gnome>(10);
			gnomenwald.addCity(newCity, new Integer[0][0]);
			JButton originalCity = new JButton("City " + 1);
			originalCity.addActionListener(new CityButtonActionListener(gnomenwald, 1, this));
			add(originalCity);
			buttonMap.put(newCity.getId(), originalCity);
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
				| InvocationTargetException | NotFoundException e) {

			e.printStackTrace();
		}

		
		setVisible(true);
	} //sets up the overall GUI and buttons
	public void createCityButton(Integer cityId) {
		
		JButton cityB = new JButton("City " + cityId.toString());
		cityB.addActionListener(new CityButtonActionListener(gnomenwald, cityId, this));
		add(cityB);
		buttonMap.put(cityId,  cityB);
		setVisible(true);
		
	} //creates city buttons

	

}
