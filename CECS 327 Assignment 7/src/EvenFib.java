import java.io.*;
import java.net.*;
import java.util.*;

public class EvenFib extends Thread {
	// Client's socket info
	protected Socket client;
	private int num;
	private HashMap<Integer, Integer> fibs = new HashMap<Integer, Integer>();

	public EvenFib(Socket c) {
		this.client = c;
		this.num = Server.fib;
	}

	public void run() {
		try {
			DataOutputStream outToClient = new DataOutputStream(client.getOutputStream());
			// Sends the result of the next even fib to the client
			outToClient.writeBytes(evenFib(num) + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int evenFib(int n) {
		int fib;
		// Loops until the fib number at n is even
		while ((fib = fib(n)) % 2 != 0) {
			// Checks to see if it has reached the max 
			// number an int can hold and if it has, 
			// reset the index to 0
			if(fib > 0) {
				n++;
			}
			else {
				n = Server.fib = 0;
			}
		}
		// Save the new n value in the server so that 
		// no duplicates can be calculated
		Server.fib = n;
		return fib(Server.fib);
	}

	// Calculates the fib number of n using
	// a hashmap in order to speed up execution
	public int fib(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1) {
			return 1;
		} 

		if(fibs.containsKey(n)) {
			return fibs.get(n);
		}

		fibs.put(n, fib(n-1) + fib(n - 2));
		// returns the fib number
		return fib(n - 1) + fib(n - 2);
	}

}
