package main;

import java.util.ArrayList;
import java.util.List;

public class SuffixTree {
	private Node root;

	public SuffixTree() {
		this.root = new Node();
	}

	public SuffixTree createSuffixTree(String text) {
		for (int i = 0; i < text.length(); i++) {
			// Phase i
			for (int j = 0; j < i + 1; j++) {
				// Extension j
				
				// Encontrar el final del camino desde raíz de I, correspondiente a S[j...i]
				Node foundChild = null;
				for (Node child : root) {
					if (text.substring(j, i + 1).contains(child.getData())) {
						foundChild = child;
						break;
					}
				}
				
				if (foundChild == null) {
					// El substring no estaba en el suffix tree
					System.out.println("Adding to Tree: " + text.substring(i, i + 1));
					root.addChildren(new Node().setData(text.substring(i, i + 1)));
				} else {
					// Uno de los hijos contiene parte del substring buscado (ej: busco "ba" y hay un hijo con "b")
					System.out.print("Modifying child of Tree: " + foundChild.getData() + " --> ");
					foundChild.setData(foundChild.getData() + text.substring(i, i + 1));
					System.out.println(foundChild.getData());
				}

			}
			System.out.println("");
		}
		return this;
	}

	public List<String> search(String word) {
		ArrayList<String> ans = new ArrayList<String>();
		
		for (Node child : root) {
			if (child.getData().contains(word)) {
				ans.add(child.getData());
			}
		}
		// TODO Retornar lo que corresponde a búsqueda
		return ans;
	}

}
