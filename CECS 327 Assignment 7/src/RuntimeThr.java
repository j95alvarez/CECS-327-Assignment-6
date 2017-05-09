import java.util.concurrent.*;

public class RuntimeThr extends Thread {
	public ConcurrentLinkedQueue<String> requestQue;
	public ConcurrentLinkedQueue<String> resultQue;
	//public Socket clientSocket;

	public RuntimeThr(ConcurrentLinkedQueue<String> request, ConcurrentLinkedQueue<String> result) {
		this.requestQue = request;
		this.resultQue = result;
		//this.clientSocket = cs;
	}

	public void run() {
		while(true){
			while(!requestQue.isEmpty()) {
				String request = requestQue.peek();
				
				System.out.println("REQUEST: " + request);


				if(!request.equals("NEXTEVEN") || !request.equals("NEXTODD")) {
					new NetworkThr(request, resultQue).run();
				} else {
					System.out.println("Spawned local thr");
				}
				requestQue.remove();
			}


			if(resultQue.isEmpty()) 
				System.out.println("Result Queue is empty");
			else {
				while(!resultQue.isEmpty()) {
					System.out.println("RESULT: " + resultQue.peek());
					resultQue.remove();
				}
			}

			break;
		}
		

	}
}