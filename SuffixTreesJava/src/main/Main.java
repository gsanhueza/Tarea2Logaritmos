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


		String processedText = TextPreprocessor.readFile("/Texts/english.50MB");

		/**
		 * Construimos el SuffixTree
		 */
		SuffixTree st = new SuffixTree(processedText);

		initTime = System.currentTimeMillis();
		st.createSuffixTree();
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
			int wordLength = word.length();
			logger.log("Palabra buscada: " + word);
			logger.log("Largo de la palabra: " + wordLength);
			initTime = System.currentTimeMillis();
			String ocurrences[] = st.search(word);
			endTime = System.currentTimeMillis();
			logger.log("Tiempo de búsqueda: " + (endTime - initTime));
			logger.log("Número de ocurrencias: " + ocurrences.length);
			logger.log();

		}
		logger.terminate();
	}

}
