package multithreading_test;

import java.lang.reflect.InvocationTargetException;

import gnomenwald.Nation;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.NotOnlyChildException;
import throwing.OutOfBoundsException;

public class GnomeIterator extends Thread {
	Nation<Gnome> nationality;
	int delay;
	Gnome dude;
	public static boolean stopThread = false;
	public GnomeIterator(Gnome dude, Nation<Gnome> nationality) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, OutOfBoundsException, NotFoundException, InterruptedException{
		this.nationality = nationality;
		this.delay = dude.getDelay();
		this.dude = dude;
		

//		this.nationality.addCitizen(dude, dude.getLocation());
//		System.out.println("im entering");
	}
	//constructor
//	public GnomeIterator(Gnome gnome, Nation<Gnome> nationality, int delay){
//		this.nationality = nationality;
//		this.delay = delay;
//		this.dude = gnome;
//	}
	
	public void run(){
//		try {
//			this.nationality.addCitizen(dude, dude.getLocation());
//			System.out.println(this.dude.getName()+"im entering");
//		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | OutOfBoundsException
//				| NotFoundException | InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		while(!stopThread){		
			try {
				sleep(delay);
				
			}
			catch (InterruptedException e){
				System.out.println("I’m done!!");
				System.exit(0);
			}
			if (!dude.hasDest()) {
				int[] destination = {((int) Math.floor(Math.random()*nationality.getNumCities())) + 1};//random number either 1 or 2
//				int [] destination = new int[1];
//				if (dude.getLocation() == 1) {
//					destination[0] = 2;
//				} else {
//					destination[0] = 1;
//				}
				
//				int[] destination = {2};
				for (int id : destination){
					try {
						dude.setDest(nationality.getNat().shortestPath(nationality.getCity(dude.getLocation()), nationality.getCity(id)));
					} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException
							| OutOfBoundsException | NotFoundException e) {
						// TODO Auto-generated catch block
//						e.printStackTrace();
					}
				}
				//dude.setDest(destination);
				//System.out.println(destination[0]);
			} else {
				try {
	
					System.out.println(this.dude.getName()+ " I'm leaving to "+dude.destinations.get(0));
					nationality.travel(dude);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NotFoundException e) {
					e.printStackTrace();
				} catch (NotOnlyChildException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					System.out.print("I'm done!!!!");
					System.exit(0);
				} catch (OutOfBoundsException e) {
					e.printStackTrace();
				}
			}

		}
		
	}
}