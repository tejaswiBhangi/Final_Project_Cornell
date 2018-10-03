package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gnomenwald.Nation;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.OutOfBoundsException;

public class InfrastructureActionListener implements ActionListener {
	private Nation<Gnome> gnomenwald;
	public InfrastructureActionListener(Nation<Gnome> gnomenwald) {
		this.gnomenwald = gnomenwald;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			gnomenwald.getNat().makeMST();
		} catch (OutOfBoundsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	// performs an actionS
	}
}
