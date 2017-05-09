import java.util.concurrent.*;

public class UClient {
	public static void main(String[] args) {
		ConcurrentLinkedQueue<String> requestQue = new ConcurrentLinkedQueue<String>();
		ConcurrentLinkedQueue<String> resultQue = new ConcurrentLinkedQueue<String>();

		/*
		requestQue.add("NEXTEVEN\n");
		
		requestQue.add("NEXTODD\n");
		
		requestQue.add("NEXTODD\n");
		
		requestQue.add("NEXTODD\n");
		
		requestQue.add("NEXTEVEN\n");
		*/

		for(int i = 0; i < 8; i++)
			new UThr(requestQue, resultQue).run();

		new RuntimeThr(requestQue, resultQue).run();	
	}
}