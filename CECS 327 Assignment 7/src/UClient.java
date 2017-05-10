import java.util.concurrent.*;

public class UClient {
	public static void main(String[] args) {
		// Creates 2 concurrent queue objects
		ConcurrentLinkedQueue<Node> requestQue = new ConcurrentLinkedQueue<Node>();
		ConcurrentLinkedQueue<Node> resultQue = new ConcurrentLinkedQueue<Node>();
		
		// Creates the RuntimeThr and initialize an arrary to hold 8 UThr
		Thread t1 = new Thread(new RuntimeThr(requestQue, resultQue), "Main");	
		Thread[] ts = new Thread[8];

		// Starts the RuntimeThr
		t1.start();

		// Creates the UThr
		for(int i = 0; i < ts.length; i++)
			ts[i] = new Thread(new UThr(i, requestQue, resultQue), i + "");

		// Starts the UThr
		for(int i = 0; i < ts.length; i++)
			ts[i].start();

	}
}