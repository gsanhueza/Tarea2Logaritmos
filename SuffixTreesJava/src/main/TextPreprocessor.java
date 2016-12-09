package main;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextPreprocessor {

	public TextPreprocessor() throws URISyntaxException {
	}

	public static String process(String text) {
		/*
		return text.toLowerCase()
				.replace("\n", " ")
				.replace(".", " ")
				.replace(":", " ")
				.replace(",", " ")
				.replace(";", " ");
				*/
		return text.replaceAll("[^a-zA-Z ]", "").toLowerCase();
	}

	public static String readFile(String readFile) {
		String everything = "";
		BufferedReader br = null;
		Path path = null;
		try {
			path = Paths.get(TextPreprocessor.class.getResource(".").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		try {
			br = new BufferedReader(new FileReader(path.getParent().getParent().getParent().getParent() + readFile));
	    StringBuilder sb = new StringBuilder();
	    String line = br.readLine();

	    while (line != null) {
	        sb.append(process(line));
	        line = br.readLine();
	  	}
	    everything = sb.toString();
		}
		catch(Exception e) {
	  	try{
				br.close();
			}
				catch (Exception ex) {

				}
		}
		return everything;
	}

}
