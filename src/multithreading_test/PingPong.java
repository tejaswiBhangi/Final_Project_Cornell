package multithreading_test;

public class PingPong extends Thread {
	String word;
	int delay;
	
	public PingPong(String word, int delay){
		this.word = word;
		this.delay = delay;
	}
	//constructor
	
	public void run(){
		for ( ; ; ){
			System.out.print(word);
			try {
				sleep(delay);
			}
			catch (InterruptedException e){
				System.out.println("I’m done!!");
				System.exit(0);
			}
		}
	}
}