import java.util.concurrent.*;

public class UClient {
	public static void main(String[] args) {
		ConcurrentLinkedQueue<Node> requestQue = new ConcurrentLinkedQueue<Node>();
		ConcurrentLinkedQueue<Node> resultQue = new ConcurrentLinkedQueue<Node>();

		/*
		requestQue.add("NEXTEVEN\n");
		
		requestQue.add("NEXTODD\n");
		
		requestQue.add("NEXTODD\n");
		
		requestQue.add("NEXTODD\n");
		
		requestQue.add("NEXTEVEN\n");
		*/

		
		Thread t1 = new Thread(new RuntimeThr(requestQue, resultQue), "Main");	
		Thread[] ts = new Thread[8];

		t1.start();

		for(int i = 0; i < ts.length; i++)
			ts[i] = new Thread(new UThr(i, requestQue, resultQue), i + "");

		for(int i = 0; i < ts.length; i++)
			ts[i].start();

	}
}