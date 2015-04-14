package advertisingSpread

object treeMapsTest {
  //import advertisingSpread.inputArray1
  case class Node(
    var Value: Int = -1, /*for undefined*/
    var CurrentHeight: Int = 1, /*'0' for 'root'*/
    var CurrentRank: Int = 0 /*'0' for 'leaf'*/ ) {
    override def toString() = "{val:" + this.Value +
      ",h:" + this.CurrentHeight +
      ",R:" + this.CurrentRank + "}"
  }

  case class Edge( Start: Int = -1,
                   End: Int = -1 ) {
    override def toString() = "{from:" + this.Start +
      ">to:" + this.End + "}"
  }

  val edgesWithMaxLeaf: Seq[ ( Int, Int ) ] = Seq(
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
    ( 1, 6 ) )                                    //> edgesWithMaxLeaf  : Seq[(Int, Int)] = List((6,8), (15,1), (1,14), (0,3), (15
                                                  //| ,13), (9,15), (2,5), (14,10), (4,9), (7,2), (8,7), (3,4), (1,6))
  val edgesOfEqualDepth: Seq[ ( Int, Int ) ] = Seq(
    ( 0, 1 ),
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
    ( 19, 21 ) )                                  //> edgesOfEqualDepth  : Seq[(Int, Int)] = List((0,1), (0,8), (0,15), (1,2), (1
                                                  //| ,5), (2,3), (2,4), (5,6), (5,7), (8,9), (8,12), (9,10), (9,11), (12,13), (1
                                                  //| 2,14), (15,16), (15,19), (16,17), (16,18), (19,20), (19,21))

  val edge0 = Map( 1 -> Edge( 0, 1 ) )            //> edge0  : scala.collection.immutable.Map[Int,advertisingSpread.treeMapsTest.
                                                  //| Edge] = Map(1 -> {from:0>to:1})
  val node0 = Map( 0 -> Node( 0, 0, 1 ) )         //> node0  : scala.collection.immutable.Map[Int,advertisingSpread.treeMapsTest.
                                                  //| Node] = Map(0 -> {val:0,h:0,R:1})
  val node1 = Map( 1 -> Node( 1, 1, 0 ) )         //> node1  : scala.collection.immutable.Map[Int,advertisingSpread.treeMapsTest.
                                                  //| Node] = Map(1 -> {val:1,h:1,R:0})
  /*for 'Map.getOrElse' 'default'*/
  val emptyNode = Node( -1, 0, 0 )                //> emptyNode  : advertisingSpread.treeMapsTest.Node = {val:-1,h:0,R:0}
  val emptyEdge = Edge( -1, -1 )                  //> emptyEdge  : advertisingSpread.treeMapsTest.Edge = {from:-1>to:-1}
  /*val n = edgesWithMaxLeaf.size */
  val n = edgesOfEqualDepth.size                  //> n  : Int = 21

  var edgesMap: Map[ Int, Edge ] = Map()          //> edgesMap  : Map[Int,advertisingSpread.treeMapsTest.Edge] = Map()
  var nodesMap: Map[ Int, Node ] = Map()          //> nodesMap  : Map[Int,advertisingSpread.treeMapsTest.Node] = Map()
  var onlyOneMaxDistantEdge: Boolean = false      //> onlyOneMaxDistantEdge  : Boolean = false
  var rootNode: Node = new Node( CurrentHeight = 0 )
                                                  //> rootNode  : advertisingSpread.treeMapsTest.Node = {val:-1,h:0,R:0}
  var optimalNode: Node = new Node( CurrentHeight = 0 )
                                                  //> optimalNode  : advertisingSpread.treeMapsTest.Node = {val:-1,h:0,R:0}
  var mostDistantLeaf: Node = new Node( CurrentHeight = 0 )
                                                  //> mostDistantLeaf  : advertisingSpread.treeMapsTest.Node = {val:-1,h:0,R:0}
  var maxHeight: Int = 0                          //> maxHeight  : Int = 0
  /*until reaches all 'leafs' in subTree
  nodes with '.CurrentRank = 0'*/
  def subTreeHeightUpdate( parent: Int, /*not child ?*/
                           parentHeight: Int ): Unit = {
    val childrenList = edgesMap
      //.view
      .values
      .filter { x => x.Start == parent }

      def innerLoop( childrenLeft: Iterable /*List*/ [ Edge ],
                     baseHeight: Int ): Iterable /*List*/ [ Edge ] = {
        if ( childrenLeft.isEmpty ) {
          /*done & return*/
          childrenLeft
        } else {
          /*for each element in list*/
          /*must be in 'nodesMap' already*/
          val child = nodesMap( childrenLeft.head.End )

          if ( child.CurrentHeight < parentHeight + 1 ) {
            child.CurrentHeight = parentHeight + 1

            if ( child.CurrentRank == 0 ) {
              /*done & next*/
            } else {
              /*has it's own children*/
              child.CurrentHeight = parentHeight + 1

              /*propagate 'Height' down to subtree*/
              subTreeHeightUpdate( child.Value, child.CurrentHeight )
            }

            //innerLoop( childrenLeft.tail, parentHeight )
          } else {
          }
          /*next in list*/
          /*forward reference extends over
		        definition of value 'childrenList'
		        solution:
		        val childrenList must be declared first*/
          innerLoop( childrenLeft.tail, parentHeight )
        }
      }

    if ( childrenList.isEmpty ) {
      /*done & return*/
    } else {
      /*for each element in list*/
      /*initialization*/
      innerLoop( childrenList, parentHeight )
    }
    //if (nodesMap( start ).CurrentHeight)
  } /*work                                        //> subTreeHeightUpdate: (parent: Int, parentHeight: Int)Unit
  & possibly like expected*/

  /*until 'root' or
  parent.CurrentRank > child.CurrentRank + 1*/
  /*if (nodesMap('start').rank < 'end'.rank + 1) then
             nodesMap('start').rank = 'end'.rank + 1 }*/
  /** rankIncrement
    * by '+1' from child
    */
  def parentRankUpdate( parent: Int, /*start*/
                        childRank: Int /* = 0*/ /*end.rank*/ ): Unit = {

    /*if ( childRank == -1 ) {
      /*done & return*/
    } else {*/
    /*try to find parent in 'nodesMap'*/
    val nodeParent: Node = nodesMap
      .getOrElse( parent, emptyNode )

    if ( nodeParent == emptyNode ) {
      /*done & return*/
    } else {
      if ( nodeParent.CurrentRank > childRank + 1 ) {
        /*done & return*/
      } else {
        nodeParent.CurrentRank = childRank + 1
        /*try to update futher up to 'root'*/
        val nextParent: Edge =
          edgesMap
            .getOrElse( parent /*as 'end'*/ , emptyEdge )
        if ( nextParent == emptyEdge ) {
          /*done & return*/
        } else {
          /*recursion*/
          parentRankUpdate( nextParent.Start,
            nodeParent.CurrentRank )
        }
      }
    }
    //*}
  } /*works                                       //> parentRankUpdate: (parent: Int, childRank: Int)Unit
  as expected
  when added where needed*/

  def inputTest( inputData: Seq[ ( Int, Int ) ] ): Unit = {
    for ( i <- 0 until /*n*/ inputData.size ) {
      /*val start = edgesWithMaxLeaf( i )._1
    val end = edgesWithMaxLeaf( i )._2*/
      /*val start = edgesOfEqualDepth( i )._1
    val end = edgesOfEqualDepth( i )._2*/
      val start = inputData( i )._1
      val end = inputData( i )._2

      /*to avoide 'Serialazible'*/
      /*val nodesMapStart = nodesMap.getOrElse( start, None )*/
      /*? not a reference ?*/
      val nodesMapStart = nodesMap.getOrElse( start, emptyNode )
      /*val nodesMapEnd = nodesMap.getOrElse( end, None )*/
      val nodesMapEnd = nodesMap.getOrElse( end, emptyNode )

      /*may be mast be after 'nodesMap' updates*/
      edgesMap += ( end -> Edge(
        Start = start,
        End = end ) )

      /*cases:
    1. if ('start' && 'end') not in 'nodesMap' then
      add 'start' to 'nodesMap' with Height = 0, Rank = 1
    2. if ('start' && 'end') not in 'nodesMap' then
      add 'end' to 'nodesMap' with Height = 1, Rank = 0
    3. if ('start' in 'nodesMap') && ('end' not in 'nodesMap') then
	      check
	      existing 'start'.height & 'start'.rank
	      add 'end' to 'nodesMap' with
	        Height = nodesMap('start').height + 1, Rank = 0
	      def rankIncrement('start', 'end'.rank) = {
		      if (nodesMap('start').rank < 'end'.rank + 1) then
		         nodesMap('start').rank = 'end'.rank + 1 }
			         then
			           search until 'hasParent' in 'nodesMap' for
			           nodesMap(edgesMap('start').'start') if exist then
		              rankIncrement(next'start', 'start'.rank)
	         else
	           stop & done
    4. if ('start' not in 'nodesMap') && ('end' in 'nodesMap') then
	      check
	      existing 'end'.height & 'end'.rank
	      add 'start' to 'nodesMap' with
	        Height = 0, Rank = nodesMap('end').rank + 1
	      propagate 'Height' down to subtree
            
    5. if ('start' in 'nodesMap') && ('end' in 'nodesMap') then
        rankIncrement('start', nodesMap('end').rank) then
        gain nodesMap('start').height + 1 then
          >>tricky recursive part<<
          update all subtree nodes with
            edgesMap.value == 'end' as 'subtree root'
              until they's '.rank' > 0
      */
      if ( nodesMapStart == /*None*/ emptyNode &&
        nodesMapEnd == /*None*/ emptyNode ) {
        nodesMap += ( start -> Node(
          Value = start,
          CurrentHeight = 0,
          CurrentRank = 1 ) )
        nodesMap += ( end -> Node(
          Value = end,
          CurrentHeight = 1,
          CurrentRank = 0 ) )
      } else if ( nodesMapStart != /*None*/ emptyNode &&
        nodesMapEnd == /*None*/ emptyNode ) {
        nodesMap += ( end -> Node(
          Value = end,
          CurrentHeight = nodesMap( start ).CurrentHeight + 1,
          CurrentRank = 0 ) )
        //*debug
        //println("nodesMap(start).CurrentHeight:" + nodesMap(start).CurrentHeight)

        parentRankUpdate( /*as edge end*/ start, 0 )
      } else if ( nodesMapStart == /*None*/ emptyNode &&
        nodesMapEnd != /*None*/ emptyNode ) {
        nodesMap += ( start -> Node(
          Value = start,
          CurrentHeight = 0,
          CurrentRank = nodesMap( end ).CurrentRank + 1 ) )
        /*propagate 'Height' down to subtree*/
        subTreeHeightUpdate( end,
          0 )
      } else if ( nodesMapStart != /*None*/ emptyNode &&
        nodesMapEnd != /*None*/ emptyNode ) {
        parentRankUpdate( /*as edge end*/ start,
          nodesMap( end ).CurrentRank )
        /*propagate 'Height' down to subtree*/
        /*nodesMap('start').height + 1*/
        subTreeHeightUpdate( start, /*to find all children of 'start'*/
          nodesMap( start ).CurrentHeight )
      } else {
      }
      //*debug
      //println("start:" + start + ",end:" + end + ",nodesMap.size:" + nodesMap.size)

      /*then check for subtrees 'height' update*/
      //*subTreeHeightUpdate( start )

      /*then check for parents 'rank' update*/
      /*each node has only one parent == edgesMap.'start'*/

      /*must look for specific values like
      max Height & Rank
      inside
      procedures that changes it directly*/

    }
  }                                               //> inputTest: (inputData: Seq[(Int, Int)])Unit

  /**expensive*/
  /*if do inverse ?
	 * node with max unique height & not leaf ?
	 * or
	 * firstly sort by decreasing 'rank' then
	 * check until has children >= '2'
	 * */
  /*first node with more then one child or
  parent of two or more nodes with
  same height &
  that hegith mast be as minimal as possible
  if single chain line with no branches at all then
  element with '0' height aka 'root'*/
  def firstFork( rankedTree: Map[ Int, Node ],
                 edgesMap: Map[ Int, Edge ] ): Node = {

    lazy val fork = ( for {
      nodes <- rankedTree.values
      if ( rankedTree
        .count {
          x => x._2.CurrentHeight == nodes.CurrentHeight
        } ) > 1
    } yield nodes )
    //*debug
    //*println("fork:" + fork.take(3))

    if ( fork.size > 0 ) {
      /*pick child with minimum '.CurrentHeight' &
      find it parent or
      if 'height' = 1 then
      find node with
      'height' = 0*/
      lazy val child =
        edgesMap( fork
          .min( Ordering[ Int ]
            .on( ( x: Node ) => x.CurrentHeight ) )
          .Value )

      rankedTree( child.Start )
    } else {
      if ( rankedTree.size > 0 ) {
        rankedTree
          .values
          .max( Ordering[ Int ]
            .on( ( x: Node ) => x.CurrentRank ) )
      } else {
        emptyNode
      }
    }
  } /*works*/                                     //> firstFork: (rankedTree: Map[Int,advertisingSpread.treeMapsTest.Node], edge
                                                  //| sMap: Map[Int,advertisingSpread.treeMapsTest.Edge])advertisingSpread.treeM
                                                  //| apsTest.Node
  /*'rankedTree' & 'edgesMap' is not empty*/
  def rankFork( rankedTree: Map[ Int, Node ],
                edgesMap: Map[ Int, Edge ] ): Node = {
    /*'Map' is unsorted*/
    val rankSortedTree = rankedTree
      .values
      .view
      .toSeq
      .sorted( Ordering[ Int ]
        .reverse
        .on( ( x: Node ) => x.CurrentRank ) )
      /*!seq.exists(p)*/
      def innerLoop( nodesLeft: Seq[ Node ],
                     fork: Node = emptyNode ): Node =
        if ( nodesLeft.isEmpty ) {
          /*no garanty on value*/
          fork
          /*that is distinguishable as wrong*/
          emptyNode
        } else {
          /*'nodesLeft.head' as 'start' in 'edgesMap'*/
          /*'seq.count(p)'
		     faster then '.filter'*/
          if ( edgesMap
            .count( x => x._2.Start == nodesLeft.head.Value ) > 1 ) {
            nodesLeft.head
          } else {
            innerLoop( nodesLeft.tail,
              nodesLeft.head )
          }
        }

    /*return value
    initializaton*/
    innerLoop( rankSortedTree, rankSortedTree.head )
  } /*works                                       //> rankFork: (rankedTree: Map[Int,advertisingSpread.treeMapsTest.Node], edges
                                                  //| Map: Map[Int,advertisingSpread.treeMapsTest.Edge])advertisingSpread.treeMa
                                                  //| psTest.Node
  but may be too slow*/
  inputTest( edgesWithMaxLeaf )
  //*inputTest( edgesOfEqualDepth )

  //*println( edgesMap )
  //*println( nodesMap )
  edgesMap.size                                   //> res0: Int = 13
  //*n
  /*must be edgesMap.size + 1*/
  nodesMap.size                                   //> res1: Int = 14
  /*nodesMap.getOrElse( 14, None )
  nodesMap.getOrElse( 14, None ) == None
  nodesMap.getOrElse( 14, None ) == Some
  nodesMap.getOrElse( 14, None ) == Some( 14 )
  nodesMap.getOrElse( 14, None ) != None */
  /*edgesMap
    .values*/
  /*val childrenOf15 = edgesMap
    .values
    .filter { x => x.Start == 15 }
    
  childrenOf15.head.Start
  childrenOf15.head.End*/
  mostDistantLeaf                                 //> res2: advertisingSpread.treeMapsTest.Node = {val:-1,h:0,R:0}
  optimalNode                                     //> res3: advertisingSpread.treeMapsTest.Node = {val:-1,h:0,R:0}
  rootNode                                        //> res4: advertisingSpread.treeMapsTest.Node = {val:-1,h:0,R:0}
  /*seq.sortBy(_.property)(Ordering[T].reverse)*/
  /*.sorted( Ordering[ ( Int, Int )]
      .on( ( x: ( Int, Int ) ) => ( x._1 - x._2, x._1 ) )*/
  mostDistantLeaf = nodesMap
    .values
    .max( Ordering[ Int ]
      .on( ( x: Node ) => x.CurrentHeight ) )
  mostDistantLeaf                                 //> res5: advertisingSpread.treeMapsTest.Node = {val:5,h:10,R:0}
  rootNode = nodesMap
    .values
    .max( Ordering[ Int ]
      .on( ( x: Node ) => x.CurrentRank ) )
  rootNode                                        //> res6: advertisingSpread.treeMapsTest.Node = {val:0,h:0,R:10}
  optimalNode = nodesMap
    .values
    .filter { x =>
      ( x.CurrentHeight == mostDistantLeaf.CurrentHeight / 2 ||
        x.CurrentRank == rootNode.CurrentRank / 2 ) &&
        ( x.CurrentHeight + x.CurrentRank == mostDistantLeaf.CurrentHeight ||
          x.CurrentHeight + x.CurrentRank == rootNode.CurrentRank )
    }
    .head

  val fork = firstFork( nodesMap, edgesMap )      //> fork  : advertisingSpread.treeMapsTest.Node = {val:15,h:4,R:6}
  rankFork( nodesMap, edgesMap )                  //> res7: advertisingSpread.treeMapsTest.Node = {val:15,h:4,R:6}

  /** compares:
    * rootNode &
    * firstFork (may be same as 'root') &
    * optimalNode (if exist, must be if .size > 1)
    */
  def optimalDistance( root: Node,
                       firstFork: Node,
                       optimalNode: Node ): Int = {
    if ( optimalNode.CurrentRank - optimalNode.CurrentHeight <= 1 ) {
      /*Math.max(optimalNode.CurrentRank, optimalNode.CurrentHeight)*/
      optimalNode.CurrentRank max optimalNode.CurrentHeight
    } else {
      if ( firstFork == root ) {
        root.CurrentRank
      } else {
        firstFork.CurrentRank max firstFork.CurrentHeight
      }
    }
  } /*works*/                                     //> optimalDistance: (root: advertisingSpread.treeMapsTest.Node, firstFork: ad
                                                  //| vertisingSpread.treeMapsTest.Node, optimalNode: advertisingSpread.treeMaps
                                                  //| Test.Node)Int

  optimalDistance( root = rootNode,
    firstFork = fork,
    optimalNode = optimalNode )                   //> res8: Int = 5

  fork.CurrentRank max fork.CurrentHeight         //> res9: Int = 6
  Math.max( optimalNode.CurrentRank, optimalNode.CurrentHeight )
                                                  //> res10: Int = 5
  /*fancy but
  except inner scope &
  more clear global namespace
  what we got ?*/
  class FieldTest( private var preservedVals: List[ Int ] = List.empty[ Int ] ) {
    def english = "Hi"
    def espanol = "Hola"
    def deutsch = "Hallo"
    def magyar = "Szia"
    def getPreserved = this.preservedVals
    def addToPreserved( newElem: Int ): List[ Int ] = {
      preservedVals = newElem :: preservedVals

      preservedVals
    }
  }
  /*traits or objects may not have parameters*/
  /*object FieldTest {
    /*lame  encapsulation*/
    //This is encapsulated!
    var currentPreserv: List[Int] = List.empty[Int]
  }*/

  val fieldTestObj = new FieldTest                //> fieldTestObj  : advertisingSpread.treeMapsTest.FieldTest = advertisingSpre
                                                  //| ad.treeMapsTest$$anonfun$main$1$FieldTest$2@45f292
  fieldTestObj.espanol                            //> res11: String = Hola
  fieldTestObj.getPreserved                       //> res12: List[Int] = List()
  fieldTestObj.addToPreserved( 1 )                //> res13: List[Int] = List(1)
  fieldTestObj.getPreserved                       //> res14: List[Int] = List(1)
  //fieldTestObj.preservedVals
  /*abstract class TreeNodeMap extends Map[ Int, Node ] {
    override def toString() = "{" +
      "Prev:" + this.keys +
      ",val[" + this.values +
      "}"
  }
  
  val testTreeNodeMap: TreeNodeMap = new TreeNodeMap(1->emptyNode)*/
  val empty1 = emptyNode                          //> empty1  : advertisingSpread.treeMapsTest.Node = {val:-1,h:0,R:0}
  val empty2 = emptyNode                          //> empty2  : advertisingSpread.treeMapsTest.Node = {val:-1,h:0,R:0}

  empty2 == empty1                                //> res15: Boolean = true
  empty2 eq empty1                                //> res16: Boolean = true
  empty2 == empty2                                //> res17: Boolean = true
  empty2 eq empty2                                //> res18: Boolean = true

  /*any good from this Luxures ?*/
  abstract class TreeNodes
  case class NodeChildrenSeq( elems: List[ TreeNodes ] ) extends TreeNodes
  case class NodesMapObj( bindings: Map[ Int, TreeNodes ] ) extends TreeNodes
  case class NodeValue( num: Int ) extends TreeNodes
  case class RootNode( //value: NodeValue,
    degree: Int,
    rank: Int,
    children: NodeChildrenSeq ) extends TreeNodes
  case class OrdinaryNode( //value: NodeValue,
    //prev: TreeNodes,
    degree: Int,
    height: Int,
    rank: Int,
    children: NodeChildrenSeq ) extends TreeNodes
  case class LeafNode( //value: NodeValue,
    //prev: TreeNodes,
    height: Int ) extends TreeNodes
  case object EmptyNode extends TreeNodes

  /*& traversal ?*/
  val nodesData1 = NodesMapObj(
    Map(
      0 -> RootNode( //NodeValue(0),
        14,
        10,
        NodeChildrenSeq(
          List(
            NodesMapObj(
              Map( 3 -> OrdinaryNode(
                13, 1, 9,
                NodeChildrenSeq(
                  List(
                    NodesMapObj(
                      Map( 15 -> LeafNode( 2 ) )
                    ),
                    NodesMapObj(
                      Map( 1 -> LeafNode( 2 ) )
                    )
                  )
                )
              )
              )
            )
          )
        )
      )
    )
  )                                               //> nodesData1  : advertisingSpread.treeMapsTest.NodesMapObj = NodesMapObj(Map
                                                  //| (0 -> RootNode(14,10,NodeChildrenSeq(List(NodesMapObj(Map(3 -> OrdinaryNod
                                                  //| e(13,1,9,NodeChildrenSeq(List(NodesMapObj(Map(15 -> LeafNode(2))), NodesMa
                                                  //| pObj(Map(1 -> LeafNode(2)))))))))))))
  def show( treeNodes: TreeNodes ): String = treeNodes match {
    case NodeChildrenSeq( elems ) =>
      "[" + ( elems map show mkString ", " ) + "]"
    case NodesMapObj( bindings ) =>
      val assocs = bindings map {
        case ( key, value ) => "\"" + key + "\": " + show( value )
      }
      "{" + ( assocs mkString ", " ) + "}"
    case NodeValue( num ) => num.toString
    case RootNode(
      degree: Int,
      rank: Int,
      children ) => "d:" + degree +
        ",r:" + rank + show( children )
    case OrdinaryNode(
      degree: Int,
      height: Int,
      rank: Int,
      children ) => "d:" + degree +
        ",h:" + height +
        ",r:" + rank + show( children )
    case LeafNode( height ) => "leaf:" + height
    case EmptyNode        => "None"
  }                                               //> show: (treeNodes: advertisingSpread.treeMapsTest.TreeNodes)String
  
  show( nodesData1 )                              //> res19: String = {"0": d:14,r:10[{"3": d:13,h:1,r:9[{"15": leaf:2}, {"1": l
                                                  //| eaf:2}]}]}
  val dummyNode = EmptyNode                       //> dummyNode  : advertisingSpread.treeMapsTest.EmptyNode.type = EmptyNode
  
  dummyNode == EmptyNode                          //> res20: Boolean = true
  
  nodesData1.bindings                             //> res21: Map[Int,advertisingSpread.treeMapsTest.TreeNodes] = Map(0 -> RootNo
                                                  //| de(14,10,NodeChildrenSeq(List(NodesMapObj(Map(3 -> OrdinaryNode(13,1,9,Nod
                                                  //| eChildrenSeq(List(NodesMapObj(Map(15 -> LeafNode(2))), NodesMapObj(Map(1 -
                                                  //| > LeafNode(2))))))))))))
  nodesData1.bindings.head                        //> res22: (Int, advertisingSpread.treeMapsTest.TreeNodes) = (0,RootNode(14,10
                                                  //| ,NodeChildrenSeq(List(NodesMapObj(Map(3 -> OrdinaryNode(13,1,9,NodeChildre
                                                  //| nSeq(List(NodesMapObj(Map(15 -> LeafNode(2))), NodesMapObj(Map(1 -> LeafNo
                                                  //| de(2))))))))))))
  nodesData1
    .bindings(0) match
    {
      case x: RootNode => "RootNode"
      case _ => "something else"
    }                                             //> res23: String = RootNode
  nodesData1
    .bindings
      .getOrElse(0, EmptyNode) match
    {
      case x: RootNode => "RootNode.children:" + x.children
      case x: OrdinaryNode => "RootNode"
      case x: LeafNode => "RootNode"
      case x if x == EmptyNode => "EmptyNode"
      case _ => "something else"
    }                                             //> res24: String = RootNode.children:NodeChildrenSeq(List(NodesMapObj(Map(3 -
                                                  //| > OrdinaryNode(13,1,9,NodeChildrenSeq(List(NodesMapObj(Map(15 -> LeafNode(
                                                  //| 2))), NodesMapObj(Map(1 -> LeafNode(2))))))))))
  
  
  
  
  
  
  
  
  
}