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

  edgesFrom_test3.size                            //> res0: Int = 106
  val edgesNumber = inputLines( 0 ).split( " " )( 0 ).toInt
                                                  //> edgesNumber  : Int = 21
  val inputLine = inputLines( 1 ).split( " " )    //> inputLine  : Array[String] = Array(0, 1)
  val inputArray1: Seq[ ( Int, Int ) ] = for ( line <- inputLines.tail ) yield {
    ( line.split( " " )( 0 ).toInt, line.split( " " )( 1 ).toInt )
                                                  //> inputArray1  : Seq[(Int, Int)] = ArraySeq((0,1), (0,8), (0,15), (1,2), (1,5
                                                  //| ), (2,3), (2,4), (5,6), (5,7), (8,9), (8,12), (9,10), (9,11), (12,13), (12,
                                                  //| 14), (15,16), (15,19), (16,17), (16,18), (19,20), (19,21))
  }

  edgesNumber == inputArray1.size                 //> res1: Boolean = true
  inputArray1( 20 )                               //> res2: (Int, Int) = (19,21)

  def fillTreeArray( edges: Seq[ ( Int, Int ) ] /*,
                     treeList: Seq[ Edges ] = Seq.empty*/ ): Seq[ Edges ] = {
      def loop( edgesRemains: Seq[ ( Int, Int ) ],
                treeList: Seq[ Edges ] = Seq.empty ): Seq[ Edges ] = {
        if ( edgesRemains.isEmpty ) {
          treeList
        } else {
          val isListHead: Boolean = true
          val nextNode: Option[ Int ] = None
          val prevNode: Option[ Int ] = Option.empty
          val nodeValue: Option[ Int ] = Option( edgesRemains.head._1 )
          val nodeChild: Option[ Int ] = Option( edgesRemains.head._2 )
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

          loop( edgesRemains = edgesRemains.tail,
            newEdge +: treeList )
        }
      }

    loop( edgesRemains = edges )
  }                                               //> fillTreeArray: (edges: Seq[(Int, Int)])Seq[advertisingSpread.treeFromLinked
                                                  //| ListsTest.Edges]
  fillTreeArray( inputArray1 ).head               //> res3: advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None,Non
                                                  //| e,Some(19),Some(21),1,true)
  fillTreeArray( inputArray1 ).last               //> res4: advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None,Non
                                                  //| e,Some(0),Some(1),1,true)
  fillTreeArray( edgesUnsorted ).head             //> res5: advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None,Non
                                                  //| e,Some(1),Some(6),1,true)
  fillTreeArray( edgesUnsorted ).last             //> res6: advertisingSpread.treeFromLinkedListsTest.Edges = Edges(true,None,Non
                                                  //| e,Some(6),Some(8),1,true)

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
}