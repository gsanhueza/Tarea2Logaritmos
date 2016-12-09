package main;

public class TextPreprocessor {

	public static String process(String text) {
		return text.replaceAll("[^a-zA-Z ]", "").toLowerCase();
	}

}
