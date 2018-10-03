package throwing;

@SuppressWarnings("serial")
public class NotOnlyChildException extends Exception {
	public NotOnlyChildException(){
		super("Wasn't an only child.");
	}
}
