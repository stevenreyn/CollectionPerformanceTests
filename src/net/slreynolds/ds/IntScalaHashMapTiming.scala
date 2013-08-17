package net.slreynolds.ds


import scala.Tuple2;
import scala.collection.immutable.HashMap;

object IntScalaHashMapTiming extends AbstractTiming[HashMap[Int,SomeValue], HashMap[Int,SomeValue]] {


	
    @Override
    def doWork(map: HashMap[Int,SomeValue]  ): Result[HashMap[Int,SomeValue]] = {
        var lmap : HashMap[Int,SomeValue] = map
        val vals = IntMapSource.valuesToInsert
        val n = IntMapTimingParameters.numToInsert
        for (i <- 0 to n-1)  {
            lmap += (vals(i))
        }
        new Result(lmap,lmap.size)
    }
    
    @Override
    def setUp() : HashMap[Int,SomeValue] = {
        var baseHMap : HashMap[Int,SomeValue] = new HashMap();
        for (i <- 0 to IntMapTimingParameters.initialSize-1)  {
             baseHMap += (new Tuple2(i,new SomeValue(i)))
        }
        baseHMap
    }
    
    def main(args: Array[String]): Unit = {
        run("Int Scala immutable HashMap")
    }

}