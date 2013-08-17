package net.slreynolds.ds


import scala.Tuple2;
import scala.collection.immutable.IntMap;

object ScalaIntMapTiming extends AbstractTiming[IntMap[SomeValue], IntMap[SomeValue]] {


    @Override
    def doWork(map: IntMap[SomeValue]  ): Result[IntMap[SomeValue]] = {
        var lmap : IntMap[SomeValue] = map
        val vals = IntMapSource.valuesToInsert
        val n = IntMapSource.numToInsert
        for (i <- 0 to n-1)  {
            lmap += (vals(i))
        }
        new Result(lmap,lmap.size)
    }
    
    @Override
    def setUp() : IntMap[SomeValue] = {
        var baseHMap : IntMap[SomeValue] = IntMap.empty
        for (i <- 0 to IntMapSource.initialSize-1)  {
             baseHMap += (new Tuple2(i,new SomeValue(i)))
        }
        baseHMap
    }
    
    def main(args: Array[String]): Unit = {
        run("Scala IntMap")
    }

}