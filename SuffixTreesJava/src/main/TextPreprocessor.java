package main;

public class TextPreprocessor {

	public static String process(String text) {
		return text.toLowerCase()
				.replace("\n", " ")
				.replace(".", " ")
				.replace(":", " ")
				.replace(",", " ")
				.replace(";", " ");
	}

}
