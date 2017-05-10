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
		int result = 0;
		if(x > 10) {
			x = 0;
		}

		if (node.command.equals("NEXTEVEN"))
			node.command = "" + setNextEven();
		else
			node.command = "" + setNextOdd();

		resultQue.add(node);
	}
	
	public int setNextEven() {
		while(x % 2 != 0) {
			x++;
		}

		RuntimeThr.evenOddSequence = x;

		return x;
	}
	
	public int setNextOdd() {
		while(x % 2 != 1){
			x++;
		}
		
		RuntimeThr.evenOddSequence = x;

		return x;
	}
}