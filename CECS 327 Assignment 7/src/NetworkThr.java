import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class NetworkThr implements Runnable {
	private Socket client;
	private String command, result;
	private Node node;

	public ConcurrentLinkedQueue<String> requestQue;
	public ConcurrentLinkedQueue<String> resultQue;

	public NetworkThr (String cmd, ConcurrentLinkedQueue<String> result) {
		this.command = cmd;
		this.resultQue = result;
	}

	public void run() {
		resultQue.add("DEVIN " + command);
	}
}