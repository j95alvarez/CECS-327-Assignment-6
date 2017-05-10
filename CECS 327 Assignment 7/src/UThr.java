import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.locks.*;

public class UThr implements Runnable {
	public int id;
	public ConcurrentLinkedQueue<Node> requestQue;
	public ConcurrentLinkedQueue<Node> resultQue;
	public boolean done = false;
	public static Lock lock = new ReentrantLock();

	public UThr(int i, ConcurrentLinkedQueue<Node> request, ConcurrentLinkedQueue<Node> result){
		this.id = i;
		this.requestQue = request;
		this.resultQue = result;
	}
	public void run() {
		//Creates random object
		Random ran = new Random();						
		
		for(int i = 0; i < 20; i++){
			int x = ran.nextInt(5);

			String request = "";

			//Chooses random number from 0 - 4
			if (x == 0)
				//If x = 0, go to nextEven thread
				request = "NEXTEVEN";
			if (x == 1)
				//If x = 1, go to nextOdd thread
				request = "NEXTODD";
			if (x == 2)
				//If x = 2, go to nextEvenFib thread
				request = "NEXTEVENFIB";
			if (x == 3)
				//If x = 3, go to nextLargerRand thread
				request = "NEXTLARGERRAND";
			if (x == 4)
				//If x = 4, go to nextPrime thread
				request = "NEXTPRIME";
			
			Node temp = new Node(id, i, request);

			requestQue.add(temp);

			// Loops until the result queue is no longer
			// empty and then prints it out using locks
			while(!done){
				lock.lock();
				try {
					// Tests to see if the result queue is not
					// empty in order to print
					if(!resultQue.isEmpty())
						if(resultQue.peek() != null) {
							// Test to see if the result from 
							// the request that was generated
							// from this iteration
							if(resultQue.peek().id == id) {
								if(resultQue.peek().cmdNum == i) {
									System.out.println(resultQue.poll());
									break;
								}
							}
							
						}
				} finally {
					lock.unlock();
				}
			}
		}
	}
}