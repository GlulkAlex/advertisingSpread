package advertisingSpread

import math._
//import scala.util._
//import scala.io.StdIn.readInt
//import scala.io.StdIn.readLine
import scala.io.Source

/* may be even,
 * use 'clustering' problem approach
 * where
 * 'spacing' is our goal
 * as optimal maximum minimal 'path' / 'distance' to
 * any 'nodes' in 'tree' &
 * 'k' - clusters number == 
 * 'leafs' + 'root' + 'optimal node'(may be same as root)
 * remember that
 * goal is to find 'distance' / 'spacing'
 * not an exact 'node' from where possible to
 * reach all others 'nodes' at most 
 * at 's' steps <= 'spacing'
 * Problem is
 * that 'distance' between adjusted nodes == '1'
 * so 
 * that must be the 'spacing' value
 * */
/*some implementation of 
 * data structure,
 * Prim's algorithm (lazy & Eager approach, priority queue),
 * Kruskal's algorithm (priority queue)
 * from:
 * http://algs4.cs.princeton.edu/43mst/ 
 * */
/**? the 'minimax path' problem ?*/
/*or*/
/**? 'minimum spanning tree' ?*/
/* shortest possible path from 
 * some 'special' nodes to any other in 'tree' or
 * amount of 'steps' to reach all nodes in 'graph'
 * at least no less then from 'root'
 * to farthest / furthest / max distant 'leaf'
 * or '(max)tree height'
 * Assumption:
 * if 
 * 'distance' to any other 'leaf' from 'root'
 * equal to arbitrary chosen 'max distant 'leaf'' then
 * 'path' from 'root' is optimal
 * else if
 * exists 'leafs' with 'path' to 'root' / 'tree height (for that leaf)'
 * less then for 'max distant 'leaf'' then
 * check
 * path to 'leaf' with 'second max distant path to root' against
 * 'node' laying / within / belongs to 
 * path from 'max distant 'leaf'' to 'root'
 * starting from node in the middle of that path
 * closer to 'root'
 * If max path from this 'node' to
 * 'root',
 * 'max distant 'leaf'', &
 * 'second max distant leaf' 
 * less then
 * default == '(max)tree height' then
 * that path is optimal
 * */

 /* In general, assume that:
  * minimal possible maximum distance from
  * some 'special' 'node' within tree
  * to any other 'node'
  * equal to 
  * max of 'Rank' or 'Height' value for
  * 'node' with
  * 'Rank' = treeHeight / 2 or maxLeafHeight / 2
  * or  
  * 'Height' = rootRank / 2 or maxNodeRank / 2
  * where
  * 'Rank' = maxChildNodeRank + 1 
  * (as distance to 'max distant' subtree 'leaf'
  * from this 'node'
  * where 'node' is 'root' of subtree)
  */
  /* Possibly,
   * 'minimal possible maximum distance'
   * may be obtained / gained / achieved / returned
   * at the end of input / data structure initialization
   * if maintained additional / auxiliary 
   * variables such as
   * 'middleEdge(someParentNode,middleNode,someNextNode,optimalHeight,optimalRank)'
   * and
   * 'maxDistantEdge(someParentNode,maxDistantLeaf,maxDistantLeaf,maxHeight,0)'
   * When
   * exist single 'maxDistantEdge' not equal to
   * others 'leafs' depth / height / distance
   * Note:
   *  question is not about 'single / one & only' 'maxDistantEdge'
   *  but
   *  about existence 'optimal fork' below 'root'
   *  such as 
   *  no 'fork' before present / single chain to root without branching
   * or 
   * ? still needed to check ?
   * if 'root' is the 'optimal node' & 
   * respectively has
   * 'optimalHeight'
   * 
   * As if only two cases possible
   * either  'optimal node' is 
   * 'root' or
   * 'fork' as 'subtree root' not equal to 'tree root'
   * 'optimal node' lays within / on path to 'maxDistantLeaf'
   * 
   * 'onlyOneMaxDistantLeaf' state / flag
   * may be traced too &
   * be known at the end of input
   * */

/*description:
codingame.com Sponsored Challenge from Teads
Goal:
find minimum possible path length (in steps) for / in tree
from nodes to all leafs & root
*/
/** TODO:
  * (-1.) remove Array input & to Vector conversion
  * all input directly to Vector[(Int, Int)] ???
  * vectors good in intense search
  * (fast random selections & fast random functional updates)
  * not when elements added so
  * >>may be '.toVector' in the right plays is enough ?
  * (-2.) fill 'leafs' at the input step / procedure / phase
  * in / as separate input Vector[Int]
  * check current node if in 'leafs' then remove it from there ???
  * >>not work that way, incorrect algorithm
  * -3. add 'height' value = maxPath = longestPath &
  * 'heightLeaf' = leaf value:Int
  * -4. add path check if maxPath from 'fork' to 'root' or 'heightLeaf'
  * greater then 'height' / 2 then
  * find that 'heightMiddle' node, starting from 'heightLeaf' &
  * 'heightMiddle' maxPath = 'height' / 2 if 'height' even or else ('height' - 1) / 2 + 1
  * -5. find maxPath / longestPath from 'fork' to 'leafs' without 'heightLeaf'
  * as 'secondAfterLeaf' path:Vector & leaf:Int
  * if 'heightMiddle' maxPath less then ('heightMiddle' path to 'fork') +
  * 'secondAfterLeaf' path then that is the 'minPath' to return
  * 6. crate / write unit tests
  * (+7). add input from file
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

/*Note:
 * 'Mutable Collections'
* 'Array' 
* (Scala arrays are 
* native JVM arrays at runtime, therefore 
* they are very performant)*/
/* possible use of '.toSet' to
 * eliminate duplicates or 'distinct' Operation on sequences    
 * */
/**Assumption:
 * input graph connected
 * all vertices / nodes reachable from each other*/
object Solution {

  /*fork is
   * 1st consequently repeated element 
   * if input nodes sorted in file 
   * from lesser to greater & then
   * fork nodes are on adjusted lines
   * (so how about useful sorting first ?
   * def msort[T](xs: List[T])(implicit ord: Ordering) = { ...}
   * pairArray.sorted - works, but not much helpful) 
   * if non found then root or 1st element in array*/
  /**When an 'edge' connects two 'vertices', 
   * we say that 
   * the 'vertices' are 'adjacent' to one another and 
   * that the 'edge' is 'incident on' both 'vertices'.
   * 
   * The 'degree' of a vertex is 
   * the number of 'edges' 'incident on' it.
   * */
  def firstFork(
    graph: Vector[ ( Int, Int ) ] ): Int = {
    //graph.foreach { x => print(x + ',') }
    /*condition check*/
    //graph.forall { x => ??? }
    val fork = ( for {
      p <- graph //*.sorted
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
  /**A 'path' in a graph is 
   * a 'sequence of vertices' connected by 'edges'. 
   * A 'simple path' is 
   * one with no repeated 'vertices'.
   * 
   * The 'length' of a 'path' or 
   * a 'cycle' is 
   * its number of 'edges'.
   * 
   * We say that 
   * one vertex is 'connected' to another if 
   * there exists a 'path' that contains both of them.
   * 
   * A graph is 'connected' if 
   * there is a 'path' from every 'vertex' to every other 'vertex'.
   * 
   * A 'graph' that is not connected consists of 
   * a set of 'connected components', which are 
   * maximal 'connected subgraphs'.
   * 
   * An 'acyclic graph' is 
   * a 'graph' with no 'cycles'.
   * 
   * A 'tree' is an 'acyclic connected graph'.
   * 
   * A 'forest' is 
   * a 'disjoint set' of 'trees'.
   * 
   * A 'spanning tree' of 
   * a 'connected graph' is 
   * a 'subgraph' that 
   * contains all of that graph's 'vertices' and 
   * is a 'single tree'. 
   * A 'spanning forest' of a 'graph' is 
   * the 'union' of 
   * the 'spanning trees' of its 'connected components'.
   * */
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

  /*more verbose and clear*/
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

  def addRelation( link: ( Int, Int ),
                   chain: Seq[ Int ] ): Seq[ Int ] = {
    val ( finish, start1 ) = link
    //if (chain.tail == start1) {
    if ( chain.head == start1 ) {
      /*postfix addition to the right
                    to create new tail or
                    for reversed order
                    ptefix to create new head*/
      finish +: chain
    } else {
      chain
    }
  } //*works                                      

  def path( graph: Seq[ ( Int, Int ) ],
            start: Int ): Seq[ Int ] = graph
    .foldRight( Seq( start ) )( addRelation ) /*works*/

  def showPath( path1: Seq[ Int ] ): String = path1.mkString( "->" )

  //benchmarking:
  /*scala.testing.Benchmark trait is
  predefined in the Scala standard library*/
  def timeNano[ R ]( block: => R ): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()

    println( "Elapsed time: " + ( t1 - t0 ) + "ns" )
    result
  }

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
    val lineVecTest = Vector(
      ( 1, 2 ), ( 2, 3 ), ( 3, 4 ),
      ( 4, 5 ), ( 5, 6 ), ( 6, 7 ), ( 7, 8 ) ) //min path == 4 = 7 / 2 + 1
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
    val testNumber = 10
    val filename = "Test_" + testNumber + "_input.txt"
    val filePath = "E:\\Java\\coursera-workspace\\Challenge\\"
    val currFile = Source
      .fromFile( filePath + filename )
    val currFileLines = Source
      .fromFile( filePath + filename )
      .getLines()
    /*as 1st line is '.head'*/
    /*to skip 1st line in file*/
    val n = if ( currFileLines.hasNext ) {
      currFileLines.next().toInt
    } else { 0 } //*works
    val fileContent: Iterator[ String ] = Iterator()
    //val testGraph: Vector[ ( Int, Int ) ] = Vector()
    /*size known upfront*/
    val pairArray: Array[ ( Int, Int ) ] =
      new Array[ ( Int, Int ) ]( n )
    val leafsArray = new Array[ Int ]( n )

    /** My effective code must be
      * within that loop / for expression
      */
    for ( i <- 0 until n ) {
      /*'xi': the identifier of a person related to 'yi'*/
      // xi: l'identifiant d'une personne liée à yi
      /*'yi': the identifier of a person related to 'xi'*/
      // yi: l'identifiant d'une personne liée à xi
      /*"1 2"*/
      /*'readLine' read next string from input*/
      //*val Array( xi, yi ) = for ( i <- graph2( i ) split " " ) yield i.toInt
      //*println("Array(xi, yi) is: (" + xi + ", " + yi + ")")
      //*pairArray( i ) = (xi, yi)
      pairArray( i ) = currFileLines
        .next()
        .toString()
        .split( " " ) match {
          case x if x.size == 1 => ( -1, x.head.toInt )
          case y if y.size == 2 => ( y.head.toInt, y( 1 ).toInt )
          case _                => ( -1, -1 )
        } //*works 

      /*do not check against all elements in 'pairArray' only i-th count
       * if any ._1 appear more then twice then
       * check fails
       * only distinct ._1 / nodes matters 
       * */
      //*leafsArray( i ) = pairArray( i )._2
      /*leafsArray.indexOf( pairArray( i )._1 ) match {
        case x if x > -1 => leafsArray( x ) = -1
        //case x          => leafsArray( i ) = pairArray( i )._2
        case x           => leafsArray( i )
      }*/ //*work 
    }

    //*val testGraph = test2
    val testGraph: Vector[ ( Int, Int ) ] = pairArray.sorted.toVector
    //testGraph unzip
    //*val ( nodesSeq, leafsSeq ) = pairArray.unzip
    /*for {
      node: Int <- nodesSeq.toSet
      } {print(node + ",")}*/ //*work
    //*println( "nodesSeq is: " + ( nodesSeq mkString "," ) )
    //*println( "leafsSeq is: " + ( leafsSeq mkString "," ) )
    /*same as 'treeLeafs' but with zip instead of much
    val leafs = ( for {
      // *curNode: Int <- nodesSeq.toSet
      // *curNode <- nodesSeq
      curLeaf <- leafsSeq
      // *if curNode != curLeaf
    	if nodesSeq.forall(_ != curLeaf)
    } yield curLeaf ).toVector*/ //*works

    val leafs = treeLeafs( testGraph )

    //*println( "leafsArray was: " + ( leafsArray mkString "," ) )
    //*val leafs = ( leafsArray filterNot ( _ == -1 ) ).toVector
    //*val maxPath = longestPath( testGraph, leafs )
    val fork = firstFork( testGraph )
    //*val minPath = pathToFork( testGraph, leafs, fork, maxPath )

    //*val pathFold = path( testGraph, leafs.head )

    /*for 'leafs'*/
    val maxPath: Seq[ Int ] = (
      for ( leaf <- leafs )
        //*yield path( testGraph, leaf ) 
        //yield path(pathToRoot( testGraph, leaf ), testGraph.head._1 )
        //yield path(pathToRoot( testGraph, leaf ), leaf )
        yield pathToRoot( testGraph, leaf )
        .foldRight[ Seq[ Int ] ]( Seq() )( _._1 +: _ ) //*work
    )
      .sortWith( _.size > _.size ) //*work
      //.sortWith( _.size < _.size )
      .head

    val maxPathSize: Int = maxPath.size
    val middleNodeIndex: Int = if ( maxPathSize % 2 == 0 ) {
      maxPathSize / 2
    } else { ( maxPathSize + 1 ) / 2 }
    /*how to get list element by index ?
  or other data type needed ?*/
    /*index used as amount of elements*/
    val ( toMiddleNode, fromMiddleNode ): ( Seq[ Int ], Seq[ Int ] ) =
      maxPath
        .splitAt( middleNodeIndex )
    val minPath: Int = if ( toMiddleNode.size > fromMiddleNode.size ) {
      toMiddleNode.size
    } else { fromMiddleNode.size }
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
    //*println( "testGraph first 7 elements is: " + testGraph.take( 7 ) )
    /*leafs.foreach {
      x =>
        println( "pathFold for leaf " + x +
          " is: " + showPath( path( testGraph, x ) ) )
    }*/ //*works
    //*println( "pathFold is: " + showPath( pathFold ) )
    //*println( "minPath is: " + minPath.size )
    //*println( "minPath is: " + minPath )
    /*may be too long & big*/
    println( "pairArray is: " + ( pairArray.take( 7 ) mkString "," ) )
    //*println( "leafsArray is: " + ( leafsArray mkString "," ) )
    //*println( "leafs is: " + leafs )
    //*println( "treeLeafs1(testGraph) is: " + treeLeafs1( testGraph ) )
    //*println( "fork is: " + fork )
    println( "maxPath is: " + ( maxPath mkString "->" ) )
    /*generic tree view*/
    //*testGraph.foreach( x => println( showPath( path( testGraph, x._2 ) ) ) )
    /*leafs
      .foreach( x => println( pathToRoot( testGraph, x ) ) )*/

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