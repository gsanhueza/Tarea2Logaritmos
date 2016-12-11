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
		//Node root = x.run();
        //imprimirSuffixTree(root);
        List<Integer> resp = x.search("ana");
        if (resp!=null) {
            System.out.println("El sufijo puede encontrarse en la/s posicion/nes: " );
            for (int integer : resp) {

                System.out.print(integer+ "  ");

            }
        }
        else
            System.out.println("No se encontro el sufijo");

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
