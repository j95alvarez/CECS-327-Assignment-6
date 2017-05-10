import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class RuntimeThr implements Runnable {
	public static int evenOddSequence = 0;

	public ConcurrentLinkedQueue<Node> requestQue;
	public ConcurrentLinkedQueue<Node> resultQue;
	public Socket clientSocket;

	public RuntimeThr(ConcurrentLinkedQueue<Node> request, ConcurrentLinkedQueue<Node> result) {
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

			while (requestQue.isEmpty()) { }

			while(!requestQue.isEmpty()) {
				Node request = requestQue.peek();
				
				System.out.println("REQUEST: " + request);


				if(request.command.equals("NEXTEVEN") || request.command.equals("NEXTODD")) {
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