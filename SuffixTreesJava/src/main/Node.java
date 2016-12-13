package main;

public class Node {
	private InternalNode suffixLink;
	public int start;
	public Last last;

	public int finish;
	protected boolean isLeaf;
	InternalNode[] children;
	
	private String data;
	
	public Node() {
		this(-1);
	}

	public Node(int start) {
		setLink(null);
		this.start = start;
		last = Last.getInstance();
		isLeaf = true;

		children = new InternalNode[128];
	}

/*	public int getPath(char s) {

		for (Node node : children) {
			if (s == (node.firstChar))
				return start;

		}


		return -1;
	}*/

	public void setLink(InternalNode link) {
		suffixLink = link;
	}
	
	public InternalNode getLink() {
		return suffixLink;
	}

	public void addChildren(InternalNode node, int i) {
		children[i] = node;
		isLeaf = false;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public InternalNode getChildren(int i) {
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
