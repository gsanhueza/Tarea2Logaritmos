package main;

public class SuffixTree {
	private String text;
	private Node root;

	public SuffixTree(String receivedText) {
		this.text = receivedText;
		this.root = new Node();
	}

	public SuffixTree createSuffixTree() {
		for (int i = 1; i <= text.length() - 1; i++) {
			// Fase i
			for (int j = 1; j <= i + 1; j++) {
				// Extensión j
				System.out.println("Encontrar el final del camino desde raíz de I, correspondiente a S[" + j + "..." + i + "]");
				System.out.println("Nos aseguramos de que S[" + j + ".." + "i+1] está en el árbol");
				System.out.println("Si es necesario, extender ese camino agregando S[i + 1]");
			}
		}
		return this;
	}

	public String[] search(String word) {
		String[] ans = {"a", "b", "c"};
		// TODO Retornar lo que corresponde a búsqueda
		return ans;
	}

}
