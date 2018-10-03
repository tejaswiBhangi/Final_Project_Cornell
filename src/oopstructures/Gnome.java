package oopstructures;

import genericoop.LList;
import gnomenwald.City;
import throwing.OutOfBoundsException;

public class Gnome {
	/*
	 * this class
	 */

	private static int count = 1;
	// keeps track of the people made, in ID
	private String name;
	private int id;
	private int age;
	private int VIPStatus;
	private String favColor;
	private int delay;
	

	public LList<Integer> destinations, passport;

	public Gnome(String name, int age, int location, String color, int VIPStatus) {
		destinations = new LList<Integer>();
		passport = new LList<Integer>();
		
		this.name = name;
		this.age = age;
		this.id = count++;
		favColor = color;
		this.VIPStatus = VIPStatus;
		
		this.delay = 100;
		
		passport.append(location);
	}
	
	public Gnome(String name, int age, int location, String color, int VIPStatus, int delay) {
		destinations = new LList<Integer>();
		passport = new LList<Integer>();
		
		this.name = name;
		this.age = age;
		this.id = count++;
		favColor = color;
		this.VIPStatus = VIPStatus;
		
		this.delay = delay;
		
		passport.append(location);
	}
	
	public int getDelay() {
		return this.delay;
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}

	public String getFavColor() {
		return favColor;
	}

	public void setFavColor(String favColor) {
		this.favColor = favColor;
	}
	
	public Gnome(String name, int location, String color, int VIPStatus) {
		destinations = new LList<Integer>();
		passport = new LList<Integer>();
		
		this.name = name;
		this.age = 0;
		this.id = count++;
		favColor = color;
		this.VIPStatus = VIPStatus;
		
		passport.append(location);
	}
	// gives gnome a name and a unique ID using static int count.
	
	public int getVIPStatus() {
		return VIPStatus;
	}

	public void setVIPStatus(int vIPStatus) {
		VIPStatus = vIPStatus;
	}

	public boolean hasDest() {
		if (destinations.length() > 0) {
			return true;
		} else {
			return false;
		}
	}
	// returns true if the gnome has a next destination, false otherwise

	public int move() {
		int whereTo = -99;
		try {
			whereTo = destinations.get(0);
			destinations.remove(0);
		} catch (OutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		passport.prepend(whereTo);
		return whereTo;
	}
	// returns the cityid of the next destination and adds it to passport

	public int getLocation() {
		
		try {
			return passport.get(0);
		} catch (OutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -99;
	}
	// gets the next location

	public <X extends Gnome> void setDest(LList<City<X>> list) throws OutOfBoundsException {
		for (int i = 0; i<list.length(); i++){
			destinations.append(list.get(i).getId());
		}

	}
	// adds the next cityid to the queue of destinations

//	public void setDest(int[] cityids) {
//		for (int id : cityids) {
//			destinations.append(id);
//		}
//	}
	// adds the next group of cityids to the queue of destinations

	public String getName() {
		return this.name;
	}
	// returns the String name

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return this.id;
	}
	// returns the unique int id.

	public boolean equals(Gnome equiv) {
		if (this == equiv) {
			return true;
		} else {
			return false;
		}
	}
	// compares objects by memory location

	public String toString() {
		return "Name: " + this.name + ", ID: " + this.id + ", Location: " + this.getLocation();
	}

}