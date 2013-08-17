package net.slreynolds.ds


import scala.Tuple2;
import scala.collection.mutable.OpenHashMap;

object ScalaMutableOpenHashMapTiming extends AbstractTiming[OpenHashMap[Int,SomeValue], OpenHashMap[Int,SomeValue]] {


	
    @Override
    def doWork(map: OpenHashMap[Int,SomeValue]  ): Result[OpenHashMap[Int,SomeValue]] = {
        var lmap : OpenHashMap[Int,SomeValue] = map
        val vals = IntMapSource.valuesToInsert
        val n = IntMapTimingParameters.numToInsert
        for (i <- 0 to n-1)  {
            lmap += (vals(i))
        }
        new Result(lmap,lmap.size)
    }
    
    @Override
    def setUp() : OpenHashMap[Int,SomeValue] = {
        var baseHMap : OpenHashMap[Int,SomeValue] = new OpenHashMap();
        for (i <- 0 to IntMapTimingParameters.initialSize-1)  {
             baseHMap += (new Tuple2(i,new SomeValue(i)))
        }
        baseHMap
    }
    
    def main(args: Array[String]): Unit = {
        run("Int Scala mutable OpenHashMap")
    }

}