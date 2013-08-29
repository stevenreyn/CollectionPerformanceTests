package net.slreynolds.ds

import scala.util.Random

object IntMapSource {
  
    def initialSize:Int = 100000;
    def numToInsert:Int = 100000;
    
  	val valuesToInsert: Array[Tuple2[Int,SomeValue]] = makeValues(numToInsert)
	
	private def makeValues(n: Int) : Array[Tuple2[Int,SomeValue]] = {
  	  val rand = new Random(71234)
	  val valsToIns:  Array[Tuple2[Int,SomeValue]] = new Array(n)
	  for (i <- 0 to n-1) {
            val key = rand.nextInt(20*(n+initialSize))
            valsToIns(i) = new Tuple2(key,new SomeValue(key));
        }
	  valsToIns
	}

}