import java.util.concurrent.*;

public class UClient {
	public static void main(String[] args) {
		ConcurrentLinkedQueue<String> requestQue = new ConcurrentLinkedQueue<String>();
		ConcurrentLinkedQueue<String> resultQue = new ConcurrentLinkedQueue<String>();

		requestQue.add("NEXTEVEN\n");
		requestQue.add("NEXTEVENFIB\n");
		requestQue.add("NEXTLARGERRAND\n");
		requestQue.add("NEXTODD\n");
		requestQue.add("NEXTPRIME\n");

		new RuntimeThr(requestQue, resultQue).run();	
	}
}