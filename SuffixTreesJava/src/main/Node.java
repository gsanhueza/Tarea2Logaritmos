package main;

public class Node {
	private Node suffixLink;
	public int start;
	private Last last;
	protected boolean isLeaf;
	public Node[] children;

	
	public Node() {
		this(-1);
	}

	public Node(int start) {
		setLink(null);
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


}
