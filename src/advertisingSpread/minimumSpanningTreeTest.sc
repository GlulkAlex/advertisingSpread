package advertisingSpread

object minimumSpanningTreeTest {
  /*tree Example:
	                  ([1])->6->8->7->2->5
	0->3->4->9->[15]->([1])->14->10
	            [15]->13*/
  def treeF2( parent: Option[ Int ], /*exist or not*/
              /*must be refference to existing tree node
            as 'treeF' or 'None' or 'treeF.empty'*/
              value: Int, /*assume it is unique*/
              children: Option[ List[ Int ] ] /*empty or not*/ ): ( Option[ Int ] /*nodeParent*/ , Int /*nodeValue*/ , Option[ List[ Int ] ] /*nodeChildren*/ , Int /*nodeHeghit*/ ) = {
      def nodeHeight: Int = parent match {
        case Some( node ) => node + 1 /*parentHeight + 1*/
        case None         => 0
      }

    (
      parent,
      value,
      children,
      nodeHeight )
  }                                               //> treeF2: (parent: Option[Int], value: Int, children: Option[List[Int]])(Optio
                                                  //| n[Int], Int, Option[List[Int]], Int)

  /*needed for 'heght' calculation*/
  def treeF2FindParent( forest: List[ Int ], nodeVal: Int ): Int = ???
                                                  //> treeF2FindParent: (forest: List[Int], nodeVal: Int)Int
  /*traversal to find leafs*/
  def treeF2FindChild( forest: List[ Int ], nodeVal: Int ): Int = ???
                                                  //> treeF2FindChild: (forest: List[Int], nodeVal: Int)Int
  /*traversal to get specific node*/
  def treeF2FindNode( forest: Seq[ ( Option[ Int ], Int, Option[ List[ Int ] ], Int ) ],
                      nodeVal: Int /*suppose to be unique*/ ): Option[ ( Option[ Int ], Int, Option[ List[ Int ] ], Int ) ] =
    forest.find( _._2 == nodeVal ) /*works, but   //> treeF2FindNode: (forest: Seq[(Option[Int], Int, Option[List[Int]], Int)], n
                                                  //| odeVal: Int)Option[(Option[Int], Int, Option[List[Int]], Int)]
                      doubfuly this is fast solution*/ //.getOrElse(-1)
  /**may be use curring ?*/
  def addNode( forest: Seq[ ( Option[ Int ], Int, Option[ List[ Int ] ], Int ) ],
               node: ( Option[ Int ], Int, Option[ List[ Int ] ], Int ) /*suppose to be unique*/ ): Seq[ ( Option[ Int ], Int, Option[ List[ Int ] ], Int ) ] =
    treeF2( Option( 15 ), 1, Option( List( 14, 6 ) ) ) +: forest
                                                  //> addNode: (forest: Seq[(Option[Int], Int, Option[List[Int]], Int)], node: (O
                                                  //| ption[Int], Int, Option[List[Int]], Int))Seq[(Option[Int], Int, Option[List
                                                  //| [Int]], Int)]
  /*? is any parameters needed ?*/
  /** too complex &
    * I do not know what to do with this right now
    */
  class Forest( capacity: Vector[ Int ] ) {
    //States:
    type Node = ( Option[ Int ], Int, Option[ List[ Int ] ], Int )
    val initialState = capacity map ( _ => 0 )
    //Moves:
    /*case class*/
    trait Update {
      /*that track how each move change it state*/
      def addNew( node: Node ): Node
    }
    /*implementing trait method in each case calass*/
    case class EmptyNode() extends Update {
      /*old state updated with new one*/
      def addNew( node: Node ): Node = ( Option.empty, -1, Option( List.empty ), -1 )
    }
    /*assumed that: these parameters alaways avalable*/
    case class NonEmptyNode( value: Int,
                             child: Int ) extends Update {
      /*depends on comparing capacites of 'from' & 'to'*/
      def addNew( node: Node ): Node = ( Option.empty, value, Option( List( child ) ), 0 )
    }
  }
  //val forest0 = Seq( node0, node1, node2, node3, node4, node0 )

  //treeF2FindNode( forest2, 15 )
  /*like 'record' in Pascal*/
  case class TreeNode( parent: Option[ Int ],
                       value: Int,
                       children: Option[ List[ Int ] ],
                       height: Int )

  /*([1])->6->8->7->2->5
  0->3->4->9->[15]->([1])->14->10
              [15]->13*/
  val tree1 = Array(
    TreeNode( Option.empty, 0, Option( List( 3 ) ), 0 ),
    TreeNode( Option( 0 ), 3, Option( List( 4 ) ), 1 ),
    TreeNode( Option( 3 ), 4, Option( List( 9 ) ), 2 ),
    TreeNode( Option( 4 ), 9, Option( List( 15 ) ), 3 ),
    TreeNode( Option( 9 ), 15, Option( List( 1, 13 ) ), 4 ),
    TreeNode( Option( 15 ), 13, Option.empty, 5 ),
    TreeNode( Option( 15 ), 1, Option( List( 6, 14 ) ), 5 ),
    TreeNode( Option( 1 ), 14, Option( List( 10 ) ), 6 ),
    TreeNode( Option( 14 ), 10, Option.empty, 7 ),
    TreeNode( Option( 1 ), 6, Option( List( 8 ) ), 6 ),
    TreeNode( Option( 6 ), 8, Option( List( 7 ) ), 7 ),
    TreeNode( Option( 8 ), 7, Option( List( 2 ) ), 8 ),
    TreeNode( Option( 7 ), 2, Option( List( 5 ) ), 9 ),
    TreeNode( Option( 2 ), 5, Option.empty, 10 )
  )                                               //> tree1  : Array[advertisingSpread.minimumSpanningTreeTest.TreeNode] = Array(
                                                  //| TreeNode(None,0,Some(List(3)),0), TreeNode(Some(0),3,Some(List(4)),1), Tree
                                                  //| Node(Some(3),4,Some(List(9)),2), TreeNode(Some(4),9,Some(List(15)),3), Tree
                                                  //| Node(Some(9),15,Some(List(1, 13)),4), TreeNode(Some(15),13,None,5), TreeNod
                                                  //| e(Some(15),1,Some(List(6, 14)),5), TreeNode(Some(1),14,Some(List(10)),6), T
                                                  //| reeNode(Some(14),10,None,7), TreeNode(Some(1),6,Some(List(8)),6), TreeNode(
                                                  //| Some(6),8
                                                  //| Output exceeds cutoff limit.

  tree1.filter {
    case TreeNode( parent,
      value,
      children,
      height
      ) => height >= tree1.length / 2
  }                                               //> res0: Array[advertisingSpread.minimumSpanningTreeTest.TreeNode] = Array(Tre
                                                  //| eNode(Some(14),10,None,7), TreeNode(Some(6),8,Some(List(7)),7), TreeNode(So
                                                  //| me(8),7,Some(List(2)),8), TreeNode(Some(7),2,Some(List(5)),9), TreeNode(Som
                                                  //| e(2),5,None,10))
  /*all tree leafs*/
  tree1.filter {
    case TreeNode( parent,
      value,
      children,
      height
      ) => children.isEmpty
  }                                               //> res1: Array[advertisingSpread.minimumSpanningTreeTest.TreeNode] = Array(Tre
                                                  //| eNode(Some(15),13,None,5), TreeNode(Some(14),10,None,7), TreeNode(Some(2),5
                                                  //| ,None,10))
  /*not define Ordering[TreeNode]*/
  val tree1Height: Int = tree1
    .max( new Ordering[ TreeNode ] {
      def compare( a: TreeNode,
                   b: TreeNode ) = {
        /*val ( parentA,
        valueA,
        childrenA,
        heightA
          ) = a
      val ( parentB,
        valueB,
        childrenB,
        heightB
          ) = b
           heightA compare heightB*/
        a.height compare b.height
      }
    } )
    .height /*works*/                             //> tree1Height  : Int = 10
    
  /*>Items in the collections aren’t required to implement 'Ordered', but
  'Ordered' uses are still statically type checked.
  >You can define your own orderings
  without any additional library support:*/
  /*'views' in the standard library that
  translates 'Ordered' into 'Ordering' (and vice versa).*/
  List( 1, 2, 3, 4 )
    .min( new Ordering[ Int ] { def compare( a: Int, b: Int ) = b compare a } )
                                                  //> res2: Int = 4
  List( 1, 2, 3, 4 )
    .min( new Ordering[ Int ] { def compare( a: Int, b: Int ) = a compare b } )
                                                  //> res3: Int = 1
  //seq.reduce(_ min _)
  /*all tree leafs for min path check
  & root*/
  tree1
    .filter {
      //val treeHeight = tree1Height
      /*case TreeNode( parent,
        value,
        children,
        height
        ) =>*/ ( x: TreeNode ) => ( x.children.isEmpty &&
        /*
      treeHeight != tree.length
      treeHeight == treeNode.maxHeight
      */
        /*upper bound*/
        (
        ( x.height <= tree1Height / 2 + 1 ) &&
          /*lower bound*/
          ( x.height >= tree1Height / 2 - 1 ) )
      ) || /*to warp string operator must be at the end*/
          ( x.parent.isEmpty )
    }                                             //> res4: Array[advertisingSpread.minimumSpanningTreeTest.TreeNode] = Array(Tre
                                                  //| eNode(None,0,Some(List(3)),0), TreeNode(Some(15),13,None,5))

}