package gnomenwald;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import genericoop.LList;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.NotOnlyChildException;
import throwing.OutOfBoundsException;

public class GnomeStorageTest {

	
	@Test
	public void test() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, OutOfBoundsException, NotFoundException, NotOnlyChildException {
		
		GnomeStorage<Gnome> t = new GnomeStorage<Gnome>(Gnome.class);
		
		LList<Gnome> gnomes = new LList<Gnome>();
		for(int i = 0; i < 50; i++) {
			Gnome temp = new Gnome("name" + i, i*2, 0, "green", 20, 1000);
			gnomes.append(temp);
			t.insert(temp);
		}
		
		for(int i = 0; i < 50; i++) {
			assertEquals(gnomes.get(i), t.findByName("name" + i));
			assertEquals(gnomes.get(i), t.findByAge(i*2));
		}
		for (int i = 0; i<49; i++){
			t.delete(gnomes.get(49-i));
		}
		System.out.println(t);
	}

}
