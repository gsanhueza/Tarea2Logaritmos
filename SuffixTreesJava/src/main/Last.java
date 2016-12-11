package main;

/**
 * Created by belisariops on 12/10/16.
 */
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

    public void increment() {
        value++;
    }
}
