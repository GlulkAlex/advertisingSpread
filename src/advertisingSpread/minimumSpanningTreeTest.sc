package advertisingSpread

object minimumSpanningTreeTest {
  /** TODO:
    * filling array with input &
    * if necessary
    * update elements height when parent changes
    */
  /*tree Example:
	                  ([1])->6->8->7->2->5
	0->3->4->9->[15]->([1])->14->10
	            [15]->13*/
  def treeF2( parent: Option[ Int ], /*exist or not*/
              /*must be refference to existing tree node
            as 'treeF' or 'None' or 'treeF.empty'*/
              value: Int, /*assume it is unique*/
              children: Option[ List[ Int ] ] /*empty or not*/ ): ( Option[ Int ] /*nodeParent*/ ,
              Int /*nodeValue*/ ,
              Option[ List[ Int ] ] /*nodeChildren*/ ,
              Int /*nodeHeghit*/ ) = {
      def nodeHeight: Int = parent match {
        case Some( node ) => node + 1 /*parentHeight + 1*/
        case None         => 0
      }

    (
      parent,
      value,
      children,
      nodeHeight )
  }

  /*needed for 'heght' calculation*/
  def treeF2FindParent( forest: List[ Int ], nodeVal: Int ): Int = ???
  /*traversal to find leafs*/
  def treeF2FindChild( forest: List[ Int ], nodeVal: Int ): Int = ???
  /*traversal to get specific node*/
  def treeF2FindNode( forest: Seq[ ( Option[ Int ], Int, Option[ List[ Int ] ], Int ) ],
                      nodeVal: Int /*suppose to be unique*/ ): Option[ ( Option[ Int ], Int, Option[ List[ Int ] ], Int ) ] =
    forest.find( _._2 == nodeVal ) /*works, but
                      doubfuly this is fast solution*/ //.getOrElse(-1)
                      
  /**may be use curring ?*/
  def addNode( forest: Seq[ ( Option[ Int ], Int, Option[ List[ Int ] ], Int ) ],
               node: ( Option[ Int ], Int, Option[ List[ Int ] ], Int ) /*suppose to be unique*/ ): Seq[ ( Option[ Int ], Int, Option[ List[ Int ] ], Int ) ] =
    treeF2( Option( 15 ), 1, Option( List( 14, 6 ) ) ) +: forest
    
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

  /*add link to chain when node with current value has parent*/
  def addStep( link: TreeNode, /*parent->child or node->leaf*/
               chain: Seq[ Int ] ): Seq[ Int ] = {
    /*for simplicity*/
    /*or*/
    /*link
      .parent
       //'Unit' & not retren values
      .foreach { v =>
        /*return*/ link
          .parent
          .get +: chain
      }*/
    /*alternative not work
      (for { v <- link      .parent
      } yield v +: chain) orElse chain*/

    if ( link.parent.isDefined /*!.isEmpty*/ ) {
      val parent = link.parent.get
      val value = link.value
      /*must be look up for chain.head*/
      if ( value == chain.head || chain.isEmpty ) {
        /*postfix addition to the right
                    to create new 'tail' or
                    for reversed order
                    ptefix to create new 'head'*/
        parent +: chain
      } else { chain }
    } else {
      chain
    }
  } /*works*/

  /** @author GlukAlex
    * @version 1.0, 06/03/2015
    * @param graph - source tree
    * @param startIndex - from which node or leaf in tree build a path
    * @return sequence on connected tree nodes as path
    */
  def path_v0( graph: /*Array*/ Seq[ TreeNode ], /*'Seq' if has 'head'->'tail' chain*/
             /*node value*/
             startIndex: Int ): Seq[ Int ] = graph
    /*traversal direction -> up to root*/
    /*full collection length / size
    however not all be used
    possible waste of operation*/
    /*applayed function mast mutch by parameters
            of Seq element & 'starting value'*/
    .foldRight( Seq( startIndex ) )(
      /*(op: (TreeNode,
    Seq[ Int ])) => Seq[ Int ]*/ addStep
    ) /*work when graph sorted from root to leafs*/

  def path( graph: /*Array*/ Seq[ TreeNode ],
            /*node value*/
            startValue: Int,/*may be as child in parent*/
            pathStore: Seq[ Int ] = Seq.empty ): Seq[ Int ] = {
      /*traversal through all available elements*/
      def findNext() = ???
      /*check if node is a child of existing entry*/
      /*must return parent node? or
      'pathStore' with chain of parent nodes?*/
      def hasParent( nodeValue: Int,
        /*Index represent amount of
        actual non null values in Array
        they goes continuously from 'head' */
                     Index: Int ) = {
        for ( i <- Index to 0 by -1 ) {
          /*get children*/
          tree1( i ).children match {
            /*chech if present
        then
        ?update parent?
        add link &
        next iteration
        until no parent found
        so
        root*/
            case Some( children ) if ( !children.contains( nodeValue ) ) => {
            /*forward reference extends over definition
              addChild( nodeValue, Index )*/
            }
            /*case Some( children ) if ( children.contains( nodeValue ) ) => {
          /*skip & do nothing for now*/
        }*/
            /*add new*/
            case None => addChildrenList( nodeValue, Index )
          }
          /*}*/
        }
      }

    Seq( startValue )
    addStep( graph( 0 ), pathStore )
    pathStore
  } /*work when graph sorted from root to leafs*/

  def showPath( path1: Seq[ Int ] ): String = path1.mkString( "->" )

  def mergePath( path1: Seq[ Int ],
                 path2: Seq[ Int ] ): Seq[ Int ] = {
    val seqIntersect = path1.intersect( path2 )
    val seqDiff_1From2 = path1.diff( path2 )
    val seqDiff_2From1 = path2.diff( path1 )
    ( seqDiff_2From1 :+ seqIntersect.last ) ++ seqDiff_1From2
  }
  /*________________([1])->6->8->7->2->5
  0->3->4->9->[15]->([1])->14->10
              [15]->13*/
  /*order close to from file input*/
  /*?order impact execution?
  hinder 'path' method*/
  val tree1 = Array(
    TreeNode( Option( 1 ), 6, Option( List( 8 ) ), 6 ),
    TreeNode( Option( 9 ), 15, Option( List( 1, 13 ) ), 4 ),
    TreeNode( Option( 15 ), 1, Option( List( 6, 14 ) ), 5 ),
    TreeNode( Option.empty, 0, Option( List( 3 ) ), 0 ),
    TreeNode( Option( 4 ), 9, Option( List( 15 ) ), 3 ),
    TreeNode( Option( 7 ), 2, Option( List( 5 ) ), 9 ),
    TreeNode( Option( 1 ), 14, Option( List( 10 ) ), 6 ),
    TreeNode( Option( 3 ), 4, Option( List( 9 ) ), 2 ),
    TreeNode( Option( 8 ), 7, Option( List( 2 ) ), 8 ),
    TreeNode( Option( 6 ), 8, Option( List( 7 ) ), 7 ),
    TreeNode( Option( 0 ), 3, Option( List( 4 ) ), 1 ),
    TreeNode( Option( 15 ), 13, Option.empty, 5 ),
    TreeNode( Option( 14 ), 10, Option.empty, 7 ),
    TreeNode( Option( 2 ), 5, Option.empty, 10 )
  )

  tree1
    //.view/*= SeqViewF(...)*/
    .filter {
      /*case TreeNode( parent,
      value,
      children,
      height
      ) => height >= tree1.length / 2*/ /*work*/
      x => x.height >= tree1.length / 2
    }
  /*all tree leafs
  actualy only two with maximum height needed
  if they has a different height*/
  tree1
    //.view/*= SeqViewF(...)*/
    .filter {
      /*case TreeNode( parent,
      value,
      children,
      height
      ) => children.isEmpty*/ /*work*/
      x => x.children.isEmpty
    }
  /*not define Ordering[TreeNode]*/
  /*farthest or furthest leaf in tree
  for non empty tree must be at least one*/
  val farthestLeaf: TreeNode = tree1
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
    } ) /*works*/

  val tree1Height: Int = farthestLeaf.height /*works*/

  tree1
    .filter { _.height == -1 }
  /*may be / exist or not
  if exist (& what returns if not? Array())
  used to check / compare maxSrotestPath with 'farthestLeaf'*/
  val lessDistantLeaf: TreeNode = tree1
    .view /*? return value if more then one method applayed ?*/
    .filterNot { x => x == farthestLeaf }
    /*UnsupportedOperationException: empty.max
    //.filter { x => x.height == -1 }*/
    .filter { x => x.children.isEmpty }
    .max( new Ordering[ TreeNode ] {
      def compare( a: TreeNode,
                   b: TreeNode ) = {
        a.height compare b.height
      }
    } ) /*work*/

  /*>Items in the collections aren’t required to implement 'Ordered', but
  'Ordered' uses are still statically type checked.
  >You can define your own orderings
  without any additional library support:*/
  /*'views' in the standard library that
  translates 'Ordered' into 'Ordering' (and vice versa).*/
  List( 1, 2, 3, 4 )
    .min( new Ordering[ Int ] { def compare( a: Int, b: Int ) = b compare a } )
  List( 1, 2, 3, 4 )
    .min( new Ordering[ Int ] { def compare( a: Int, b: Int ) = a compare b } )
  //seq.reduce(_ min _)
  /*all tree leafs for min path check
  & root
  useing 'path' instead for simplicity*/
  tree1
    .filter {
      //val treeHeight = tree1Height
      /*case TreeNode( parent,
        value,
        children,
        height
        ) =>*/ ( x: TreeNode ) =>
        ( x.children.isEmpty &&
          /*
      treeHeight != tree.length
      treeHeight == treeNode.maxHeight
      */
          /*if tree1Height == 10 then looking for
          nodes with height (5,4,3,2,1,0)
          but at the same branch as 'farthestLeaf'
          so
          use 'path' instead*/
          /*upper bound*/
          (
            ( x.height <= tree1Height / 2 + 1 ) &&
            /*lower bound*/
            ( x.height >= tree1Height / 2 - 1 ) )
        ) || /*to warp string operator must be at the end*/
            ( x.parent.isEmpty )
    }
  /*________________([1])->6->8->7->2->5
  0->3->4->9->[15]->([1])->14->10
              [15]->13*/
  val path5 = path( tree1, 5 )
  val path9 = path( tree1, 9 )
  val path1 = path( tree1, 1 )
  val path15 = path( tree1, 15 )
  val path0 = path( tree1, 0 )
  val path10 = path( tree1, 10 )
  val path13 = path( tree1, 13 )
  
  mergePath( path1, path9 )
  /*java.util.NoSuchElementException
  when Array unsorted*/
  mergePath( path9, path5 )
  mergePath( path5, path9 )
  mergePath( path0, path9 )
  mergePath( path0, path0 )
  mergePath( path10, path13 )
  mergePath( path13, path10 )

  val farthestLeafPath = path( tree1, farthestLeaf.value )
  /*if exist 'lessDistantLeaf'*/
  val lessFartLeafPath = Option( lessDistantLeaf ) match {
    case Some( node ) => path( tree1, lessDistantLeaf.value )
    case None         => Seq.empty
  }
  /*do not bother if path is empty?
  must be not empty*/
  /*cases
  (0 + 1)/2 = 0
  (2 + 1)/2 = 1
  as 'path length' = path.size - 1 then
  may be no '+1' needed to pick node close to root
  */
  val middleIndex = farthestLeafPath.size / 2
  val initialCheckNode = farthestLeafPath( middleIndex )
  /** compare path length
    * from 'checkFromNode' to
    * root == 'longetLeafPath.head',
    * 'lessDistantLeaf' if exist &
    * 'farthestLeaf.value'
    */
  def minMaxPath( longestLeafPath: Seq[ Int ],
                  checkFromNode: Int,
                  bestLength: Int = farthestLeaf.height ): Int = {
    /*Option(lessDistantLeaf).getOrElse(-1)*/
    /*if ( lessDistantLeaf == null ) {*/
    if ( lessFartLeafPath.isEmpty ) {
      /*no any else / other leaf in tree - one leaf only*/
      if ( middleIndex % 2 == 0 ) {
        farthestLeaf.height / 2
      } else {
        /*max part from odd number half*/
        farthestLeaf.height / 2 + 1
      }
    } else {
      /*creating Seq[Int] of possible path length
      until < bestLength or check complete
      if not interrapted then
      pick 'max' result as 'bestLength' then
      change 'checkFromNode' by next to 'root' &
      call 'minMaxPath' recursively
      if 'root' then
      return 'bestLength'*/
      if ( checkFromNode == longestLeafPath.head ) {
        println( "nothing with compare" )
        bestLength
      } else {
        /*path length from 'checkFromNode' to 'root' =
        pick / find 'checkFromNode' by node value then
        'checkFromNode.height' or
        'checkFromNode' index in 'longetLeafPath'*/
        val checkFromNodeToRootLength = farthestLeafPath.indexOf( checkFromNode )
        val currentMax = Seq( checkFromNodeToRootLength,
          farthestLeaf.height - checkFromNodeToRootLength,
          /*if 'lessDistantLeaf' then
        possible to reuse it path to root as 'val'*/
          mergePath( lessFartLeafPath, path( tree1, checkFromNode ) ).length - 1 ).max

        //Seq( bestLength, currentMax ).max
        if ( currentMax < bestLength ) {
          println( "recursive call" )
          /*recursive call*/
          minMaxPath( farthestLeafPath,
            /*index must be positive, not negative*/
            farthestLeafPath( checkFromNodeToRootLength - 1 ),
            currentMax )
        } else {
          println( "no length improvment" )
          bestLength
        }
      }
    }
  } /*possibly work, on some input definetly*/

  minMaxPath( farthestLeafPath, initialCheckNode )
  farthestLeafPath.indexOf( 5 )
  farthestLeafPath.indexOf( 15 )
  farthestLeafPath.indexOf( 9 )
  farthestLeafPath.indexOf( 1 )
  farthestLeafPath.indexOf( 0 )
  /*longestLeafPath.head == */
  farthestLeafPath.head
  val check1 = farthestLeafPath.indexOf( 1 )
  val currentMax1 = Seq( check1,
    farthestLeaf.height - check1,
    /*if 'lessDistantLeaf' then
        possible to reuse it path to root as 'val'*/
    mergePath( lessFartLeafPath, path( tree1, 1 ) ).length ).max
  val treeArray2: Array[ TreeNode ] =
    new Array[ TreeNode ]( 13 ) /*full of 'null'*/
  /*update element in predefined Array*/
  def addChildrenList( nodeValue: Int,
                       Index: Int ) = {
    tree1( Index ) =
      TreeNode( tree1( Index ).parent,
        tree1( Index ).value,
        Option( List( nodeValue ) ),
        tree1( Index ).height )
  }
  def addChild( nodeValue: Int,
                Index: Int ) = {
    tree1( Index ) =
      TreeNode( tree1( Index ).parent,
        tree1( Index ).value,
        Option( nodeValue +: tree1( Index ).children.get ),
        tree1( Index ).height )
  }
  /*check if node is a child of existing entry*/
  def isChild( nodeValue: Int,
               Index: Int ) = {
    for ( i <- Index to 0 by -1 ) {
      /*?get value?*/
      /*if (tree1( i ).value == nodeValue) {*/
      /*get children*/
      tree1( i ).children match {
        /*chech if present
        if not
        add new*/
        case Some( children ) if ( !children.contains( nodeValue ) ) => {
          addChild( nodeValue, Index )
        }
        /*case Some( children ) if ( children.contains( nodeValue ) ) => {
          /*skip & do nothing for now*/
        }*/
        /*add new*/
        case None => addChildrenList( nodeValue, Index )
      }
      /*}*/
    }
  }

  def addNewNode( treeArray: Array[ TreeNode ],
                  currentIndex: Int,
                  nodeValue: Int,
                  nodeChild: Int ) = {
    treeArray( currentIndex ) =
      TreeNode( Option.empty, nodeValue, Option( List( nodeChild ) ), 0 )
  }

  tree1( 5 )
  /*reassigment to val
  tree1( 5 ).children = Option(List(33)) */
  tree1( 5 ) =
    TreeNode( tree1( 5 ).parent,
      tree1( 5 ).value,
      Option( List( 33 ) ),
      tree1( 5 ).height )
  tree1( 5 )
  tree1( 5 ) =
    TreeNode( tree1( 5 ).parent,
      tree1( 5 ).value,
      Option.empty,
      tree1( 5 ).height )
  tree1( 5 )
  tree1( 4 ).children
  tree1( 4 ).children.get.contains( 1 )
  tree1( 4 ).children.get.contains( 13 )
  tree1( 6 ).parent
  tree1( 6 ).parent.getOrElse( -1 )
  tree1.indexOf( 15, 4 )
  tree1.indexOf( 15 )
  /*Some(TreeNode)*/
  val someParentFindedOpt = tree1.find { x => x.value == 15 }
  /*too complex to handle*/
  //val someParentFinded: TreeNode=someParentFindedOpt.getOrElse(TreeNode())
  //someParentFinded.
  /*:None*/
  tree1.find { x => x.value == -15 }
  tree1( 0 ).parent.getOrElse( -1 )
  ( 10 to 0 by -1 )

}