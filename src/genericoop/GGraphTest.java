package genericoop;

import static org.junit.Assert.*;

import org.junit.Test;

import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.OutOfBoundsException;

public class GGraphTest {

	/*
	 * public void addGNode(T thign)
	 * public void addEdge(T from, T to, int c) 
	 * public void delEdge(T from, T to)
	 */
	
	@Test
	public void contains() throws OutOfBoundsException, NotFoundException{
		GGraph<Gnome> testing = new GGraph<Gnome>();
		
		Gnome A = new Gnome("A", 2, 0, "green", 1, 1000);
		Gnome B = new Gnome("B", 2, 0, "green", 2, 1000);
		Gnome C = new Gnome("C", 2, 0, "green", 3, 1000);
		
		testing.addGNode(A);;
		testing.addGNode(B);
		testing.addGNode(C);
		
//		testing.addEdge(A, B, 5);
//		testing.addEdge(A, C, 4);
//		testing.addEdge(B, C, 3);
//		
//		assertEquals(true, testing.containsGNode(A));
//		assertEquals(false, testing.containsGNode(new Gnome("lol", 1, 0)));
//		assertEquals(true, testing.containsGNode(B));
//		assertEquals(false, testing.containsGNode(new Gnome("B", 2, 0)));
//		assertEquals(true, testing.containsGNode(C));
//		assertEquals(false, testing.containsGNode(new Gnome("C", 2, 0)));
//
//		System.out.println(testing);
//		
//		testing.delEdge(A, B);
//		testing.delEdge(A, C);
//		testing.delEdge(B, C);
//		
//		System.out.println(testing);
		
		testing.addEdge(A,  B, 4);
		testing.addEdge(B, C, 3);
		
		assertEquals(false, testing.connected(A, C));
		
		System.out.println(testing);
		
		testing.delGNodeThru(B);
		
		System.out.println("third");
		
		System.out.println(testing);
		assertEquals(true, testing.connected(A, C));
		

	
	}

}
