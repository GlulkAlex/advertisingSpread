package advertisingSpread

object pathFoldTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(478); 
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
  };System.out.println("""addRelation: (link: (Int, Int), chain: Seq[Int])Seq[Int]""");$skip(143);  //*works

  def path( graph: Seq[ ( Int, Int ) ],
            start: Int ): Seq[ Int ] = graph
    .foldRight( Seq( start ) )( addRelation );System.out.println("""path: (graph: Seq[(Int, Int)], start: Int)Seq[Int]""");$skip(69);  /*works*/

  def showPath( path1: Seq[ Int ] ): String = path1.mkString("->");System.out.println("""showPath: (path1: Seq[Int])String""");$skip(150); 

  val graph1 = Array(
    ( 1, 2 ), //root
    ( 2, 3 ),
    ( 3, 4 ),
    ( 3, 7 ),
    ( 4, 5 ), //end
    ( 4, 6 ), //end
    ( 7, 8 ) //end
  );System.out.println("""graph1  : Array[(Int, Int)] = """ + $show(graph1 ));$skip(22); val res$0 = 

  path( graph1, 8 );System.out.println("""res0: Seq[Int] = """ + $show(res$0));$skip(30); val res$1 = 
  showPath(path( graph1, 8 ));System.out.println("""res1: String = """ + $show(res$1));$skip(38); val res$2 = 
  showPath(path( graph1, 8 ).reverse);System.out.println("""res2: String = """ + $show(res$2))}
}
