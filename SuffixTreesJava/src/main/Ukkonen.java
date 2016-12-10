package main;

/**
 * Created by belisariops on 12/9/16.
 */
public class Ukkonen {
    static int end;
    private int remaining;
    private Node activeNode;
    public int activeEdge;
    public int activeLength;

    private char[] receivedString;

    public Ukkonen (String receivedString) {
        this.receivedString = receivedString.toCharArray();
        end = -1;
        remaining = 0;
        activeNode = null;
        activeEdge = -1;
        activeLength  = 0;
    }

    public int getPath(char s, Node n,int var) {

        for (Node node : n.children) {
            if (s == (receivedString[n.getChildren(activeEdge).start + var]))
                return n.getChildren(activeEdge).start;

        }


        return -1;
    }


    public Node run() {
        Node suffixLink = null;
        activeNode = new Node();
        Node root = activeNode;
        for (int i = 0; i < receivedString.length; i++) {
            remaining++;
            end++;

            while (remaining > 0) {
				/*getPath entrega la variable "start" si es que hay match con el primer caracter de la branch,
				o -1 si es que no hay*/
                if (activeLength == 0) {
                    int path = getPath(receivedString[i],activeNode,0);
                    if (path == -1) {
                        activeNode.addChildren(new Node(i),i);
                        remaining--;
                    } else {
					/*Rule 3*/
                        activeEdge = path;
                        activeLength++;
                        break;
                    }
                }
                else {
                    Node aux = activeNode.getChildren(activeEdge);
					/*Caracter en el que estamos parados*/
                    char c = receivedString[activeNode.getChildren(activeEdge).start + activeLength];
                    if (c == receivedString[i]) {
                        if (suffixLink != null) {
							suffixLink.setLink(activeNode.getChildren(receivedString[i]));
                        }

                        if (aux.getLast() - aux.start >= activeLength) {
                            activeLength++;
                        }
                        else {
                            activeNode = aux;
                            activeLength = activeLength - (aux.getLast() - aux.start);
                            activeEdge = aux.getChildren(receivedString[i]).start;
                        }

                    }

                    else {

                    }


                }
            }

        }
        return root;
    }
}
