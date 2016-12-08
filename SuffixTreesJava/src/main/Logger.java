package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Logger {
	private PrintWriter output;

	public Logger(String fileName) throws FileNotFoundException {
		this.output= new PrintWriter(fileName);
	}

	public void log(String string) {
		output.println(string);
		
	}
	
	public void terminate() {
		output.close();
	}

	public void log() {
		this.log("");
	}

}
