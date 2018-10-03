package genericoop;

import throwing.AlreadyConnectedException;
import throwing.NotFoundException;
import throwing.OutOfBoundsException;

public class GGraphExt<T> extends GGraph<T> {
	/*
	 * this class extends GGraph (general graph), but includes people friendship
	 * making and such.
	 */

	public boolean makeFriend(T self, T with)
			throws OutOfBoundsException, AlreadyConnectedException, NotFoundException {
		if (connected(self, with)) {
			throw new AlreadyConnectedException(self, with);
		}
		LList<Edge> myFriends = super.getList(self);

		// creates a list of my friends.
		LList<LList<Edge>> possibilities = new LList<LList<Edge>>();
		// creates a list to hold lists of my friends' friends, which lists
		// include the person I want to meet.
		for (int i = 1; i < myFriends.length(); i++) {
			// for every single friend I have,
			LList<Edge> friendsFriends = super.getList(myFriends.get(i).getData());
			// create a list of their friends.
			boolean add = true;
			// indicator of whether my friend is friends with the person I want
			// to meet
			try {
				super.getEdge(with, friendsFriends);
				// try to find them on their friends list!
			} catch (NotFoundException e) {
				add = false;
				// if not found, don't add them to the list of friends' friends!
			}
			if (add)
				possibilities.append(friendsFriends);
		}
		// now possibilities holds lists of friends' friends, whose lists
		// include "with".

		if (possibilities.length() == 0) {
			return false;
		}

		int[] willingness = new int[possibilities.length()];

		for (int i = 0; i < possibilities.length(); i++) {
			// for all of my friends with connections to person I want to
			// connect with,
			LList<Edge> tempFriend = possibilities.get(i);
			// temp list of my friend's (singular) friends
			int withMe = super.getEdge(tempFriend.get(0).getData(), myFriends).getCost();
			// gets the willingess of my friend to help me.
			int withTarget = super.getEdge(with, tempFriend).getCost();
			// gets the willingness of my friend with my target.
			willingness[i] = Math.min(withMe, withTarget);
		}
		// now willingness holds the minimum willingness of any connection to
		// the target! simply find the maximum here (the most WILLING person),
		// and become friends with them!

		int maxWillIndex = 0;
		int maxWill = Integer.MIN_VALUE;
		for (int i = 0; i < willingness.length; i++) {
			if (maxWill < willingness[i]) {
				maxWillIndex = i;
				maxWill = willingness[i];
			}
		}
		// gets the strongest connection. now become friends w/target!

		T yourFriend = possibilities.get(maxWillIndex).get(0).getData();
		Edge meFriend = super.getEdge(yourFriend, myFriends);
		T friendsFriend = super.getEdge(with, possibilities.get(maxWillIndex)).getData();
		Edge friendF = super.getEdge(friendsFriend, possibilities.get(maxWillIndex));
		// gets the proper edges, and subtracts one from their costs
		// respectively.

		meFriend.minusMinus();
		friendF.minusMinus();

		if (meFriend.getCost() < 1) {
			super.delEdge(self, meFriend.getData());
		}
		if (friendF.getCost() < 1) {
			super.delEdge(friendF.getData(), with);
		}
		// and, if the cost between the Ts is zero, delete the edge.
		return true;
	}
	// tries to add an edge between self and with, through a friend of a friend

	public T whosPopular(T self) throws NotFoundException, OutOfBoundsException {
		LList<Edge> myFriends = super.getList(self);
		// creates a list of my friends.

		int[][] connections = new int[myFriends.length()][2];
		// an int[][] to keep track of my friends' most popular friends, and how
		// many friends they have.

		for (int i = 1; i < myFriends.length(); i++) {
			LList<Edge> conns = super.getList(myFriends.get(i).getData());
			int[] newConns = new int[conns.length()];
			// creates a list of my friends' friends, and an int[] to keep track
			// of their connections
			for (int j = 1; j < conns.length(); j++) {
				// needs to be with people you aren't already friends with
				// though, so:
				// lets check if your friends of friends of friends are friends
				// with you.

				newConns[j] = getNewConnections(self, super.getList(conns.get(j).getData()));
				// records the number of new connections with the specific
				// friend
			}
			int maxNewIndex = 1;
			int maxNew = Integer.MIN_VALUE;
			for (int x = 1; x < newConns.length; x++) {
				if (maxNew < newConns[x]) {
					maxNewIndex = x;
					maxNew = newConns[x];
				}
			}
			connections[i][0] = maxNewIndex;
			connections[i][1] = maxNew;
			// records the most popular person to connections, and how popular
			// they are by, for a certain friend
		}
		// now connections[][] holds a list of my friends' most popular friends,
		// and how popular they are

		int maxNewFinal = -1;
		int maxFinal = Integer.MIN_VALUE;
		for (int i = 1; i < connections.length; i++) {
			if (maxFinal < connections[i][1]) {
				maxNewFinal = i;
				maxFinal = connections[i][1];
			}
		}
		// gets the friend of my friends with the most new connections

		if (maxNewFinal < 0) {
			return null;
		}
		// obviously if they have no friends then you can't get their most
		// popular man

		LList<Edge> theOne = super.getList(myFriends.get(maxNewFinal).getData());
		T only = theOne.get(connections[maxNewFinal][0]).getData();
		// finds the most popular of the most popular!!

		return only;
	}
	// this took me three hours to write
	// returns the most optimal person to become friends with, based off the
	// amount of potential new connections you could make off of them, given a
	// person

	public int getNewConnections(T self, LList<Edge> connections) throws OutOfBoundsException, NotFoundException {
		LList<Edge> myFriends = super.getList(self);
		int totalNew = 0;
		for (int i = 1; i < connections.length(); i++) {
			boolean known = true;
			try {
				super.getEdge(connections.get(i).getData(), myFriends);
			} catch (NotFoundException e) {
				known = false;
			}
			if (!known) {
				totalNew++;
			}
		}
		return totalNew;
	}
	// given T and a list of edges with Ts, returns the number of times T is not
	// connected with the Ts in the list. doesn't count the first person,
	// because they are the self.

	public LList<T> shortestPath(T from, T to) throws OutOfBoundsException, NotFoundException {
		if (!TList.contains(from)) {
			throw new NotFoundException(from);
		}
		if (!TList.contains(to)) {
			throw new NotFoundException(to);
		}

		LList<DijNode> wip = new LList<DijNode>();
		DijNode first = new DijNode(from);
		
		first.setPrevious(first);
		first.setTotalCost(0);
		wip.append(first);
		for (int i = 0; i < wip.length(); i++) {
			DijNode min = new DijNode(null);

			for (int j = 0; j < wip.length(); j++) {
				DijNode tempMin = wip.get(j);
				if (!tempMin.isDone() && tempMin.getTotalCost() < min.getTotalCost()) {
					min = tempMin;
				}
			}
			if (min.getSelf() == to) {
				LList<T> temp = new LList<T>();

				for (DijNode v = min; v != v.getPrevious(); v = v.getPrevious()) {
					temp.prepend(v.getSelf());
				}
				return temp;
			}
			LList<Edge> currentList = super.getList(min.getSelf());
			for (int k = 1; k < currentList.length(); k++) {
				Edge temp = currentList.get(k);
				try {
					DijNode toModify = find(temp.getData(), wip);
					if (min.getTotalCost() + temp.getCost() < toModify.getTotalCost()) {
						toModify.setPrevious(min);
						toModify.setTotalCost(min.getTotalCost() + temp.getCost());
					}

				} catch (Exception e) {
					DijNode toAdd = new DijNode(temp.getData());
					toAdd.setPrevious(min);
					toAdd.setTotalCost(min.getTotalCost() + temp.getCost());
					wip.append(toAdd);
				}

			}
			min.setDone();

		}
		throw new NotFoundException(to);

	} //Dijkstra's algorithm implementation
	//finds the shortest path between two nodes in a graph
	//Uses custom DijNode objects

	public DijNode find(T toFind, LList<DijNode> list) throws NotFoundException, OutOfBoundsException {
		for (int i = 0; i < list.length(); i++) {
			DijNode temp = list.get(i);
			if (temp.getSelf() == toFind) {
				return temp;
			}
		}

		throw new NotFoundException(toFind);
	} //simple function to find and return the DijNode containing toFind from a Linked List of DijNodes

	public class DijNode {
		T self;
		DijNode previous;
		int totalCost;
		boolean done;

		public DijNode(T self) {
			totalCost = Integer.MAX_VALUE;
			done = false;
			this.self = self;
			previous = null;
		}

		public T getSelf() {
			return self;
		}

		public DijNode getPrevious() {
			return previous;
		}

		public void setPrevious(DijNode previous) {
			this.previous = previous;
		}

		public int getTotalCost() {
			return totalCost;
		}

		public void setTotalCost(int totalCost) {
			this.totalCost = totalCost;
		}

		public boolean isDone() {
			return done;
		}

		public void setDone() {
			this.done = true;
		}
	} //a specialized storage class for Dijkstra's algorithm
	//contains the current tentative cost, tentative previous node, self, and a "done" boolean
	//done is set to true when the program is done checking

	public LList<Pair> MST() {
		try {
			LList<Pair> output = new LList<Pair>();
			LList<T> done = new LList<T>();
			LList<LList<Edge>> wip = new LList<LList<Edge>>();
	
			LList<Edge> first = adjList.get(0);
			done.append(first.get(0).getData());
			wip.append(first);
	
			while (true) {
				T from = null;
				Edge min = new Edge(Integer.MAX_VALUE, null);
				for (int i = 0; i < wip.length(); i++) {
					LList<Edge> temp = wip.get(i);
					for (int j = 0; j < temp.length(); j++) {
						Edge tempEdge = temp.get(j);
						if (tempEdge.getCost() < min.getCost() && !done.contains(tempEdge.getData())) {
							from = temp.get(0).getData();
							min = tempEdge;
						}
					}
	
				}
				if (from != null) {
					output.append(new Pair(from, min.getData(), min.getCost()));
					done.append(min.getData());
					wip.append(super.getList(min.getData()));
					continue;
				}
				return output;
			}
		} catch (Exception e) {
			System.out.println("this shouldn't happen ever");
		}
		return null;
		
	} //finds a minimum spanning tree of a graph
	//returns a linked list of pairs of nodes
	//the pairs are the pairs that must be connected in the MST
	// assumes all edges are bidirectional
	//the output pairs must also be bidirectional
	//uses prim's algorithm
	
	public void makeMST() throws OutOfBoundsException, NotFoundException {
		LList<Pair> pairs = MST();
		//finish later
		
		for(int i = 0; i < adjList.length(); i++) {
			try {
				delEdges(adjList.get(i).get(0).getData());
			} catch (NotFoundException | OutOfBoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int j = 0; j<pairs.length(); j++){
			Pair temp = pairs.get(j);
			addEdge(temp.getA(), temp.getB(), temp.getWeight());
			addEdge(temp.getB(), temp.getA(), temp.getWeight());
		}
		
		
	}
	
	private class Pair {
		T a, b;
		int weight;

		public Pair(T a, T b, int weight) {
			this.a = a;
			this.b = b;
			this.weight = weight;
		}

		public int getWeight() {
			return weight;
		}

		@SuppressWarnings("unused")
		public void setWeight(int weight) {
			this.weight = weight;
		}

		public T getA() {
			return a;
		}

		@SuppressWarnings("unused")
		public void setA(T a) {
			this.a = a;
		}

		public T getB() {
			return b;
		}

		@SuppressWarnings("unused")
		public void setB(T b) {
			this.b = b;
		}

		public String toString() {
			return "(" + a + ", " + b + ", "+weight+")";
		}
	} //a simplistic class that just holds two objects
	//useful for the minimum spanning tree
	//the MST function returns a linked list of pairs of nodes that must be connected
}
