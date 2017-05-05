import java.io.*;
import java.net.*;

public class Server {

	// Keep track of the current n so that duplicates are not sent
	public static int fib = 0;
	public static int prime = 0;
	public static int largerRan = 10;
	
	public static void main(String args[]) {
		// Client socket
		Socket client = null;
		// Server Socket
		ServerSocket server = null;
		// Prints a message indicating that the server is awaiting connection
		System.out.println("Server awaiting connection");
		
		try {
			// Binds the server socket to port 4445
			server = new ServerSocket(4445); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Loops to accept connections from multiple clients
		while (true) {
			try {
				// Server accepts a connection from a client
				client = server.accept();		
				// Prints a confirmation that the client is now 
				// connected to the server
				System.out.println("Connected to a client");
				// Creates a new ServerThread in order to 
				// process requests from the client
				new ServerThread(client).start();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
