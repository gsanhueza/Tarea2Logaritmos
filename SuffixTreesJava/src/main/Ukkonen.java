package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by belisariops on 12/9/16.
 */
public class Ukkonen {
    private  Last end;
    private int remaining;
    private Node activeNode;
    public int activeEdge;
    public int activeLength;
    private List<String> suffixes;
    private String realString;

    private char[] receivedString;

    public Ukkonen (String receivedString) {
        realString = receivedString;
        this.receivedString = receivedString.toCharArray();
        end = Last.getInstance();
        remaining = 0;
        activeNode = null;
        activeEdge = -1;
        activeLength  = 0;
        suffixes = new ArrayList<String>();
    }

    public int getPath(char s, Node n) {
        Node child = activeNode.getChildren(s);
        if (child!= null)
            return child.start;
        return -1;

    }

    /*Retorna todas las posiciones donde comienza el sufijo*/
    public List<Integer> search(String suffix) {
        Node root = run();
        char [] charArray = suffix.toCharArray();
        root = root.children[charArray[0]];


        return getSuffixes(root,suffix,0);
    }

    private List<Integer> getSuffixes(Node root, String suffix,int count) {

        char [] charArray = suffix.toCharArray();
        int max = charArray.length;
        List<Integer> resp = new ArrayList<Integer>();
        if (root == null) {
            return null;
        }

        if (root.isLeaf() ) {
            if (suffix.equals(realString.substring(root.start,root.getLast()+1))) {

                resp.add(realString.length() -(count+1) );
                return resp;
            }
            else
                return null;

        }


        if (realString.substring(root.start,root.getLast()+1).equals(suffix)) {

            List<Integer> integer;
            for (Node n : root.children) {
                List<Integer> aux = (getLeafPath(n, count +root.getLast()- root.start +1));
                if (aux != null) {
                    for (int i : aux)
                        resp.add(i);
                }
            }

            return resp;

        }



        if (suffix.contains(realString.substring(root.start,root.getLast()+1))){

                for (Node n : root.children) {
                    List<Integer> aux = getSuffixes(n,suffix.substring(root.getLast()- root.start +1) ,count +root.getLast()- root.start +1);
                    if ( aux != null){
                        for (int integer : aux){
                            resp.add(integer);
                        }
                    }

                }
        }

        else
            return null;

        return resp;




    }

    private List<Integer> getLeafPath(Node n,int count) {
        List<Integer> resp = new ArrayList<Integer>();
        if (n == null)
            return null;

        if (n.isLeaf()) {
            resp.add(realString.length() -( (count + 1)  +n.getLast()- n.start) );
            return resp;

        }
        for (Node aux : n.children) {
            List<Integer> integers = getLeafPath(aux, count + n.getLast() - n.start + 1);
            if (integers != null) {
                for (int i : integers)
                    resp.add(i);
            }
            return resp;

        }
        return resp;
    }


    /*La creacion del arbol es O(n)*/
    public Node run() {
        Node suffixLink = null;
        activeNode = new InternalNode(-1,-1);
        Node root = activeNode;
        for (int i = 0; i < receivedString.length; i++) {
            remaining++;
            end.increment();;

            while (remaining > 0) {
				/*getPath entrega la variable "start" si es que hay match con el primer caracter de la branch,
				o -1 si es que no hay*/
                if (activeLength == 0) {
                    int path = getPath(receivedString[i],activeNode);
                    if (path == -1) {
                        activeNode.addChildren(new Node(i),receivedString[i]);
                        remaining--;
                    } else {
					/*Rule 3*/
                        activeEdge = path;
                        activeLength++;
                        break;
                    }
                }
                else {
                    /*Nos paramos en el caracter que tenemos guardado (active edge)*/
                    Node activeLeaf = activeNode.getChildren(receivedString[activeEdge]);
					/*Es el siguiente caracter del que estamos parados igual a i?*/
                    char c = receivedString[activeNode.getChildren(receivedString[activeEdge]).start + activeLength];
                    if (c == receivedString[i]) {
                        if (suffixLink != null) {
							suffixLink.setLink(activeNode.getChildren(receivedString[i]));
                            suffixLink = activeNode.getChildren(receivedString[i]);
                            activeNode.setLink(root);
                        }

                        if (activeLeaf.getLast() - activeLeaf.start >= activeLength) {
                            activeLength++;
                            break;
                        }
                        else {
                            activeNode = activeLeaf;
                            activeLength = 1;
                            activeEdge = activeLeaf.getChildren(receivedString[i]).start;
                            break;
                        }

                    }
                    /*Crear nodo interno*/
                    else {
                        /*auxNode se utiliza para ver si el nodo en que estamos parados es uno interno o una hoja*/
                        Node auxNode = activeNode.getChildren(receivedString[activeEdge]);
                        int x = activeNode.getChildren(receivedString[activeEdge]).start;
                        InternalNode internal = new InternalNode(x, x + activeLength - 1);
                        internal.setLink(root);
                        Node child1;
                        Node child2;
                        if (auxNode.isLeaf()) {
                            child1 = new Node(x + activeLength);
                            child2 = new Node(i);


                        }
                        else {
                            child1 = new InternalNode(x + activeLength, auxNode.getLast());
                            child1.children = auxNode.children;
                            child2 = new Node(i);

                        }

                        internal.addChildren(child1, receivedString[child1.start]);
                        internal.addChildren(child2, receivedString[child2.start]);
                        activeNode.addChildren(internal, receivedString[activeEdge]);
                        if (suffixLink == null) {
                            suffixLink = internal;

                        }
                        else {
                            suffixLink.setLink(internal);
                            suffixLink = internal;
                        }

                        remaining--;
                        if (activeNode.equals(root)) {
                            activeLength--;
                            activeEdge++;
                        }

                        else {
                            activeNode = activeNode.getLink();
                        }



                    }


                }
            }
            /*Como salimos del caracter i, olvidamos el suffixLink guardado*/
            suffixLink = null;

        }
        return root;
    }
}
