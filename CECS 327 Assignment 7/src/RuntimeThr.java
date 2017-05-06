import java.util.concurrent.*;

public class RuntimeThr extends Thread {
	public ConcurrentLinkedQueue<Node> requestQue;
	public ConcurrentLinkedQueue<Node> resultQue;

	public RuntimeThr(ConcurrentLinkedQueue<Node> request, ConcurrentLinkedQueue<Node> result) {
		this.requestQue = request;
		this.resultQue = result;
	}

	public void run() {
		while(true){
			System.out.println("Hello");
		}
	}
}