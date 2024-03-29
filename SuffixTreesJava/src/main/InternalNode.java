package main;

public class InternalNode extends Node {
    private int lastNum = 0;

    public InternalNode(int start, int lastNum) {
        super();
        this.start = start;
        this.lastNum = lastNum;
        this.isLeaf = false;
    }

    public int getLast() {
        return lastNum;
    }
}
