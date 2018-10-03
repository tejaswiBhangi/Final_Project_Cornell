package throwing;

@SuppressWarnings("serial")
public class AlreadyConnectedException extends Exception {
	/*
	 * throws a custom exception, for objects not found.
	 */
	public AlreadyConnectedException(Object self, Object with){
		super("The Object " + self + " is already connected to " + with + "!");
	}
}
