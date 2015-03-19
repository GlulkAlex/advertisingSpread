package advertisingSpread

object testMap {
  /*relative to root or
  parent node*/
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
  } /*work, but                                   //> pathToRoot: (treeMap: Map[Int,Int], node: Int, currentPath: Seq[Int])Seq[Int
                                                  //| ]
  node itself not includet in result
  as it is 'key' not 'value'*/

  /*all duplicates, except one last common in both path
  must be eliminated &
  remaining parts of pathes must be combained
  if starting from root
  so, path sequences must be sorted or ordered
  then
  proceed to the first difference or fork &
  last common is memory store or accumulator */
  /** Emulate:
    * @example {{{
    * (seqDiff_2From1 :+ seqIntersect.last) ++ seqDiff_1From2
    * }}}
    */
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
      longestPath.drop( longestPath.size / 2 ).head
    }
  } /*works */                                    //> middleNode: (longestPath: Seq[Int])Int

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

  val nodeLeafMap2 = Seq( 14 -> 1, 13 -> 15, 0 -> -1,
    3 -> 0, 4 -> 3, 9 -> 4, 15 -> 9,
    1 -> 15, 6 -> 1, 8 -> 6, 7 -> 8, 2 -> 7, 5 -> 2, 10 -> 14 )
                                                  //> nodeLeafMap2  : Seq[(Int, Int)] = List((14,1), (13,15), (0,-1), (3,0), (4,3
                                                  //| ), (9,4), (15,9), (1,15), (6,1), (8,6), (7,8), (2,7), (5,2), (10,14))
  nodeLeafMap2.size                               //> res5: Int = 14
  val nodeLeafMap3 = Vector( ( 14, 1 ), ( 13, 15 ), ( 0, -1 ),
    ( 3, 0 ), ( 4, 3 ), ( 9, 4 ),
    ( 15, 9 ), ( 1, 15 ), ( 6, 1 ),
    ( 8, 6 ), ( 7, 8 ), ( 2, 7 ), ( 5, 2 ), ( 10, 14 ) )
    .toMap                                        //> nodeLeafMap3  : scala.collection.immutable.Map[Int,Int] = Map(0 -> -1, 5 ->
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
  11 / 2                                          //> res19: Int(5) = 5
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
  /*same as 'mergePath'*/
  val seqIntersect = path5.intersect( path10 )    //> seqIntersect  : Seq[Int] = List(0, 3, 4, 9, 15, 1)
  path10.intersect( path5 )                       //> res36: Seq[Int] = List(0, 3, 4, 9, 15, 1)
  val seqDiff_1From2 = path5.diff( path10 )       //> seqDiff_1From2  : Seq[Int] = List(6, 8, 7, 2, 5)
  val seqDiff_2From1 = path10.diff( path5 )       //> seqDiff_2From1  : Seq[Int] = List(14, 10)

  ( seqDiff_2From1 :+ seqIntersect.last ) ++ seqDiff_1From2
                                                  //> res37: Seq[Int] = List(14, 10, 1, 6, 8, 7, 2, 5)
  (path5 intersect path10)                        //> res38: Seq[Int] = List(0, 3, 4, 9, 15, 1)
  (path5 intersect path10).head                   //> res39: Int = 0
  (path5 intersect path10).init                   //> res40: Seq[Int] = List(0, 3, 4, 9, 15)
  (path5.toSet union path10.toSet) -- (path5 intersect path10).init
                                                  //> res41: scala.collection.immutable.Set[Int] = Set(5, 10, 14, 1, 6, 2, 7, 8)
                                                  //| 

  mergePath( path5, path10 )                      //> res42: Seq[Int] = List(14, 10, 1, 6, 8, 7, 2, 5)
  mergePath( path10, path10 )                     //> res43: Seq[Int] = List(10, 14, 1, 15, 9, 4, 3, 0)
  mergePath( path10, path5 )                      //> res44: Seq[Int] = List(6, 8, 7, 2, 5, 1, 14, 10)
  mergePath( path5, path9 )                       //> res45: Seq[Int] = List(9, 15, 1, 6, 8, 7, 2, 5)
  mergePath( path9, path9 )                       //> res46: Seq[Int] = List(9, 4, 3, 0)
  mergePath( path9, path5 )                       //> res47: Seq[Int] = List(9, 15, 1, 6, 8, 7, 2, 5)
  middleNode( path5 )                             //> res48: Int = 1
  path5.drop( 5 )                                 //> res49: Seq[Int] = List(1, 6, 8, 7, 2, 5)
  middleNode( path10 )                            //> res50: Int = 15
  middleNode( path9 )                             //> res51: Int = 4
  middleNode( path3 )                             //> res52: Int = 3
  middleNode( path0 )                             //> res53: Int = -1
  nodeLeafMap2 groupBy ( _._1 )                   //> res54: scala.collection.immutable.Map[Int,Seq[(Int, Int)]] = Map(0 -> List(
                                                  //| (0,-1)), 5 -> List((5,2)), 10 -> List((10,14)), 14 -> List((14,1)), 1 -> Li
                                                  //| st((1,15)), 6 -> List((6,1)), 9 -> List((9,4)), 13 -> List((13,15)), 2 -> L
                                                  //| ist((2,7)), 7 -> List((7,8)), 3 -> List((3,0)), 8 -> List((8,6)), 4 -> List
                                                  //| ((4,3)), 15 -> List((15,9)))
  /*forks*/
  val forks = ( nodeLeafMap2 groupBy ( _._2 ) )
    .values                                       //> forks  : Iterable[Seq[(Int, Int)]] = MapLike(List((3,0)), List((10,14)), Li
                                                  //| st((14,1), (6,1)), List((8,6)), List((15,9)), List((5,2)), List((2,7)), Lis
                                                  //| t((4,3)), List((0,-1)), List((7,8)), List((9,4)), List((13,15), (1,15)))
  forks
    .toSeq
    .sortWith( _.length > _.length )
    .last                                         //> res55: Seq[(Int, Int)] = List((9,4))
  forks
    .toVector
    .sortWith( _.length > _.length )              //> res56: scala.collection.immutable.Vector[Seq[(Int, Int)]] = Vector(List((14
                                                  //| ,1), (6,1)), List((13,15), (1,15)), List((3,0)), List((10,14)), List((8,6))
                                                  //| , List((15,9)), List((5,2)), List((2,7)), List((4,3)), List((0,-1)), List((
                                                  //| 7,8)), List((9,4)))
  /*Map 'keys' may be of mixed type:*/
  val myMap1 = Map( "Ann Arbor" -> "MI", 49931 -> "MI" )
                                                  //> myMap1  : scala.collection.immutable.Map[Any,String] = Map(Ann Arbor -> MI,
                                                  //|  49931 -> MI)
  myMap1( "Ann Arbor" ) /*should be()*/           //> res57: String = MI
  myMap1( 49931 ) /*should be()*/                 //> res58: String = MI
  /*Mixed type 'values' can be added to a map:*/
  val myMap2 = scala.collection.mutable.Map.empty[ String, Any ]
                                                  //> myMap2  : scala.collection.mutable.Map[String,Any] = Map()
  //val myMap2 = scala.collection.mutable.Map.empty[ Any, Any ]
  //val myMap2 = Map.empty[String, Any]
  /*update*/
  //*myMap2 ++= Map( "Ann Arbor" -> "MI", 49931 -> "MI" )
  /*Array or JS Object like syntax*/
  myMap2( "Ann Arbor" ) = ( 48103, 48104, 48108 )
  myMap2( "Houghton" ) = 49931
  myMap2                                          //> res59: scala.collection.mutable.Map[String,Any] = Map(Houghton -> 49931, An
                                                  //| n Arbor -> (48103,48104,48108))
  myMap2( "Ann Arbor" )                           //> res60: Any = (48103,48104,48108)
  
  /*? explicit class declaration / reference ?
  & after it all 'Map's be mutable? or
  only with 'var' declaration?*/
  import scala.collection.mutable.Map
  /*? type inference ?*/
  var nodeLeafMapVar1 = Map.empty ++ nodeLeafMap1 //> nodeLeafMapVar1  : scala.collection.mutable.Map[Int,Int] = Map(8 -> 6, 2 ->
                                                  //|  7, 5 -> 2, 14 -> 1, 13 -> 15, 4 -> 3, 7 -> 8, 10 -> 14, 1 -> 15, 9 -> 4, 3
                                                  //|  -> 0, 15 -> 9, 6 -> 1, 0 -> -1)
  /*?for immutable only distinct 'values' returns?
  case is
  distinct 'keys'
  if repeated 'keys' occurs then
  only last one preserve*/
  //nodeLeafMapVar1.length
  nodeLeafMap1.size                               //> res61: Int = 14
  nodeLeafMapVar1.size                            //> res62: Int = 14
  nodeLeafMapVar1.values.size                     //> res63: Int = 14
  nodeLeafMapVar1.values                          //> res64: Iterable[Int] = HashMap(6, 7, 2, 1, 15, 3, 8, 14, 15, 4, 0, 9, 1, -1
                                                  //| )
  nodeLeafMap1.values.size                        //> res65: Int = 14
  nodeLeafMap1.values                             //> res66: Iterable[Int] = MapLike(-1, 2, 14, 1, 15, 1, 4, 15, 7, 8, 0, 6, 3, 9
                                                  //| )
  nodeLeafMapVar1.isDefinedAt( 5 )                //> res67: Boolean = true
  nodeLeafMapVar1.exists( _ == ( 5, 2 ) )         //> res68: Boolean = true
  nodeLeafMapVar1.exists( _._1 == 5 )             //> res69: Boolean = true
  nodeLeafMapVar1.contains( 5 )                   //> res70: Boolean = true
  nodeLeafMapVar1.isDefinedAt( 57 )               //> res71: Boolean = false
  /*only for mutable*/
  nodeLeafMapVar1 += 0 -> -2                      //> res72: scala.collection.mutable.Map[Int,Int] = Map(8 -> 6, 2 -> 7, 5 -> 2, 
                                                  //| 14 -> 1, 13 -> 15, 4 -> 3, 7 -> 8, 10 -> 14, 1 -> 15, 9 -> 4, 3 -> 0, 15 ->
                                                  //|  9, 6 -> 1, 0 -> -2)
  nodeLeafMapVar1 ++= Map( 31 -> 22, 57 -> 99 )   //> res73: scala.collection.mutable.Map[Int,Int] = Map(8 -> 6, 2 -> 7, 5 -> 2, 
                                                  //| 14 -> 1, 13 -> 15, 4 -> 3, 31 -> 22, 7 -> 8, 10 -> 14, 1 -> 15, 9 -> 4, 57 
                                                  //| -> 99, 3 -> 0, 15 -> 9, 6 -> 1, 0 -> -2)
  nodeLeafMapVar1 -= 31                           //> res74: scala.collection.mutable.Map[Int,Int] = Map(8 -> 6, 2 -> 7, 5 -> 2, 
                                                  //| 14 -> 1, 13 -> 15, 4 -> 3, 7 -> 8, 10 -> 14, 1 -> 15, 9 -> 4, 57 -> 99, 3 -
                                                  //| > 0, 15 -> 9, 6 -> 1, 0 -> -2)
  nodeLeafMapVar1 --= Seq( 57 )                   //> res75: scala.collection.mutable.Map[Int,Int] = Map(8 -> 6, 2 -> 7, 5 -> 2, 
                                                  //| 14 -> 1, 13 -> 15, 4 -> 3, 7 -> 8, 10 -> 14, 1 -> 15, 9 -> 4, 3 -> 0, 15 ->
                                                  //|  9, 6 -> 1, 0 -> -2)
}