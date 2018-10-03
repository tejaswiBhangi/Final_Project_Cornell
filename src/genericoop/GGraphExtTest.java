package genericoop;

import org.junit.Test;

import oopstructures.Gnome;
import throwing.AlreadyConnectedException;
import throwing.NotFoundException;
import throwing.OutOfBoundsException;

public class GGraphExtTest {

	@Test
		//checks the whosPopular() function
		//it should return the Gnome who any given Gnome should want to make friends with
	public void testPopular() throws NotFoundException, OutOfBoundsException, AlreadyConnectedException {
//		GGraphExt<Gnome> test = new GGraphExt<Gnome>();
		//creates the test graph
		
//		Gnome A = new Gnome("A"), B = new Gnome("B"), C = new Gnome("C"), D = new Gnome("D"), E = new Gnome("E");
//		Gnome F = new Gnome("F"), G = new Gnome("G");
//		//makes a load of people for the graph
//		
//		test.addGNode(A);
//		test.addGNode(B);
//		test.addGNode(C);
//		test.addGNode(D);
//		test.addGNode(E);
//		test.addGNode(F);
//		test.addGNode(G);
//		//populates the graph with nodes
//		
//		test.addEdge(A, B, 3);
//		test.addEdge(A, C, 4);
//		test.addEdge(A, D, 5);
//		
//		test.addEdge(B, E, 9);
//		test.addEdge(C, F, 7);
//		test.addEdge(D, E, 5);
//		
//		test.addEdge(F, G, 4);
//		//adds various connections
//		
//		assertEquals(F, test.whosPopular(A));
//		assertEquals(null, test.whosPopular(B));
//		assertEquals(G, test.whosPopular(C));
//		assertEquals(null, test.whosPopular(G));
	}
	
	@Test
		//tests the alreadyFriends() checker
	public void testAlreadyFriends() throws NotFoundException{
//		CGraph<Gnome> test = new CGraph<Gnome>();
//		//creates the test graph
//		
//		Gnome A = new Gnome("A"), B = new Gnome("B"), C = new Gnome("C"), D = new Gnome("D"), E = new Gnome("E");
//		Gnome F = new Gnome("F"), G = new Gnome("G");
//		//makes a load of people for the graph
//		
//		test.addGNode(A);
//		test.addGNode(B);
//		test.addGNode(C);
//		test.addGNode(D);
//		test.addGNode(E);
//		test.addGNode(F);
//		test.addGNode(G);
//		//populates the graph with nodes
//		
//		test.addEdge(A, B, 3);
//		test.addEdge(A, C, 4);
//		test.addEdge(A, D, 5);
//		
//		test.addEdgZe(B, E, 9);
//		test.addEdge(C, F, 7);
//		test.addEdge(D, E, 5);
//		
//		test.addEdge(F, G, 4);
//		//adds various connections
//		
//		assertEquals(true, test.alreadyFriends(A, B));
//		assertEquals(false, test.alreadyFriends(A, F));
//		assertEquals(false, test.alreadyFriends(E, B));
//		assertEquals(true, test.alreadyFriends(F, G));
//		
	}
	
	@Test
		//tests making friends between people
		//also checks to make sure the karma costs are paid
	public void testMakeFriends() throws NotFoundException, OutOfBoundsException, AlreadyConnectedException{
//		CGraph<Gnome> test = new CGraph<Gnome>();
//		//creates the test graph
//		
//		Gnome A = new Gnome("A"), B = new Gnome("B"), C = new Gnome("C"), D = new Gnome("D"), E = new Gnome("E");
//		Gnome F = new Gnome("F"), G = new Gnome("G");
//		//makes a load of people for the graph
//		
//		test.addGNode(A);
//		test.addGNode(B);
//		test.addGNode(C);
//		test.addGNode(D);
//		test.addGNode(E);
//		test.addGNode(F);
//		test.addGNode(G);
//		//populates the graph with nodes
//		
//		test.addEdge(A, B, 3);
//		test.addEdge(A, C, 4);
//		test.addEdge(A, D, 5);
//		
//		test.addEdge(B, E, 9);
//		test.addEdge(C, F, 7);
//		test.addEdge(D, E, 5);
//		
//		test.addEdge(F, G, 4);
//		//adds various connections
//		
//		assertEquals(false, test.makeFriend(E, A));
//		assertEquals(3, test.edgeValue(A, B));
//		assertEquals(5, test.edgeValue(A, D));
//		assertEquals(5, test.edgeValue(D, E));
//		assertEquals(true, test.makeFriend(A, E));
//		assertEquals(4, test.edgeValue(A, D));
//		assertEquals(4, test.edgeValue(D, E));
//	
//		
	}
	
	@Test
	public void testDij() throws NotFoundException, OutOfBoundsException {
//		GGraphExt<Gnome> t = new GGraphExt<Gnome>();
//		
//		Gnome A = new Gnome("A", 0, 0);
//		Gnome B = new Gnome("B", 0, 0);
//		Gnome C = new Gnome("C", 0 ,0);
//		Gnome D = new Gnome("D", 0, 0);
//		
//		t.addGNode(A);
//		t.addGNode(B);
//		t.addGNode(C);
//		t.addGNode(D);
//		
//		t.addEdge(A, C, 1);
//		t.addEdge(A, B, 3);
//		t.addEdge(B, D, 2);
//		t.addEdge(C, D, 8);
//		
//		System.out.println(t.shortestPath(A, D));
//		System.out.println("B, D");
		
		/*
		 * new thingy below
		 */
		
//		GGraphExt<Gnome> t = new GGraphExt<Gnome>();
//		
//		Gnome A = new Gnome("A", 0, 0);
//		Gnome B = new Gnome("B", 0, 0);
//		Gnome C = new Gnome("C", 0 ,0);
//		Gnome D = new Gnome("D", 0, 0);
//		Gnome E = new Gnome("E", 0, 0);
//		Gnome F = new Gnome("F", 0, 0);
//		Gnome G = new Gnome("G", 0, 0);
//		
//		t.addGNode(A);
//		t.addGNode(B);
//		t.addGNode(C);
//		t.addGNode(D);
//		t.addGNode(E);
//		t.addGNode(F);
//		t.addGNode(G);
//		
//		t.addEdge(A, B, 2);
//		t.addEdge(A, C, 3);
//		t.addEdge(B, E, 5);
//		t.addEdge(C, D, 1);
//		t.addEdge(D, E, 2);
//		t.addEdge(E, F, 2);
//		t.addEdge(E, G, 4);
//		t.addEdge(F, G, 1);
//		
//		System.out.println(t.shortestPath(A, G));
//		System.out.println("C, D, E, F, G");
		
		
		/*
		 * new thingy bewlo
		 */
		
		GGraphExt<Gnome> t = new GGraphExt<Gnome>();
		
		Gnome A = new Gnome("A", 0, 0, "green", 1, 1000);
		Gnome B = new Gnome("B", 0, 0, "green", 2, 1000);
		Gnome C = new Gnome("C", 0 ,0, "green", 3, 1000);
		Gnome D = new Gnome("D", 0, 0, "blue", 4, 1000);
		Gnome E = new Gnome("E", 0, 0, "blue", 5, 1000);
		Gnome F = new Gnome("F", 0, 0, "blue", 6, 1000);
		Gnome G = new Gnome("G", 0, 0, "blue", 7, 1000);
		
		t.addGNode(A);
		t.addGNode(B);
		t.addGNode(C);
		t.addGNode(D);
		t.addGNode(E);
		t.addGNode(F);
		t.addGNode(G);
		
		t.addEdge(A, B, 1);
		t.addEdge(A, C, 2);
		t.addEdge(B, E, 5);
		t.addEdge(C, B, 2);
		t.addEdge(C, E, 4);
		t.addEdge(A, D, 1);
		t.addEdge(D, C, 1);
		t.addEdge(D, F, 1);
		t.addEdge(F, G, 1);
		t.addEdge(G, E, 1);

//		System.out.println(t.shortestPath(A, E));
//		System.out.println("D, F, G, E");
	}
	
	@Test
	public void mstTest() throws NotFoundException, OutOfBoundsException {
		
//		GGraphExt<Gnome> t = new GGraphExt<Gnome>();
//		
//		Gnome A = new Gnome("A", 0, 0);
//		Gnome B = new Gnome("B", 0, 0);
//		Gnome C = new Gnome("C", 0 ,0);
//		Gnome D = new Gnome("D", 0, 0);
//		Gnome E = new Gnome("E", 0, 0);
//		Gnome F = new Gnome("F", 0, 0);
//		Gnome G = new Gnome("G", 0, 0);
//		
//		t.addGNode(A);
//		t.addGNode(B);
//		t.addGNode(C);
//		t.addGNode(D);
//		t.addGNode(E);
//		t.addGNode(F);
//		t.addGNode(G);
//		
//		t.addEdge(A, B, 1);
//		t.addEdge(A, C, 2);
//		t.addEdge(B, E, 5);
//		t.addEdge(C, B, 2);
//		t.addEdge(C, E, 4);
//		t.addEdge(A, D, 1);
//		t.addEdge(D, C, 1);
//		t.addEdge(D, F, 1);
//		t.addEdge(F, G, 1);
//		t.addEdge(G, E, 1);
//
//		System.out.println(t.MST());
		//System.out.println("D, F, G, E");
		
//		GGraphExt<Gnome> t = new GGraphExt<Gnome>();
//		
//		Gnome A = new Gnome("A", 0, 0);
//		Gnome B = new Gnome("B", 0, 0);
//		Gnome C = new Gnome("C", 0 ,0);
//		Gnome D = new Gnome("D", 0, 0);
//		Gnome E = new Gnome("E", 0, 0);
//		Gnome F = new Gnome("F", 0, 0);
//		Gnome G = new Gnome("G", 0, 0);
//		
//		t.addGNode(A);
//		t.addGNode(B);
//		t.addGNode(C);
//		t.addGNode(D);
//		t.addGNode(E);
//		t.addGNode(F);
//		t.addGNode(G);
//		
//		t.addEdge(A, B, 2);
//		t.addEdge(A, C, 3);
//		t.addEdge(B, E, 5);
//		t.addEdge(C, D, 1);
//		t.addEdge(D, E, 2);
//		t.addEdge(E, F, 2);
//		t.addEdge(E, G, 4);
//		t.addEdge(F, G, 1);
//		
//		System.out.println(t.MST());
		
		GGraphExt<Integer> t = new GGraphExt<Integer>();
		
		Integer A = 1, B=2, C=3, D=4;
		
		t.addGNode(A);
		t.addGNode(B);
		t.addGNode(C);
		t.addGNode(D);
		
		t.addEdge(A, B, 2);
		t.addEdge(A, C, 1);
		t.addEdge(B, D, 3);
		t.addEdge(C, D, 7);
		t.addEdge(C, B, 70);
		System.out.println(t);
		t.makeMST();
		System.out.println(t);
		
	}
}
