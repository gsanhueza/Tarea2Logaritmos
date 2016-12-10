package main;

public class Node {
	private Node suffixLink;
	private String suffix;
	public int start,finish;
	private boolean isLeaf;
	private Node[] children;
	public char firstChar;

	
	public Node() {
		this(-1,-1, '\0');
	}

	public Node(int start, int finish,char first) {
		setLink(null);
		this.start = start;
		this.finish = finish;
		firstChar = first;
		isLeaf = true;
		children = new Node[256];
		}


	public int getPath(char s) {

		for (Node node : children) {
			if (s == (node.firstChar))
				return start;

		}


		return -1;
	}

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

}
