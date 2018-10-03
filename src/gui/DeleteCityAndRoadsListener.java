package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gnomenwald.City;
import gnomenwald.Nation;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.OutOfBoundsException;

public class DeleteCityAndRoadsListener implements ActionListener {
	Nation<Gnome>  gnomenwald;
	int location;
	public DeleteCityAndRoadsListener(Nation<Gnome> gnomenwald, int cityId) {
		this.gnomenwald = gnomenwald;
		this.location = cityId;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int answer = JOptionPane.showConfirmDialog((Component)e.getSource(), "Confirm Deleeting City and roads");
		if (answer != JOptionPane.YES_OPTION)  return;
		try {
			gnomenwald.deleteCity(location, "a");
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
