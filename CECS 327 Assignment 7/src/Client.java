import java.io.*;
import java.net.*;

public class Client {
	public static void main(String argv[]) {
		String messageFromServer;
		Socket clientSocket;
		try {
			clientSocket = new Socket("localhost", 4445);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			// Generates 15 total requests that will be sent to the server
			for (int i = 0; i < 15; i++) {
				// Sends 5 requests to receive the even fib number
				if (i < 5) {
					outToServer.writeBytes("NEXTEVENFIB" + '\n');
					messageFromServer = inFromServer.readLine();
					System.out.println("Next even fib: " + messageFromServer);
				// Sends 5 requests to receive the nextLargerRand number
				} else if (i >= 5 && i < 10) {
					outToServer.writeBytes("NEXTLARGERRAND" + '\n');
					messageFromServer = inFromServer.readLine();
					System.out.println("Next larger Ran: " + messageFromServer);
				// Sends 5 requests to receive the next prime number
				} else {
					outToServer.writeBytes("NEXTPRIME" + '\n');
					messageFromServer = inFromServer.readLine();
					System.out.println("Next Prime: " + messageFromServer);
				}
			}
			clientSocket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}