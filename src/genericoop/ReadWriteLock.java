package genericoop;

public class ReadWriteLock {
	//TODO make private
	public int readers = 0;
	private int writers = 0;
	private int writeRequests = 0;
	private int max_readers = Integer.MAX_VALUE;

	public ReadWriteLock() {
	}

	public ReadWriteLock(int max_readers) {
		this.max_readers = max_readers;
	}

	public synchronized void lockRead() throws InterruptedException {
		while (writers > 0 || writeRequests > 0 || readers >= max_readers) {
			System.out.println("waiting");
			wait();
		}
		readers++;
	}
	public synchronized void VIPlockRead() throws InterruptedException{
		readers++;
	}

	public synchronized void unlockRead() {
		readers--;
		notifyAll();
	}

//	public synchronized void lockWrite() throws InterruptedException {
//		writeRequests++;
//		while (readers > 0 || writers > 0) {
//			wait();
//		}
//		writeRequests--;
//		writers++;
//	}
//
//	public void unlockWrite() throws InterruptedException {
//		writers--;
//		notifyAll();
//	}
	
	
	
//	private String myName() {
//		return Thread.currentThread().getName();
//	}
//
//	private static void log(Object obj) {
//    System.out.println(String.valueOf(obj));
//  }
//
//	public static void main(String[] args) {
//		ReadWriteLock lock = new ReadWriteLock(3);
//
//		Runnable runnable = () -> {
//    	try {
//        String name = Thread.currentThread().getName();
//        System.out.println("Created " + name);
//				for (int i = 0; i < 10; i++) {
//					lock.lockRead();
//					log(name + " started reading");
//					TimeUnit.SECONDS.sleep(1);
//					lock.unlockRead();
//					log(name + " finished reading");
//				}
//    	} catch (InterruptedException e) {
//        e.printStackTrace();
//    	}
//		};
//
//		Thread t[] = new Thread[10];
//		for (int i = 0; i < 10; i++) {
//			t[i] = new Thread(runnable);
//			t[i].start();
//		}
//		for (int i = 0; i< 10; i++) {
//			try {
//				t[i].join();
//			} catch (InterruptedException e) {}
//		}
//	}
}