package gnomenwald;

import java.lang.reflect.InvocationTargetException;

import genericoop.ReadWriteLock;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.NotOnlyChildException;

public class City<T extends Gnome> {
	private GnomeStorage<Gnome> occupants;
	public static int firstId = 1;
	private int id;
	public ReadWriteLock lock;
	private int citizens;
	// private variables specific to the city

	public City(int capacity) throws NoSuchMethodException, SecurityException {
		this.lock = new ReadWriteLock(capacity);
		occupants = new GnomeStorage<Gnome>(Gnome.class); /// ?????????
		id = firstId++;
	}
	// constructor, initializes GnomeStorage of occupants and unique id.

	public GnomeStorage<Gnome> getOccupants() {
		return occupants;
	}
	// gets the gnomestorage of the occupants
	
	public int getNumCitizens() {
		return citizens;
	}
	// gets the number of citizens in the city
	
	public void addCitizen(T newbie)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InterruptedException {
		if (newbie.getVIPStatus() > 20) {
			lock.VIPlockRead();
		} else {
			lock.lockRead();
		}	
		synchronized(this){
			occupants.insert(newbie);
			citizens++;
		}
	}
	// adds an occupant

	public void removeCitizen(T oldie) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NotFoundException, NotOnlyChildException {
		try {
			synchronized(this){
				occupants.delete(oldie);
				citizens--;
			}
		}
		finally {
			lock.unlockRead();

		}
	}
	// removes an occupant

	public boolean contains(T target) {
		try {
			occupants.findByAge(target.getAge());
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	// checks whether the city contains an occupant

	public int getId() {
		return id;
	}
	// gets id. used for comparison in BST
	
	public String toString() {
		return "city" + id + ": " + getNumCitizens() + "";
	}
}
