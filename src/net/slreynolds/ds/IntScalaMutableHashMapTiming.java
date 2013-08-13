package net.slreynolds.ds;


import scala.Tuple2;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Map;


public class IntScalaMutableHashMapTiming extends AbstractTiming<HashMap, HashMap> {


    @Override
    protected Result<HashMap> doWork(HashMap map) {
        Map lmap = map;
        final Tuple2[] vals = IntMapTimingParameters.valuesToInsert;
        final int n = IntMapTimingParameters.numToInsert;
        for (int i = 0; i < n; i++) {
            lmap = lmap.$plus(vals[i]);
        }
        return new Result(lmap,lmap.size());
    }
    
    @Override
    protected HashMap setUp() {
        HashMap baseHMap = new HashMap();
        for (int i = 0; i < IntMapTimingParameters.initialSize; i++) {
            baseHMap = (HashMap)baseHMap.$plus(new Tuple2(i,new SomeValue(i)));
        }
        return baseHMap;
    }
    
    public static void main(String[] args) { 
        new IntScalaMutableHashMapTiming().run("Int Scala mutable HashMap");
    }


}
