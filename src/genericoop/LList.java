package genericoop;

import throwing.NotFoundException;
import throwing.OutOfBoundsException;

public class LList<T> {
	/*
	 * this class creates Linkedlists, which are lists of nodes containing
	 * objects.
	 * 
	 * remove(int targ)
	 * 
	 * indexOutOfBounds for .get(index i); generics are cool! specifying the
	 * type of types! linkedlist of linkedlists of vertexes! (for graph)
	 *
	 * implementation vs. containing. ENCAPSULATION!!! use == vs. .equals
	 * contains vs finds implementation dont need finds? use a for loop instead
	 */
	private Node front, back;
	private int length = 0;
	// instantiate variables

	public LList() {
		front = null;
		back = null;
	}
	// empty constructor

	public boolean isEmpty() {
		return this.length == 0;
	}
	// return true if empty, false if not

	public void prepend(T newbie) {
		Node before = new Node(newbie);
		// creates new node
		if (isEmpty()) {
			back = front = before;
			// if empty, sets front to before.
		} else {
			before.setNext(front);
			front = before;
			// if not empty, sets before's next node to front. then labels
			// before as front.
		}
		length++;
	}
	// inserts a Node containing newbie at the beginning of the list.

	public void insert(T newbie, int index) throws OutOfBoundsException {
		if (index < 0 || index > length) {
			throw new OutOfBoundsException(index);
		}

		Node insert = new Node(newbie);

		if (index == 0) {
			insert.setNext(front);
			front = insert;
		} else if (index == this.length) {
			back.setNext(insert);
			back = insert;
		} else {
			Node afterThis = front;

			for (int i = 1; i < index; i++) {
				afterThis = afterThis.getNext();
			}

			insert.setNext(afterThis.getNext());
			afterThis.setNext(insert);
		}

		length++;
	}
	// inserts a node containing newbie at a given index (can be 0 or length).

	public void append(T newbie) {
		Node after = new Node(newbie);

		if (isEmpty()) {
			front = back = after;
		} else {
			back.setNext(after);
			back = after;
		}
		length++;
	}
	// inserts a node containing newbie at the end of the list.

	public T getFront() {
		return front.getData();
	}
	// returns the first piece of data, even if it's null.

	public T get(int index) throws OutOfBoundsException {
		if (index < 0 || index >= length) {
			throw new OutOfBoundsException(index);
		}

		Node target = front;

		for (int i = 0; i < index; i++) {
			target = target.getNext();
		}
		return target.getData();
	}
	// given an int, will try to return an object at that index.

	public boolean contains(T findThis) {
		Node current = front;

		for (int i = 0; i < this.length; i++) {
			if (current.getData() == findThis) {
				return true;
			} else {
				current = current.getNext();
			}
		}
		return false;
	}
	// searches the list for an object, if found returns true. else returns
	// false.
	// something something I dont know how to use trees, what?

	public int indexOf(T findThis) {
		Node current = front;

		for (int i = 0; i < this.length; i++) {
			if (current.getData() == findThis) {
				return i;
			} else {
				current = current.getNext();
			}
		}
		return -1;
	}
	// given an object, will return the index of location if

	public void remove(int target) throws OutOfBoundsException {
		if (target < 0 || target >= this.length) {
			throw new OutOfBoundsException(target);
		}

		if (target == 0) {
			front = front.getNext();
			this.length--;
			return;
		}

		Node removeAfterThis = front;

		for (int i = 0; i < target - 1; i++) {
			removeAfterThis = removeAfterThis.getNext();
		}

		removeAfterThis.setNext(removeAfterThis.getNext().getNext());
		length--;
	}
	// given an integer, will try to remove an object at that index.

	public void remove(T target) throws NotFoundException {
		if (!contains(target)) {
			throw new NotFoundException(target);
		}
		
		if (front.getData() == target) {
			front = front.getNext();
			this.length--;
			return;
		}
		
		Node delNext = front;
		
		for (int i = 0; i < this.length - 1; i++) {
			if (delNext.getNext().getData() == target) {
				if (delNext.getNext() == back){
					back = delNext;
					delNext.setNext(null);
					length--;
					return;
				}
				delNext.setNext(delNext.getNext().getNext());
				length--;
			} else {
				delNext = delNext.getNext();
			}
		}
	}
	// attempts to remove T target, if not found then throws a
	// NotFoundException.

	public int length() {
		return length;
	}
	// returns the length of the list

	public String toString() {
		String last = "[";

		if (!isEmpty()) {
			Node current = front;
			for (int i = 0; i < this.length-1; i++) {
				last += current.getData() + ", ";
				current = current.getNext();
			}
			last += current.getData();

		}

		return last + "]";
	}
	// returns a string of all the things in array form ish.

	public class Node {
		/*
		 * this class makes Node objects, that point at another Node "next" and
		 * hold an object called "data".
		 */
		private Node next;
		private T data;

		public Node(T data) {
			this.data = data;
		}
		// if data is given

		public Node getNext() {
			return this.next;
		}
		// returns Node next

		public T getData() {
			return this.data;
		}
		// returns data

		public void setNext(Node next) {
			this.next = next;
		}
		// sets Node next

	}

}
