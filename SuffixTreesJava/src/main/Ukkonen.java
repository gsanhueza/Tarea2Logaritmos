package main;

/**
 * Created by belisariops on 12/9/16.
 */
public class Ukkonen {
    private  Last end;
    private int remaining;
    private Node activeNode;
    public int activeEdge;
    public int activeLength;

    private char[] receivedString;

    public Ukkonen (String receivedString) {
        this.receivedString = receivedString.toCharArray();
        end = Last.getInstance();
        remaining = 0;
        activeNode = null;
        activeEdge = -1;
        activeLength  = 0;
    }

    public int getPath(char s, Node n) {
        Node child = activeNode.getChildren(s);
        if (child!= null)
            return child.start;
        return -1;

    }


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
                        activeNode.addChildren(new Node(receivedString[i]),receivedString[i]);
                        remaining--;
                    } else {
					/*Rule 3*/
                        activeEdge = path;
                        activeLength++;
                        break;
                    }
                }
                else {
                    Node activeLeaf = activeNode.getChildren(activeEdge);
					/*Es el siguiente caracter del que estamos parados igual a i?*/
                    char c = receivedString[activeEdge + activeLength];
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
                            activeLength = activeLength - (activeLeaf.getLast() - activeLeaf.start);
                            activeEdge = activeLeaf.getChildren(receivedString[i]).start;
                            break;
                        }

                    }
                    /*Crear nodo interno*/
                    else {
                        int x = activeNode.getChildren(activeEdge).start;
                        Node child1 = new Node(x + activeLength);
                        Node child2 = new Node(i);
                        InternalNode internal = new InternalNode(x, x + activeLength -1);
                        internal.addChildren(child1,child1.start);
                        internal.addChildren(child2,child2.start);
                        if (suffixLink == null) {
                            suffixLink = internal;

                        }
                        else {
                            suffixLink.setLink(internal);
                            suffixLink = internal;
                        }
                        internal.setLink(root);
                        activeNode.addChildren(internal,activeEdge);
                        remaining--;
                        if (activeNode.equals(root)) {
                            activeLength--;
                            activeEdge++;
                        }



                    }


                }
            }

        }
        return root;
    }
}
