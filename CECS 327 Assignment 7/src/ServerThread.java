import java.io.*;
import java.net.*;
import java.util.Random;

class ServerThread extends Thread {
	private Socket client = null;
	
	public ServerThread(Socket s) {
		// Stores the a reference of the client's socket
		// that was accepted from the main server thread
		this.client = s;
	}

	public void run() {
		// Stores the request from the client
		String choice = null;
		BufferedReader inFromClient = null;
		DataOutputStream outToClient = null;
		
		try {
			inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
			outToClient = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// Loops until the client's connection is lost
			while (true) {	
				// Request that was sent from the client
				choice = inFromClient.readLine();
				if(choice != null) {
					System.out.println(choice);
					switch (choice) {
						// Calculates the next even fib by creating a new
						// thread to handle that calculation
						case "NEXTEVENFIB":
							new EvenFib(client).run();
							// Increments the fib index tracker so no duplicates
							// are sent
							Server.fib++;
							break;
						// Calculates the next even nextLargerRan by creating a new
						// thread to handle that calculation
						case "NEXTLARGERRAND":
							new NextLargeRan(client).run();
							break;
						// Calculates the next prime by creating a new
						// thread to handle that calculation
						case "NEXTPRIME":
							new NextPrime(client).run();
							// Increments the prime index tracker so no duplicates
							// are sent
							Server.prime++;
							break;
					}
				}
				
			}
		} catch (Exception e) {
			// Displays that the client it was connected to
			// has disconnected
			System.out.println("Client Disconnected");
		}

	}
}
