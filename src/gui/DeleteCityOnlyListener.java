package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gnomenwald.Nation;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.OutOfBoundsException;

public class DeleteCityOnlyListener implements ActionListener  {
	Nation<Gnome>  gnomenwald;
	int location;
	public DeleteCityOnlyListener(Nation<Gnome> gnomenwald, int cityId) {
		this.gnomenwald = gnomenwald;
		this.location = cityId;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int answer = JOptionPane.showConfirmDialog((Component)e.getSource(), "Confirm Deleting City and combining roads");
		if (answer != JOptionPane.YES_OPTION)  return;
		try {
			gnomenwald.deleteCity(location, "t");
			disableCityButton();
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NotFoundException e1) {
		
			e1.printStackTrace();
		} catch (OutOfBoundsException e1) {
			
			e1.printStackTrace();
		}
	
	}
	private void disableCityButton() {
		JButton button = GnomeFrame.buttonMap.get(location);
		button.setText(button.getText()+ " deleted");
		button.setEnabled(false);
	}
}
