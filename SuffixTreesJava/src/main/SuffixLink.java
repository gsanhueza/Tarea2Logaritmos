package main;

public class SuffixLink {
	private int from;
	private int to;
	private Node nextNode;
	
	public SuffixLink() {
		from = 0;
		to = 0;
		setNextNode(null);
	}
	
	public void setNextNode(Node node) {
		nextNode = node;
	}
	
	public Node getNextNode() {
		return nextNode;
	}

}
