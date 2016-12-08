package main;

public class ImplicitSuffixTree {
	
	private String receivedString;

	public ImplicitSuffixTree(String receivedString) {
		this.receivedString = receivedString;
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

}
