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
  case class Edge( var isListHead: Boolean = false,
                   var Next: Option[ Int ] = None,
                   var Prev: Option[ Int ] = None,
                   var NodeValue: Option[ Int ], /*start node*/
                   var Child: Option[ Int ], /*end node*/
                   var ChildHeight: Int = 1,
                   var isLeaf: Boolean = true )

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
  /*duplicated root values in keys*/
  edgesMap.size                                   //> res0: Int = 11
  edgesLeafToParentMap.size                       //> res1: Int = 13
  edgesUnsorted.size                              //> res2: Int = 13
  /*tree traversal
  from leaf to root*/
  edgesLeafToParentMap2.getOrElse( 13, -1 )       //> res3: Int = 15
  edgesLeafToParentMap2.getOrElse( 15, -1 )       //> res4: Int = 9
  edgesLeafToParentMap2.getOrElse( 9, -1 )        //> res5: Int = 4
  edgesLeafToParentMap2.getOrElse( 4, -1 )        //> res6: Int = 3
  edgesLeafToParentMap2.getOrElse( 3, -1 )        //> res7: Int = 0
  /*root*/
  edgesLeafToParentMap2.getOrElse( 0, -1 )        //> res8: Int = -1

  edgesLeafToParentMap2.keys.groupBy { x => 13 }  //> res9: scala.collection.immutable.Map[Int,Iterable[Int]] = Map(13 -> Set(5, 
                                                  //| 10, 14, 1, 6, 9, 13, 2, 7, 3, 8, 4, 15))
  edgesLeafToParentMap2.keys.takeWhile { x => x == 13 }
                                                  //> res10: Iterable[Int] = Set()
  edgesLeafToParentMap2.keys.groupBy { x => 15 }  //> res11: scala.collection.immutable.Map[Int,Iterable[Int]] = Map(15 -> Set(5,
                                                  //|  10, 14, 1, 6, 9, 13, 2, 7, 3, 8, 4, 15))
  edgesLeafToParentMap2.keys.dropWhile { x => x == 15 }
                                                  //> res12: Iterable[Int] = Set(5, 10, 14, 1, 6, 9, 13, 2, 7, 3, 8, 4, 15)
  edgesLeafToParentMap2.keys.groupBy { x => 0 }   //> res13: scala.collection.immutable.Map[Int,Iterable[Int]] = Map(0 -> Set(5, 
                                                  //| 10, 14, 1, 6, 9, 13, 2, 7, 3, 8, 4, 15))
  edgesLeafToParentMap2.keys.iterator.withFilter { x => 0 == x }
                                                  //> res14: Iterator[Int] = empty iterator
  edgesLeafToParentMap2.keys.iterator.indexOf( 9 )//> res15: Int = 5
  edgesLeafToParentMap2.keys.iterator.drop( 5 ).next()
                                                  //> res16: Int = 9
  edgesLeafToParentMap2.iterator.drop( 5 - 1 )    //> res17: Iterator[(Int, Int)] = non-empty iterator
  edgesLeafToParentMap2.iterator.drop( 5 - 1 ).next()
                                                  //> res18: (Int, Int) = (6,1)
  edgesLeafToParentMap2.iterator.drop( 5 ).next() //> res19: (Int, Int) = (9,4)
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
    .split( "\n" )                                //> inputLines  : Array[String] = Array(21, 0 1, 0 8, 0 15, 1 2, 1 5, 2 3, 2 4,
                                                  //|  5 6, 5 7, 8 9, 8 12, 9 10, 9 11, 12 13, 12 14, 15 16, 15 19, 16 17, 16 18,
                                                  //|  19 20, 19 21)

  edgesFrom_test3.size                            //> res20: Int = 106
  val edgesNumber = inputLines( 0 ).split( " " )( 0 ).toInt
                                                  //> edgesNumber  : Int = 21
  val inputLine = inputLines( 1 ).split( " " )    //> inputLine  : Array[String] = Array(0, 1)
  /*'Array' is mutable,
  so
  no need to create new collection with
  chenged elements*/
  val inputArray1: Seq[ ( Int, Int ) ] = for ( line <- inputLines.tail ) yield {
    ( line.split( " " )( 0 ).toInt, line.split( " " )( 1 ).toInt )
                                                  //> inputArray1  : Seq[(Int, Int)] = ArraySeq((0,1), (0,8), (0,15), (1,2), (1,5
                                                  //| ), (2,3), (2,4), (5,6), (5,7), (8,9), (8,12), (9,10), (9,11), (12,13), (12,
                                                  //| 14), (15,16), (15,19), (16,17), (16,18), (19,20), (19,21))
  }

  edgesNumber == inputArray1.size                 //> res21: Boolean = true
  inputArray1( 20 )                               //> res22: (Int, Int) = (19,21)

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
  }                                               //> fillTreeArray: (edges: Seq[(Int, Int)])Seq[advertisingSpread.treeFromLinked
                                                  //| ListsTest.Edges]
  lazy val tree1 = fillTreeArray( inputArray1 )   //> tree1: => Seq[advertisingSpread.treeFromLinkedListsTest.Edges]
  tree1.head                                      //> res23: advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None,So
                                                  //| me(15),Some(19),Some(21),1,true)
  tree1.last                                      //> res24: advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None,No
                                                  //| ne,Some(0),Some(1),1,true)
  lazy val tree2 = fillTreeArray( edgesUnsorted ) //> tree2: => Seq[advertisingSpread.treeFromLinkedListsTest.Edges]
  tree2.head                                      //> res25: advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None,So
                                                  //| me(15),Some(1),Some(6),1,true)
  tree2.last                                      //> res26: advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None,No
                                                  //| ne,Some(6),Some(8),1,true)
  tree2.size                                      //> res27: Int = 13
  tree2( 0 )                                      //> res28: advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None,So
                                                  //| me(15),Some(1),Some(6),1,true)
  tree2( 12 )                                     //> res29: advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None,No
                                                  //| ne,Some(6),Some(8),1,true)
  /*reassigment,
  so
  unmutable*/
  //tree2(12).height = 2
  var t2Array = tree2.toArray                     //> t2Array  : Array[advertisingSpread.treeFromLinkedListsTest.Edges] = Array(E
                                                  //| dges(true,None,Some(15),Some(1),Some(6),1,true), Edges(true,None,Some(0),So
                                                  //| me(3),Some(4),1,true), Edges(true,None,Some(6),Some(8),Some(7),1,true), Edg
                                                  //| es(true,None,None,Some(7),Some(2),1,true), Edges(true,None,None,Some(4),Som
                                                  //| e(9),1,true), Edges(true,None,Some(1),Some(14),Some(10),1,true), Edges(true
                                                  //| ,None,None,Some(2),Some(5),1,true), Edges(true,None,None,Some(9),Some(15),1
                                                  //| ,true), E
                                                  //| Output exceeds cutoff limit.
  /*'Edges' still 'val',
  so
  unmutable*/
  //t2Array(12).height = 2

  tree2.find( ( elem: Edges ) => elem.Child == Some( 8 ) )
                                                  //> res30: Option[advertisingSpread.treeFromLinkedListsTest.Edges] = Some(Edges
                                                  //| (true,None,None,Some(6),Some(8),1,true))
  tree2.find( ( elem: Edges ) => elem.Child == Some( 5 ) )
                                                  //> res31: Option[advertisingSpread.treeFromLinkedListsTest.Edges] = Some(Edges
                                                  //| (true,None,None,Some(2),Some(5),1,true))
  tree2.find( ( elem: Edges ) => elem.Child == Some( 0 ) )
                                                  //> res32: Option[advertisingSpread.treeFromLinkedListsTest.Edges] = None

  val Edges0: Edges = new Edges( true,
    None /*Int.в*/ ,
    Option.empty /*option.orNull*/ ,
    None /*(-1)*/ ,
    None /*(-1)*/ ,
    1,
    true )                                        //> Edges0  : advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None
                                                  //| ,None,None,None,1,true)
  val Edges1: Edges = new Edges()                 //> Edges1  : advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None
                                                  //| ,None,None,None,1,true)
  val mapValue: List[ _ /*Int, Option[Int], Boolean*/ ] = List( true, None, None, Some( 2 ), Some( 5 ), 1, true )
                                                  //> mapValue  : List[_] = List(true, None, None, Some(2), Some(5), 1, true)
  val mapValue2: List[ Edge ] = List( Edge( true, None, None, Some( 2 ), Some( 5 ), 1, true ) )
                                                  //> mapValue2  : List[advertisingSpread.treeFromLinkedListsTest.Edge] = List(E
                                                  //| dge(true,None,None,Some(2),Some(5),1,true))
  mapValue.head                                   //> res33: _$1 = true
  mapValue(3)                                     //> res34: _$1 = Some(2)
  mapValue2.head.NodeValue                        //> res35: Option[Int] = Some(2)
  mapValue2.head.isLeaf = false
  mapValue2                                       //> res36: List[advertisingSpread.treeFromLinkedListsTest.Edge] = List(Edge(tr
                                                  //| ue,None,None,Some(2),Some(5),1,false))
}