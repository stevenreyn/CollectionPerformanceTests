package net.slreynolds.ds


import scala.Tuple2;
import scala.collection.mutable.HashMap;

object ScalaMutableHashMapTiming extends AbstractTiming[HashMap[Int,SomeValue], HashMap[Int,SomeValue]] {

	private val valuesToInsert: Array[Tuple2[Int,SomeValue]] = makeValues(IntMapTimingParameters.numToInsert)
	
	private def makeValues(n: Int) : Array[Tuple2[Int,SomeValue]] = {
	  val valsToIns:  Array[Tuple2[Int,SomeValue]] = new Array(n)
	  for (i <- 0 to n-1) {
            val j = i + IntMapTimingParameters.initialSize;
            valsToIns(i) = new Tuple2(j,new SomeValue(j));
        }
	  valsToIns
	}
	
    @Override
    def doWork(map: HashMap[Int,SomeValue]  ): Result[HashMap[Int,SomeValue]] = {
        var lmap : HashMap[Int,SomeValue] = map
        val vals = valuesToInsert
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
        run("Int Scala mutable HashMap")
    }

}