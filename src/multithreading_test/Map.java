package multithreading_test;

import gnomenwald.Nation;
import oopstructures.Gnome;

public class Map extends Thread {
	int delay;
	Nation<Gnome> nation;

	public Map(Nation<Gnome> nation, int delay){
		this.nation = nation;
		this.delay = delay;
	}
	//constructor
	
	public void run(){
		for ( ; ; ){		
			System.out.println(nation);
			try {
				sleep(delay);
			}
			catch (InterruptedException e){
				System.out.println("I’m done!!");
				System.exit(0);
			}
		}
	}
}