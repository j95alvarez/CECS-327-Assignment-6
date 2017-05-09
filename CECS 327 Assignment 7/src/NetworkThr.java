import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class NetworkThr implements Runnable {
	private Socket client;
	private String command, result;
	private Node node;

	public ConcurrentLinkedQueue<String> requestQue;
	public ConcurrentLinkedQueue<String> resultQue;

	public NetworkThr (String cmd, ConcurrentLinkedQueue<String> result, Socket c) {
		this.command = cmd;
		this.resultQue = result;
		this.client = c;
	}

	public void run() {
		try {
			DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			outToServer.writeBytes(command);
			result = inFromServer.readLine();
			
			resultQue.add(result + "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		resultQue.add("DEVIN " + command);
		*/
	}
}