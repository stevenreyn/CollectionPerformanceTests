package net.slreynolds.ds;

/**
 * A simple class that we can put into containers.
 *
 */
class SomeValue {
    private final int something;
    SomeValue(int i) {
        this.something = i;
    }
    SomeValue(Integer i) {
        this.something = i.intValue();
    }
    int getSomething() {
        return something;
    }
}