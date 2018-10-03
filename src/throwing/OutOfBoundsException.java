package throwing;

@SuppressWarnings("serial")
public class OutOfBoundsException extends Exception {
	/*
	 * throws a custom exception, for linkedlist out of bounds.
	 */
	public OutOfBoundsException(int index){
		super("Sorry, the index " + index + " was out of bounds!");
	}
}
