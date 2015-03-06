package advertisingSpread

import scala.io.Source

object readFromFileTest {
  val testNumber = 1                              //> testNumber  : Int = 1
  val filename = "Test_" + testNumber + "_input.txt"
                                                  //> filename  : String = Test_1_input.txt
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
  } else { 0 } //*works                           //> n  : Int = 4
  val stringArray: Array[ String ] = /*Array()*/
    new Array[ String ]( n )                      //> stringArray  : Array[String] = Array(null, null, null, null)
  val pairArray: Array[ ( Int, Int ) ] =
    new Array[ ( Int, Int ) ]( n )                //> pairArray  : Array[(Int, Int)] = Array(null, null, null, null)

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
                                                  //> stringArray> null,null,null,null
  println( "pairArray> " + pairArray.mkString( "," ) )
                                                  //> pairArray> (0,1),(1,2),(2,3),(2,4)
  Vector( 1, 2 ) ++ Vector( 3, 4 )                //> res0: scala.collection.immutable.Vector[Int] = Vector(1, 2, 3, 4)
  1 +: 2 +: Vector( 3, 4 )                        //> res1: scala.collection.immutable.Vector[Int] = Vector(1, 2, 3, 4)
  Vector( 1, 2 ) :+ 3 :+ 4                        //> res2: scala.collection.immutable.Vector[Int] = Vector(1, 2, 3, 4)
  /*constractor object not same as for Arrays
  no Instance Constructors
  new Vector[ (Int, Int) ]( n )*/
  val positiveArray = Array( 1, -1, 3, -1, 5, -1, 7 ) filterNot ( _ == -1 )
                                                  //> positiveArray  : Array[Int] = Array(1, 3, 5, 7)

  positiveArray.indexOf( 4 ) match {
    case x if x > 0 => positiveArray( x ) = 9
    //*case _          => "not found"
    case x          => x
  }  //*work                                      //> res3: AnyVal = -1
  positiveArray                                   //> res4: Array[Int] = Array(1, 3, 5, 7)
  positiveArray.forall(_ != 3)                    //> res5: Boolean = false
  positiveArray.forall(_ != -1)                   //> res6: Boolean = true
  
  val (nodesSeq, leafsSeq) = pairArray.unzip      //> nodesSeq  : Array[Int] = Array(0, 1, 2, 2)
                                                  //| leafsSeq  : Array[Int] = Array(1, 2, 3, 4)
  nodesSeq.toSet                                  //> res7: scala.collection.immutable.Set[Int] = Set(0, 1, 2)
}