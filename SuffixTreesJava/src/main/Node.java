package main;

public class Node {
	private SuffixLink intermediateLinkToNode;
	
	public Node() {
		setLink(null);
	}
	
	public void setLink(SuffixLink link) {
		intermediateLinkToNode = link;
	}
	
	public SuffixLink getLink() {
		return intermediateLinkToNode;
	}

}
