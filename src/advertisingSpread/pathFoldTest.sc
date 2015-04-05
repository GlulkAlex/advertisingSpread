package advertisingSpread

object pathFoldTest {
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
  } //*works                                      //> addRelation: (link: (Int, Int), chain: Seq[Int])Seq[Int]

  /** @author GlukAlex
    * @version 1.0, 06/03/2015
    * @param graph - source tree
    * @param startIndex - from which node or leaf in tree build a path
    * @return sequence on connected tree nodes as path
    */
  def path( graph: Seq[ ( Int, Int ) ],
            startIndex: Int ): Seq[ Int ] = graph
    .foldRight( Seq( startIndex ) )( addRelation ) /*works*/
                                                  //> path: (graph: Seq[(Int, Int)], startIndex: Int)Seq[Int]

  def showPath( path1: Seq[ Int ] ): String = path1.mkString( "->" )
                                                  //> showPath: (path1: Seq[Int])String

  val graph1 = Array(
    ( 1, 2 ), //root
    ( 2, 3 ),
    ( 3, 4 ),
    ( 3, 7 ),
    ( 4, 5 ), //end
    ( 4, 6 ), //end
    ( 7, 8 ) //end
  )                                               //> graph1  : Array[(Int, Int)] = Array((1,2), (2,3), (3,4), (3,7), (4,5), (4,6
                                                  //| ), (7,8))
  val test10Vec = Vector(
    ( 2, 5 ), ( 7, 2 ), ( 8, 7 ), ( 6, 8 ), ( 1, 6 ), ( 15, 1 ),
    ( 9, 15 ), ( 4, 9 ), ( 3, 4 ), ( 0, 3 ) )     //> test10Vec  : scala.collection.immutable.Vector[(Int, Int)] = Vector((2,5), 
                                                  //| (7,2), (8,7), (6,8), (1,6), (15,1), (9,15), (4,9), (3,4), (0,3))
  test10Vec
    .foldRight[ Seq[ Int ] ]( Seq() )( ( link: ( Int, Int ),
      chain: Seq[ Int ] ) => link._1 +: chain )   //> res0: Seq[Int] = List(2, 7, 8, 6, 1, 15, 9, 4, 3, 0)
  val test10VecPath = test10Vec
    .foldRight[ Seq[ Int ] ]( Seq() )( _._1 +: _ )//> test10VecPath  : Seq[Int] = List(2, 7, 8, 6, 1, 15, 9, 4, 3, 0)

  showPath( test10VecPath )                       //> res1: String = 2->7->8->6->1->15->9->4->3->0
  showPath( test10VecPath.reverse )               //> res2: String = 0->3->4->9->15->1->6->8->7->2
  /*without leaf itself
  exactly the right path*/
  test10VecPath.size                              //> res3: Int = 10

  test10Vec( 2 )                                  //> res4: (Int, Int) = (8,7)
  path(
    test10Vec,
    2 )                                           //> res5: Seq[Int] = List(7, 2)
  test10Vec( 5 )                                  //> res6: (Int, Int) = (15,1)
  path( test10Vec, 5 )                            //> res7: Seq[Int] = List(2, 5)
  test10Vec( 0 )                                  //> res8: (Int, Int) = (2,5)
  path( test10Vec, 0 )                            //> res9: Seq[Int] = List(0)
  path( test10Vec.reverse, 0 )                    //> res10: Seq[Int] = List(0)
  test10Vec( 3 )                                  //> res11: (Int, Int) = (6,8)
  path( test10Vec, 3 )                            //> res12: Seq[Int] = List(0, 3)
  /*java.lang.ArrayIndexOutOfBoundsException: 8
  graph1(8)*/
  path( graph1, 8 )                               //> res13: Seq[Int] = List(1, 2, 3, 7, 8)
  showPath( path( graph1, 8 ) )                   //> res14: String = 1->2->3->7->8
  showPath( path( graph1, 8 ).reverse )           //> res15: String = 8->7->3->2->1
  /*max possible path length*/
  Seq( 5, 6, 8 )
    .foreach( x => println( showPath( path( graph1, x ) ) ) )
                                                  //> 1->2->3->4->5
                                                  //| 1->2->3->4->6
                                                  //| 1->2->3->7->8
  graph1
    .foreach( x => println( showPath( path( graph1, x._2 ) ) ) )
                                                  //> 1->2
                                                  //| 1->2->3
                                                  //| 1->2->3->4
                                                  //| 1->2->3->7
                                                  //| 1->2->3->4->5
                                                  //| 1->2->3->4->6
                                                  //| 1->2->3->7->8
  ( for ( x <- Seq( 5, 6, 8 ) ) yield showPath( path( graph1, x ) ) ).max
                                                  //> res16: String = 1->2->3->7->8
  /*for 'leafs'*/
  val maxPath: Seq[ Int ] = (
    for ( x <- Seq( 5, 6, 8 ) ) yield path( graph1, x ) )
    .sortWith( _.size > _.size )
    .head                                         //> maxPath  : Seq[Int] = List(1, 2, 3, 4, 5)
  val maxPathSize: Int = maxPath.size             //> maxPathSize  : Int = 5

  ( 7 + 1 ) / 2                                   //> res17: Int = 4
  ( 7 ) / 2 + 1                                   //> res18: Int = 4
  ( 5 + 1 ) / 2                                   //> res19: Int = 3
  ( 5 ) / 2 + 1                                   //> res20: Int = 3
  val middleNodeIndex: Int = if ( maxPathSize % 2 == 0 ) {
    maxPathSize / 2
  } else { ( maxPathSize + 1 ) / 2 }              //> middleNodeIndex  : Int = 3
  /*how to get list element by index ?
  or other data type needed ?*/
  //val middleNode = maxPath(maxPath)
  /*index used as amount of elements*/
  val ( toMiddleNode, fromMiddleNode ): ( Seq[ Int ], Seq[ Int ] ) =
    maxPath
      .splitAt( middleNodeIndex )                 //> toMiddleNode  : Seq[Int] = List(1, 2, 3)
                                                  //| fromMiddleNode  : Seq[Int] = List(4, 5)

  val minPath: Int = if ( toMiddleNode.size > fromMiddleNode.size )
    toMiddleNode.size
  else { fromMiddleNode.size }                    //> minPath  : Int = 3

  //showPath( path( maxPath, 3 ) )

  ( for ( x <- Seq( 5, 6, 8 ) ) yield path( graph1, x ) )
    .sortWith( _.size > _.size )                  //> res21: Seq[Seq[Int]] = List(List(1, 2, 3, 4, 5), List(1, 2, 3, 4, 6), List(
                                                  //| 1, 2, 3, 7, 8))
  for ( x <- graph1 ) yield showPath( path( graph1, x._2 ) )
                                                  //> res22: Array[String] = Array(1->2, 1->2->3, 1->2->3->4, 1->2->3->7, 1->2->3
                                                  //| ->4->5, 1->2->3->4->6, 1->2->3->7->8)
}