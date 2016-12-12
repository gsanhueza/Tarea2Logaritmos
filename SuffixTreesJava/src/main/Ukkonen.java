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
			if (suffix.equals(realString.substring(root.start, root.getLast() + 1))) {

				resp.add(realString.length() - (count + 1));
				return resp;
			} else
				return null;

		}
		if (realString.substring(root.start, root.getLast() + 1).equals(suffix)) {
			for (Node n : root.children) {
				List<Integer> aux = (getLeafPath(n, count + root.getLast() - root.start + 1));
				if (aux != null) {
					for (int i : aux)
						resp.add(i);
				}
			}
			return resp;
		}

		if (suffix.contains(realString.substring(root.start, root.getLast() + 1))) {
			for (Node n : root.children) {
				List<Integer> aux = getSuffixes(n, suffix.substring(root.getLast() - root.start + 1),
						count + root.getLast() - root.start + 1);
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
			resp.add(realString.length() - ((count + 1) + n.getLast() - n.start));
			return resp;

		}
		for (Node aux : n.children) {
			List<Integer> integers = getLeafPath(aux, count + n.getLast() - n.start + 1);
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
		Node suffixLink = null;
		activeNode = new InternalNode(-1, -1);
		Node root = activeNode;
		for (int i = 0; i < realString.length(); i++) {
			remaining++;
			end.setValue(i);
			while (remaining > 0) {
				/*
				 * getPath entrega la variable "start" si es que hay match con
				 * el primer caracter de la branch, o -1 si es que no hay
				 */
				if (activeLength == 0) {
					int path = getPath(receivedString[i], activeNode);
					if (path == -1) {
						activeNode.addChildren(new Node(i), receivedString[i]);
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
					 * Es el siguiente caracter del que estamos parados igual a
					 * i?
					 */
					int dif = activeLength;
					if (activeLength == 0) {
						System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$");
					}
					if (activeLeaf.getLast() - activeLeaf.start +1 < activeLength+1) {
						activeNode = activeLeaf;

						activeLength = activeLength -(activeLeaf.getLast() - activeLeaf.start+1);
						continue;//Esto esta malo, da 0 a veces
						/*dif = dif -activeLength;
						if (activeLeaf.getChildren(receivedString[activeEdge+dif]) == null)
							System.out.println("mmmmmmmmmm: " + realString.substring(i,i+11));
						try {
							activeEdge = activeLeaf.getChildren(receivedString[activeEdge + dif]).start;
						}
						catch (NullPointerException e) {
							Node auxNode = new Node(activeEdge+dif);
							activeLeaf.addChildren(auxNode,receivedString[activeEdge + dif]);
							activeEdge = activeLeaf.getChildren(receivedString[activeEdge + dif]).start;
							if (suffixLink!= null) {
								suffixLink.setLink(auxNode);

							}
							suffixLink = activeLeaf;
							remaining--;


						}*/
					}
					char c = receivedString[activeNode.getChildren(receivedString[activeEdge]).start + activeLength];
					if (c == receivedString[i]) {
						/*if (suffixLink != null) {
							suffixLink.setLink(activeNode.getChildren(receivedString[i]));
							suffixLink = activeNode.getChildren(receivedString[i]);
							activeNode.setLink(root);
						}*/
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

						System.out.println("aaaaaaaa: " + internal.start +"  " + internal.getLast());
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
						System.out.println("child1: " + child1.start + "  " + child1.getLast());
						System.out.println("child2: " + child2.start + "  " + child2.getLast());
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
			suffixLink = null;

		}
		return root;
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
					int difference = auxNode.getLast() - auxNode.start+1;
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
