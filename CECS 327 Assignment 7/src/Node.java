public class Node {
	public int id;
	public int cmdNum;
	public String command;

	public Node(int i, int cmdN, String r){
		this.id = i;
		this.cmdNum = cmdN;
		this.command = r;
	}

	public String toString() {
		return "Interation: " + cmdNum + " Thread ID: " + id + ": " + command;
	}
}