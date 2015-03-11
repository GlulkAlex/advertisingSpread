package advertisingSpread

object testMap {
  /*relative to root*/
  def nodeHeight( treeMap: Map[ Int, Int ],
                  node: Int,
                  currentHeight: Int = -1 ): Int = treeMap get node match {
    case Some( result ) => nodeHeight( treeMap, result, currentHeight + 1 )
    case None           => currentHeight
  } /*work*/                                      //> nodeHeight: (treeMap: Map[Int,Int], node: Int, currentHeight: Int)Int

  def pathToRoot( treeMap: Map[ Int, Int ],
                  node: Int,
                  currentPath: Seq[ Int ] = Seq() ): Seq[ Int ] = treeMap get node match {
    case Some( result ) if result > -1 => currentPath match {
      case Seq()    => pathToRoot( treeMap, result, result +: Seq( node ) )
      case somePath => pathToRoot( treeMap, result, result +: somePath )
    }
    case None | Some( -1 ) => currentPath
  } /*work, but nod itself not includet in result //> pathToRoot: (treeMap: Map[Int,Int], node: Int, currentPath: Seq[Int])Seq[Int
                                                  //| ]
  as it is 'key' not 'value'*/
  /*all duplicates, except one last common in both path
  must be eliminated &
  remaining parts of pathes must be combained
  if starting from root
  so, path sequences must be sorted or ordered
  then
  proceed to the first difference or fork &
  last common is memory store or accumulator */
  /**TODO: make it done -- Ok*/
  def mergePath( path1: Seq[ Int ],
                 path2: Seq[ Int ],
                 //lastChecked: Option[ Int ],
                 /*at least root is common*/
                 //commonPath: Option[ Seq[ Int ] ]
                 /*started with Empty*/
                 commonPath: Seq[ Int ] = Seq() ): Seq[ Int ] = {
    /*cases:
                 stops when
                 1. path1 or path2 is empty, return: Seq()?
                 2. no common elements left
                 3. end of path1 or path2 achived or
                 last element in any path
                 return: path1 from commonPath.tail +: path2 from commonPath.tail
                 where commonPath.tail excludet from path2
                 4. if short path2 is a part of long path1 or
                 they equal ?*/
    /*path1       = 0->3->4->9->15->1->6->8->7->2
    path2       = 0->3->4->9->15->1->14
    path2       = 0->3->4->9
    commonPath  = 0->3->4->9->15->1
    return: 14->1->6->8->7->2
    return: 9->15->1->6->8->7->2
  */
    if ( path1.isEmpty && path2.isEmpty ) {
      commonPath //Seq()
    } else if ( path1.isEmpty ) {
      commonPath.head +: path2
    } else if ( path2.isEmpty ) {
      commonPath.head +: path1
    } else {
      /*Asuming that at least one root node is common
    as processed / compared paths from root*/
      if ( path1.head == path2.head ) {
        commonPath match {
          //case Some( somePath ) => path1.head +: somePath
          //case None             => path1.head
          case Seq() =>
            mergePath( path1.drop( 1 ), path2.drop( 1 ), Seq( path1.head ) )
          case somePath =>
            mergePath( path1.drop( 1 ), path2.drop( 1 ), path1.head +: somePath )
        }
      } else {
        //Seq()
        path2 ++ ( commonPath.head +: path1 )
      }
    }
  } /*works, but better when path1 > path2*/      //> mergePath: (path1: Seq[Int], path2: Seq[Int], commonPath: Seq[Int])Seq[Int]
                                                  //| 
  /*to find / determin shortest path / height
  from this node to root & leafs
  may be the case when
  this / goal is another node adjasted to that*/
  /** @return node in path
  */
  def middleNode( longestPath: Seq[ Int ] ): Int = {
    if ( longestPath.isEmpty ) {
      -1
    } else {
      /*odd or even*/
      /*but actual height = size - 1*/
      /*if even picks one close to root
      if size > 2*/
      longestPath.drop(longestPath.size / 2).head
    }
  }/*works */                                     //> middleNode: (longestPath: Seq[Int])Int

  Seq.fill( 7 )( "Nil" )                          //> res0: Seq[String] = List(Nil, Nil, Nil, Nil, Nil, Nil, Nil)
  Seq.tabulate( 7 )( _ => "Nil" )                 //> res1: Seq[String] = List(Nil, Nil, Nil, Nil, Nil, Nil, Nil)
  Seq.range( 0, 9, 1 )                            //> res2: Seq[Int] = List(0, 1, 2, 3, 4, 5, 6, 7, 8)
  Seq.iterate( 0, 9 )( _ + 1 )                    //> res3: Seq[Int] = List(0, 1, 2, 3, 4, 5, 6, 7, 8)
  /*(1){14;6},(15){13;5},(){0;0},(0){3;1},(3){4;2},(4){9;3},(9){15;4},
(15){1;5},(1){6;6},(6){8;7},(8){7;8},(7){2;9},(2){5;10},(14){10;7}*/
  val nodeLeafMap1 = Map( 14 -> 1, 13 -> 15, 0 -> -1, 3 -> 0, 4 -> 3, 9 -> 4, 15 -> 9,
    1 -> 15, 6 -> 1, 8 -> 6, 7 -> 8, 2 -> 7, 5 -> 2, 10 -> 14 )
                                                  //> nodeLeafMap1  : scala.collection.immutable.Map[Int,Int] = Map(0 -> -1, 5 ->
                                                  //|  2, 10 -> 14, 14 -> 1, 1 -> 15, 6 -> 1, 9 -> 4, 13 -> 15, 2 -> 7, 7 -> 8, 3
                                                  //|  -> 0, 8 -> 6, 4 -> 3, 15 -> 9)
  nodeLeafMap1.size                               //> res4: Int = 14

  val nodeLeafMap2 = Seq( 14 -> 1, 13 -> 15, 0 -> -1, 3 -> 0, 4 -> 3, 9 -> 4, 15 -> 9,
    1 -> 15, 6 -> 1, 8 -> 6, 7 -> 8, 2 -> 7, 5 -> 2, 10 -> 14 )
                                                  //> nodeLeafMap2  : Seq[(Int, Int)] = List((14,1), (13,15), (0,-1), (3,0), (4,3
                                                  //| ), (9,4), (15,9), (1,15), (6,1), (8,6), (7,8), (2,7), (5,2), (10,14))
  nodeLeafMap2.size                               //> res5: Int = 14
  val nodeLeafMap3 = Vector( ( 14, 1 ), ( 13, 15 ), ( 0, -1 ), ( 3, 0 ), ( 4, 3 ), ( 9, 4 ),
    ( 15, 9 ), ( 1, 15 ), ( 6, 1 ), ( 8, 6 ), ( 7, 8 ), ( 2, 7 ), ( 5, 2 ), ( 10, 14 ) ).toMap
                                                  //> nodeLeafMap3  : scala.collection.immutable.Map[Int,Int] = Map(0 -> -1, 5 ->
                                                  //|  2, 10 -> 14, 14 -> 1, 1 -> 15, 6 -> 1, 9 -> 4, 13 -> 15, 2 -> 7, 7 -> 8, 3
                                                  //|  -> 0, 8 -> 6, 4 -> 3, 15 -> 9)
  nodeLeafMap3.size                               //> res6: Int = 14
  nodeLeafMap1( nodeLeafMap1( nodeLeafMap1( nodeLeafMap1( 5 ) ) ) )
                                                  //> res7: Int = 6
  nodeLeafMap1.updated( 0, -2 )                   //> res8: scala.collection.immutable.Map[Int,Int] = Map(0 -> -2, 5 -> 2, 10 -> 
                                                  //| 14, 14 -> 1, 1 -> 15, 6 -> 1, 9 -> 4, 13 -> 15, 2 -> 7, 7 -> 8, 3 -> 0, 8 -
                                                  //| > 6, 4 -> 3, 15 -> 9)
  /*addet value 'shadow' / replace existed
  with the same 'key'*/
  nodeLeafMap1 + ( 0 -> -3 )                      //> res9: scala.collection.immutable.Map[Int,Int] = Map(0 -> -3, 5 -> 2, 10 -> 
                                                  //| 14, 14 -> 1, 1 -> 15, 6 -> 1, 9 -> 4, 13 -> 15, 2 -> 7, 7 -> 8, 3 -> 0, 8 -
                                                  //| > 6, 4 -> 3, 15 -> 9)
  nodeLeafMap1.withDefault { x => "not int" }     //> res10: scala.collection.immutable.Map[Int,Any] = Map(0 -> -1, 5 -> 2, 10 ->
                                                  //|  14, 14 -> 1, 1 -> 15, 6 -> 1, 9 -> 4, 13 -> 15, 2 -> 7, 7 -> 8, 3 -> 0, 8 
                                                  //| -> 6, 4 -> 3, 15 -> 9)
  /*0, 1, 6 - keys to remove*/
  ( nodeLeafMap1 - ( 0, 1, 6 ) ).size             //> res11: Int = 11
  nodeLeafMap1.keys                               //> res12: Iterable[Int] = Set(0, 5, 10, 14, 1, 6, 9, 13, 2, 7, 3, 8, 4, 15)
  //nodeLeafMap1.keyIterator
  nodeLeafMap1.values                             //> res13: Iterable[Int] = MapLike(-1, 2, 14, 1, 15, 1, 4, 15, 7, 8, 0, 6, 3, 9
                                                  //| )
  nodeLeafMap1.values.init                        //> res14: Iterable[Int] = List(-1, 2, 14, 1, 15, 1, 4, 15, 7, 8, 0, 6, 3)
  nodeLeafMap1.values.sliding( 2, 2 )             //> res15: Iterator[Iterable[Int]] = non-empty iterator
  nodeLeafMap1 mapValues ( _ * 2 )                //> res16: scala.collection.immutable.Map[Int,Int] = Map(0 -> -2, 5 -> 4, 10 ->
                                                  //|  28, 14 -> 2, 1 -> 30, 6 -> 2, 9 -> 8, 13 -> 30, 2 -> 14, 7 -> 16, 3 -> 0, 
                                                  //| 8 -> 12, 4 -> 6, 15 -> 18)
  /*Also exist
  Class mutable.Map*/
  val cache = collection.mutable.Map[ String, String ]()
                                                  //> cache  : scala.collection.mutable.Map[String,String] = Map()
  nodeHeight( nodeLeafMap1, 5 )                   //> res17: Int = 10
  pathToRoot( nodeLeafMap1, 5 ).size              //> res18: Int = 11
  11/2                                            //> res19: Int(5) = 5
  nodeHeight( nodeLeafMap1, 10 )                  //> res20: Int = 7
  pathToRoot( nodeLeafMap1, 10 ).size             //> res21: Int = 8
  nodeHeight( nodeLeafMap1, 13 )                  //> res22: Int = 5
  pathToRoot( nodeLeafMap1, 13 ).size             //> res23: Int = 6
  nodeHeight( nodeLeafMap1, 23 )                  //> res24: Int = -1
  1 +: Seq()                                      //> res25: Seq[Int] = List(1)
  3 +: Seq( 1, 2 )                                //> res26: Seq[Int] = List(3, 1, 2)
  Seq( 1, 2 ) :+ 4                                //> res27: Seq[Int] = List(1, 2, 4)

  val path5 = pathToRoot( nodeLeafMap1, 5 )       //> path5  : Seq[Int] = List(0, 3, 4, 9, 15, 1, 6, 8, 7, 2, 5)
  val path10 = pathToRoot( nodeLeafMap1, 10 )     //> path10  : Seq[Int] = List(0, 3, 4, 9, 15, 1, 14, 10)
  val path9 = pathToRoot( nodeLeafMap1, 9 )       //> path9  : Seq[Int] = List(0, 3, 4, 9)
  val path3 = pathToRoot( nodeLeafMap1, 3 )       //> path3  : Seq[Int] = List(0, 3)
  val path0 = pathToRoot( nodeLeafMap1, 0 )       //> path0  : Seq[Int] = List()

  path5.mkString( "->" )                          //> res28: String = 0->3->4->9->15->1->6->8->7->2->5
  path10.mkString( "->" )                         //> res29: String = 0->3->4->9->15->1->14->10
  path10.drop( 1 )                                //> res30: Seq[Int] = List(3, 4, 9, 15, 1, 14, 10)
  path10.drop( 6 )                                //> res31: Seq[Int] = List(14, 10)
  path10.drop( 7 )                                //> res32: Seq[Int] = List(10)
  path10.drop( 8 )                                //> res33: Seq[Int] = List()
  ( path5 ++ path10 ).toSet.mkString( "->" )      //> res34: String = 0->5->10->14->1->6->9->2->7->3->8->4->15
  path5.companion                                 //> res35: scala.collection.generic.GenericCompanion[Seq] = scala.collection.im
                                                  //| mutable.List$@144fc21
  mergePath( path5, path10 )                      //> res36: Seq[Int] = List(14, 10, 1, 6, 8, 7, 2, 5)
  mergePath( path10, path10 )                     //> res37: Seq[Int] = List(10, 14, 1, 15, 9, 4, 3, 0)
  mergePath( path10, path5 )                      //> res38: Seq[Int] = List(6, 8, 7, 2, 5, 1, 14, 10)
  mergePath( path5, path9 )                       //> res39: Seq[Int] = List(9, 15, 1, 6, 8, 7, 2, 5)
  mergePath( path9, path9 )                       //> res40: Seq[Int] = List(9, 4, 3, 0)
  mergePath( path9, path5 )                       //> res41: Seq[Int] = List(9, 15, 1, 6, 8, 7, 2, 5)
  middleNode( path5 )                             //> res42: Int = 1
  path5.drop( 5 )                                 //> res43: Seq[Int] = List(1, 6, 8, 7, 2, 5)
  middleNode( path10 )                            //> res44: Int = 15
  middleNode( path9 )                             //> res45: Int = 4
  middleNode( path3 )                             //> res46: Int = 3
  middleNode( path0 )                             //> res47: Int = -1
}