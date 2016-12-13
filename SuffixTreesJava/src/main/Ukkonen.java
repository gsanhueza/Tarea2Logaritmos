package main;

import java.util.ArrayList;
import java.util.List;

public class Ukkonen {
	private Last end;
	private int remaining;
	private InternalNode activeNode;
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

	/* Retorna todas las posiciones donde comienza el sufijo */
	public List<Integer> search(String suffix, Node root) {
		// Node root = run();
		char[] charArray = suffix.toCharArray();
		char la;
		if (charArray.length != 0) {
			la = charArray[0];
			root = root.children[charArray[0]];
		}
		else
			return null;

		return getSuffixes(root, suffix, 0);
	}

	private List<Integer> getSuffixes(Node root, String suffix, int count) {
		List<Integer> resp = new ArrayList<Integer>();
		if (root == null) {
			return null;
		}

		if (root.isLeaf()) {
			if (suffix.equals(realString.substring(root.start, root.last.getValue() + 1))) {

				resp.add(realString.length() - (count + 1));
				return resp;
			} else
				return null;

		}
		if (realString.substring(root.start, root.last.getValue() + 1).equals(suffix)) {
			for (Node n : root.children) {
				List<Integer> aux = (getLeafPath(n, count + root.last.getValue() - root.start + 1));
				if (aux != null) {
					for (int i : aux)
						resp.add(i);
				}
			}
			return resp;
		}

		if (suffix.contains(realString.substring(root.start, root.last.getValue() + 1))) {
			for (Node n : root.children) {
				List<Integer> aux = getSuffixes(n, suffix.substring(root.last.getValue() - root.start + 1),
						count + root.last.getValue() - root.start + 1);
				if (aux != null) {
					for (int integer : aux) {
						resp.add(integer);
					}
				}
			}
		}
		else {
			return null;
		}
		return resp;
	}

	private List<Integer> getLeafPath(Node n, int count) {
		List<Integer> resp = new ArrayList<Integer>();
		if (n == null)
			return null;

		if (n.isLeaf()) {
			resp.add(realString.length() - ((count + 1) + n.last.getValue() - n.start));
			return resp;

		}
		for (Node aux : n.children) {
			List<Integer> integers = getLeafPath(aux, count + n.last.getValue() - n.start + 1);
			if (integers != null) {
				for (int i : integers)
					resp.add(i);
			}
			return resp;

		}
		return resp;
	}

	/* La creacion del arbol es O(n) */
	public Node run() {

		InternalNode root = new InternalNode(1, 0);
		activeNode = root;
		Last.getInstance().setValue(-1);
		for (int i = 0; i < realString.length(); i++) {
			Node suffixLink = null;
			remaining++;
			end.increment();
			while (remaining > 0) {
				/*
				 * getPath entrega la variable "start" si es que hay match con
				 * el primer caracter de la branch, o -1 si es que no hay
				 */
				if (activeLength == 0) {
					Node selectNode = activeNode.getChildren(receivedString[i]);
					if (selectNode == null ) {
						root.addChildren(new InternalNode(i, end.getValue()), receivedString[i]);
						remaining--;
					} else {
						/* Rule 3 */
						activeEdge = selectNode.start;
						activeLength++;
						break;
					}
				} else {

					try {
						char c = nextChar(i);

						if (c == receivedString[i]) {
							if (suffixLink != null) {
								suffixLink.setLink(activeNode.getChildren(receivedString[activeEdge]));
							}
							InternalNode selectNode = activeNode.getChildren(receivedString[activeEdge]);
							if (selectNode.last.getValue()- selectNode.start < activeLength) {
								activeNode = selectNode;
								activeLength = activeLength - (selectNode.last.getValue()- selectNode.start);
								activeEdge = selectNode.getChildren(receivedString[i]).start;
							} else {
								activeLength++;
							}
							break;


						}
					/* Crear nodo interno */
						else {

						/*
						 * selectNode se utiliza para ver si el nodo en que estamos
						 * parados es uno interno o una hoja
						 */

							InternalNode selectNode = activeNode.getChildren(receivedString[activeEdge]);
							int selectStart = selectNode.start;
							selectNode.start = selectNode.start + activeLength;


							InternalNode internal = new InternalNode(selectStart, selectStart + activeLength - 1);

							InternalNode leaf = new InternalNode(i, end.getValue());

							internal.addChildren(selectNode, receivedString[internal.start + activeLength]);
							internal.addChildren(leaf, receivedString[i]);
							internal.finish = -1;
							activeNode.addChildren(internal, receivedString[internal.start]);

							if (suffixLink != null) {
								suffixLink.setLink(internal);
							}
							suffixLink = internal;
							internal.setLink(root);

							if (activeNode != root) {
								activeNode = activeNode.getLink();
							} else {
								activeEdge++;
								activeLength--;
							}
							remaining--;
						}
					}
					catch (NullPointerException n) {

						InternalNode selectedNode = activeNode.getChildren(receivedString[activeEdge]);
							selectedNode.addChildren(new InternalNode(i, end.getValue()), receivedString[i]);
							if (suffixLink != null) {
								suffixLink.setLink(selectedNode);
							}
							suffixLink = selectedNode;
							if (activeNode != root) {
								activeNode = activeNode.getLink();
							} else {
								activeEdge++;
								activeLength--;
							}
							remaining--;
					}

				}
			}
		}
		return root;
	}

	private char nextChar(int i) throws NullPointerException{
		InternalNode selectNode = activeNode.getChildren(receivedString[activeEdge]);

		if (selectNode.last.getValue()-selectNode.start >= activeLength) {
			return receivedString[activeNode.getChildren(receivedString[activeEdge]).start + activeLength];
		}
		if(selectNode.last.getValue()- selectNode.start + 1 == activeLength) {
			if ( selectNode.getChildren(receivedString[i]) != null ) {
				return receivedString[i];
			}
		}
		else {
			activeNode = selectNode;
			activeLength = activeLength - (selectNode.last.getValue()- selectNode.start ) -1;
			activeEdge = activeEdge + (selectNode.last.getValue()- selectNode.start) + 1;
			return nextChar(i);
		}
		throw new NullPointerException();
	}

	public List<Integer> buscar(Node root, String suffix) {
		int i = 0;
		int suffixLength = suffix.length();
		List<Integer> resp = new ArrayList<Integer>();
		Node currentNode = root;
		for (;;) {
			System.out.println("aaaa");
			if (currentNode.children == null)
				break;
			if (i>suffixLength)
				break;
			for (Node auxNode : currentNode.children) {
				if (auxNode == null)
					continue;
				if (receivedString[auxNode.start] == suffix.charAt(i)) {
					int difference = auxNode.last.getValue() - auxNode.start+1;
					if (difference < suffixLength-i) {
						i+= difference;
						currentNode = auxNode;

						break;
					}
					else if (difference > suffixLength-i) {
						resp.add(i);
						i +=difference;
						currentNode = auxNode;
					}

					else {
						resp.add(i);
					}

				}
			}
		}
		return resp;
	}

	private void moveToLink() {

	}
}
