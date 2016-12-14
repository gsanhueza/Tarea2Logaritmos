package main;

import java.util.ArrayList;
import java.util.List;

public class Ukkonen {
	private Last end;
	private int remaining;
	private Node activeNode;
	public int activeEdge;
	public int activeLength;
	private String realString;

	private char[] receivedString;

	public Ukkonen(String receivedString) {
		realString = receivedString;
		this.receivedString = receivedString.toCharArray();
		end = Last.getInstance();
		remaining = 0;
		activeNode = null;
		activeEdge = -1;
		activeLength = 0;
	}

	public int getPath(char s, Node n) {
		Node child = activeNode.getChildren(s);
		if (child != null)
			return child.start;
		return -1;

	}

	/* La creacion del arbol es O(n) */
	public Node run() {
		Node suffixLink = null;
		activeNode = new InternalNode(-1, -1);
		Node root = activeNode;

		for (int i = 0; i < realString.length(); i++) {
			remaining++;
			end.setValue(i);
			suffixLink = null;
			while (remaining > 0) {
				/*
				 * getPath entrega la variable "start" si es que hay match con
				 * el primer caracter de la branch, o -1 si es que no hay
				 */
				if (activeLength == 0) {
					int path = getPath(receivedString[i], activeNode);
					if (path == -1) {
						Node myNode = new Node(i);
						activeNode.addChildren(myNode, receivedString[i]);
						if (!activeNode.equals(root) &&!activeNode.isLeaf()){
							if (suffixLink!=null) {
								suffixLink.setLink(activeNode);
							}
							suffixLink = activeNode;
							activeNode.setLink(root);

						}
						if (activeNode.getLink()!= null) {
							activeNode = activeNode.getLink();
						}
						remaining--;

					} else {
						/* Rule 3 */
						activeEdge = path;
						activeLength++;
						break;
					}
				} else {
					/*
					 * Nos paramos en el caracter que tenemos guardado (active
					 * edge)
					 */

					Node activeLeaf = activeNode.getChildren(receivedString[activeEdge]);
					if (activeLeaf == null) {
						activeNode.children[receivedString[activeEdge]] = new Node(activeEdge);
						activeLeaf = activeNode.getChildren(receivedString[activeEdge]);
					}

					/*
					 * Es el siguiente caracter del que estamos parados igual a "i"?
					 */
					if (activeLeaf.getLast() - activeLeaf.start +1 < activeLength+1) {
						activeNode = activeLeaf;

						activeLength = activeLength -(activeLeaf.getLast() - activeLeaf.start+1);
						activeEdge = activeEdge + (activeLeaf.getLast() - activeLeaf.start+1) ;
						continue;
					}
					char c = receivedString[activeNode.getChildren(receivedString[activeEdge]).start + activeLength];

					if (c == receivedString[i]) {
						activeLength++;
						break;
					}
					/* Crear nodo interno */
					else {
						/*
						 * auxNode se utiliza para ver si el nodo en que estamos
						 * parados es uno interno o una hoja
						 */
						Node auxNode = activeNode.getChildren(receivedString[activeEdge]);
						int x = activeNode.getChildren(receivedString[activeEdge]).start;

						InternalNode internal = new InternalNode(x, x + activeLength - 1);
						internal.setLink(root);
						Node child1;
						Node child2;
						if (auxNode.isLeaf()) {
							child1 = new Node(x + activeLength);
							child2 = new Node(i);
						} else {
							child1 = new InternalNode(x + activeLength, auxNode.getLast());
							child1.children = auxNode.children;
							child2 = new Node(i);
						}
						
						internal.addChildren(child1, receivedString[child1.start]);
						internal.addChildren(child2, receivedString[child2.start]);
						activeNode.addChildren(internal, receivedString[activeEdge]);
						
						if (suffixLink == null) {
							suffixLink = internal;
						} else {
							suffixLink.setLink(internal);
							suffixLink = internal;
						}
						remaining--;

						if (activeNode.equals(root)) {
							activeLength--;
							activeEdge++;
						}
						else {
							if (activeNode.getLink() != null)
								activeNode = activeNode.getLink();
							else
								activeNode = root;
						}
					}
				}
			}
			/* Como salimos del caracter i, olvidamos el suffixLink guardado */


		}
		return root;
	}

	public List<Integer> suffixStartPosition(String suffix, Node root) {
		int i = 0;
		int suffixLength = suffix.length();
		List<Integer> resp = new ArrayList<Integer>();
		Node currentNode = root;
		int dif;
		Node auxNode;

		while(true) {
			auxNode = currentNode;
			if (currentNode.isLeaf())
				break;
			if (i>suffixLength)
				break;
			for (Node hijo : currentNode.children) {
				if(hijo == null)
					continue;
				if (i>suffixLength)
					break;
				dif = hijo.getLast() - hijo.start +1;
				if (receivedString[hijo.start] == suffix.charAt(i)){
					if (dif >suffixLength - i) {
						resp.add(hijo.start-i);
						currentNode = hijo;
						break;
					}
					else if (dif < suffixLength-i) {
						i+=dif;
						currentNode = hijo;
						break;
					}
					else {
						resp.add(hijo.start-i);
						currentNode = hijo;
						break;
					}
				}
			}
			if (auxNode.equals(currentNode))
				break;
		}
		return resp;
	}

	public int countSuffixOcurrences(String suffix,Node root) {
		Node currentNode = root;
		Node auxNode;
		int i = 0;
		int dif;
		int suffixLength = suffix.length();

		while (true) {
			auxNode = currentNode;
			for (Node child : currentNode.children) {
				if (child == null)
					continue;
				if (receivedString[child.start] == suffix.charAt(i)) {
					dif = child.getLast() - child.start +1;
					if (dif >suffixLength - i) {
						return countLeafs(child);

					}
					else if (dif < suffixLength-i) {
						i+=dif;
						currentNode = child;
						break;
					}
					else {
						return countLeafs(child);
					}
				}
			}

			if (currentNode.equals(auxNode))
				return -1;
		}
	}

	private int countLeafs(Node child) {
		int count = 0;
		if (child.isLeaf())
			return 1;
		for (Node aux : child.children){
			if (aux == null)
				continue;
			count += countLeafs(aux);
		}
		return count;
	}
}
