import java.util.concurrent.*;

public class UClient {
	public static void main(String[] args) {
		ConcurrentLinkedQueue<String> requestQue = new ConcurrentLinkedQueue<String>();
		ConcurrentLinkedQueue<String> resultQue = new ConcurrentLinkedQueue<String>();

		requestQue.add("NEXTEVEN");
		requestQue.add("NEXEVENFIB");
		requestQue.add("NEXTEVENFIB");
		requestQue.add("NEXTODD");
		requestQue.add("NEXTPRIM");

		new RuntimeThr(requestQue, resultQue).run();	
	}
}