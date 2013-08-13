package net.slreynolds.ds;

public abstract class AbstractTiming<T> {
    
    protected abstract T setUp();

    protected abstract int doWork(T o);
    
    private int warmUp(int N) {
        T o;
        int res = 7;
        for (int i = 0; i < N; i++) {
            o = setUp();
            res |= doWork(o);
        }
        return res;
    }
    
    private long[] timeIt(int N) {
        long[] times = new long[N];
        int res = 7;
        for(int i = 0; i < N; i++) {
            T o = setUp();
            long before = System.nanoTime();
            res |= doWork(o);
            long after = System.nanoTime();
            times[i] = after - before;
        }
        System.out.printf("%d\n", res);
        return times;
    }
    
    public void run(String title) {
        int res = warmUp(20);
        long[] times = timeIt(5);
        System.out.printf("Times for %s (%d)\n", title,res);
        for (int i = 0; i < times.length; i++) {
            System.out.printf("%f ", times[i]/1.0E9);
        }
        System.out.printf("\n");
    }

}
