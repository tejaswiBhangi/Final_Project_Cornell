package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;

import gnomenwald.City;
import gnomenwald.Nation;
import multithreading_test.GnomeIterator;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.OutOfBoundsException;

public class MultiThreadActionListener implements ActionListener {
	private Nation<Gnome> gnomenwald;
	private Gnome[] allGnomes;
	public MultiThreadActionListener(Nation<Gnome> gnomenwald) {
		this.gnomenwald = gnomenwald;
	}
	// listens to the multithread
       //performs an action
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			for(Gnome g: gnomenwald.inCity()){
				try {
					GnomeIterator gnomeIterator = new GnomeIterator(g, gnomenwald);
					Thread gnomeItThread = new Thread(gnomeIterator);
					gnomeItThread.start();
					
				} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException
						| OutOfBoundsException | NotFoundException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			//create thread to update button labels
			Thread nt = new Thread(new ButtonUpdater());
			nt.setPriority(Thread.MIN_PRIORITY);
			nt.start();
		} catch (OutOfBoundsException e1) {

			e1.printStackTrace();
		}
	}
	//performs an action
	//updates the city buttons
	private class ButtonUpdater implements Runnable {
		
		public ButtonUpdater() {
			super();
			
		}

		 
		@Override
		public void run() {
			Gnome[] gnomes;
			String names;
			JButton button;
			while (true) {
				try {
					
					for (Integer cityId: GnomeFrame.buttonMap.keySet()) {
						City city = null;
						city = gnomenwald.getCity(cityId);
						gnomes = gnomenwald.inCity(city.getId());
						names = " (Gnomes ";
						for (int i = 0; i< gnomes.length; i++) {
							names = names + gnomes[i].getId()+" ";
						}
						names = names+")";
						button = GnomeFrame.buttonMap.get(cityId);
						String newText = city.getId() + names;
						if(!(button.getText()== newText)){
							button.setForeground(Color.BLUE);
							button.setText(newText);
						}
							Thread.sleep(300);
							button.setForeground(Color.BLACK);
						
						
					}
					Thread.sleep(500);
				} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException
						| OutOfBoundsException | NotFoundException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
}
