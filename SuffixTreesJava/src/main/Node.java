package main;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private Node suffixLink;
	private String suffix;
	public int start,finish;
	private boolean isLeaf;
	private List<Node> children;

	
	public Node() {
		this(-1,-1);
	}

	public Node(int start, int finish) {
		setLink(null);
		this.start = start;
		this.finish = finish;
		isLeaf = true;
		children = new ArrayList<Node>();
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

}
