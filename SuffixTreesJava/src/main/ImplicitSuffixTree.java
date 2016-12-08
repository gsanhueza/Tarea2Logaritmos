package main;

public class ImplicitSuffixTree {
	static int end;
	private int remaining;
	private Node activeNode;
	private int activeEdge;
	private int activeLength;

	private String receivedString;

	public ImplicitSuffixTree(String receivedString) {
		this.receivedString = receivedString;
		end = -1;
		remaining = 0;
		activeNode = new Node();
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
		Node root = activeNode;
		for (int i = 0; i < receivedString.length(); i++) {
			remaining++;
			end = i;

			while (remaining > 0) {
				/*No existe un camine desde el Nodo en que estamos*/
				if (activeLength == 0) {
					activeNode.addChildren(new Node(i,end));
				}

			}

		}
		return root;
	}

}
