package advertisingSpread

import math._
import scala.util._
import scala.io.StdIn.readInt
import scala.io.StdIn.readLine
import scala.io.Source

/*description:
codingame.com Sponsored Challenge from Teads
Goal:
find minimum possible path length (in steps) for / in tree
from nodes to all leafs & root
*/
/** TODO:
  * 1. remove Array input & to Vector conversion
  * all input directly to Vector[(Int, Int)]
  * 2. fill 'leafs' at the input step / procedure / phase
  * in / as separate input Vector[Int]
  * check current node if in 'leafs' then remove it from there
  * 3. add 'height' value = maxPath = longestPath &
  * 'heightLeaf' = leaf value:Int
  * 4. add path check if maxPath from 'fork' to 'root' or 'heightLeaf'
  * greater then 'height' / 2 then
  * find that 'heightMiddle' node, starting from 'heightLeaf' &
  * 'heightMiddle' maxPath = 'height' / 2 if 'height' even or else ('height' - 1) / 2 + 1
  * 5. find maxPath / longestPath from 'fork' to 'leafs' without 'heightLeaf'
  * as 'secondAfterLeaf' path:Vector & leaf:Int
  * if 'heightMiddle' maxPath less then ('heightMiddle' path to 'fork') +
  * 'secondAfterLeaf' path then that is the 'minPath' to return
  * 6. crate / write unit tests
  * 7. add input from file
  */
/*
  test 6: fails
  on test 8:
  "Process has timed out.
  This may mean that
  your solution is
  not optimized enough to handle some cases."
  vector size 30 000+
  */
object Solution {

  /*fork is
   * 1st consequently repeated element (as they sorted)
   * if non found then root or 1st element in array*/
  def firstFork(
    graph: Vector[ ( Int, Int ) ] ): Int = {
    //graph.foreach { x => print(x + ',') }
    /*condition check*/
    //graph.forall { x => ??? }
    val fork = ( for {
      p <- graph
      if /*{
        val currNode = p._1
        val dub1 = graph2.filter { x => x.split(" ")(0).toInt == currNode }
        !(dub1.isEmpty)
      } || {*/ ( graph.count { x => x._1 == p._1 } ) > 1 //}
    } yield p._1 )

    if ( fork.size > 0 ) { fork.head } else {
      if ( graph.size > 0 ) { graph.head._1 } else { -1 }
    }
  }

  def pathToRoot(
    graph: Vector[ ( Int, Int ) ],
    fromLeaf: Int,
    storedPath: Vector[ ( Int, Int ) ] = Vector() ): Vector[ ( Int, Int ) ] = {

    if ( graph.size > 0 ) {
      if ( fromLeaf == graph.head._1 ) {
        storedPath
      } else {
        val leafConnect = graph.find { x => x._2 == fromLeaf }

        if ( leafConnect.size > 0 ) {
          //val Option((node, leaf)) = leafConnect
          pathToRoot( graph, leafConnect.get._1, storedPath ++ leafConnect )
        } else {
          storedPath //++ graph.find { x => x._2 == fromLeaf }
        }
      }
    } else {
      storedPath
    }
  } //*work as expected

  /*possible alternative:
  (for {
    /*Pattern Matching*/
    (node1, leaf1) <- graph
    (node2, leaf2) <- graph
    if (node2 < leaf1 &&
      leaf1 < leaf2 &&
      node1 < leaf2)
  } yield leaf1).distinct //works 
   * */
  def treeLeafs( graph: Vector[ ( Int, Int ) ] ): Vector[ Int ] = {
    for {
      elem <- graph
      /*all methods uses same Iterator[A] as traversal loop*/
      if graph.forall( node => elem._2 != node._1 )
      //*if !graph.exists(node => elem._2 == node._1)
      //*if (graph.count { x => x._1 == elem._2 } == 0)
    } yield elem._2
  } //*work as expected

  def treeLeafs1( graph: Vector[ ( Int, Int ) ] ): Vector[ Int ] = {
    for {
      /*Pattern Matching*/
      ( node, leaf ) <- graph
      if graph.forall {
        case ( nodeX, leafX ) => nodeX != leaf
      }
    } yield leaf //*works 
  } //*work as expected  

  def longestPath(
    graph: Vector[ ( Int, Int ) ],
    leafs: Vector[ Int ] ): Vector[ ( Int, Int ) ] = {

    var maxPath: Vector[ ( Int, Int ) ] = Vector() //graph//.size
    var currentPath: Vector[ ( Int, Int ) ] = Vector()
    /*must be collection of paths from leaf to root
          then pick longese / biggest / max length */
    leafs.foreach { elem =>
      currentPath = pathToRoot( graph, elem )
      if ( currentPath.size > maxPath.size ) {
        maxPath = currentPath
      } else {
        // maxPath
      }
    }

    maxPath
  } //*works 

  def pathToFork(
    graph: Vector[ ( Int, Int ) ],
    leafs: Vector[ Int ],
    fork: Int,
    maxPath: Vector[ ( Int, Int ) ] ): Vector[ ( Int, Int ) ] = {

    var minPath: Vector[ ( Int, Int ) ] = Vector() //graph//.size
    var currentPath: Vector[ ( Int, Int ) ] = Vector()
    /*for final check*/
    val fromForkToRoot = pathToRoot( graph, fork )

      def upwardPath(
        fromLeaf: Int,
        //to: Int,
        storedPath: Vector[ ( Int, Int ) ] = Vector() ): Vector[ ( Int, Int ) ] = {

        if ( fromLeaf == fork ) {
          storedPath
        } else {
          val leafConnect = graph.find { x => x._2 == fromLeaf }
          if ( leafConnect.size > 0 ) {
            upwardPath( leafConnect.get._1, storedPath ++ leafConnect )
          } else {
            storedPath //++ graph.find { x => x._2 == fromLeaf }
          }
        }
      }
    /*must be max path in test
    bun less then over all maxPath*/
    /*if path > the maxPath skip element & check next */
    //println("leafs.size:" + leafs.size)
    for ( i <- 0 until leafs.size ) {
      currentPath = upwardPath( leafs( i ) )
      //println("currentPath:" + currentPath +
      //", for leafs(i):" + leafs(i))
      if ( currentPath.size >= maxPath.size ) {
        //skip elem ?
        minPath = maxPath
      } else {
        if ( currentPath.size > minPath.size ) {
          //println("minPath was:" + minPath)
          minPath = currentPath
        } else {
          // minPath
        }
      }
    }

    if ( fromForkToRoot.size > minPath.size ) {
      fromForkToRoot
    } else {
      minPath
    }
  } //*works 

  //unit test
  def main( args: Array[ String ] ) {
    val graph2 = Array(
      "1 2", //root
      "2 3",
      "3 4",
      "3 7",
      "4 5", //end
      "4 6", //end
      "7 8" //end
    ) //min path == 2
    val lineVecTest =
      Vector( ( 1, 2 ), ( 2, 3 ), ( 3, 4 ), ( 4, 5 ), ( 5, 6 ), ( 6, 7 ), ( 7, 8 ) ) //min path == 4 = 7 / 2 + 1
    val test1 = Vector( ( 0, 1 ),
      ( 1, 2 ),
      ( 2, 3 ),
      ( 2, 4 ) )
    val test2 = Vector( ( 0, 1 ),
      ( 1, 2 ),
      ( 1, 4 ),
      ( 2, 3 ),
      ( 4, 5 ),
      ( 4, 6 ) ) //min path == 2
    //default path needed
    //if not implicit
    val testNumber = "1"
    val filename = "Test_" + testNumber + "_input.txt"
    val filePath = "E:\\Java\\coursera-workspace\\Challenge\\"
    val currFile = Source
      .fromFile( filePath + filename )
    val currFileLines = Source
      .fromFile( filePath + filename )
      .getLines()
    val n = if ( currFileLines.hasNext ) {
      currFileLines.next()
    } else { 0 }
    val fileContent: Iterator[ String ] = Iterator()
    val testGraph: Vector[ ( Int, Int ) ] = try {
      //val fileContent: Iterator[ ( Int, Int ) ] = try {
      //*(

      /** My effective code must be within that loop / for expression
        */
      //*for ( i <- 0 until n ) {
      /*'xi': the identifier of a person related to 'yi'*/
      // xi: l'identifiant d'une personne liée à yi
      /*'yi': the identifier of a person related to 'xi'*/
      // yi: l'identifiant d'une personne liée à xi
      /*"1 2"*/
      /*'readLine' read next string from input*/
      //*val Array( xi, yi ) = for ( i <- graph2( i ) split " " ) yield i.toInt
      //*println("Array(xi, yi) is: (" + xi + ", " + yi + ")")
      //*} //yield i
      (
      for {
        /*how to skip 1st line?*/
        line <- Source
          //i <- 0 until Source
          .fromFile( filePath + filename )
          .getLines() //; //.size
        //*) //*yield line
        /*for first line in file
       * line.split( " " )( 1 ) index '1' is out of bounds
       * */
      } yield ( line.split( " " )( 0 ).toInt, line.split( " " )( 1 ).toInt ) //:( Int, Int)
    //} yield  line split " " map (arElem => arElem.toInt) //:Array[ Int ] 

      //*} yield line
      ).toVector
      //*Vector()
      /*{
        val Array( xi, yi ) = for ( i <- line split " " ) yield i.toInt*/
      //*println( line )
      //*} 
    } catch {
      //*case ex: Exception => println("Bummer, an exception happened.")//*works
      //*case ex: Exception => println( ex ) //*works even better
      //*case ex: Exception => Iterator() /*Vector() */
      case ex: Exception => Vector()
    } //*works

    //*val testGraph: Vector[ ( Int, Int ) ] = Vector()
    /*for {
      //index <- (1 to fileContent.size)
      line <- fileContent
      //if line.index > 0
      //if line != fileContent.head
      if line != fileContent.take( 1 )
    } yield ( line.split( " " )( 0 ).toInt, line.split( " " )( 1 ).toInt )*/

    //*val testGraph = test2
    val leafs = treeLeafs( testGraph )
    val maxPath = longestPath( testGraph, leafs )
    val fork = firstFork( testGraph )
    val minPath = pathToFork( testGraph, leafs, fork, maxPath )

    //val n = readInt
    //*val n = testGraph match {
    //*case Vector() => 0
    //*case _        => testGraph.size
    //case _        => testGraph.head._1
    //*} // le nombre 'n' de relations au total.

    //*println( "fileContent is: " + fileContent )
    //*println( "fileContent size is: " + fileContent.size )
    /*any operation on 'Iterator' affect its content ?*/
    //*println( "fileContent is: " + fileContent.mkString )
    //println( "fileContent first element is: " + fileContent.take(0)/*.mkString*/ )
    println( "total number of relations? / elements in graph is: " + n )
    println( "testGraph first 7 elements is: " + testGraph.take( 7 ) )
    println( "minPath is: " + minPath.size )
    println( "minPath is: " + minPath )
    println( "leafs is: " + leafs )
    println( "treeLeafs1(testGraph) is: " + treeLeafs1( testGraph ) )
    println( "fork is: " + fork )
    println( "maxPath is: " + maxPath )
    //println("yield i is: " + i)
    //println("Array(xi, yi) is: " + Array(xi, yi))
    /*println("graph2 is: " + graph2.toString())
    print("graph2 is: ")
    print(graph2.foreach { x => print(x + ", ") })
    println()*/
    //println("upwardToRoot(8, 1, graph2) = " + upwardToRoot(8, 1, graph2).size)
    //*graph2.foreach { x => print(x + ',') }
    /*println("upwardToRoot(3, 1, graph2) = " +
      upwardToRoot(3, 1, graph2).foreach { x => print(x + ", ") })
    println("12")*/
  }

}