package main;

public class Last {
    private static Last ourInstance = new Last();
    public int value;

    public static Last getInstance() {
        return ourInstance;
    }

    private Last() {
        value = -1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int i) {
        value = i;
    }

    public void increment() {
        value++;
    }
}
