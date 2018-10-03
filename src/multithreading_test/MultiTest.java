package multithreading_test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import gnomenwald.City;
import gnomenwald.Nation;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.NotOnlyChildException;
import throwing.OutOfBoundsException;

public class MultiTest {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, NotFoundException, OutOfBoundsException, InterruptedException, NotOnlyChildException {
		// PingPong little = new PingPong("ping", 333);
		// PingPong bigger = new PingPong("PONG", 1000);
		// little.start();
		// bigger.start();
		
		System.out.println("I carried on after spawning 2 threads.");

		// for (; ;) {
		// System.out.println("testing");
		//
		// } this thread has priority, others will run only after this one ends

		Nation<Gnome> gnomenwald = new Nation<Gnome>();
		// creates the nation
		gnomenwald.addCity(new City<Gnome>(3), new Integer[0][0]);
		gnomenwald.addCity(new City<Gnome>(10), new Integer[0][0]);
		gnomenwald.addCity(new City<Gnome>(2), new Integer[0][0]);
		gnomenwald.addCity(new City<Gnome>(4), new Integer[0][0]);
		// adds a cities to the nation
		gnomenwald.addRoad(1, 2, 10);
		gnomenwald.addRoad(2, 1, 10);
		gnomenwald.addRoad(1, 3, 2);
		gnomenwald.addRoad(2, 4, 10);
		gnomenwald.addRoad(3, 4, 3);
		gnomenwald.addRoad(4, 2, 1);
		gnomenwald.addRoad(2, 1, 12);
		// adds roads to and from the cities
		
		//Map tracker = new Map(gnomenwald, 1000);

		GnomeIterator a = new GnomeIterator(new Gnome("a", 5, 1, "lol", 2, 1000), gnomenwald);
		GnomeIterator b = new GnomeIterator(new Gnome("b", 5, 1, "lol", 2, 1000), gnomenwald);
		GnomeIterator c = new GnomeIterator(new Gnome("c", 5, 1, "lol", 2, 1000), gnomenwald);
		GnomeIterator d = new GnomeIterator(new Gnome("d", 5, 1, "lol", 2, 1000), gnomenwald);
		GnomeIterator e = new GnomeIterator(new Gnome("e", 5, 1, "lol", 2, 1000), gnomenwald);
		GnomeIterator f = new GnomeIterator(new Gnome("f", 5, 1, "lol", 2, 1000), gnomenwald);
		GnomeIterator g = new GnomeIterator(new Gnome("g", 5, 1, "lol", 2, 1000), gnomenwald);

		
		//tracker.start();
		a.start();
		b.start();
		c.start();
		d.start();
		e.start();
		f.start();
		g.start();
//		Gnome a = new Gnome("a", 5, 1, "lol", 2);
//		Gnome b = new Gnome("b", 5, 1, "lol", 2);
//		Gnome c = new Gnome("c", 5, 1, "lol", 2);
//		Gnome d = new Gnome("d", 5, 1, "lol", 2);
//		Gnome e = new Gnome("e", 5, 1, "lol", 2);
//		Gnome f = new Gnome("f", 5, 1, "lol", 2);
//		Gnome g = new Gnome("g", 5, 1, "lol", 2);
//		gnomenwald.addCitizen(a, a.getLocation());
//		gnomenwald.addCitizen(b, b.getLocation());
//		gnomenwald.addCitizen(c, c.getLocation());
//		gnomenwald.addCitizen(d, d.getLocation());
//		gnomenwald.addCitizen(e, e.getLocation());
//		gnomenwald.addCitizen(f, f.getLocation());
//		gnomenwald.addCitizen(g, g.getLocation());
//		int[] destination = {2};
//		a.setDest(destination);
//		b.setDest(destination);
//		c.setDest(destination);
//		d.setDest(destination);
//		e.setDest(destination);
//		f.setDest(destination);
//		g.setDest(destination);
//		gnomenwald.travel(a);
//		gnomenwald.travel(b);
//		gnomenwald.travel(c);
//		gnomenwald.travel(d);
//		gnomenwald.travel(e);
//		gnomenwald.travel(f);
//		gnomenwald.travel(g);
	}
}
