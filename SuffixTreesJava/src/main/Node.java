package main;

public class Node {
	static int count=0;
	private Node suffixLink;
	public int start;
	private Last last;

	public int finish;
	protected boolean isLeaf;
	Node[] children;
	
	private String data;
	
	public Node() {
		this(-1);
	}

	public Node(int start) {
		setLink(null);
		count++;
		this.start = start;
		last = Last.getInstance();
		isLeaf = true;

		children = new Node[128];
	}

/*	public int getPath(char s) {

		for (Node node : children) {
			if (s == (node.firstChar))
				return start;

		}


		return -1;
	}*/

	public void setLink(Node link) {
		suffixLink = link;
	}
	
	public Node getLink() {
		return suffixLink;
	}

	public void addChildren(Node node, int i) {
		children[i] = node;
		isLeaf = false;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public Node getChildren(int i) {
		return children[i];
	}

	public int getLast() {
		return last.getValue();
	}

	public String getData() {
		return data;
	}

	public Node setData(String data) {
		this.data = data;
		return this;
	}

}
