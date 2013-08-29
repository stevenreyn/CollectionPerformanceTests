package net.slreynolds.ds;


import scala.Tuple2;
import java.util.HashMap;

public class JavaHashMapTiming extends AbstractTiming<HashMap, HashMap> {

	
	@SuppressWarnings("rawtypes")
	final Tuple2[] _valuesToInsert = IntMapSource.valuesToInsert();
	
	final int[] _keysToLookup = IntMapSource.keysToLookUp();
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected Result<HashMap> doInsertWork(HashMap map) {
        
        final Tuple2[] vals = _valuesToInsert;
        final int n = IntMapSource.numToInsert();
        for (int i = 0; i < n; i++) {
            map.put(vals[i]._1,vals[i]._2);
        }
        return new Result(map,map.size());
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    protected Result<HashMap> doWork(HashMap map) {
        
        final int[] keys = _keysToLookup;
        final int n = IntMapSource.numToInsert();
        Object res = null;
        for (int i = 0; i < n; i++) {
            res = map.get(keys[i]);
        }
        int something = res==null ? 1 : ((SomeValue)res).getSomething();
        return new Result(map,something);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    protected HashMap setUp() {
        HashMap baseHMap = new HashMap();
        for (int i = 0; i < IntMapSource.initialSize(); i++) {
            baseHMap.put(i,new SomeValue(i));
        }
        return baseHMap;
    }
    
    public static void main(String[] args) { 
        new JavaHashMapTiming().run("Int Java HashMap");
    }


}
