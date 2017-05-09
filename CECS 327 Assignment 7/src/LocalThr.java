import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class LocalThr extends Thread {
	public int x;
	public String command;
	public ConcurrentLinkedQueue<String> resultQue;

	public LocalThr(String cmd, int val, ConcurrentLinkedQueue<String> r) {
		this.command = cmd;
		this.x = val;
		this.resultQue = r;
	}
	
	public void run() {
		int result = 0;
		if(x > 10) {
			x = 0;
		}

		if (!command.equals("NEXTEVEN"))
			result = setNextEven();
		else
			result = setNextOdd();

		resultQue.add("NOT PENIS " +result);

		System.out.println("Hello");
	}
	
	public int setNextEven() {
		while(x % 2 != 0) {
			x++;
		}

		return x;
		//put x in queue if even
	}
	
	public int setNextOdd() {
		while(x % 2 == 0){
			x++;
		}
		
		return x;
	}
}