package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node implements Iterable<Node> {
	private Node suffixLink;
	private String suffix;
	public int start,finish;
	private boolean isLeaf;
	private List<Node> children;
	
	// FIXME Maybe deprecated
	private String data;
	
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

	@Override
	public Iterator<Node> iterator() {
		return children.iterator();
	}

	public String getData() {
		return data;
	}

	public Node setData(String data) {
		this.data = data;
		return this;
	}

}
