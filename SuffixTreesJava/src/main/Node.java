package main;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

public class Node {
	private Node suffixLink;
	private String suffix;
	public int start,finish;
	private boolean isLeaf;
	private List<Node> children;
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
		children = new ArrayList<Node>();
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

	public void addChildren(Node node) {
		children.add(node);
		isLeaf = false;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public Node getChildren(int i) {
		try {
			return children.get(i);
		}
		catch (NullPointerException e) {
			return null;
		}
	}

}
