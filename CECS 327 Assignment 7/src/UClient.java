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

		for(int i = 0; i < 8; i++)
			new UThr(i, requestQue, resultQue).run();

		new RuntimeThr(requestQue, resultQue).run();	
	}
}