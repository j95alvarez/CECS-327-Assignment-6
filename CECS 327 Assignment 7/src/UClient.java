import java.util.concurrent.*;


public class UClient {
	public static void main(String[] args) {
		// Creates 2 concurrent queue objects
		ConcurrentLinkedQueue<Node> requestQue = new ConcurrentLinkedQueue<Node>();
		ConcurrentLinkedQueue<Node> resultQue = new ConcurrentLinkedQueue<Node>();
		
		try {
			// Creates the RuntimeThr and initialize an arrary to hold 8 UThr
			Thread t1 = new Thread(new RuntimeThr(requestQue, resultQue));	
			Thread[] ts = new Thread[8];

			// Starts the RuntimeThr
			t1.start();
			t1.join();
		} catch (Exception e) {
			System.out.println("error");
		}
		
	}
}