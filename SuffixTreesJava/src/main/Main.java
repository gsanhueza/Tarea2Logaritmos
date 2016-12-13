package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		/**
		 * TEST 1 text = banana search = ana
		 */
		String input = "101001100$";
		Ukkonen x = new Ukkonen(input);
		Node t = x.run();
		imprimirSuffixTree(t);

		/*List<Integer> resp = x.buscar(t,"00");
		if (resp != null) {
			System.out.println("El sufijo puede encontrarse en la/s posicion/nes: ");
			for (int integer : resp) {
				System.out.print(integer + "  ");
			}
		} else {
			System.out.println("No se encontro el sufijo");
		}*/

		/**
		 * TEST 2 text = (english.short) search = book
		 */
		long initTime;
		long endTime;

		Logger logger = null;

		try {
			logger = new Logger("LogFile.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		/**
		 * Recibimos un texto para procesarlo
		 */
		String text = null;

		try {
			text = new String(Files.readAllBytes(Paths.get("../Texts/english.N15")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String processedText = TextPreprocessor.process(text);

		double textLength = Math.log(processedText.length()) / Math.log(2.0);
		System.out.println("El texto procesado es de aprox. N = 2^" + Math.round(textLength) + " caracteres");

		/**
		 * Construimos el SuffixTree
		 */
		Ukkonen st = new Ukkonen(processedText);

		initTime = System.currentTimeMillis();
		Node root = st.run();
		endTime = System.currentTimeMillis();

		logger.log("Tiempo de construcción de SuffixTree = " + (endTime - initTime));
		logger.log();

		/**
		 * Elegimos N/10 palabras del texto al azar para buscarlas
		 */
		List<String> toSearch = new ArrayList<String>();
		String splittedText[] = processedText.split(" ");

		for (int i = 0; i < splittedText.length / 10; i++) {
			toSearch.add(splittedText[(int) (Math.random() * splittedText.length)]);
		}

		/**
		 * Buscamos las palabras en el Suffix Tree
		 */
		for (String word : toSearch) {
			if (word.length() != 0) {

				int wordLength = word.length();
				logger.log("Palabra buscada: " + word);
				logger.log("Largo de la palabra: " + wordLength);
				initTime = System.nanoTime();
				List<Integer> ocurrences = st.search(word, root);
				endTime = System.nanoTime();
				logger.log("Tiempo de búsqueda: " + (endTime - initTime));
				if (ocurrences != null)
					logger.log("Número de ocurrencias: " + ocurrences.size());
				else
					logger.log("Número de ocurrencias: 0");
				logger.log();
			}
		}
		logger.terminate();

	}

	

	public static void imprimirSuffixTree(Node root) {
		int i = 0;
		int j = -1;
		for (Node n : root.children) {
			j++;
			if (n != null) {
				i++;
				System.out.println("i: " + i);
				System.out.println("suffix: " + n.start + "  finish: " + n.getLast());
				System.out.println("j: " + j);
				System.out.println("\n");
				imprimirSuffixTree(n);

			}
		}
		System.out.println("Cantidad de hijos: " + i);

	}


}
