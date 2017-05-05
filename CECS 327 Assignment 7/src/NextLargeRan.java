import java.io.*;
import java.net.*;
import java.util.Random;

public class NextLargeRan extends Thread {
	public static int last;//last number that nextLargeRand produced
	public int MAXNUM;//maximum number that nextLargeRand can produce
	protected Socket client;


	public NextLargeRan(Socket c) {
		this.client = c;
		MAXNUM = 5;
	}

	public void run() {
		try {
			DataOutputStream outToClient = new DataOutputStream(client.getOutputStream());

			// Sends the result of the nextLargerRand to the client using
			// a new max that is based on the previous one 
			outToClient.writeBytes(nextLargeRand(100) + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int nextLargeRand(int max) {
		Random r = new Random();
		int ranNum;
		do{
			// Generates a random number up the max
			ranNum = r.nextInt(max) + 1;
	    	
			// Test to see if the num that was generated is less than the
			// previous one that was stored in the main server and
			// also it is less than the one that was passed in
	    	if(ranNum > Server.largerRan && ranNum < max){
	    		Server.largerRan = ranNum;
	    		break;
	    	}
	    	if(ranNum >= max){
	    		Server.largerRan = 0;
	    	}
		}
		while(true);
		return ranNum;
	}
}