package net.slreynolds.ds


import scala.Tuple2;
import scala.collection.mutable.OpenHashMap;

object ScalaMutableOpenHashMapTiming extends AbstractTiming[OpenHashMap[Int,SomeValue], OpenHashMap[Int,SomeValue]] {


	
   
    def doInsertWork(map: OpenHashMap[Int,SomeValue]  ): Result[OpenHashMap[Int,SomeValue]] = {
        var lmap : OpenHashMap[Int,SomeValue] = map
        val vals = IntMapSource.valuesToInsert
        val n = IntMapSource.numToInsert
        for (i <- 0 to n-1)  {
            lmap += (vals(i))
        }
        new Result(lmap,lmap.size)
    }
    
    @Override
    def doWork(map: OpenHashMap[Int,SomeValue]  ): Result[OpenHashMap[Int,SomeValue]] = {
        var lmap : OpenHashMap[Int,SomeValue] = map
        val keys = IntMapSource.keysToLookUp
        val n = keys.length
        var res : Option[SomeValue] = None
        for (i <- 0 to n-1)  {
            res = lmap.get(keys(i))
        }
        val something = res match  {
          case None => 1
          case Some(sValue) => sValue.getSomething()
        }
        new Result(lmap,something)
    }
    
    @Override
    def setUp() : OpenHashMap[Int,SomeValue] = {
        var baseHMap : OpenHashMap[Int,SomeValue] = new OpenHashMap();
        for (i <- 0 to IntMapSource.initialSize-1)  {
             baseHMap += (new Tuple2(i,new SomeValue(i)))
        }
        baseHMap
    }
    
    def main(args: Array[String]): Unit = {
        run("Int Scala mutable OpenHashMap")
    }

}