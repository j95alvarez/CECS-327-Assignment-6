import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class LocalThr implements Runnable {
	public int x;
	public Node node;
	public ConcurrentLinkedQueue<Node> resultQue;

	public LocalThr(Node cmd, ConcurrentLinkedQueue<Node> r) {
		this.node = cmd; 
		this.resultQue = r;
		this.x = RuntimeThr.evenOddSequence;
	}
	
	public void run() {
		// Determines if the request that is in the 
		// request queue is for even or odd
		if (node.command.equals("NEXTEVEN"))
			node.command = "" + setNextEven();
		else
			node.command = "" + setNextOdd();

		// puts the result in the result queue
		resultQue.add(node);
	}
	
	public int setNextEven() {
		// Loops until an even number
		// is found
		while(x % 2 != 0) {
			x++;
		}

		// sets the new even number equal to x
		RuntimeThr.evenOddSequence = x;

		// Returns the next even number
		return x;
	}
	
	public int setNextOdd() {
		// Loops until an odd number
		// is found
		while(x % 2 != 1){
			x++;
		}
		
		// sets the new odd number equal to x
		RuntimeThr.evenOddSequence = x;

		// Returns the next odd number
		return x;
	}
}