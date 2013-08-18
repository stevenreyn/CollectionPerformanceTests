package net.slreynolds.ds;

/**
 * A simple class that we can put into containers.
 *
 */
public class SomeValue {
    private final int something;
    public SomeValue(int i) {
        this.something = i;
    }
    public SomeValue(Integer i) {
        this.something = i.intValue();
    }
    
    public int getSomething() {
        return something;
    }
}