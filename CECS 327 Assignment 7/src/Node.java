public class Node {
	public int id;
	public int cmdNum;
	public String command;

	// Initializes the values needed in order 
	// to compare the nodes to each other
	public Node(int i, int cmdN, String r){
		this.id = i;
		this.cmdNum = cmdN;
		this.command = r;
	}

	// Prints the node's data members when printing
	// to the console
	public String toString() {
		return "Interation: " + cmdNum + " Thread ID: " + id + ": " + command;
	}
}