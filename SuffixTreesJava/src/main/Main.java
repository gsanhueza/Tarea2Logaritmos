package main;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		/**
		 * Recibimos un texto para procesarlo
		 */
		
		String processedText = TextPreprocessor.process("");
		
		/**
		 * Construimos el SuffixTree
		 */
		SuffixTree st = new SuffixTree(processedText);
		
		// Init time
		st.createSuffixTree();
		// End time
		
		/**
		 * Elegimos N/10 palabras del texto al azar para buscarlas
		 */
		List<String> toSearch = new ArrayList<String>();
		String splittedText[] = processedText.split(" ");
		
		for (int i = 0; i < splittedText.length / 10; i++) {
			toSearch.add(splittedText[(int) Math.random() * splittedText.length]);
		}
		
		/**
		 * Buscamos las palabras en el Suffix Tree
		 */
		for (String word : toSearch) {
			int wordLength = word.length();
			// Init Time
			String ocurrences[] = st.search(word);
			// End Time
			
			// Escribimos en un texto el largo de la palabra, la cantidad de ocurrencias y el tiempo que tardó. 
		}
	}

}
