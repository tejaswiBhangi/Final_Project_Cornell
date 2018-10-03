package throwing;

@SuppressWarnings("serial")
public class QueueEmptyException extends Exception {
	public QueueEmptyException (){
		super("The queue was empty, and you tried to remove an element from it!");
	}
}
