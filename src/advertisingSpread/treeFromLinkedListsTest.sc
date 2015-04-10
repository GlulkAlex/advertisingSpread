package advertisingSpread

object treeFromLinkedListsTest {
  /*<!-- Linked lists using arrays of node -->
	Here is more then 1 root
	so,
	may be 'dummy Root' needed as 1st element
	if path finding algorithm will not work correctly:
	t,0,(3,4),n/a(R),0,1,1,f
	t,1,(9,10),n/a(R),0,8,1,f
	t,2,(15,16),n/a(R),0,15,1,f
	f,3,(5,6),0,1,2,2,f
	f,4,(7,8),0,1,5,2,f
	f,5,n/a(L),3,2,3,3,t
	f,6,n/a(L),3,2,4,3,t
	f,7,n/a(L),4,5,6,3,t
	f,8,n/a(L),4,5,7,3,t
	f,9,(11,12),1,8,9,2,f
	f,10,(13,14),1,8,12,2,f
	f,11,n/a(L),9,9,10,3,t
	f,12,n/a(L),9,9,11,3,t
	f,13,n/a(L),10,12,13,3,t
	f,14,n/a(L),10,12,14,3,t
	f,15,(17),2,15,16,2,f
	f,16,(19,20),2,15,19,2,f
	f,17,(18),15,16,17,3,f
	f,18,n/a(L),15,16,18,3,t
	f,19,n/a(L),16,19,20,3,t
	f,20,n/a(L),16,19,21,3,t
	<!-- Linked lists using arrays of node -->
	record structure
	listHead:Boolean(root mark/flag)
	*Index:Int array of size = n (known in advance)
	  Auto generated, no need to implement in data structure
	Next:Int or List[Int](point to 'child' index)
	  None for Leafs or one or more
	Prev:Int(point to 'parent/NodeValue' index)
	  Restriction: only one allowed/permitted or None for Root
	NodeValue:Int(parent value)
	child:Int(child value)
	childHeight:Int(Height of tree for child/leaf)
	Leaf:Boolean(leaf mark/flag)
	
	listHead  *Index  Next  Prev    NodeValue Child ChildHeight Leaf
	f         0       10    12      6         8     7           f
	f         1       2,12  5       15        1     5           f
	f         2       7     1       1         14    6           f
	t*        3       11    n/a(r)  0         3     1           f
	f         4       n/a(L)5       15        13    5           t
	f         5       1,4   8       9         15    4           f
	f         6       n/a(L)9       2         5     10*         t
	f         7       n/a(L)2       14        10    7           t
	f         8       5     11      4         9     3           f
	f         9       6     10      7         2     9           f
	f         10      9     0       8         7     8           f
	f         11      8     3       3         4     2           f
	f         12      0     1       1         6     6           f
	<!-- ------------------------------------------------------------  -->
	Initial value (default)
	t,currIndex(auto),n/a(L),n/a(R),currVal(-1),currChild(-1),1,t
	currIndex,currVal,currChild -- always known / available
	(-1)non existing value, assume then actual input values are positive
	<!-- ------------------------------------------------------------  -->
	path from leaf with max height to root
	array filter(Leaf == true) max ChildHeight
	(L)5-2-7-8-6-1-15-9-4-0(R)
	(L)5-2-7-8-6-(1)-[15]-9-4-0(R)
	on the same path
	5 to 1 = 10 - 5
	1 to 0(R) = 5 - 0 or 5
	1 to 13 = mergePaths = same level, so may be same parents, so = 2
	1 to 10 = mergePaths = 10 is descendant of 1, so 7-5 = 2
	<!-- ------------------------------------------------------------  -->
	             ([1])>6>8>7>2>5
	0>3>4>9>[15]>([1])>14>10
	        [15]>13
	<!-- ------------------------------------------------------------  -->
	when height of node/leaf known
	to find a path between any arbitrary node in tree (connected graph)
	1.check if on same level
	1.1 if so, check parent node
	1.1.1 if common then path = node1 + common node + node2 & path.size = 2
	1.2 if not go up to previous level then (1)
	2.if one node deeper / below another
	2.1 go up from deeper node with bigger height to same level then (1.1)*/

  /*? May be use
	Map(Leaf -> Root) where
	'Leaf' is 'Child'
	'Root is List(NodeValue, isListHead, Next, Prev, ChildHeight, isLeaf)' ?
	? and change 'List' content if necessery ?
	? replacing it with new 'List' ?*/
  /*& to
	constantly change 'Map' in cycle
	? use 'reduce' pr '.foldLeft' to
	obtain one result in return ?*/
  /*
	map.keys
  map.keySet
  map.keysIterator
  &
  map.values
  map.valuesIterator
  //Use assignment operators to
  //reassign a 'map'
  map += x -> y
  map1 ++= map2
  map -= x
  map --= seq
	*/
  val testMap1 /*: Map[ _, _ ]*/ = Map( "v" -> "w", "x" -> "y" )
                                                  //> testMap1  : scala.collection.immutable.Map[String,String] = Map(v -> w, x -
                                                  //| > y)
  /*'+=' is not a member of 'Map'*/
  //testMap1 += "z" -> "w"
  /*reassignment to 'val'*/
  //testMap1 = testMap1 + "z" -> "w"
  testMap1( "v" )                                 //> res0: String = w
  testMap1( "x" )                                 //> res1: String = y
  /*java.util.NoSuchElementException: key not found: 1*/
  //testMap1("1")
  testMap1.getOrElse( "1", None )                 //> res2: java.io.Serializable = None
  /*insertion with
  'duplicate key'
  updates previous entry with
  subsequent 'value'*/
  testMap1 + ( "v" -> "z" )                       //> res3: scala.collection.immutable.Map[String,String] = Map(v -> z, x -> y)
  /*same as*/
  testMap1.updated( "v", "z" )                    //> res4: scala.collection.immutable.Map[String,String] = Map(v -> z, x -> y)
  testMap1.mapValues { x => x + 1 }               //> res5: scala.collection.immutable.Map[String,String] = Map(v -> w1, x -> y1)
                                                  //| 
  testMap1.mapValues { x => "z" }                 //> res6: scala.collection.immutable.Map[String,String] = Map(v -> z, x -> z)

  ( for ( i <- 1 to 5 ) yield i ).map( _ + 1 )    //> res7: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 3, 4, 5, 6)
  ( for ( i <- 1 to 5 ) yield i ).map( ( x: Int ) => Map.empty + ( x -> ( x + 1 ) ) )
                                                  //> res8: scala.collection.immutable.IndexedSeq[scala.collection.immutable.Map[
                                                  //| Int,Int]] = Vector(Map(1 -> 2), Map(2 -> 3), Map(3 -> 4), Map(4 -> 5), Map(
                                                  //| 5 -> 6))

  val emptyStartMap: Map[ Int, Int ] = Map()      //> emptyStartMap  : Map[Int,Int] = Map()
  ( for ( i <- 1 to 5 ) yield i )
    .foldLeft( emptyStartMap )( ( m: Map[ Int, Int ], x: Int ) => m + ( x -> ( x + 1 ) ) )
                                                  //> res9: Map[Int,Int] = Map(5 -> 6, 1 -> 2, 2 -> 3, 3 -> 4, 4 -> 5)
  /*'for (x <- e1) yield e2' is translated to
  'e1.map(x => e2)'*/
  ( 1 to 5 )
    .map( i => i )
    .foldLeft( emptyStartMap )(
      ( m: Map[ Int, Int ], x: Int ) => m + ( x -> ( x * 2 ) ) )
                                                  //> res10: Map[Int,Int] = Map(5 -> 10, 1 -> 2, 2 -> 4, 3 -> 6, 4 -> 8)
  Double.PositiveInfinity.toInt                   //> res11: Int = 2147483647
  Double.NegativeInfinity.toInt                   //> res12: Int = -2147483648
  /**Alternative*/
  /*
  'root' may point to itself as it's parent
  not 'None' / 'null' but same value
  that eliminates use of 'Option' type
  'Next' or 'Child' may be selfreference too
  */
  case class Edge0( var isListHead: Boolean = false, /*or simply check
    if (Prev or Parent == None or NodeValue) */
                    var Next: Option[ Int ] = None, /*same as 'Child' ?*/
                    var Prev: Option[ Int ] = None, /*Parent or 'root'*/
                    var NodeValue: Option[ Int ], /*start node*/
                    var Child: Option[ Int ], /*end node*/
                    var ChildHeight: Int = 1,
                    var isLeaf: Boolean = true ) /*or simply check
    if (Next or Child == None or NodeValue or Double.NegativeInfinity.toInt) */

  /*possible to
  check / maintain chain
  with / of two links
  'Prev'->'Current'->'Next'
  for traversal in both directions
  up to 'root' &
  down to 'leaf'
  where
  from input directly available only
  'Prev'->'Current' or
  'Current'->'Next'*/
  /*Also:
  for each node possible to maintain field 'rank[x]'
  In general
  rank[x] = 1 + (max rank of x’s children)
  so,
  ? 'Rank' as inverted 'Height' ?
  for 'leafs' == 0
  for 'root' == 1 + (max 'rank' of children)
  */
  /*
  in addition to 'edges' list
  possible 'root' 'edge'
  with no actual 'Prev' as 'Prev == Current' &
  only one 'Next',
  'CurrentHeight == 0'
  example:
  Map( -1 -> Edge( Prev = -1, Current = -1, Next = 0, 0, ? ))
  */
  /*so, actualy this is
  'linkedEdges' or
  'Node' with pointers to pair of adjusted 'nodes'*/
  case class Edge(
    var Prev: Int, /*Parent or 'root' or selfreference*/
    /*'NodeValue' may be same as
                   'Prev' or 'Next' &
                   in this case ambiguous / redundant
                   but it allow to evaluate / compute
                   'isListHead' or 'isTreeRoot' &
                   'isLeaf'*/
    var Current: Int, /*may this be second pair value
                   with pointer to 'parent' in 'Prev'*/
    var Next: Int, /*same as 'Child' or
                   selfreference*/
    var CurrentHeight: Int = 1, /*'0' for 'root'*/
    var CurrentRank: Int = 0 /*'0' for 'leaf'*/ ) {
    override def toString() = "{p:" + this.Prev +
      ",C:" + this.Current +
      ",n:" + this.Next +
      ",h:" + this.CurrentHeight +
      ",R:" + this.CurrentRank + "}"
  }

  case class Edges( isListHead: Boolean,
                    Next: Option[ Int ],
                    Prev: Option[ Int ],
                    NodeValue: Option[ Int ], /*start node*/
                    Child: Option[ Int ], /*end node*/
                    ChildHeight: Int,
                    isLeaf: Boolean ) {
    val height: Int = 1
    // invoke auxiliary constructor
    def this() {
      this(
        isListHead = true,
        Next = None /*Int.в*/ ,
        Prev = Option.empty /*option.orNull*/ ,
        NodeValue = None /*(-1)*/ ,
        Child = None /*(-1)*/ ,
        ChildHeight = 1,
        isLeaf = true )

      /*works if
        'auxiliary' has
        more parameters then 'primary constructor'*/
      //this.ChildHeight = height
    }
  }

  val edgesUnsorted: Seq[ ( Int, Int ) ] = Seq(
    ( 6, 8 ),
    ( 15, 1 ),
    ( 1, 14 ),
    ( 0, 3 ),
    ( 15, 13 ),
    ( 9, 15 ),
    ( 2, 5 ),
    ( 14, 10 ),
    ( 4, 9 ),
    ( 7, 2 ),
    ( 8, 7 ),
    ( 3, 4 ),
    ( 1, 6 ) )                                    //> edgesUnsorted  : Seq[(Int, Int)] = List((6,8), (15,1), (1,14), (0,3), (15,1
                                                  //| 3), (9,15), (2,5), (14,10), (4,9), (7,2), (8,7), (3,4), (1,6))
  val edgesMap = edgesUnsorted.toMap              //> edgesMap  : scala.collection.immutable.Map[Int,Int] = Map(0 -> 3, 14 -> 10,
                                                  //|  1 -> 6, 6 -> 8, 9 -> 15, 2 -> 5, 7 -> 2, 3 -> 4, 8 -> 7, 4 -> 9, 15 -> 13)
                                                  //| 
  val edgesLeafToParentMap = edgesUnsorted.map( ( x: ( Int, Int ) ) => ( x._2, x._1 ) ).toMap
                                                  //> edgesLeafToParentMap  : scala.collection.immutable.Map[Int,Int] = Map(5 -> 
                                                  //| 2, 10 -> 14, 14 -> 1, 1 -> 15, 6 -> 1, 9 -> 4, 13 -> 15, 2 -> 7, 7 -> 8, 3 
                                                  //| -> 0, 8 -> 6, 4 -> 3, 15 -> 9)
  val edgesLeafToParentMap2 = edgesUnsorted.map( ( x: ( Int, Int ) ) => x._2 -> x._1 ).toMap
                                                  //> edgesLeafToParentMap2  : scala.collection.immutable.Map[Int,Int] = Map(5 ->
                                                  //|  2, 10 -> 14, 14 -> 1, 1 -> 15, 6 -> 1, 9 -> 4, 13 -> 15, 2 -> 7, 7 -> 8, 3
                                                  //|  -> 0, 8 -> 6, 4 -> 3, 15 -> 9)
  /*duplicated 'root' values in keys*/
  edgesMap.size                                   //> res13: Int = 11
  edgesLeafToParentMap.size                       //> res14: Int = 13
  edgesUnsorted.size                              //> res15: Int = 13
  /*tree traversal
  from leaf to root*/
  edgesLeafToParentMap2.getOrElse( 13, -1 )       //> res16: Int = 15
  edgesLeafToParentMap2.getOrElse( 15, -1 )       //> res17: Int = 9
  edgesLeafToParentMap2.getOrElse( 9, -1 )        //> res18: Int = 4
  edgesLeafToParentMap2.getOrElse( 4, -1 )        //> res19: Int = 3
  edgesLeafToParentMap2.getOrElse( 3, -1 )        //> res20: Int = 0
  /*root*/
  edgesLeafToParentMap2.getOrElse( 0, -1 )        //> res21: Int = -1

  edgesLeafToParentMap2.keys.groupBy { x => 13 }  //> res22: scala.collection.immutable.Map[Int,Iterable[Int]] = Map(13 -> Set(5,
                                                  //|  10, 14, 1, 6, 9, 13, 2, 7, 3, 8, 4, 15))
  edgesLeafToParentMap2.keys.takeWhile { x => x == 13 }
                                                  //> res23: Iterable[Int] = Set()
  edgesLeafToParentMap2.keys.groupBy { x => 15 }  //> res24: scala.collection.immutable.Map[Int,Iterable[Int]] = Map(15 -> Set(5,
                                                  //|  10, 14, 1, 6, 9, 13, 2, 7, 3, 8, 4, 15))
  edgesLeafToParentMap2.keys.dropWhile { x => x == 15 }
                                                  //> res25: Iterable[Int] = Set(5, 10, 14, 1, 6, 9, 13, 2, 7, 3, 8, 4, 15)
  edgesLeafToParentMap2.keys.groupBy { x => 0 }   //> res26: scala.collection.immutable.Map[Int,Iterable[Int]] = Map(0 -> Set(5, 
                                                  //| 10, 14, 1, 6, 9, 13, 2, 7, 3, 8, 4, 15))
  edgesLeafToParentMap2.keys.iterator.withFilter { x => 0 == x }
                                                  //> res27: Iterator[Int] = empty iterator
  edgesLeafToParentMap2.keys.iterator.indexOf( 9 )//> res28: Int = 5
  edgesLeafToParentMap2.keys.iterator.drop( 5 ).next()
                                                  //> res29: Int = 9
  edgesLeafToParentMap2.iterator.drop( 5 - 1 )    //> res30: Iterator[(Int, Int)] = non-empty iterator
  edgesLeafToParentMap2.iterator.drop( 5 - 1 ).next()
                                                  //> res31: (Int, Int) = (6,1)
  edgesLeafToParentMap2.iterator.drop( 5 ).next() //> res32: (Int, Int) = (9,4)
  val edgesFrom_test3: String = "21\n" +
    "0 1\n" +
    "0 8\n" +
    "0 15\n" +
    "1 2\n" +
    "1 5\n" +
    "2 3\n" +
    "2 4\n" +
    "5 6\n" +
    "5 7\n" +
    "8 9\n" +
    "8 12\n" +
    "9 10\n" +
    "9 11\n" +
    "12 13\n" +
    "12 14\n" +
    "15 16\n" +
    "15 19\n" +
    "16 17\n" +
    "16 18\n" +
    "19 20\n" +
    "19 21"                                       //> edgesFrom_test3  : String = 21
                                                  //| 0 1
                                                  //| 0 8
                                                  //| 0 15
                                                  //| 1 2
                                                  //| 1 5
                                                  //| 2 3
                                                  //| 2 4
                                                  //| 5 6
                                                  //| 5 7
                                                  //| 8 9
                                                  //| 8 12
                                                  //| 9 10
                                                  //| 9 11
                                                  //| 12 13
                                                  //| 12 14
                                                  //| 15 16
                                                  //| 15 19
                                                  //| 16 17
                                                  //| 16 18
                                                  //| 19 20
                                                  //| 19 21
  val inputLines = edgesFrom_test3
    .split( "\n" )                                //> inputLines  : Array[String] = Array(21, 0 1, 0 8, 0 15, 1 2, 1 5, 2 3, 2 4
                                                  //| , 5 6, 5 7, 8 9, 8 12, 9 10, 9 11, 12 13, 12 14, 15 16, 15 19, 16 17, 16 1
                                                  //| 8, 19 20, 19 21)

  edgesFrom_test3.size                            //> res33: Int = 106
  val edgesNumber = inputLines( 0 ).split( " " )( 0 ).toInt
                                                  //> edgesNumber  : Int = 21
  val inputLine = inputLines( 1 ).split( " " )    //> inputLine  : Array[String] = Array(0, 1)
  /*'Array' is mutable,
  so
  no need to create new collection with
  chenged elements*/
  val inputArray1: Seq[ ( Int, Int ) ] = for ( line <- inputLines.tail ) yield {
    ( line.split( " " )( 0 ).toInt, line.split( " " )( 1 ).toInt )
                                                  //> inputArray1  : Seq[(Int, Int)] = ArraySeq((0,1), (0,8), (0,15), (1,2), (1,
                                                  //| 5), (2,3), (2,4), (5,6), (5,7), (8,9), (8,12), (9,10), (9,11), (12,13), (1
                                                  //| 2,14), (15,16), (15,19), (16,17), (16,18), (19,20), (19,21))
  }

  edgesNumber == inputArray1.size                 //> res34: Boolean = true
  inputArray1( 20 )                               //> res35: (Int, Int) = (19,21)

  def fillTreeArray( edges: Seq[ ( Int, Int ) ] /*,
                     treeList: Seq[ Edges ] = Seq.empty*/ ): Seq[ Edges ] = {
      def findParent( nodeValue: Option[ Int ],
                      treeList: Seq[ Edges ] = Seq.empty ): Option[ Edges ] =
        /*one possible element of Edges return or None*/
        treeList.find( ( elem: Edges ) => elem.Child == nodeValue )
      def findChild( nodeChild: Option[ Int ],
                     treeList: Seq[ Edges ] = Seq.empty ): Option[ Edges ] =
        /*one possible element of Edges return or None*/
        treeList.find( ( elem: Edges ) => elem.NodeValue == nodeChild )
      def updateNodeHeight: Int = ???
      def loop( edgesRemains: Seq[ ( Int, Int ) ],
                treeList: Seq[ Edges ] = Seq.empty ): Seq[ Edges ] = {
        if ( edgesRemains.isEmpty ) {
          treeList
        } else {
          val nodeValue: Option[ Int ] = Option( edgesRemains.head._1 )
          val nodeChild: Option[ Int ] = Option( edgesRemains.head._2 )
          val parent: Option[ Edges ] = findParent( nodeValue,
            treeList )
          /*must be reference to the element in 'treeList'
          if 'Array' then simply 'index'
          if 'List' then ?*/
          val prevNode: Option[ Int ] = /*Option.empty*/
            if ( parent == None ) {
              None
            } else {
              /*? & update parent.nextNode ?*/
              parent.get.NodeValue
            }
          val isListHead: Boolean = true
          val nextNode: Option[ Int ] = None
          val childNodeHeight: Int = 1
          val isLeaf: Boolean = true
          val newEdge: Edges = new Edges(
            isListHead,
            nextNode,
            prevNode,
            nodeValue,
            nodeChild,
            childNodeHeight,
            isLeaf )
          /*? only change whole 'newEdge'?
          too slow & many operations */
          //val (seq1, seq2) = seq.partition(p)
          //seq1 ++ Seq(newEdge) ++ seq2
          loop( edgesRemains = edgesRemains.tail,
            newEdge +: treeList )
        }
      }

    loop( edgesRemains = edges )
  }                                               //> fillTreeArray: (edges: Seq[(Int, Int)])Seq[advertisingSpread.treeFromLinke
                                                  //| dListsTest.Edges]

  /*no extra lines with edges number / sequence size*/
  val n = edgesUnsorted.size                      //> n  : Int = 13
  /*? top common 'root' ?*/
  val emptyEdge: Edge = //new Edge()
    Edge(
      Prev = -1,
      Current = -1,
      Next = -1,
      CurrentHeight = 0,
      CurrentRank = 0 )                           //> emptyEdge  : advertisingSpread.treeFromLinkedListsTest.Edge = {p:-1,C:-1,n
                                                  //| :-1,h:0,R:0}

  var treeMap: Map[ Int, Edge ] = Map() //Map.empty
                                                  //> treeMap  : Map[Int,advertisingSpread.treeFromLinkedListsTest.Edge] = Map()
                                                  //| 
  //(someParentNode,middleNode,someNextNode,optimalHeight,optimalRank)
  var middleEdge: Edge = Edge(
    Prev = -1,
    Current = -1,
    Next = -1,
    CurrentHeight = 0,
    CurrentRank = 0 )                             //> middleEdge  : advertisingSpread.treeFromLinkedListsTest.Edge = {p:-1,C:-1,
                                                  //| n:-1,h:0,R:0}
  //(someParentNode,maxDistantLeaf,maxDistantLeaf,maxHeight,0)
  var maxDistantEdge: Edge = Edge(
    Prev = -1,
    Current = -1,
    Next = -1,
    CurrentHeight = 0,
    CurrentRank = 0 )                             //> maxDistantEdge  : advertisingSpread.treeFromLinkedListsTest.Edge = {p:-1,C
                                                  //| :-1,n:-1,h:0,R:0}
  var onlyOneMaxDistantEdge: Boolean = false      //> onlyOneMaxDistantEdge  : Boolean = false

  /*must be done recursively
  but stop somewhere*/
  def updateParent( node: Edge,
                    treeList: Map[ Int, Edge ] ): Unit /*or new 'tree'*/ = {
    /*one possible element of Edges return or None*/
    val parent: Edge /*Option[Edge]*/ = treeMap
      .getOrElse( node.Prev, emptyEdge )

    if ( parent == emptyEdge ) {
      /*done
      & exit*/
    } else {
      /*(1,2,2,1/*0 for root*/,0/*while no child*/)*/
      /*single 'Next' is pointless for 'children'
          so,
          store only deepest one ?*/
      if ( parent.CurrentRank - 1 < node.CurrentRank ) {
        /*UpDate*/
        parent.Next = node.Current
        parent.CurrentRank = node.CurrentRank + 1
        /*'height' changes when new ancestor(s) added*/
        if ( parent.CurrentHeight + 1 == node.CurrentHeight ) {
          /*stay the same*/
        } else {
          node.CurrentHeight = parent.CurrentHeight + 1
          /*? all children must suffer ?
          in due time / it they turn */
        }
        
        if ( parent.Current == parent.Next ) {
          /*common 'root'*/
          /*done
          & exit*/
        } else {
          updateParent( parent, treeList )
        }
      }

    }
  }                                               //> updateParent: (node: advertisingSpread.treeFromLinkedListsTest.Edge, treeL
                                                  //| ist: Map[Int,advertisingSpread.treeFromLinkedListsTest.Edge])Unit

  /*must be done recursively
  but stop somewhere*/
  def updateChildren( node: Edge, /*parent of one child*/
                      treeList: Map[ Int, Edge ] ): Unit /*or new updated 'tree'*/ = {
    /* case example:
    S              *     E
    0>3>4>9>15>1>6>8>7>2>5
    (0>3>4>9>15)(>1)>14>10
    (0>3>4>9>15)>13
    {0>3
    8>7>2>5}
    ( 6, 8 ),
    ( 15, 1 ),
    ( 1, 14 ),
    ( 0, 3 ),
    ( 15, 13 ),
    ( 9, 15 ),
    ( 2, 5 ),
    ( 14, 10 ),
    ( 4, 9 ),
    ( 7, 2 ),
    ( 8, 7 ),
    ( 3, 4 ),
    ( 1, 6 )
    steps:
    (1)treeList(),
        node(8 -> {p:6,C:8,n:8,h:1,R:0}),
      no children in 'treeList'
    (2)treeList((8 -> {p:6,C:8,n:8,h:1,R:0})),
        node(1 -> {p:15,C:1,n:1,h:1,R:0}),
        no children in 'treeList'
    (3)treeList(
	      (8 -> {p:6,C:8,n:8,h:1,R:0}),
	      (1 -> {p:15,C:1,n:14,h:2,R:1})),/*updated*/
      node(14 -> {p:1,C:14,n:14,h:3,R:0}),/*updated*/
      no children in 'treeList',
      has parent in 'treeList'
    (4)treeList(
      (8 -> {p:6,C:8,n:8,h:1,R:0}),
      (1 -> {p:15,C:1,n:14,h:2,R:1}),
      (14 -> {p:1,C:14,n:14,h:3,R:0})),
      node(3 -> {p:0,C:3,n:3,h:1,R:0}),
      no children in 'treeList',
      no parent in 'treeList'
    (5)treeList(
      (8 -> {p:6,C:8,n:8,h:1,R:0}),
      (1 -> {p:15,C:1,n:14,h:2,R:1}),
      (14 -> {p:1,C:14,n:14,h:3,R:0}),
      (3 -> {p:0,C:3,n:3,h:1,R:0})),
      node(13 -> {p:15,C:13,n:13,h:1,R:0}),
      no children in 'treeList',
      no parent in 'treeList'
    (6)treeList(
      (8 -> {p:6,C:8,n:8,h:1,R:0}),
      (1 -> {p:15,C:1,n:14,h:2,R:1}),/*updated*/
      (14 -> {p:1,C:14,n:14,h:3,R:0}),
      (3 -> {p:0,C:3,n:3,h:1,R:0}),
      (13 -> {p:15,C:13,n:13,h:2,R:0})),/*updated*/
      node(15 -> {p:9,C:15,n:1/*max 'R'*/,h:1,R:2}),/*updated*/
      has children in 'treeList',
      no parent in 'treeList'
    (7)treeList(
        (8 -> {p:6,C:8,n:8,h:1,R:0}),
	      (1 -> {p:15,C:1,n:14,h:2,R:1}),
	      (14 -> {p:1,C:14,n:14,h:3,R:0}),
	      (3 -> {p:0,C:3,n:3,h:1,R:0}),
	      (13 -> {p:15,C:13,n:13,h:2,R:0}),
	      (15 -> {p:9,C:15,n:1,h:1,R:2})),
      node(5 -> {p:2,C:5,n:5,h:1,R:0}),
      no children in 'treeList',
      no parent in 'treeList'
    (8)treeList(
        (8 -> {p:6,C:8,n:8,h:1,R:0}),
	      (1 -> {p:15,C:1,n:14,h:2,R:1}),
	      (14 -> {p:1,C:14,n:10,h:3,R:1}),/*Updated*/
	      (3 -> {p:0,C:3,n:3,h:1,R:0}),
	      (13 -> {p:15,C:13,n:13,h:2,R:0}),
	      (15 -> {p:9,C:15,n:1,h:1,R:2}),
	      (5 -> {p:2,C:5,n:5,h:1,R:0})),
      node(10 -> {p:14,C:10,n:10,h:4,R:0}),/*updated*/
      no children in 'treeList',
      has parent in 'treeList'
    (9)treeList(
        (8 -> {p:6,C:8,n:8,h:1,R:0}),
	      (1 -> {p:15,C:1,n:14,h:3,R:2}),/*updated*/
	      (14 -> {p:1,C:14,n:10,h:4,R:1}),/*updated*/
	      (3 -> {p:0,C:3,n:3,h:1,R:0}),
	      (13 -> {p:15,C:13,n:13,h:2,R:0}),
	      (15 -> {p:9,C:15,n:1,h:2,R:3}),/*updated*/
	      (5 -> {p:2,C:5,n:5,h:1,R:0}),
	      (10 -> {p:14,C:10,n:10,h:5,R:0})),/*updated*/
      node(9 -> {p:4,C:9,n:15,h:1,R:4}),/*updated*/
      no parent in 'treeList'
      has children in 'treeList',
    (10)treeList(
        (8 -> {p:6,C:8,n:8,h:1,R:0}),
	      (1 -> {p:15,C:1,n:14,h:3,R:2}),
	      (14 -> {p:1,C:14,n:10,h:4,R:1}),
	      (3 -> {p:0,C:3,n:3,h:1,R:0}),
	      (13 -> {p:15,C:13,n:13,h:2,R:0}),
	      (15 -> {p:9,C:15,n:1,h:2,R:3}),
	      (5 -> {p:2,C:5,n:5,h:2,R:0}),/*updated*/
	      (10 -> {p:14,C:10,n:10,h:5,R:0}),
	      (9 -> {p:4,C:9,n:15,h:1,R:4})),
      node(2 -> {p:7,C:2,n:5,h:1,R:1}),/*updated*/
      no parent in 'treeList',
      has children in 'treeList'
    (11)treeList(
        (8 -> {p:6,C:8,n:7,h:1,R:1}),/*updated*/
	      (1 -> {p:15,C:1,n:14,h:3,R:2}),
	      (14 -> {p:1,C:14,n:10,h:4,R:1}),
	      (3 -> {p:0,C:3,n:3,h:1,R:0}),
	      (13 -> {p:15,C:13,n:13,h:2,R:0}),
	      (15 -> {p:9,C:15,n:1,h:2,R:3}),
	      (5 -> {p:2,C:5,n:5,h:4,R:0}),/*updated*/
	      (10 -> {p:14,C:10,n:10,h:5,R:0}),
	      (9 -> {p:4,C:9,n:15,h:1,R:4}),
	      (2 -> {p:7,C:2,n:5,h:3,R:1})),/*updated*/
      node(7 -> {p:8,C:7,n:7,h:2,R:2}),/*updated*/
      has parent in 'treeList',
      has children in 'treeList'
    (12)treeList(
        (8 -> {p:6,C:8,n:7,h:1,R:1}),
	      (1 -> {p:15,C:1,n:14,h:5,R:2}),/*updated*/
	      (14 -> {p:1,C:14,n:10,h:6,R:1}),/*updated*/
	      (3 -> {p:0,C:3,n:4,h:1,R:1}),/*updated*/
	      (13 -> {p:15,C:13,n:13,h:2,R:0}),
	      (15 -> {p:9,C:15,n:1,h:4,R:3}),/*updated*/
	      (5 -> {p:2,C:5,n:5,h:4,R:0}),
	      (10 -> {p:14,C:10,n:10,h:7,R:0}),/*updated*/
	      (9 -> {p:4,C:9,n:15,h:3,R:4}),/*updated*/
	      (2 -> {p:7,C:2,n:5,h:3,R:1}),
	      (7 -> {p:8,C:7,n:7,h:2,R:2})),
      node(4 -> {p:3,C:4,n:9,h:2,R:5}),/*updated*/
      has parent in 'treeList',
      has children in 'treeList'
    (13)treeList(
        (8 -> {p:6,C:8,n:7,h:7,R:3}),/*updated*/
	      (1 -> {p:15,C:1,n:6/*new max*/,h:5,R:5/*max R*/}),/*updated*/
	      (14 -> {p:1,C:14,n:10,h:6,R:1}),
	      (3 -> {p:0,C:3,n:4,h:1,R:9}),/*updated*/
	      (13 -> {p:15,C:13,n:13,h:2,R:0}),
	      (15 -> {p:9,C:15,n:1,h:4,R:6}),/*updated*/
	      (5 -> {p:2,C:5,n:5,h:4,R:0}),
	      (10 -> {p:14,C:10,n:10,h:7,R:0}),
	      (9 -> {p:4,C:9,n:15,h:3,R:7}),/*updated*/
	      (2 -> {p:7,C:2,n:5,h:3,R:1}),
	      (7 -> {p:8,C:7,n:7,h:8,R:2}),/*updated*/
	      (4 -> {p:3,C:4,n:9,h:2,R:8})),/*updated*/
      node(6 -> {p:1,C:6,n:8,h:6,R:4}),/*updated*/
      has parent in 'treeList',
      has children in 'treeList'
    */
    
    /*cycle trough 'treeList'
    check conditions
    update elements
    as side effect
    or
    return
    new updated 'tree'*/
    def loop( parent: Edge,
      remainingNodes: Map[ Int, Edge ],
      cildrenList: List[Edge]): List[Edge] = {
      
      //if (cildrenList.isEmpty) {
      if (remainingNodes.isEmpty) {
        cildrenList
      } else {
	      loop(parent, remainingNodes.tail, cildrenList.tail)
      }
    }
                      
                      
    /*one possible element of Edges return or None*/
    val child: Edge /*Option[Edge]*/ = treeMap
      .getOrElse( node.Prev, emptyEdge )

    /*must check all nodes with 'node.Current' as '.Prev'*/
    /*if cildrenList.size > 0 then
    for each element in list do
    'updateChild'*/
    /*val childrenList =
      treeMap
        .values
          .filter { x => x.Prev == mapValue.Current }*/
                                                    
    /*val child1 = childrenList.head
                                                    
    /*child update*/
    child1.CurrentHeight += 1
    treeMap( 1 )*/
        
    if ( child == emptyEdge ) {
      /*done
      & exit*/
    } else {
      /*(1,2,2,1/*0 for root*/,0/*while no child*/)*/
      /*single 'Next' is pointless for 'children'
          so,
          store only deepest one ?*/
      if ( child.CurrentRank - 1 < node.CurrentRank ) {
        /*UpDate*/
        child.Next = node.Current
        child.CurrentRank = node.CurrentRank + 1
        /*'height' changes when new ancestor(s) added*/
        if ( child.Current == child.Next ) {
          /*common 'root'*/
          /*done
          & exit*/
        } else {
          //updateParent( child, treeList )
        }
      }

    }
  }                                               //> updateChildren: (node: advertisingSpread.treeFromLinkedListsTest.Edge, tre
                                                  //| eList: Map[Int,advertisingSpread.treeFromLinkedListsTest.Edge])Unit

  for ( i <- 0 until n ) {
    val mapValue: Edge = Edge(
      Prev = edgesUnsorted( i )._1,
      Current = edgesUnsorted( i )._2,
      Next = edgesUnsorted( i )._2,
      CurrentHeight = 1 /*as has 'parent'*/ ,
      CurrentRank = 0 /*as for 'leaf'*/ )

    /*Traversal*/
    //treeMap.getOrElse( mapValue.Prev, None )
    /*Updates
	  consequent / cascade*/
    /*if 'child / Next' found then*/
    //mapValue.Next = 8
    /*same for current node*/
    //mapValue.CurrentHeight += 1
    /*max from existing children*/
    //mapValue.CurrentRank += 1
    
    /*single chain*/
    updateParent( mapValue, treeMap )/*work ?*/

	  /*if cildrenList.size > 0 then
	  for each element in list do
	  'updateChild'*/
	  /*val childrenList =
	    treeMap
	      .values
	        .filter { x => x.Prev == mapValue.Current }*/
	                                                  
	  /*val child1 = childrenList.head
	                                                  
	  /*child update*/
	  child1.CurrentHeight += 1
	  treeMap( 1 )*/
	  
	  /*many branches possible*/
    updateChildren( mapValue, treeMap )
  
    if ( mapValue.CurrentHeight > maxDistantEdge.CurrentHeight ) {
      /*new leader*/
      maxDistantEdge = mapValue
      onlyOneMaxDistantEdge = true
    } else if ( mapValue.CurrentHeight == maxDistantEdge.CurrentHeight ) {
      onlyOneMaxDistantEdge = false
    } else {
      /*may be it is 'middleEdge' ?*/
      /*'Rank' = treeHeight / 2 or maxLeafHeight / 2
       or
      'Height' = rootRank / 2 or maxNodeRank / 2*/
      if ( mapValue.CurrentRank / 2 == maxDistantEdge.CurrentHeight / 2 ) {
        middleEdge = mapValue
      }
    }
    /*add new 'edge'*/
    treeMap += (
      mapValue.Current -> mapValue )
  }
  /*treeMap.head
  treeMap.last
  treeMap.getOrElse( 1, None )
  treeMap.getOrElse( 14, None )
  treeMap.getOrElse( 6, None )*/
  onlyOneMaxDistantEdge                           //> res36: Boolean = true
  middleEdge                                      //> res37: advertisingSpread.treeFromLinkedListsTest.Edge = {p:-1,C:-1,n:-1,h:
                                                  //| 0,R:0}
  maxDistantEdge                                  //> res38: advertisingSpread.treeFromLinkedListsTest.Edge = {p:14,C:10,n:10,h:
                                                  //| 3,R:0}
  println( treeMap )                              //> Map(5 -> {p:2,C:5,n:5,h:1,R:0}, 10 -> {p:14,C:10,n:10,h:3,R:0}, 14 -> {p:1
                                                  //| ,C:14,n:10,h:2,R:1}, 1 -> {p:15,C:1,n:14,h:2,R:2}, 6 -> {p:1,C:6,n:6,h:1,R
                                                  //| :0}, 9 -> {p:4,C:9,n:9,h:1,R:0}, 13 -> {p:15,C:13,n:13,h:1,R:0}, 2 -> {p:7
                                                  //| ,C:2,n:2,h:1,R:0}, 7 -> {p:8,C:7,n:7,h:2,R:0}, 3 -> {p:0,C:3,n:4,h:1,R:1},
                                                  //|  8 -> {p:6,C:8,n:7,h:1,R:1}, 4 -> {p:3,C:4,n:4,h:2,R:0}, 15 -> {p:9,C:15,n
                                                  //| :1,h:1,R:3})
  /*same parent
  affect children height*/
  treeMap( 15 )                                   //> res39: advertisingSpread.treeFromLinkedListsTest.Edge = {p:9,C:15,n:1,h:1,
                                                  //| R:3}
  /*if cildrenList.size > 0 then
  for each element in list do
  'updateChild'*/
  val childrenList =
    treeMap
      .values
        .filter { x => x.Prev == 15 }             //> childrenList  : Iterable[advertisingSpread.treeFromLinkedListsTest.Edge] =
                                                  //|  List({p:15,C:1,n:14,h:2,R:2}, {p:15,C:13,n:13,h:1,R:0})
  val child1 = childrenList.head                  //> child1  : advertisingSpread.treeFromLinkedListsTest.Edge = {p:15,C:1,n:14,
                                                  //| h:2,R:2}
  /**child update*/
  child1.CurrentHeight += 1
  treeMap( 1 )                                    //> res40: advertisingSpread.treeFromLinkedListsTest.Edge = {p:15,C:1,n:14,h:3
                                                  //| ,R:2}
  
  lazy val tree1 = fillTreeArray( inputArray1 )   //> tree1: => Seq[advertisingSpread.treeFromLinkedListsTest.Edges]
  tree1.head                                      //> res41: advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None,S
                                                  //| ome(15),Some(19),Some(21),1,true)
  tree1.last                                      //> res42: advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None,N
                                                  //| one,Some(0),Some(1),1,true)
  lazy val tree2 = fillTreeArray( edgesUnsorted ) //> tree2: => Seq[advertisingSpread.treeFromLinkedListsTest.Edges]
  tree2.head                                      //> res43: advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None,S
                                                  //| ome(15),Some(1),Some(6),1,true)
  tree2.last                                      //> res44: advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None,N
                                                  //| one,Some(6),Some(8),1,true)
  tree2.size                                      //> res45: Int = 13
  tree2( 0 )                                      //> res46: advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None,S
                                                  //| ome(15),Some(1),Some(6),1,true)
  tree2( 12 )                                     //> res47: advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None,N
                                                  //| one,Some(6),Some(8),1,true)
  /*reassigment,
  so
  unmutable*/
  //tree2(12).height = 2
  var t2Array = tree2.toArray                     //> t2Array  : Array[advertisingSpread.treeFromLinkedListsTest.Edges] = Array(
                                                  //| Edges(true,None,Some(15),Some(1),Some(6),1,true), Edges(true,None,Some(0),
                                                  //| Some(3),Some(4),1,true), Edges(true,None,Some(6),Some(8),Some(7),1,true), 
                                                  //| Edges(true,None,None,Some(7),Some(2),1,true), Edges(true,None,None,Some(4)
                                                  //| ,Some(9),1,true), Edges(true,None,Some(1),Some(14),Some(10),1,true), Edges
                                                  //| (true,None,None,Some(2),Some(5),1,true), Edges(true,None,None,Some(9),Some
                                                  //| (15),1,t
                                                  //| Output exceeds cutoff limit.
  /*'Edges' still 'val',
  so
  unmutable*/
  //t2Array(12).height = 2

  tree2.find( ( elem: Edges ) => elem.Child == Some( 8 ) )
                                                  //> res48: Option[advertisingSpread.treeFromLinkedListsTest.Edges] = Some(Edge
                                                  //| s(true,None,None,Some(6),Some(8),1,true))
  tree2.find( ( elem: Edges ) => elem.Child == Some( 5 ) )
                                                  //> res49: Option[advertisingSpread.treeFromLinkedListsTest.Edges] = Some(Edge
                                                  //| s(true,None,None,Some(2),Some(5),1,true))
  tree2.find( ( elem: Edges ) => elem.Child == Some( 0 ) )
                                                  //> res50: Option[advertisingSpread.treeFromLinkedListsTest.Edges] = None

  val Edges0: Edges = new Edges( true,
    None /*Int.в*/ ,
    Option.empty /*option.orNull*/ ,
    None /*(-1)*/ ,
    None /*(-1)*/ ,
    1,
    true )                                        //> Edges0  : advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,Non
                                                  //| e,None,None,None,1,true)
  val Edges1: Edges = new Edges()                 //> Edges1  : advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,Non
                                                  //| e,None,None,None,1,true)
  val mapValue: List[ _ /*Int, Option[Int], Boolean*/ ] =
    List( true, None, None, Some( 2 ), Some( 5 ), 1, true )
                                                  //> mapValue  : List[_] = List(true, None, None, Some(2), Some(5), 1, true)
  val mapValue6: Edge = Edge(
    Prev = 1,
    Current = 6,
    Next = 6,
    CurrentHeight = 1,
    CurrentRank = 0 )                             //> mapValue6  : advertisingSpread.treeFromLinkedListsTest.Edge = {p:1,C:6,n:6
                                                  //| ,h:1,R:0}

  val mapValue8: Edge = Edge(
    Prev = 6,
    Current = 8,
    Next = 8,
    CurrentHeight = 1 )                           //> mapValue8  : advertisingSpread.treeFromLinkedListsTest.Edge = {p:6,C:8,n:8
                                                  //| ,h:1,R:0}
  mapValue.head                                   //> res51: _$1 = true
  mapValue( 3 )                                   //> res52: _$1 = Some(2)
  mapValue6.Current                               //> res53: Int = 6
  //mapValue2.head.isLeaf = false
  mapValue6.Prev == mapValue6.Current             //> res54: Boolean = false
  mapValue6.Next == mapValue6.Current             //> res55: Boolean = true
  /**add new 'edge'*/
  val treeFrom1To8 = Map(
    mapValue6.Current -> mapValue6 ) +
    ( mapValue8.Current -> mapValue8 )            //> treeFrom1To8  : scala.collection.immutable.Map[Int,advertisingSpread.treeF
                                                  //| romLinkedListsTest.Edge] = Map(6 -> {p:1,C:6,n:6,h:1,R:0}, 8 -> {p:6,C:8,n
                                                  //| :8,h:1,R:0})
  /*
  first value in Pair / Edge as 'root' or
  'parent node' may has many repeated occurences
  second value in Pair / Edge as 'leaf' or 'child node'
  is unique on it's respectful / relative position
  so,
  may / must be 'key' in 'Map'*/
  /*then
  'leaf' point to his 'root'*/
  /**Traversal*/
  treeFrom1To8( 6 )                               //> res56: advertisingSpread.treeFromLinkedListsTest.Edge = {p:1,C:6,n:6,h:1,R
                                                  //| :0}
  treeFrom1To8( 8 )                               //> res57: advertisingSpread.treeFromLinkedListsTest.Edge = {p:6,C:8,n:8,h:1,R
                                                  //| :0}
  /**Update*/
  treeFrom1To8( 6 ).Next = 8
  treeFrom1To8( 8 ).CurrentHeight += 1
  treeFrom1To8( 8 ).CurrentRank += 1
  /*parent for '8'
  'Prev == 6'*/
  treeFrom1To8( 6 )                               //> res58: advertisingSpread.treeFromLinkedListsTest.Edge = {p:1,C:6,n:8,h:1,R
                                                  //| :0}
  /*parent for 'leaf' ?
  child for '6'
  'Next == 8'*/
  treeFrom1To8( 8 )                               //> res59: advertisingSpread.treeFromLinkedListsTest.Edge = {p:6,C:8,n:8,h:2,R
                                                  //| :1}
  /*for tree 'root'
  when all edges added from input*/
  treeFrom1To8.getOrElse( 1, None )               //> res60: Product with Serializable = None
}