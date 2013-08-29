package net.slreynolds.ds


import scala.Tuple2;
import scala.collection.mutable.HashMap;

object ScalaMutableHashMapTiming extends AbstractTiming[HashMap[Int,SomeValue], HashMap[Int,SomeValue]] {

    def doInsertWork(map: HashMap[Int,SomeValue]  ): Result[HashMap[Int,SomeValue]] = {
        var lmap : HashMap[Int,SomeValue] = map
        val vals = IntMapSource.valuesToInsert
        val n = IntMapSource.numToInsert
        for (i <- 0 to n-1)  {
            lmap += (vals(i))
        }
        new Result(lmap,lmap.size)
    }
    
    @Override
    def doWork(map: HashMap[Int,SomeValue]  ): Result[HashMap[Int,SomeValue]] = {
        var lmap : HashMap[Int,SomeValue] = map
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
    def setUp() : HashMap[Int,SomeValue] = {
        var baseHMap : HashMap[Int,SomeValue] = new HashMap();
        for (i <- 0 to IntMapSource.initialSize-1)  {
             baseHMap += (new Tuple2(i,new SomeValue(i)))
        }
        baseHMap
    }
    
    def main(args: Array[String]): Unit = {
        run("Int Scala mutable HashMap")
    }

}