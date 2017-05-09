import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class RuntimeThr extends Thread {
	public static int evenOddSequence = 0;

	public ConcurrentLinkedQueue<String> requestQue;
	public ConcurrentLinkedQueue<String> resultQue;
	public Socket clientSocket;

	public RuntimeThr(ConcurrentLinkedQueue<String> request, ConcurrentLinkedQueue<String> result) {
		this.requestQue = request;
		this.resultQue = result;

		try {
			clientSocket = new Socket("localhost", 4445);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		while(true){
			while(!requestQue.isEmpty()) {
				String request = requestQue.peek();
				
				System.out.println("REQUEST: " + request);


				if(request.equals("NEXTEVEN\n") || request.equals("NEXTODD\n")) {
					new LocalThr(request, resultQue).run();
					RuntimeThr.evenOddSequence++;

					//System.out.println("Spawned local thr");
				} else {
					if(request != null) {
						new NetworkThr(request, resultQue, clientSocket).run();
					}
					
				}
				requestQue.remove();
			}


			if(resultQue.isEmpty()) 
				System.out.println("Result Queue is empty");
			else {
				while(!resultQue.isEmpty()) {
					if(resultQue.peek() != null) {
						System.out.println("RESULT: " + resultQue.peek());
						resultQue.remove();
					}
				}
			}

			break;
		}
		

	}
}