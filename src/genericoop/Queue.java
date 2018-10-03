package genericoop;

import throwing.QueueEmptyException;

public class Queue<T> {
	/*
	 * this class holds objects, in a queue format. can add from one end, take
	 * off from the other.
	 */
	Node<T> back, header;
	int length;
	Object id;

	public Queue() {
		this.length = 0;
		this.header = new Node<T>();
	}

	public Queue(Object id) {
		this.length = 0;
		this.header = new Node<T>();
		this.id = id;
	}

	public Queue(Object id, T init) {
		this.id = id;
		this.header = new Node<T>();
		this.header.setNext(new Node<T>(init));
		this.back = header.getNext();
		this.length = 1;
	}

	public void add(T toAdd) {
		if (this.length == 0) {
			this.header.setNext(new Node<T>(toAdd));
			this.back = header.getNext();
		} else {
			this.back.setNext(new Node<T>(toAdd));
			this.back = this.back.getNext();
		}
		length++;
	}

	public T leave() throws QueueEmptyException {
		if (this.length == 0) {
			throw new QueueEmptyException();
		}
		Node<T> temp = this.header;
		this.header = this.header.getNext();
		length--;
		return temp.getData();
	}

	public int getLength() {
		return this.length;
	}

	public Node<T> getHeader() {
		return this.header;
	}

	public Node<T> getBack() {
		return this.back;
	}

	public boolean contains(T obj) {
		Node<T> temp = header.getNext();
		while (temp != null) {
			if (temp.equals(obj)) {
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}
}
