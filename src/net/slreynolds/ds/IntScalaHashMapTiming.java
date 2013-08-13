package net.slreynolds.ds;


import scala.Tuple2;
import scala.collection.immutable.HashMap;
import scala.collection.immutable.IntMap;

public class IntScalaHashMapTiming extends AbstractTiming<HashMap, HashMap> {


    @Override
    protected Result<HashMap> doWork(HashMap map) {
        
        final Tuple2[] vals = IntMapTimingParameters.valuesToInsert;
        final int n = IntMapTimingParameters.numToInsert;
        for (int i = 0; i < n; i++) {
            map = map.$plus(vals[i]);
        }
        return new Result(map,map.size());
    }
    
    @Override
    protected HashMap setUp() {
        HashMap baseHMap = new HashMap();
        for (int i = 0; i < IntMapTimingParameters.initialSize; i++) {
            baseHMap = baseHMap.$plus(new Tuple2(i,new SomeValue(i)));
        }
        return baseHMap;
    }
    
    public static void main(String[] args) { 
        new IntScalaHashMapTiming().run("Int Scala HashMap");
    }


}
