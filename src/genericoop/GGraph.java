package genericoop;

import throwing.NotFoundException;
import throwing.OutOfBoundsException;

public class GGraph<T> {
	/*
	 * class that makes directional PGraphs, which hold objects and connect them
	 * with weighted "edges," stored in int values. assuming the objects in the
	 * graph are stored outside of the graph, somewhere.
	 */
	public LList<LList<Edge>> adjList = new LList<LList<Edge>>();
	// stores adjacency lists with the object in an edge with cost zero at the
	// beginning, followed by a list of edges which it connects to.
	public LList<T> TList = new LList<T>();
	// stores a list of the graph nodes.

	public void addGNode(T add) {
		LList<Edge> newbie = new LList<Edge>();
		newbie.append(new Edge(0, add));
		TList.append(add);
		adjList.append(newbie);
	}
	// adds a T "add" to the adjList and TList.

	public void delGNodeAll(T del) {
		/*
		 * steps to remove something from the graph
		 * 
		 * 1. get rid of that thing
		 * 
		 * 2. get rid of edges from that thing
		 * 
		 * 3. get rid of edges to that thing
		 */

		try {
			TList.remove(del);
		} catch (NotFoundException e1) {
			//e1.printStackTrace();
		}
		// 1

		try {
			adjList.remove(adjList.indexOf(getList(del)));
		} catch (OutOfBoundsException e1) {
			//e1.printStackTrace();
		} catch (NotFoundException e1) {

			//e1.printStackTrace();
		}
		// 2

		for (int i = 0; i < TList.length(); i++) {
			try {
				delEdge(TList.get(i), del);
			} catch (NotFoundException e) {
			} catch (OutOfBoundsException e) {
			}
		}
		// 3
	}
	// deletes existence of a T in the graph
	
	public void delGNodeThru(T del) throws NotFoundException, OutOfBoundsException {
		LList<Edge> dead = getList(del);
		
		TList.remove(del);
		
		for(int i = 0; i < TList.length(); i++) {
			T current = TList.get(i);
			if (connected(current, del)) {
				//if a node is connceted to del
				for(int j = 1; j < dead.length(); j++) {
					//for all the nodes del is connected to
					if (!connected(current, dead.get(j).getData())) {
						//if the current node isn't connected to one of them
						addEdge(current, dead.get(j).getData(), edgeValue(current, del) + edgeValue(del, dead.get(j).getData()));
							//connect to it
					}
				}
			}
		}
		adjList.remove(dead);
		delGNodeAll(del);
	}
	// deletes the T in the graph, but keeps edges through the T.
	
	public void addEdge(T from, T to, int c) throws NotFoundException {
		if (connected(from, to) ) {
			return;
		}
		//if the edge alread yexists
		
		LList<Edge> temp = getList(from);
		temp.append(new Edge(c, to));
	}
	// attempts to add an edge from T "from" to the graph to T "to", with cost
	// "c". will throw an exception if "from" doen't exist.

	public void delEdge(T from, T to) throws NotFoundException {
		LList<Edge> temp = getList(from);
		Edge target = getEdge(to, temp);

		temp.remove(target);
	}
	// attempts to delete an edge from T "from", to T "to".

	public void delEdges(T from) throws NotFoundException, OutOfBoundsException {
		LList<Edge> soon = getList(from);
		
		while (soon.length()>1){
			delEdge(from, soon.get(1).getData());
		}
	}
	//deletes all the edges of a specified graphnode
	
	public boolean connected(T self, T with) throws NotFoundException {
		try {
			edgeValue(self, with);
		} catch (NotFoundException e) {
			return false;
		}
		return true;
	}
	// presumes the given "self" is already in the graph.
	
	public boolean containsGNode(T poss) {
		return TList.contains(poss);
	}
	// checks if the adjList contains a certain node, returns false if it does
	// not.

	public int edgeValue(T from, T to) throws NotFoundException {
		LList<Edge> first = getList(from);
		Edge second = getEdge(to, first);
		return second.getCost();
	}
	// returns cost of an edge.

	public LList<Edge> getList(T from) throws NotFoundException {
		LList<Edge> temp;

		for (int i = 0; i < adjList.length(); i++) {
			try {
				temp = adjList.get(i);
				if (temp.get(0).getData() == from) {
					return temp;
				}
			} catch (OutOfBoundsException e) {
				e.printStackTrace();
				// should never happen. there's probably a more
				// efficient/superior way to do this.
			}
		}
		throw new NotFoundException(from);
	}
	// given T from, will return the LList<Edge> in adjList that holds the
	// adjacency links for the object.

	public Edge getEdge(T to, LList<Edge> in) throws NotFoundException {
		Edge found;
		for (int i = 1; i < in.length(); i++) {
			try {
				found = in.get(i);
				if (found.getData() == to) {
					return found;
				}
			} catch (OutOfBoundsException e) {
				e.printStackTrace();
				// same thing, I feel there's a superior way to do this using
				// pointers.
			}
		}
		throw new NotFoundException(to);
	}
	// given a list of edges and a T "to", will return the edge that contains
	// "to". excludes the first index of the list from the search. 
	
	public Edge getEdge(T from, T to) throws NotFoundException {
		Edge found;
		LList<Edge> foundl;
		try {
			for(int i = 0; i < adjList.length(); i++) {
				foundl = adjList.get(i);
				found = foundl.get(0);
				if (found.getData() == from) {
					for (int j = 1; j < foundl.length(); j++) {
							found = foundl.get(j);
							if (found.getData() == to) {
								return found;
							}
					}
				}
			}
		} catch (Exception e) {
			System.exit(-99);
			//oh no
		}
		throw new NotFoundException(to);
	}
	// given a list of edges and a T "to", will return the edge that contains
	// "to". excludes the first index of the list from the search. 

	public String toString() {
		String done = "";
		for (int i = 0; i < adjList.length(); i++) {
			try {
				for (int j = 0; j < adjList.get(i).length(); j++) {
					if (j == 0) {
						done += adjList.get(i).get(j).getData() + " : ";
						if (j == adjList.get(i).length() - 1) {
							done += "\n";
						}
					} else if (j == adjList.get(i).length() - 1) {
						done += adjList.get(i).get(j).getData() + "\n";
					} else {
						done += adjList.get(i).get(j).getData() + ", ";
					}
				}
			} catch (OutOfBoundsException e) {
				e.printStackTrace();
				// I wish there was a way where I don't need to put this here,
				// cause this is never gonna get thrown.
			}
		}
		return done;

	}
	// returns a String containing all the sublists in adjList, each on its own
	// line.

	public class Edge {
		private int cost;
		private T towards;
		public ReadWriteLock lock;

		public Edge(int c, T p) {
			this.lock = new ReadWriteLock(c);
			cost = c;
			towards = p;
		}

		public int getCost() {
			return cost;
		}

		public void minusMinus() {
			cost--;
		}

		public T getData() {
			return towards;
		}

		public String toString() {
			return "" + towards + "-" + cost;
		}
	}
}
