package advertisingSpread

import scala.io.Source

object readFromFileTest {
  val testNumber = 10                             //> testNumber  : Int = 10
  val filename = "Test_" + testNumber + "_input.txt"
                                                  //> filename  : String = Test_10_input.txt
  val filePath = "E:\\Java\\coursera-workspace\\Challenge\\"
                                                  //> filePath  : String = E:\Java\coursera-workspace\Challenge\
  val currFile = Source
    .fromFile( filePath + filename )              //> currFile  : scala.io.BufferedSource = non-empty iterator
  val currFileLines = Source
    .fromFile( filePath + filename )
    .getLines()                                   //> currFileLines  : Iterator[String] = non-empty iterator
  val testGraph: Vector[ ( Int, Int ) ] = Vector()//> testGraph  : Vector[(Int, Int)] = Vector()
  val stringVector: Vector[ String ] = Vector()   //> stringVector  : Vector[String] = Vector()

  /*? char by char or binary stream ?*/
  //currFile.foreach {
  //print
  //} //*works
  val n = if ( currFileLines.hasNext ) {
    currFileLines.next().toInt
  } else { 0 } //*works                           //> n  : Int = 13
  val stringArray: Array[ String ] = /*Array()*/
    new Array[ String ]( n )                      //> stringArray  : Array[String] = Array(null, null, null, null, null, null, nul
                                                  //| l, null, null, null, null, null, null)
  val pairArray: Array[ ( Int, Int ) ] =
    new Array[ ( Int, Int ) ]( n )                //> pairArray  : Array[(Int, Int)] = Array(null, null, null, null, null, null, n
                                                  //| ull, null, null, null, null, null, null)

  //for (
  //line <- currFileLines
  //) {
  //println( "l>" + line )
  //} //*works

  /*after non-empty iterator reach his tail
  by invoking a method on it
  loop is usless
  .next() couses 'NoSuchElementException'*/

  //while ( currFileLines.hasNext ) {
  for (
    i <- 0 until n
  ) {
    //*println( "range>" + currFileLines.next() )
    /*println( "array>" + currFileLines
      .next()
      .toString()
      .split( " " ) )*/ //*works

    /*stringVector :+*/ /*stringArray( i ) = currFileLines
      .next()
      .toString()*/ //*works

    pairArray( i ) = currFileLines
      .next()
      .toString()
      .split( " " ) match {
        case x if x.size == 1 => ( -1, x.head.toInt )
        case y if y.size == 2 => ( y.head.toInt, y( 1 ).toInt )
        case _                => ( -1, -1 )
      } //*works

  } //*works

  println( "stringVector> " + stringVector )      //> stringVector> Vector()
  println( "stringArray> " + stringArray.mkString( "," ) )
                                                  //> stringArray> null,null,null,null,null,null,null,null,null,null,null,null,nu
                                                  //| ll
  /*math.Ordering*/
  pairArray.sorted                                //> res0: Array[(Int, Int)] = Array((0,3), (1,6), (1,14), (2,5), (3,4), (4,9), 
                                                  //| (6,8), (7,2), (8,7), (9,15), (14,10), (15,1), (15,13))
  val unsortedVec = pairArray.toVector            //> unsortedVec  : Vector[(Int, Int)] = Vector((6,8), (15,1), (1,14), (0,3), (1
                                                  //| 5,13), (9,15), (2,5), (14,10), (4,9), (7,2), (8,7), (3,4), (1,6))
  
  unsortedVec.sorted                              //> res1: scala.collection.immutable.Vector[(Int, Int)] = Vector((0,3), (1,6), 
                                                  //| (1,14), (2,5), (3,4), (4,9), (6,8), (7,2), (8,7), (9,15), (14,10), (15,1), 
                                                  //| (15,13))
  println( "pairArray> " + pairArray.mkString( "," ) )
                                                  //> pairArray> (6,8),(15,1),(1,14),(0,3),(15,13),(9,15),(2,5),(14,10),(4,9),(7,
                                                  //| 2),(8,7),(3,4),(1,6)
  Vector( 1, 2 ) ++ Vector( 3, 4 )                //> res2: scala.collection.immutable.Vector[Int] = Vector(1, 2, 3, 4)
  1 +: 2 +: Vector( 3, 4 )                        //> res3: scala.collection.immutable.Vector[Int] = Vector(1, 2, 3, 4)
  Vector( 1, 2 ) :+ 3 :+ 4                        //> res4: scala.collection.immutable.Vector[Int] = Vector(1, 2, 3, 4)
  /*constractor object not same as for Arrays
  no Instance Constructors
  new Vector[ (Int, Int) ]( n )*/
  val positiveArray = Array( 1, -1, 3, -1, 5, -1, 7 ) filterNot ( _ == -1 )
                                                  //> positiveArray  : Array[Int] = Array(1, 3, 5, 7)

  positiveArray.indexOf( 4 ) match {
    case x if x > 0 => positiveArray( x ) = 9
    //*case _          => "not found"
    case x          => x
  }  //*work                                      //> res5: AnyVal = -1
  positiveArray                                   //> res6: Array[Int] = Array(1, 3, 5, 7)
  positiveArray.forall(_ != 3)                    //> res7: Boolean = false
  positiveArray.forall(_ != -1)                   //> res8: Boolean = true
  
  val (nodesSeq, leafsSeq) = pairArray.unzip      //> nodesSeq  : Array[Int] = Array(6, 15, 1, 0, 15, 9, 2, 14, 4, 7, 8, 3, 1)
                                                  //| leafsSeq  : Array[Int] = Array(8, 1, 14, 3, 13, 15, 5, 10, 9, 2, 7, 4, 6)
  nodesSeq.toSet                                  //> res9: scala.collection.immutable.Set[Int] = Set(0, 14, 1, 6, 9, 2, 7, 3, 8,
                                                  //|  4, 15)
}