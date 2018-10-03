package gnomenwald;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import genericoop.BinarySearchTree;
import genericoop.GGraph.Edge;
import genericoop.GGraphExt;
import genericoop.LList;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.NotOnlyChildException;
import throwing.OutOfBoundsException;

public class Nation<T extends Gnome> {
	/*
	 * this class creates a nation, which holds cities (in a graph) as well as
	 * Gnomes (gnomes or their descendants only!) (in a BST) assumes the
	 * gnomes/descendants are being held outside of this nation.
	 */

	private GGraphExt<City<T>> nat;
	// graph of Cities

	private BinarySearchTree<City<T>> cities;
	private int numCities = 0;
	// may not be a linked list in final design. used to manage graph
	// holds the cities

	private GnomeStorage<Gnome> citizens;
	// holds the citizens
	
	//citizens from all cities
	public Gnome[] inCity() throws OutOfBoundsException{
		Gnome[] output = new Gnome[citizens.storage.length()];
		for (int i = 0; i<citizens.storage.length(); i++){
			output[i] = citizens.storage.get(i);
		}
		return output;
	}
	//citizens in a city
	public Gnome[] inCity(int cityId) throws OutOfBoundsException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NotFoundException{
		LList<Gnome> storage;
		Gnome[] gnomesInCity = null;
			storage = this.getCityGoers(cityId).storage;
			int numberOfGnomes = storage.length();
			gnomesInCity = new Gnome[numberOfGnomes];
			for(int i = 0; i<numberOfGnomes; i++){
				gnomesInCity[i] = storage.get(i);
			} 
		return gnomesInCity;
	}
	public City<T> getCity(int id) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NotFoundException {
		return cities.findByValue(id);
	}
	// returns a city
	
	public Nation() throws NoSuchMethodException, SecurityException {
		setNat(new GGraphExt<City<T>>());
		cities = new BinarySearchTree<City<T>>(City.class.getDeclaredMethod("getId"));
		citizens = new GnomeStorage<Gnome>(Gnome.class);
	}
	// instantiates stuff

	public synchronized void addCitizen(T person, int location) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, OutOfBoundsException, NotFoundException, InterruptedException {
		citizens.insert(person);
		cities.findByValue(location).addCitizen(person);
	}
	// adds a person to citizens

	public synchronized void delCitizen(T person) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			NotFoundException, NotOnlyChildException, OutOfBoundsException {
		City<T> location = cities.findByValue(person.getLocation());
		location.removeCitizen(person);
	}
	// removes a person

	public void addCity(City<T> newbie, Integer[][] pointers)
			throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NotFoundException {
		City<T> temp = newbie;
		cities.insert(temp);
		getNat().addGNode(temp);

		for (int i = 0; i < pointers.length; i++) {
			try {
				getNat().addEdge(cities.findByValue(pointers[i][0]), temp, pointers[i][1]);
			} catch (NotFoundException e) {
				throw new NotFoundException("\\city doesn't exist!!");
			}
		}
		numCities++;
	}
	// expects a double array of ints, with each array in the array having a
	// length of two. expected params are [cityid, cost] for edge creation

	public void addRoad(int from, int to, int cost)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NotFoundException {
		getNat().addEdge(cities.findByValue(from), cities.findByValue(to), cost);
	}
	// expects the ids of two cities, to create a road from the first one to the
	// second

	public int getCurrentId(City<T> current) {
		return current.getId();
	}
	// gets the id of the city last created

	public void deleteCity(int id, String d) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NotFoundException, OutOfBoundsException {

		/*
		 * two ways to delete a city
		 * 
		 * 1. delete all the roads going to/from the city (easy)
		 * 
		 * 2. delete the roads from the city, but keep roads through the city
		 */

		if (d.equals("a")) {
			getNat().delGNodeAll(cities.findByValue(id));
			// 1
		} else if (d.equals("t")) {
			getNat().delGNodeThru(cities.findByValue(id));
			// 2
		} else {
			throw new NotFoundException("the string " + d + " is not a t or a");
		}
		numCities--;
	}
	// deletes the city, with method all or thru. d is expected to be either "a"
	// or "t", for all or thru

	public int getNumCities() {
		return numCities;
	}
	//gets the number of cities 
	
	public void travel(T immigrant) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			NotFoundException, NotOnlyChildException, InterruptedException, OutOfBoundsException {
		int destination = immigrant.destinations.get(0);
		if (!immigrant.hasDest()) {
			return;
		} else if (immigrant.getLocation() == destination) {
			destination = immigrant.move();
			return;
		} else {
			City<T> from = cities.findByValue(immigrant.getLocation());
			City<T> to = cities.findByValue(destination);
			destination = immigrant.move();
			@SuppressWarnings("rawtypes")
			Edge theRoad = getNat().getEdge(from, to);
			theRoad.lock.lockRead();
			//locks the edge
			from.removeCitizen(immigrant);
			// removes person from their current location
			to.addCitizen(immigrant);
			// adds person to their new location
			
			theRoad.lock.unlockRead();
			//unlocks the edge
		}

	}
	// moves a person to the city

	public void travelList(T citizen, int destination) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NotFoundException, OutOfBoundsException {
		Gnome temp = citizens.findByAge(citizen.getAge());
		City<T> to = cities.findByValue(temp.getLocation());
		City<T> from = cities.findByValue(destination);
		temp.setDest(getNat().shortestPath(to, from));
	}
	// adds a destination to a gnome's travel list

	public void travelList(T citizen, int[] destinations)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NotFoundException, OutOfBoundsException {
//		Gnome temp = citizens.findByAge(citizen.getAge());
		for (int i : destinations){
			travelList(citizen, i);
		}
	}
	// adds destinations to a gnome's travel list
	
	public GnomeStorage<Gnome> getCityGoers(int id) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NotFoundException {
		return cities.findByValue(id).getOccupants();
	}
	//gets the occupants of a specified city
	
	public String toString() {
		return cities.toString();
	}

	public GGraphExt<City<T>> getNat() {
		return nat;
	}

	public void setNat(GGraphExt<City<T>> nat) {
		this.nat = nat;
	}
}
