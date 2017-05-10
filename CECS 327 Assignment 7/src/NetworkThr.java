import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class NetworkThr implements Runnable {
	private Socket client;
	private Node command, result;
	private Node node;

	public ConcurrentLinkedQueue<Node> requestQue;
	public ConcurrentLinkedQueue<Node> resultQue;

	public NetworkThr (Node cmd, ConcurrentLinkedQueue<Node> result, Socket c) {
		this.node = cmd;
		this.resultQue = result;
		this.client = c;
	}

	public void run() {
		try {
			// Creats the output stream to be able to send the request to the server
			// and a buffered reader to be able to read the result from the server
			DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			// Sends the request that is in the request queue
			outToServer.writeBytes(node.command + '\n');

			// Waits for the server to send back the result
			node.command = inFromServer.readLine();
			
			// Adds the result back in the queue
			resultQue.add(node);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}