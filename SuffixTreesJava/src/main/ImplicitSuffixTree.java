package main;

public class ImplicitSuffixTree {
	static int end;
	private int remaining;
	private Node activeNode;
	public int activeEdge;
	public int activeLength;

	private String receivedString;

	public ImplicitSuffixTree(String receivedString) {
		this.receivedString = receivedString;
		end = -1;
		remaining = 0;
		activeNode = null;
		activeEdge = -1;
		activeLength  = 0;

	}
	
	public ImplicitSuffixTree createImplicitSuffixTree() {
		this.removeSymbol("$");
		this.removeUnlabeledEdges();
		this.removeLessThan2SonsNodes();
		return this;
	}

	/**
	 * Remueve las instancias del símbolo terminal.
	 * @param symbol Símbolo terminal (Ej.: "$")
	 */
	public String removeSymbol(String symbol) {
		String stringParts[] = receivedString.split(symbol);
		StringBuilder finalString = new StringBuilder();
		
		for (String s : stringParts) {
			finalString.append(s);
		}
		return finalString.toString();
		
	}

	public void removeUnlabeledEdges() {
		// TODO Auto-generated method stub
		
	}

	public void removeLessThan2SonsNodes() {
		// TODO Auto-generated method stub
		
	}

	public String getString() {
		return receivedString;
	}

	public Node runUkkonen() {
		Node suffixLink = null;
		activeNode = new Node();
		Node root = activeNode;
		for (int i = 0; i < receivedString.length(); i++) {
			remaining++;
			end++;

			while (remaining > 0) {
				/*getPath entrega la variable "start" si es que hay match con el primer caracter de la branch,
				o -1 si es que no hay*/
				if (activeLength == 0) {
					//int path = activeNode.getPath(receivedString.charAt(i));
					//if (path == -1) {
					//	activeNode.addChildren(new Node(i, end, receivedString.charAt(i)), i);
					//	remaining--;
					//} else {
					/*Rule 3*/
					//	activeEdge = path;
					//	activeLength++;
					//	break;
					//}
				}
				else {
					Node aux = activeNode.getChildren(activeEdge);
					/*Caracter en el que estamos parados*/
					char c = receivedString.charAt(activeNode.getChildren(activeEdge).start + activeLength );
					if (c == receivedString.charAt(i)) {
						if (suffixLink != null) {
							/*Hacer algo*/
						}


					}


				}
			}

		}
		return root;
	}

}
