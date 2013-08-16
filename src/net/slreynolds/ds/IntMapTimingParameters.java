package net.slreynolds.ds;

import scala.Tuple2;

public class IntMapTimingParameters {

    static final int initialSize = 100000;
    static final int numToInsert = 100000;
    
    static final Tuple2<Integer,SomeValue>[] valuesToInsert = new Tuple2[numToInsert];
    static {
        for (int i = 0; i < IntMapTimingParameters.initialSize; i++) {
            int j = i + IntMapTimingParameters.initialSize;
            valuesToInsert[i] = new Tuple2(j,new SomeValue(j));
        }
    }
}
