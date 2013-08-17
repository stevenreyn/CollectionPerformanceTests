package net.slreynolds.ds

object IntMapSource {
  
  	val valuesToInsert: Array[Tuple2[Int,SomeValue]] = makeValues(IntMapTimingParameters.numToInsert)
	
	private def makeValues(n: Int) : Array[Tuple2[Int,SomeValue]] = {
	  val valsToIns:  Array[Tuple2[Int,SomeValue]] = new Array(n)
	  for (i <- 0 to n-1) {
            val j = i + IntMapTimingParameters.initialSize;
            valsToIns(i) = new Tuple2(j,new SomeValue(j));
        }
	  valsToIns
	}

}