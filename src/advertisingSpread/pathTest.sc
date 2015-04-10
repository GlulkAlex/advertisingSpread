package advertisingSpread
//import scala.util.matching.Regex

object pathTest {
  var myList = Array( 1.9, 2.9, 3.4, 3.5 )        //> myList  : Array[Double] = Array(1.9, 2.9, 3.4, 3.5)

  println( "myList(0):" + myList( 0 ) )           //> myList(0):1.9
  // Finding the largest element
  var max = myList( 0 );                          //> max  : Double = 1.9

  for ( i <- 1 to ( myList.length - 1 ) ) {
    if ( myList( i ) > max ) max = myList( i );
  }

  println( "Max is " + max );                     //> Max is 3.5

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

  val graph2 = Array(
    "1 2", //root
    "2 3",
    "3 4",
    "3 7",
    "4 5", //end
    "4 6", //end
    "7 8" //end
  )                                               //> graph2  : Array[String] = Array(1 2, 2 3, 3 4, 3 7, 4 5, 4 6, 7 8)
  println( "graph1(0) is " + graph1( 0 ) );       //> graph1(0) is (1,2)
  val ( label, value ) = /*pair*/ graph1( 0 )     //> label  : Int = 1
                                                  //| value  : Int = 2
  graph1( 0 )._1                                  //> res0: Int = 1
  graph1( 0 )._2                                  //> res1: Int = 2
  //var (node: Int, leaf: Int)
  var ( node, leaf ) = ( -1, -1 )                 //> node  : Int = -1
                                                  //| leaf  : Int = -1
  var nodeLeafPair = new Tuple2[ Int, Int ]( 0, 0 )
                                                  //> nodeLeafPair  : (Int, Int) = (0,0)
  nodeLeafPair = ( -1, -1 )
  nodeLeafPair                                    //> res2: (Int, Int) = (-1,-1)
  //print("Please enter your input : ")
  //val line = Console.readLine//not in sheet
  //val Array(node7, leaf7) = "1".split(" ")
  //val Array(node7/*: Int*/, leaf7/*: Int*/) = "1 2".split(" ") match {
  //case Array() => Array(-1, -1)
  //case Array(node8: String) => Array(node8/*.toInt*/, node8/*.toInt*/)
  //case Array(node8, leaf8) => Array(node8/*.toInt*/, leaf8/*.toInt*/)
  //case _ => Array(-1, -1)
  //}
  val Array( node9, leaf9 ) = for ( i <- "3 4" split " " ) yield i.toInt
                                                  //> node9  : Int = 3
                                                  //| leaf9  : Int = 4
  /*not work when pattern not fit*/
  //val Array( node9, leaf9 ) = for ( i <- "3" split " " ) yield i.toInt

  graph2( 0 ).split( " " )                        //> res3: Array[String] = Array(1, 2)
  graph2( 0 ).split( " " )( 1 )                   //> res4: String = 2
  graph2( 0 ).split( " " )( 1 ).toInt             //> res5: Int = 2
  "1".split( " " )                                //> res6: Array[String] = Array(1)
  "1 2".splitAt( 1 )                              //> res7: (String, String) = (1," 2")

  val n12_l112 = "12 112"                         //> n12_l112  : String = 12 112

  n12_l112.splitAt( n12_l112.indexOf( " " ) )     //> res8: (String, String) = (12," 112")
  "112".splitAt( "112".indexOf( " " ) )           //> res9: (String, String) = ("",112)
  "112".splitAt( "112".indexOf( " " ) ) match {
    case ( "", x ) => ( -1, x.toInt )
    case ( y, x )  => ( y.toInt, x.toInt )
    case _         => ( -1, -1 )
  }                                               //> res10: (Int, Int) = (-1,112)
  "112".split( " " ) match {
    case Array( "", x ) => ( -1, x.toInt )
    case Array( y, x )  => ( y.toInt, x.toInt )
    case _              => ( -1, -1 )
  }                                               //> res11: (Int, Int) = (-1,-1)
  "112".split( " " ) match {
    case x if x.size == 1 => ( -1, x.head.toInt )
    //*case y if y.size == 2 => (y.head.toInt, y.tail/*.toInt*/)
    case y if y.size == 2 => ( y.head.toInt, y( 1 ).toInt )
    case _                => ( -1, -1 )
  }                                               //> res12: (Int, Int) = (-1,112)
  "12 112".split( " " ) match {
    case x if x.size == 1 => ( -1, x.head.toInt )
    //*case y if y.size == 2 => (y.head.toInt, y.tail/*.toInt*/)
    case y if y.size == 2 => ( y.head.toInt, y( 1 ).toInt )
    case _                => ( -1, -1 )
  }                                               //> res13: (Int, Int) = (12,112)
  /*java.lang.NumberFormatException:
  "112".splitAt( "112".indexOf( " " ) )._1.toInt*/
  val set112 = "112".toSet                        //> set112  : scala.collection.immutable.Set[Char] = Set(1, 2)

  set112.splitAt( 0 )                             //> res14: (scala.collection.immutable.Set[Char], scala.collection.immutable.Se
                                                  //| t[Char]) = (Set(),Set(1, 2))
  set112
    .find { x => x == ' ' }
    .orElse( Some( -1 ) ) /*withDefaultValue*/    //> res15: Option[AnyVal{def getClass(): Class[_ >: Char with Int <: AnyVal]}] 
                                                  //| = Some(-1)
  n12_l112.split( " " )                           //> res16: Array[String] = Array(12, 112)
  //java.lang.String cannot be cast to java.lang.Integer or Long or Double or Short                                                Integer
  //graph2(0).split(" ")(1).asInstanceOf[Int]
  /*graph2(0).split(" ")(1).charAt(0)
  //graph2(0).split(" ")(1).charAt(0).toShort*/
  val x = graph2( 0 ).split( " " )( 1 )           //> x  : String = 2
  //x.toInt //char2int
  "1".length()                                    //> res17: Int = 1
  "2".##()                                        //> res18: Int = 50
  "3".toSeq                                       //> res19: Seq[Char] = 3
  "4".toFloat                                     //> res20: Float = 4.0
  "5".toInt                                       //> res21: Int = 5
  graph2 ++ myList                                //> res22: Array[Any] = Array(1 2, 2 3, 3 4, 3 7, 4 5, 4 6, 7 8, 1.9, 2.9, 3.4,
                                                  //|  3.5)
  graph2.size                                     //> res23: Int = 7
  graph2( graph2.size - 1 )                       //> res24: String = 7 8
  graph2.last                                     //> res25: String = 7 8
  graph2.indices                                  //> res26: scala.collection.immutable.Range = Range(0, 1, 2, 3, 4, 5, 6)
  graph2.take( 3 )                                //> res27: Array[String] = Array(1 2, 2 3, 3 4)
  graph2.drop( 3 )                                //> res28: Array[String] = Array(3 7, 4 5, 4 6, 7 8)
  graph2.dropRight( 3 )                           //> res29: Array[String] = Array(1 2, 2 3, 3 4, 3 7)
  graph2.dropWhile { x => x.split( " " )( 0 ).toInt == 4 }
                                                  //> res30: Array[String] = Array(1 2, 2 3, 3 4, 3 7, 4 5, 4 6, 7 8)
  graph2.dropWhile { x => x.split( " " )( 0 ) == "3" }
                                                  //> res31: Array[String] = Array(1 2, 2 3, 3 4, 3 7, 4 5, 4 6, 7 8)
  graph2.dropWhile { x => x == "3 4" }            //> res32: Array[String] = Array(1 2, 2 3, 3 4, 3 7, 4 5, 4 6, 7 8)
  /*to gain duplicate values or group by*/
  val currNode = graph2( 2 ).split( " " )( 0 ).toInt
                                                  //> currNode  : Int = 3
  val dub1 = graph2.filter { x => x.split( " " )( 0 ).toInt == currNode }
                                                  //> dub1  : Array[String] = Array(3 4, 3 7)
  graph2.count { x => x.split( " " )( 0 ).toInt == currNode }
                                                  //> res33: Int = 2
  dub1.isEmpty                                    //> res34: Boolean = false
  graph2.filterNot { x => x.split( " " )( 0 ).toInt == 4 }
                                                  //> res35: Array[String] = Array(1 2, 2 3, 3 4, 3 7, 7 8)
  graph2.find { x => x.split( " " )( 1 ).toInt == 4 }
                                                  //> res36: Option[String] = Some(3 4)
  graph2.indexWhere { x => x.split( " " )( 1 ).toInt == 4 }
                                                  //> res37: Int = 2
  "0 0" +: graph2                                 //> res38: Array[String] = Array(0 0, 1 2, 2 3, 3 4, 3 7, 4 5, 4 6, 7 8)
  graph2 :+ "10 10"                               //> res39: Array[String] = Array(1 2, 2 3, 3 4, 3 7, 4 5, 4 6, 7 8, 10 10)
  /*Unit loop*/
  graph2.foreach { x => print( x + ',' ) }        //> 1 2,2 3,3 4,3 7,4 5,4 6,7 8,
  // Print all the array elements
  for ( x <- myList ) {
    println( x )                                  //> 1.9
                                                  //| 2.9
                                                  //| 3.4
                                                  //| 3.5
  }
  !true                                           //> res40: Boolean = false
  graph1.unzip                                    //> res41: (Array[Int], Array[Int]) = (Array(1, 2, 3, 3, 4, 4, 7),Array(2, 3, 4
                                                  //| , 7, 5, 6, 8))
  /** In 'Scala',
    * 'pattern matching' does not appears only
    * after a the function 'match'.
    * You can
    * put 'pattern matching' in
    * any 'closure' '{}' and
    * also in a 'catch block'.
    */
  /*
  You can
  provide your own behavior for 'pattern matching'.
  This is called 'extractor'.
  To do so,
  you have to
  provide your own implementation of
  the 'unapply()' method
  (and/or 'unapplySeq()' for a sequence).
  */
  /*To go further,
	we will use
	this approach with
	tuples
	in order to
	improve our method 'parserArgument()' above:*/
  /** def parseArgument(arg: String, value: Any) = (arg, value) match {
    * case ("-l", lang) => setLanguageTo(lang)
    * case ("-o" | "--optim", n: Int) //'guard condition'
    * if ((0 < n) && (n <= 5)) => setOptimizationLevelTo(n)
    * case ("-o" | "--optim", badLevel) => badOptimizationLevel(badLevel)
    * case ("-h" | "--help", null) => displayHelp()
    * case bad => badArgument(bad)
    * }
    */
  /*Notice
	first
	the use of the operator '|' that
	allows to
	match alternative forms of 'arg' inside the 'tuple'.
	Notice also
	the use of
	two patterns for options '-o' and '--optim'.
	These patterns are
	distinguished by
	the use of a 'guard condition'.
	The 'guard conditions' help you to
	get fine tuned 'pattern matching' when
	'pattern matching' alone is not enough.*/
  for ( p <- graph2 if p.split( " " )( 1 ).toInt > 20 ) yield p
                                                  //> res42: Array[String] = Array()
  for ( p <- graph2 if p.split( " " )( 1 ).toInt > graph2.size ) yield p.split( " " )( 0 ).toInt
                                                  //> res43: Array[Int] = Array(7)
  for ( p <- graph2 if p.split( " " )( 1 ).toInt == 7 ) yield p
                                                  //> res44: Array[String] = Array(3 7)
  for ( p <- graph2 if p.split( " " )( 1 ).toInt <= graph2.size ) yield p
                                                  //> res45: Array[String] = Array(1 2, 2 3, 3 4, 3 7, 4 5, 4 6)
  graph2.length                                   //> res46: Int = 7
  /*combination of sum operands equal 'x'*/
  for {
    i <- 1 to 8
    j <- 1 until 8
    if ( i + j == 8 )
  } yield ( i, j )                                //> res47: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((1,7), (2
                                                  //| ,6), (3,5), (4,4), (5,3), (6,2), (7,1))
  val sPair = "1 2"                               //> sPair  : String = 1 2
  /*r( ) method.
  Scala implicitly converts the 'String' to
  a 'RichString' and
  invokes that method to get an instance of 'Regex'
  */
  //*val pattern = "\\d\\s\\d".r//*work
  //*val pattern = "\\d".r//*work
  //*val pattern = "^\\d".r//*work
  //*val pattern = "\\d$".r //*work
  val pattern = "(\\d)\\s(\\d)".r( "node", "leaf" ) //*work
                                                  //> pattern  : scala.util.matching.Regex = (\d)\s(\d)
  //*(pattern findAllIn sPair).mkString(",")
  pattern.toString()                              //> res48: String = (\d)\s(\d)
  //pattern._1
  /*'Regex' in 'case pattern matching'*/
  /*There are two ways of
  using a 'Regex' to find a pattern:
  calling methods on 'Regex', such as
  'findFirstIn' or 'findAllIn', or
  using it as an 'extractor' in a 'pattern match'.
  */
  //*method on 'Regex' -- matters
  //*for Option[String] use 'findFirstMatchIn'
  val ( node1: Int, leaf1: String ) = pattern findFirstMatchIn sPair match {
    //*case Some(m) => m group "leaf"//*works
    /*if no cast -- returns pair of type 'Any'*/
    case Some( m ) => ( ( m group "node" ).toInt, ( m group "leaf" ).toString() ) //*works
    //case None => "No copyright"
    case _         => "exception"
  } //*works                                      //> node1  : Int = 1
                                                  //| leaf1  : String = 2

  for ( m <- pattern findFirstMatchIn sPair ) yield m group "node" //*works
                                                  //> res49: Option[String] = Some(1)
  for ( m <- pattern findFirstMatchIn sPair ) yield m group "leaf" //*works
                                                  //> res50: Option[String] = Some(2)
  for ( m <- pattern findFirstMatchIn sPair ) yield ( m group "node", m group "leaf" ) //*works
                                                  //> res51: Option[(String, String)] = Some((1,2))

  for {
    elem <- graph1
    /*Pattern Matching*/
    ( node, leaf ) = elem
  } yield ( node, leaf )                          //> res52: Array[(Int, Int)] = Array((1,2), (2,3), (3,4), (3,7), (4,5), (4,6), 
                                                  //| (7,8))

  for {
    /*Pattern Matching*/
    ( node, leaf ) <- graph1
    if graph1.forall {
      case ( nodeX, leafX ) => nodeX != leaf
    }
  } yield ( node, leaf ) //*works                 //> res53: Array[(Int, Int)] = Array((4,5), (4,6), (7,8))

  ( for {
    /*Pattern Matching*/
    ( node1, leaf1 ) <- graph1
    ( node2, leaf2 ) <- graph1
    if ( node2 < leaf1 &&
      leaf1 < leaf2 &&
      node1 < leaf2 )
  } yield leaf1 ).distinct //*works               //> res54: Array[Int] = Array(4, 5, 6)

  for {
    /*Pattern Matching*/
    ( node1, leaf1 ) <- graph1
    ( node2, leaf2 ) <- graph1
    if ( node2 < leaf1 &&
      //node1 < node2 &&
      leaf1 < leaf2 &&
      node1 < leaf2 )
  } yield leaf1                                   //> res55: Array[Int] = Array(4, 5, 5, 6)

  for {
    p <- graph2
    leaf <- 1 to 7
    node <- 1 until 7
    if ( node + " " + leaf == p )
  } yield ( node, leaf )                          //> res56: Array[(Int, Int)] = Array((1,2), (2,3), (3,4), (3,7), (4,5), (4,6))
                                                  //| 
  //*val Array(xi, yi) = for (i <- graph2(0) split " ") yield i.toInt
  val n = 7 // le nombre 'n' de relations au total.
                                                  //> n  : Int = 7
  //var inputStore: Array[(T1, T2)]
  var inputStore: Array[ ( Int, Int ) ] = Array() //> inputStore  : Array[(Int, Int)] = Array()
  var inputVector: Vector[ ( Int, Int ) ] = Vector()
                                                  //> inputVector  : Vector[(Int, Int)] = Vector()
  //inputStore ++ Array(1, 2)
  for ( i <- 0 until n ) {
    /*pattern matching*/
    val Array( xi, yi ) = for ( i <- graph2( i ) split " " ) yield i.toInt
    //inputStore +: Array((xi, yi))
    //inputStore :+ Array((xi, yi))
    //inputStore :++ Array((xi, yi))
    //inputStore ++: Array((xi, yi))
    //inputStore ++ Array((xi, yi))
    //inputStore ++ Array(xi, yi)
    /*vector of pairs*/
    inputVector = inputVector ++ Vector( ( xi, yi ) )
    /*array of pairs*/
    inputStore = inputStore :+ ( xi, yi )
    //inputStore union Array(xi, yi)
    //inputStore :+ Array(xi, yi)
    println( "(" + xi + "," + yi +
      "),size:" + inputStore.size +
      ",isEmpty:" + inputStore.isEmpty )
  }                                               //> (1,2),size:1,isEmpty:false
                                                  //| (2,3),size:2,isEmpty:false
                                                  //| (3,4),size:3,isEmpty:false
                                                  //| (3,7),size:4,isEmpty:false
                                                  //| (4,5),size:5,isEmpty:false
                                                  //| (4,6),size:6,isEmpty:false
                                                  //| (7,8),size:7,isEmpty:false
  inputStore                                      //> res57: Array[(Int, Int)] = Array((1,2), (2,3), (3,4), (3,7), (4,5), (4,6), 
                                                  //| (7,8))
  inputVector                                     //> res58: Vector[(Int, Int)] = Vector((1,2), (2,3), (3,4), (3,7), (4,5), (4,6)
                                                  //| , (7,8))
  inputStore.size                                 //> res59: Int = 7
  inputStore.mkString( "[", ",", "]" )            //> res60: String = [(1,2),(2,3),(3,4),(3,7),(4,5),(4,6),(7,8)]
  val inputVector2 = inputStore.toVector          //> inputVector2  : Vector[(Int, Int)] = Vector((1,2), (2,3), (3,4), (3,7), (4,
                                                  //| 5), (4,6), (7,8))
  inputVector2.head                               //> res61: (Int, Int) = (1,2)
  inputVector2( 2 )                               //> res62: (Int, Int) = (3,4)
  inputVector2.last                               //> res63: (Int, Int) = (7,8)
  for {
    i <- 0 until n
    //Array(node, leaf) <- graph2(i) split " "
  } yield graph2( i ) split " "                   //> res64: scala.collection.immutable.IndexedSeq[Array[String]] = Vector(Array(
                                                  //| 1, 2), Array(2, 3), Array(3, 4), Array(3, 7), Array(4, 5), Array(4, 6), Arr
                                                  //| ay(7, 8))

  //Array("1 2", "2 3")(0).split(" ")
  val gr2Elem0 = graph2( 0 ) split " "            //> gr2Elem0  : Array[String] = Array(1, 2)
  val Array( node2, leaf2 ) = graph2( 0 ) split " "
                                                  //> node2  : String = 1
                                                  //| leaf2  : String = 2
  gr2Elem0( 0 )                                   //> res65: String = 1
  gr2Elem0( 1 )                                   //> res66: String = 2
  //*representation
  graph2.mkString( "[", ",", "]" )                //> res67: String = [1 2,2 3,3 4,3 7,4 5,4 6,7 8]
  graph2.exists { x => x.split( " " )( 0 ).toInt == 4 }
                                                  //> res68: Boolean = true
  for ( i <- graph2( 0 ) split " " ) yield i      //> res69: Array[String] = Array(1, 2)
  for ( i <- graph2( 0 ) split " " ) yield i.toInt//> res70: Array[Int] = Array(1, 2)
  val sampleAr1 = Array( "1", "2" )               //> sampleAr1  : Array[String] = Array(1, 2)
  //(Array("1", "2")).toInt
  //sampleAr1.toInt
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
  }                                               //> firstFork: (graph: Vector[(Int, Int)])Int

  def pathToRoot(
    graph: Vector[ ( Int, Int ) ],
    fromLeaf: Int,
    storedPath: Vector[ ( Int, Int ) ] = Vector() ): Vector[ ( Int, Int ) ] = {

    if ( graph.size > 0 ) {
      /*checking what ?*/
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
  } //*work as expected                           //> pathToRoot: (graph: Vector[(Int, Int)], fromLeaf: Int, storedPath: Vector[
                                                  //| (Int, Int)])Vector[(Int, Int)]

  def treeLeafs( graph: Vector[ ( Int, Int ) ] ): Vector[ Int ] =
    for {
      elem <- graph
      if ( graph.count { x => x._1 == elem._2 } == 0 )
    } yield elem._2 //*work as expected           //> treeLeafs: (graph: Vector[(Int, Int)])Vector[Int]

  /*all node._2 that not occure in node._1*/
  def treeLeafs2( graph: Vector[ ( Int, Int ) ] ): Vector[ Int ] =
    for {
      node <- graph //.distinct
      //leaf <- graph
      /*elements in nested loops must be distinct*/
      //if (leaf != node) && (node._1 != leaf._2)
      if graph.forall( elem => elem._1 != node._2 )
    } yield node._2 //*work as expected           //> treeLeafs2: (graph: Vector[(Int, Int)])Vector[Int]

  /*val xs = CC(
           (1, "one"),
           (2, "two"),
           (3, "three")).unzip
	// xs == (CC(1, 2, 3),
	//        CC(one, two, three))*/
  def treeLeafs3( graph: Vector[ ( Int, Int ) ] ): Vector[ Int ] = {
    //(graph filter (x => x._1 != x._2)).unzip._2
    val ( nodes, leafs ) = graph.unzip

    for {
      leaf <- leafs
      if !nodes.exists( node => leaf == node )
    } yield leaf
  } //*work as expected                           //> treeLeafs3: (graph: Vector[(Int, Int)])Vector[Int]

  def treeLeafs4( graph: Vector[ ( Int, Int ) ] ): Vector[ Int ] =
    for {
      elem <- graph
      //if !graph.exists(node => elem._1 == node._2)
      if !graph.exists( node => elem._2 == node._1 )
    } yield elem._2 //*work as expected           //> treeLeafs4: (graph: Vector[(Int, Int)])Vector[Int]

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
  } //*works                                      //> longestPath: (graph: Vector[(Int, Int)], leafs: Vector[Int])Vector[(Int, I
                                                  //| nt)]

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
  } //*works                                      //> pathToFork: (graph: Vector[(Int, Int)], leafs: Vector[Int], fork: Int, max
                                                  //| Path: Vector[(Int, Int)])Vector[(Int, Int)]

  val lineVecTest = Vector( ( 1, 2 ), ( 2, 3 ), ( 3, 4 ), ( 4, 5 ), ( 5, 6 ), ( 6, 7 ), ( 7, 8 ) )
                                                  //> lineVecTest  : scala.collection.immutable.Vector[(Int, Int)] = Vector((1,2
                                                  //| ), (2,3), (3,4), (4,5), (5,6), (6,7), (7,8))
  val test1 = Vector( ( 0, 1 ),
    ( 1, 2 ),
    ( 2, 3 ),
    ( 2, 4 ) ) //min==2                           //> test1  : scala.collection.immutable.Vector[(Int, Int)] = Vector((0,1), (1,
                                                  //| 2), (2,3), (2,4))
  /*
  0>1>2>3
  (0>1)>4>5
  (0>1)>(4)>6
  
  0>1>
     2>3
     4>
      5
      6
  */
  3 / 2                                           //> res71: Int(1) = 1
  val test2 = Vector( ( 0, 1 ),
    ( 1, 2 ),
    ( 1, 4 ),
    ( 2, 3 ),
    ( 4, 5 ),
    ( 4, 6 ) ) //min==2                           //> test2  : scala.collection.immutable.Vector[(Int, Int)] = Vector((0,1), (1,
                                                  //| 2), (1,4), (2,3), (4,5), (4,6))
  val test3 = Vector( ( 0, 1 ),
    ( 0, 8 ),
    ( 0, 15 ),
    ( 1, 2 ),
    ( 1, 5 ),
    ( 2, 3 ),
    ( 2, 4 ),
    ( 5, 6 ),
    ( 5, 7 ),
    ( 8, 9 ),
    ( 8, 12 ),
    ( 9, 10 ),
    ( 9, 11 ),
    ( 12, 13 ),
    ( 12, 14 ),
    ( 15, 16 ),
    ( 15, 19 ),
    ( 16, 17 ),
    ( 16, 18 ),
    ( 19, 20 ),
    ( 19, 21 ) ) //min==3                         //> test3  : scala.collection.immutable.Vector[(Int, Int)] = Vector((0,1), (0,
                                                  //| 8), (0,15), (1,2), (1,5), (2,3), (2,4), (5,6), (5,7), (8,9), (8,12), (9,10
                                                  //| ), (9,11), (12,13), (12,14), (15,16), (15,19), (16,17), (16,18), (19,20), 
                                                  //| (19,21))
  /*
  test 6: fails
  on test 8:
  Process has timed out.
  This may mean that
  your solution is
  not optimized enough to handle some cases.
  vector size 30 000+
  */
  val testGraph = test3                           //> testGraph  : scala.collection.immutable.Vector[(Int, Int)] = Vector((0,1),
                                                  //|  (0,8), (0,15), (1,2), (1,5), (2,3), (2,4), (5,6), (5,7), (8,9), (8,12), (
                                                  //| 9,10), (9,11), (12,13), (12,14), (15,16), (15,19), (16,17), (16,18), (19,2
                                                  //| 0), (19,21))
  val fork = firstFork( testGraph )               //> fork  : Int = 0
  val leafs = treeLeafs( testGraph )              //> leafs  : Vector[Int] = Vector(3, 4, 6, 7, 10, 11, 13, 14, 17, 18, 20, 21)
                                                  //| 
  treeLeafs2( testGraph )                         //> res72: Vector[Int] = Vector(3, 4, 6, 7, 10, 11, 13, 14, 17, 18, 20, 21)
  treeLeafs3( testGraph )                         //> res73: Vector[Int] = Vector(3, 4, 6, 7, 10, 11, 13, 14, 17, 18, 20, 21)
  treeLeafs4( testGraph )                         //> res74: Vector[Int] = Vector(3, 4, 6, 7, 10, 11, 13, 14, 17, 18, 20, 21)
  val maxPath = longestPath( testGraph, leafs )   //> maxPath  : Vector[(Int, Int)] = Vector((2,3), (1,2), (0,1))
  val minPath = pathToFork( testGraph, leafs, fork, maxPath )
                                                  //> minPath  : Vector[(Int, Int)] = Vector((2,3), (1,2), (0,1))

  Vector() :+ 1                                   //> res75: scala.collection.immutable.Vector[Int] = Vector(1)
  inputVector2                                    //> res76: Vector[(Int, Int)] = Vector((1,2), (2,3), (3,4), (3,7), (4,5), (4,6
                                                  //| ), (7,8))
  inputVector2.count { x => x._1 == 1 }           //> res77: Int = 1
  inputVector2.count { x => x._1 == 3 }           //> res78: Int = 2
  inputVector2.count { x => x._1 == 8 }           //> res79: Int = 0
  inputVector2.count { x => x._1 == 5 }           //> res80: Int = 0
  inputVector2.count { x => x._1 == 6 }           //> res81: Int = 0
  inputVector2.count { x => x._1 == 7 }           //> res82: Int = 1
  firstFork( lineVecTest )                        //> res83: Int = 1
  pathToRoot( inputVector2, 1 )                   //> res84: Vector[(Int, Int)] = Vector()
  pathToRoot( inputVector2, 3 )                   //> res85: Vector[(Int, Int)] = Vector((2,3), (1,2))
  pathToRoot( inputVector2, 8 )                   //> res86: Vector[(Int, Int)] = Vector((7,8), (3,7), (2,3), (1,2))

  def intPair( line: String ) /*: ( Int, Int )*/ = {
    var node = ""
    var leaf = "" //: String = "-1"
    var delim = ""

    for (
      i <- 0 until line.size
    ) {
      val char = line.charAt( i )

      println( "char=" + char )
      if ( i == 0 ) {
        //skip ?
        node += char
        //} else if ( char == line.last ) {
      } else if ( char.toString() == " " ) {
        //node ends
        delim = " "
        //*println ("delim=" + delim)
      } else {
        if ( delim == "" ) {
          node += char
        } else {
          leaf += char
          //*println ("leaf=" + leaf)
        }
      }
    }

    //( node.toInt, leaf.toInt )
    ( node, leaf )
  }                                               //> intPair: (line: String)(String, String)

  def strPair( line: String ) /*: ( String, String )*/ = {
    var node = ""
    var leaf = ""
    var delim = ""

    for (
      char <- line
    ) {

      //*println ("char=" + char)
      if ( char == line.head ) {
        //skip ?
        node += char
        //} else if ( char == line.last ) {
        //*} else if ( char == ' ' ) {
        /*or*/
      } else if ( char.isSpaceChar ) {
        //node ends
        delim = " "
        //*println ("delim=" + delim)
      } else {
        if ( delim == "" ) {
          node += char
        } else {
          leaf += char
          //*println ("leaf=" + leaf)
        }
      }
    }

    //( node.toInt, leaf.toInt )
    ( node, leaf )
  }                                               //> strPair: (line: String)(String, String)

  val fileStr = "1 2"                             //> fileStr  : String = 1 2

  fileStr.charAt( 0 )                             //> res87: Char = 1
  fileStr.charAt( 2 )                             //> res88: Char = 2
  intPair( fileStr )                              //> char=1
                                                  //| char= 
                                                  //| char=2
                                                  //| res89: (String, String) = (1,2)
  intPair( "3" )                                  //> char=3
                                                  //| res90: (String, String) = (3,"")
  strPair( "4" )                                  //> res91: (String, String) = (4,"")
  strPair( "5 6" )                                //> res92: (String, String) = (5,6)
  " ".charAt( 0 ).isSpaceChar                     //> res93: Boolean = true
  " ".charAt( 0 ).charValue() == " "              //> res94: Boolean = false
  " ".charAt( 0 ).charValue() == ' '              //> res95: Boolean = true
  " ".charAt( 0 ) == ' '                          //> res96: Boolean = true
  " ".charAt( 0 ).isWhitespace                    //> res97: Boolean = true
  " ".charAt( 0 ).getNumericValue                 //> res98: Int = -1
  " ".equalsIgnoreCase( " " )                     //> res99: Boolean = true
  val test10 = Map( 6 -> 8, 15 -> 1, 1 -> 14, 0 -> 3, 15 -> 13,
    9 -> 15, 2 -> 5, 14 -> 10, 4 -> 9, 7 -> 2,
    8 -> 7, 3 -> 4, 1 -> 6 )                      //> test10  : scala.collection.immutable.Map[Int,Int] = Map(0 -> 3, 14 -> 10, 
                                                  //| 1 -> 6, 6 -> 8, 9 -> 15, 2 -> 5, 7 -> 2, 3 -> 4, 8 -> 7, 4 -> 9, 15 -> 13)
                                                  //| 
  test10.toList.sorted                            //> res100: List[(Int, Int)] = List((0,3), (1,6), (2,5), (3,4), (4,9), (6,8), 
                                                  //| (7,2), (8,7), (9,15), (14,10), (15,13))
  test10.toList sortWith ((n1:(Int,Int),n2:(Int,Int)) => n1._1 == n2._2 )
                                                  //> res101: List[(Int, Int)] = List((0,3), (14,10), (6,8), (1,6), (9,15), (2,5
                                                  //| ), (7,2), (4,9), (3,4), (8,7), (15,13))
  test10.toVector sortWith ((n1:(Int,Int),n2:(Int,Int)) => n1._1 > n2._2 )
                                                  //> res102: scala.collection.immutable.Vector[(Int, Int)] = Vector((14,10), (9
                                                  //| ,15), (15,13), (6,8), (8,7), (3,4), (7,2), (0,3), (1,6), (2,5), (4,9))
  test10.toSeq sortWith ((n1:(Int,Int),n2:(Int,Int)) => n1._1 < n2._2 )
                                                  //> res103: Seq[(Int, Int)] = ArrayBuffer((2,5), (1,6), (0,3), (7,2), (3,4), (
                                                  //| 4,9), (6,8), (8,7), (9,15), (14,10), (15,13))
}