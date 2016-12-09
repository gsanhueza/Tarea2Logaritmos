package main;

import java.util.ArrayList;
import java.util.List;

public class SuffixTree {
	private Node root;

	public SuffixTree() {
		this.root = new Node();
	}

	public SuffixTree createSuffixTree(String text) {
		for (int i = 1; i <= text.length() - 1; i++) {
			// Phase i
			for (int j = 1; j <= i + 1; j++) {
				// Extension j
				
				// Encontrar el final del camino desde raíz de I, correspondiente a S[j...i]
				Node foundChild = null;
				for (Node child : root) {
					if (text.substring(j, i + 1).contains(child.getData())) {
						foundChild = child;
						break;
					}
				}
				
				// Si es necesario, extender ese camino agregando S[i + 1]
				if (foundChild == null || !foundChild.getData().contains(text.substring(j, i + 2))) {
					root.addChildren(new Node().setData(text.substring(i + 1, i + 2)));
				}
			}
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
