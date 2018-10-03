package throwing;

@SuppressWarnings("serial")
public class NotFoundException extends Exception {
	/*
	 * throws a custom exception, for objects not found.
	 */
	public NotFoundException(Object target){
		super("The Object " + target + " was not found!");
	}
}
