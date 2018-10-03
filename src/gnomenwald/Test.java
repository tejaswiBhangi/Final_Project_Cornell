package gnomenwald;

import java.lang.reflect.InvocationTargetException;

import genericoop.BinarySearchTree;
import throwing.NotFoundException;
import throwing.NotOnlyChildException;

public class Test {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NotFoundException, NotOnlyChildException {
		
		
//		GnomeStorage<Gnome> box = new GnomeStorage<Gnome>(Gnome.class);
//		Gnome bob = new Gnome("bob", 1);
//		Gnome joe = new Gnome("joe", 1);
//		Gnome tom = new Gnome("tom", 1);
//		Gnome jack = new Gnome("jack", 500);
//		Gnome alex = new Gnome("alex", 69);
//		
//		box.insert(bob);
//		box.insert(joe);
//		box.insert(jack);
//		box.insert(alex);
//		box.insert(tom);
//		System.out.println(box.findByName("jack"));
//		System.out.println(box.findByName("alex"));
//		System.out.println(box.findById(1));
//		System.out.println(box.findByAge(69));
//		System.out.println(box.findByAge(500));
//		System.out.println(box);
//		
//		box.delete(bob);
//		box.delete(joe);
//		box.delete(jack);
//		box.delete(tom);
//		System.out.println(box.findByName("alex"));
//		System.out.println(box.findById(5));
//
//		System.out.println(box);

		BinarySearchTree<Integer> thing = new BinarySearchTree<Integer>(Integer.class.getDeclaredMethod("toString"));
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 4;
		Integer e = 5;
		Integer f = 6;
		Integer g = 7;
		Integer h = 8;
		Integer i = 9;
		Integer j = 90;
		Integer k = 91;
		Integer l = 92;
		thing.insert(c);
		thing.insert(b);
		thing.insert(e);
		thing.insert(a);
		thing.insert(d);
		thing.insert(g);
		thing.insert(f);
		thing.insert(h);
		thing.insert(g);
		thing.insert(i);
		thing.insert(j);
		thing.insert(k);
		thing.insert(l);
		
//		thing.delete(g);
//		thing.delete(f);
//		thing.delete(c);
//		thing.delete(a);
//		thing.delete(b);
//		thing.delete(d);
//		thing.delete(k);
//		thing.delete(l);
//		thing.delete(h);
//		thing.delete(e);
//		thing.delete(i);
//		thing.delete(g);
		
		
//		thing.delete(d);
		
//		thing.rotateLeft(b, c);

		System.out.println(thing);
//		
//		GGraph<BinarySearchTree<Gnome>> gnomenwald = new GGraph<BinarySearchTree<Gnome>>();
		
	}

}
