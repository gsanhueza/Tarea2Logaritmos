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
		Node root = x.run();
		System.out.println(root.getChildren('b').start);
	}

}
