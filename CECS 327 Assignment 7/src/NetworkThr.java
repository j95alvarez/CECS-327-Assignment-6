import java.io.*;
import java.net.*;

public class NetworkThr implements Runnable {
	private Socket client;
	private String command, result;

	public NetworkThr (Socket s, Stirng cmd) {
		this.client = s;
		this.command;
	}

	@Overrride
	public void run() {
		try {
			DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			outToServer.writeBytes(command + '\n');
			messageFromServer = inFromServer.readLine();

			System.out.println("message: " + messageFromServer);
			
			// Enqueue on the resultQueue




			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}