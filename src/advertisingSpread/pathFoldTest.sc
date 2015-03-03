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

  def path( graph: Seq[ ( Int, Int ) ],
            start: Int ): Seq[ Int ] = graph
    .foldRight( Seq( start ) )( addRelation ) /*works*/
                                                  //> path: (graph: Seq[(Int, Int)], start: Int)Seq[Int]

  def showPath( path1: Seq[ Int ] ): String = path1.mkString("->")
                                                  //> showPath: (path1: Seq[Int])String

  val graph1 = Array(
    ( 1, 2 ), //root
    ( 2, 3 ),
    ( 3, 4 ),
    ( 3, 7 ),
    ( 4, 5 ), //end
    ( 4, 6 ), //end
    ( 7, 8 ) //end
  )                                               //> graph1  : Array[(Int, Int)] = Array((1,2), (2,3), (3,4), (3,7), (4,5), (4,6)
                                                  //| , (7,8))

  path( graph1, 8 )                               //> res0: Seq[Int] = List(1, 2, 3, 7, 8)
  showPath(path( graph1, 8 ))                     //> res1: String = 1->2->3->7->8
  showPath(path( graph1, 8 ).reverse)             //> res2: String = 8->7->3->2->1
}