package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import gnomenwald.City;
import gnomenwald.Nation;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.OutOfBoundsException;

public class GenerateTestDataListener implements ActionListener {
	Nation<Gnome> gnomenwald;
	GnomeFrame gnomeFrame;
	public GenerateTestDataListener(Nation<Gnome> gnomenwald, GnomeFrame gnomeFrame) {
		this.gnomenwald = gnomenwald;
		this.gnomeFrame = gnomeFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	//generate test data
		
		try {
			City<Gnome>  c1 = new City<Gnome>(4);
			City<Gnome> c2 = new City<Gnome>(5);
			City<Gnome> c3 = new City<Gnome>(3);
			City<Gnome> c4 = new City<Gnome>(4);
			City<Gnome> c5 = new City<Gnome>(5);
			City<Gnome> c6 = new City<Gnome>(6);
			City<Gnome> c7 = new City<Gnome>(7);
			City<Gnome> c8 = new City<Gnome>(8);
			City<Gnome> c9 = new City<Gnome>(9);
			City<Gnome> c10 = new City<Gnome>(10);
			City<Gnome> c11 = new City<Gnome>(11);
			City<Gnome> c12 = new City<Gnome>(12);
			City<Gnome> c13 = new City<Gnome>(13);

		Gnome g0 = new Gnome("0", 0, 1, "BLUE", 0, 332);	
		Gnome g1 = new Gnome("1", 1, 1, "BLUE", 1, 333);
		Gnome g2 = new Gnome("2", 2, 2, "BLUE", 2, 334);
		Gnome g3 = new Gnome("3", 3, 3, "BLUE", 3, 335);
		Gnome g4 = new Gnome("4", 4, 4, "BLUE", 4, 336);
		Gnome g5 = new Gnome("5", 5, 5, "BLUE", 5, 537);
		Gnome g6 = new Gnome("6", 6, 6, "BLUE", 6, 666);
		Gnome g7 = new Gnome("7", 7, 7, "BLUE", 7, 778);
		Gnome g8 = new Gnome("8", 8, 8, "BLUE", 8, 833);
		Gnome g9 = new Gnome("9", 9, 9, "BLUE", 9, 834);
		Gnome g10 = new Gnome("10", 10, 10, "BLUE", 10, 1000);
		Gnome g11 = new Gnome("11", 11, 11, "BLUE", 11, 220);
		Gnome g12 = new Gnome("12", 12, 12, "BLUE", 12, 150);
		Gnome g13 = new Gnome("13", 13, 13, "BLUE", 13, 1300);
		Gnome g14 = new Gnome("14", 14, 14, "BLUE", 14, 1409);
		Gnome g15 = new Gnome("15", 15, 15, "BLUE", 15, 154);
		Gnome g16 = new Gnome("16", 16, 16, "BLUE", 16, 164);
		Gnome g17 = new Gnome("17", 17, 17, "BLUE", 17, 177);
		Gnome g18 = new Gnome("18", 18, 18, "BLUE", 18, 188);
		Gnome g19 = new Gnome("19", 19, 19, "BLUE", 19, 199);

//cant add cities and roads at same time as cities wont exist yet
			gnomenwald.addCity(c1, new Integer[][]{});
			gnomenwald.addCity(c2, new Integer[][]{});
			gnomenwald.addCity(c3, new Integer[][]{{2, 2}, {1, 1}});
			gnomenwald.addCity(c4, new Integer[][]{{3, 3}, {2,2}});
			gnomenwald.addCity(c5, new Integer[][]{{3, 3}, {2, 2}});
			gnomenwald.addCity(c6, new Integer[][]{{1, 1}, {2, 2}});
			gnomenwald.addCity(c7, new Integer[][]{{4, 4}, {3, 3}});
			gnomenwald.addCity(c8, new Integer[][]{});
			gnomenwald.addCity(c9, new Integer[][]{});
			gnomenwald.addCity(c10, new Integer[][]{});
			gnomenwald.addCity(c11, new Integer[][]{{7, 7}, {9, 9}});
			gnomenwald.addCity(c12, new Integer[][]{{5, 5}, {1, 1}});
			gnomenwald.addCity(c13, new Integer[][]{{4, 4}, {9, 9}});
			gnomenwald.addRoad(1, 2, 2);
			gnomenwald.addRoad(1, 3, 3);
			gnomenwald.addRoad(2, 3, 3);
			gnomenwald.addRoad(3, 4, 4);
			gnomenwald.addRoad(3, 5, 5);
			gnomenwald.addRoad(4, 5, 5);
			gnomenwald.addRoad(4, 6, 6);
			gnomenwald.addRoad(5, 7, 7);
			gnomenwald.addRoad(5, 6, 6);
			gnomenwald.addRoad(6, 7, 7);
			gnomenwald.addRoad(7, 8, 8);
			gnomenwald.addRoad(9, 10, 10);
			
			gnomenwald.addRoad(11, 12, 12);
			gnomenwald.addRoad(11, 13, 13);
			gnomenwald.addRoad(10, 11, 11);
			gnomenwald.addRoad(10, 12, 12);
			gnomenwald.addRoad(7, 9, 9);
			
			gnomenwald.addRoad(6, 8, 8);
			
			gnomenwald.addCitizen(g0, 1);
			gnomenwald.addCitizen(g1, 1);
			gnomenwald.addCitizen(g2, 2);
			gnomenwald.addCitizen(g3, 3);
			gnomenwald.addCitizen(g4, 4);
			gnomenwald.addCitizen(g5, 5);
			gnomenwald.addCitizen(g6, 6);
			gnomenwald.addCitizen(g7, 7);
			gnomenwald.addCitizen(g8, 8);
			gnomenwald.addCitizen(g9, 9);
			gnomenwald.addCitizen(g10, 10);
			gnomenwald.addCitizen(g11,  11);
			gnomenwald.addCitizen(g12, 12);
			gnomenwald.addCitizen(g13, 13);
			gnomenwald.addCitizen(g14, 1);
			gnomenwald.addCitizen(g15, 2);
			gnomenwald.addCitizen(g16, 3);
			gnomenwald.addCitizen(g17, 4);
			gnomenwald.addCitizen(g18, 5);
			gnomenwald.addCitizen(g19, 6);
			
			for (int i = c1.getId(); i <= c13.getId(); i++) {
				gnomeFrame.createCityButton(i);
			}
			
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
				| InvocationTargetException | NotFoundException | OutOfBoundsException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
