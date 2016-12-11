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
		String input = "banana$";
		Ukkonen x = new Ukkonen(input);
        Node t = x.run();
		//Node root = x.run();
        //imprimirSuffixTree(root);
        List<Integer> resp = x.search("ana",t);
        if (resp!=null) {
            System.out.println("El sufijo puede encontrarse en la/s posicion/nes: " );
            for (int integer : resp) {

                System.out.print(integer+ "  ");

            }
        }
        else
            System.out.println("No se encontro el sufijo");

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
			text = new String(Files.readAllBytes(Paths.get("../Texts/english.short")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		text = "banana";

		String processedText = TextPreprocessor.process(text);

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
			int wordLength = word.length();
			logger.log("Palabra buscada: " + word);
			logger.log("Largo de la palabra: " + wordLength);
			initTime = System.currentTimeMillis();
			List<Integer> ocurrences = st.search(word,root);
			endTime = System.currentTimeMillis();
			logger.log("Tiempo de búsqueda: " + (endTime - initTime));
			logger.log("Número de ocurrencias: " + ocurrences.size());
			logger.log();

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
