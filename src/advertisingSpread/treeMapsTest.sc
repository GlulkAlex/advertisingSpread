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
  var optimalNode: Node = new Node( CurrentHeight = 0 )
                                                  //> optimalNode  : advertisingSpread.treeMapsTest.Node = {val:-1,h:0,R:0}
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
			      val child = nodesMap(childrenLeft.head.End)
			      
		        if (child.CurrentHeight < parentHeight + 1) {
		          child.CurrentHeight = parentHeight + 1
		          
			        if (child.CurrentRank == 0) {
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

  for ( i <- 0 until n ) {
    /*val start = edgesWithMaxLeaf( i )._1
    val end = edgesWithMaxLeaf( i )._2*/
    val start = edgesOfEqualDepth( i )._1
    val end = edgesOfEqualDepth( i )._2
    
    val nodesMapStart = nodesMap.getOrElse( start, None )
    val nodesMapEnd = nodesMap.getOrElse( end, None )

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
    if ( nodesMapStart == None &&
      nodesMapEnd == None ) {
      nodesMap += ( start -> Node(
        Value = start,
        CurrentHeight = 0,
        CurrentRank = 1 ) )
      nodesMap += ( end -> Node(
        Value = end,
        CurrentHeight = 1,
        CurrentRank = 0 ) )
    } else if ( nodesMapStart != None &&
      nodesMapEnd == None ) {
      nodesMap += ( end -> Node(
        Value = end,
        CurrentHeight = nodesMap( start ).CurrentHeight + 1,
        CurrentRank = 0 ) )
      //*debug
      //println("nodesMap(start).CurrentHeight:" + nodesMap(start).CurrentHeight)

      parentRankUpdate( /*as edge end*/ start, 0 )
    } else if ( nodesMapStart == None &&
      nodesMapEnd != None ) {
      nodesMap += ( start -> Node(
        Value = start,
        CurrentHeight = 0,
        CurrentRank = nodesMap( end ).CurrentRank + 1 ) )
      /*propagate 'Height' down to subtree*/
      subTreeHeightUpdate( end,
        0 )
    } else if ( nodesMapStart != None &&
      nodesMapEnd != None ) {
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
  }

  //*println( edgesMap )
  println( nodesMap )                             //> Map(0 -> {val:0,h:0,R:3}, 5 -> {val:5,h:2,R:1}, 10 -> {val:10,h:3,R:0}, 14 
                                                  //| -> {val:14,h:3,R:0}, 20 -> {val:20,h:3,R:0}, 1 -> {val:1,h:1,R:2}, 6 -> {va
                                                  //| l:6,h:3,R:0}, 21 -> {val:21,h:3,R:0}, 9 -> {val:9,h:2,R:1}, 13 -> {val:13,h
                                                  //| :3,R:0}, 2 -> {val:2,h:2,R:1}, 17 -> {val:17,h:3,R:0}, 12 -> {val:12,h:2,R:
                                                  //| 1}, 7 -> {val:7,h:3,R:0}, 3 -> {val:3,h:3,R:0}, 18 -> {val:18,h:3,R:0}, 16 
                                                  //| -> {val:16,h:2,R:1}, 11 -> {val:11,h:3,R:0}, 8 -> {val:8,h:1,R:2}, 19 -> {v
                                                  //| al:19,h:2
                                                  //| Output exceeds cutoff limit.
  edgesMap.size                                   //> res0: Int = 21
  n                                               //> res1: Int = 21
  /*must be edgesMap.size + 1*/
  nodesMap.size                                   //> res2: Int = 22
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
}