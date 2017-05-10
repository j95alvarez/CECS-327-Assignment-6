import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class RuntimeThr implements Runnable {
	public static int evenOddSequence = 0;

	public ConcurrentLinkedQueue<Node> requestQue;
	public ConcurrentLinkedQueue<Node> resultQue;
	public Socket clientSocket;
	Thread[] ts = new Thread[8];

	public RuntimeThr(ConcurrentLinkedQueue<Node> request, ConcurrentLinkedQueue<Node> result) {
		this.requestQue = request;
		this.resultQue = result;

		try {
			clientSocket = new Socket("localhost", 4445);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Creates the UThr
		for(int i = 0; i < ts.length; i++)
			ts[i] = new Thread(new UThr(i, requestQue, resultQue), i + "");

		// Starts the UThr
		for(int i = 0; i < ts.length; i++)
			ts[i].start();

	}

	public void run() {
		// Loops until there is something in the request queue
		// because if it is emmpty, the thread will stop
		while (requestQue.isEmpty()) { }

		while(!requestQue.isEmpty()) {
			// Stores the reference to the head element in the request queue
			Node request = requestQue.peek();
			
			//System.out.println("REQUEST: " + request);

			// Test to see if the local thr or the network thr will be created
			if(request.command.equals("NEXTEVEN") || request.command.equals("NEXTODD")) {
				new LocalThr(request, resultQue).run();
				// Increments a counter to keep track of the even or odd number
				RuntimeThr.evenOddSequence++;
			} else {
				// Checks to see if the peeked request is not null
				if(request != null) {
					// Spawns the NetworkThr in order to send the request to the server
					new NetworkThr(request, resultQue, clientSocket).run();
				}
				
			}
			// Removes the request that was at the head of the queue
			// since it was just processed
			requestQue.remove();
		}
	}
}