package genericoop;

public class Node<T> {
	/*
	 * this class makes Node objects, that point at another Node (next) and hold
	 * an object called data.
	 */

	private Node<T> next;
	private T data;

	public Node() {
	}

	public Node(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return this.next;
	}

	public T getData() {
		return this.data;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public boolean equals(Node<T> equivalent) {
		if (this.getData().equals(equivalent)) {
			return true;
		} else {
			return false;
		}
	}
}
