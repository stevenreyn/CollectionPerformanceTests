package net.slreynolds.ds;


import scala.Tuple2;
import scala.collection.immutable.HashMap;
import scala.collection.immutable.IntMap;

public class ScalaIntMapTiming extends AbstractTiming<IntMap,IntMap> {

    @Override
    protected Result<IntMap> doWork(IntMap map) {
        final Tuple2[] vals = IntMapTimingParameters.valuesToInsert;
        final int n = IntMapTimingParameters.numToInsert;
        for (int i = 0; i < n; i++) {
            map = map.$plus(vals[i]);
        }
        return new Result(map,map.size());
    }
    
    @Override
    protected IntMap setUp() {
        IntMap baseIMap = IntMap.singleton(1,SomeValue.class); // TODO what is first param?
        for (int i = 0; i < IntMapTimingParameters.initialSize; i++) {
            baseIMap = baseIMap.$plus(new Tuple2(i,new SomeValue(i)));
        }
        return baseIMap;
    }
    
    public static void main(String[] args) { 
        new ScalaIntMapTiming().run("Scala IntMap");
    }


}
