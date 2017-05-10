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
			DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			outToServer.writeBytes(node.command + '\n');
			node.command = inFromServer.readLine();
			
			resultQue.add(node);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		resultQue.add("DEVIN " + command);
		*/
	}
}