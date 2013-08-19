package net.slreynolds.ds;

/**
 * Base class for timing tests.
 *
 * @param <S> Type parameter of Result that has memory reference accumulated during
 * doWork
 * @param <T> Type of object returned from setUp and passed as an argument to doWork
 */
public abstract class AbstractTiming<S,T> {
    
    protected abstract T setUp();

    protected abstract Result<S> doWork(T o);
    
    private int warmUp(int N) {
        T o;
        int computation = 7;
        for (int i = 0; i < N; i++) {
            o = setUp();
            Result<S> res = doWork(o); 
            computation |= res.getIntParam();
        }
        return computation;
    }
    
    private void sleep() {
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// don't care
		}
    }
    
    private long getMemoryUsed() {
        Runtime runt = Runtime.getRuntime();
        System.gc();
        System.gc();
        System.gc();
        sleep();
        System.gc();
        System.gc();
        sleep();
        return runt.totalMemory();
    }
    
    private long measureSpace() {
        
        int computation = 7;
        T o = setUp();
        long before = getMemoryUsed();
        
        Result<S> res = doWork(o);
        long after = getMemoryUsed();

        computation |= res.getIntParam();
            
        System.out.printf("%d\n", computation);
        return Math.max(0,(after - before)); 
    }
    
    private long[] timeIt(int N) {
        final long[] times = new long[N];
        int computation = 7;
        long before = 0;
        long after = 0;
        T o = null;
        Result<S> res = null;
        for(int i = 0; i < N; i++) {
            o = setUp();
            before = System.nanoTime();
            res = doWork(o);
            after = System.nanoTime();
            computation |= res.getIntParam();
            times[i] = after - before;
        }
        System.out.printf("%d\n", computation);
        return times;
    }
    
    public void run(String title) {
        int res = warmUp(20);
        System.out.printf("res (%d)\n",res);
        System.out.printf("%s uses %d, %d, %d bytes\n",title, measureSpace(), measureSpace(), measureSpace());
        long[] times = timeIt(5);
        System.out.printf("Times (seconds)\n");
        for (int i = 0; i < times.length; i++) {
            System.out.printf("%f ", times[i]/1.0E9);
        }
        System.out.printf("\n");
    }
}
