import java.io.*;
import java.net.*;

public class NextPrime extends Thread {

	protected Socket client;
	private int num;

	public NextPrime(Socket c) {
		this.client = c;
		num = Server.prime;
	}

	public void run() {
		try {
			DataOutputStream outToClient = new DataOutputStream(client.getOutputStream());
			// Sends the result of the next prime to the client
			outToClient.writeBytes(nextPrime(num) + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int nextPrime(int prev) {
		// Increments the current index
		prev++;
		// Loops until the current index is not prime
		while (!isPrime(prev)) {
			if(prev > 0)
				prev++;
			else
				prev = Server.prime = 2;
		}
		// Store the current index in order to prevent duplicate
		// calculations
		Server.prime = prev;
		return prev;
	}
	// Determine if a number is prime
	private static boolean isPrime(int n) {
		// if 2 is a factor, it is not prime
		if (n % 2 == 0) {
			return false;
		}
		// Check all the odd factors
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

}