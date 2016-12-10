package main;

/**
 * Created by belisariops on 12/10/16.
 */
public class InternalNode extends Node {
    private int lastNum = 0;

    public InternalNode(int start, int lastNum) {
        super();
        this.start = start;
        this.lastNum = lastNum;
    }

    public int getLast() {
        return lastNum;
    }
}
