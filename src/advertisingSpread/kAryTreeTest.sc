package advertisingSpread

object kAryTreeTest {
  /*In graph theory,
  a 'k-ary' tree is
  a rooted tree in which
  each node has
  no more than 'k' children.
  It is also sometimes known as
  a k-way tree,
  an N-ary tree,
  or an M-ary tree.
  A binary tree is
  the special case where k=2.*/
  /**Left-child right-sibling binary tree*/
  /*In computer science,
  a 'left-child, right-sibling binary tree' is
  a 'binary tree' representation of a 'k-ary tree'.
  The process of converting from a 'k-ary' tree to an 'LC-RS' binary tree is
  sometimes called a 'Knuth transform'.
  To form a binary tree from
  an arbitrary 'k-ary' tree by this method,
  the root of the original tree is
  made the root of the 'binary tree'.
  Then,
  starting with the root,
  each node's 'leftmost child' in
  the original tree is
  made its 'left child' in the binary tree, and
  its 'nearest sibling' to the right in
  the original tree is
  made its 'right child' in the binary tree.
  If the original tree was sorted,
  the new tree will be
  a 'binary search tree'.
  */
  /*A generic tree view
  like in the file system directories*/

  /*Type theory
    As an ADT,
    the abstract 'tree' type 'T'
    with 'values' of some type 'E' is defined, using
    the abstract 'forest' type 'F' (list of 'trees'),
    by the functions:
      value: T → E
      children: T → F
      nil: () → F
      node: E × F → T
    with the axioms:
      value(node(e, f)) = e
      children(node(e, f)) = f
      
  In terms of type theory,
  a tree is
  an 'inductive type' defined by
  the 'constructors':
    'nil' (empty forest) and
    'node' ('tree' with
      'root node' with
      given 'value' and
      children).*/

  /*Recursive
    Recursively,
    as a data type a 'tree' is defined as
    a 'value' (of some data type, possibly empty), together with
    a 'list' of 'trees' (possibly an 'empty list'),
    the 'subtrees' of its 'children';
    symbolically:
      t: v [t[1], ..., t[k]]
    (A tree 't' consists of a value 'v' and
    a 'list' of other 'trees'.)
    
    More elegantly,
    via 'mutual recursion', of which
    a 'tree' is
    one of the most basic examples,
    a 'tree' can be defined in terms of
    a 'forest' (a 'list of trees'), where
    a 'tree' consists of
    a 'value' and a 'forest' (the 'subtrees' of its 'children'):
      f: [t[1], ..., t[k]]
      t: v f
      
    Note: that
    this definition is
    in terms of 'values', and
    is appropriate in functional languages
    (it assumes 'referential transparency');
      different trees have no connections, as
      they are simply 'lists' of 'values'.
      
    As a 'data structure',
    a 'tree' is defined as
      a node (the root), which
      itself consists of a 'value' (of some data type, possibly 'empty'),
      together with
      a 'list of references' to
      other nodes (list possibly 'empty', references possibly 'null');
      symbolically:
        n: v [&n[1], ..., &n[k]]
        
    (A node 'n' consists of
    a value 'v' and
    a 'list of references' to other nodes.)
    
    This data structure defines
    a 'directed graph', and
    for it to be
    a 'tree'
    one must
    add a condition on its 'global structure' (its topology),
    namely that
    at most one reference can point to
    any given node (a node has at most a 'single parent'), and
    no node in the tree point to the 'root'.
    In fact,
    every node (other than the 'root') must have
    exactly 'one parent', and
    the 'root' must have 'no parents'.
  */

  /*restrictions:
no duplicate(ed) values
if exist / contains then
stay the same, no / without change*/
  /**Samples:*/
  /*empty tree*/
  "(Nil){Nil;-1}[Empty]"                          //> res0: String("(Nil){Nil;-1}[Empty]") = (Nil){Nil;-1}[Empty]
  /*non empty tree*/
  /*root without children*/
  "(Nil){1;0}[Empty]"                             //> res1: String("(Nil){1;0}[Empty]") = (Nil){1;0}[Empty]
  /*root with children*/
  "(Nil){2;0}[nonEmpty, .. , nonEmpty, .. , Empty]"
                                                  //> res2: String("(Nil){2;0}[nonEmpty, .. , nonEmpty, .. , Empty]") = (Nil){2;0
                                                  //| }[nonEmpty, .. , nonEmpty, .. , Empty]
  /*node without children*/
  "(!=3){3;>0}[Empty]"                            //> res3: String("(!=3){3;>0}[Empty]") = (!=3){3;>0}[Empty]
  /*node with children*/
  "(!=4){4;>0}[nonEmpty, .. , nonEmpty, .. , Empty]"
                                                  //> res4: String("(!=4){4;>0}[nonEmpty, .. , nonEmpty, .. , Empty]") = (!=4){4;
                                                  //| >0}[nonEmpty, .. , nonEmpty, .. , Empty]
  /**Where:*/
  /*(Nil) or (!='value') -- 'parent' node or root value
{Nil;-1} or {1;0} or {2;>0} -- {'value';'height'}
[Empty] or [nonEmpty, .. , nonEmpty, .. , Empty] --
list of children subtrees
*/
  /*convention:
How to choose in which child store / add value / search for specific value ?
check with head.value, then next child in list ?
No, tree already predefined
only build copy / representation needed
*/

  /*Example:
                  ([1])->6->8->7->2->5
0->3->4->9->[15]->([1])->14->10
            [15]->13
            
(Nil){0;0}[
  (0){3;1}[
    (3){4;2}[
      (4){9;3}[
	      (9){15;4}[
  	      (15){1;5}[
	  	      (1){6;6}[
  	  	      (6){8;7}[
	  	  	      (8){7;8}[
  	  	  	      (7){2;9}[
	  	  	  	      (2){5;10}[Empty]
  	  	  	      ]
	  	  	      ]
  	  	      ]
	  	      ]
	  	      (1){14;6}[
		  	      (14){10;7}[Empty]
	  	      ]
  	      ]
  	      (15){13;5}[Empty]
	      ]
      ]
    ]
  ]
]
*/
  /*so, leafs is nodes with [Empty]
presumably:
 node or root from which begins 'minimum path' to
any other leaf & root
lays / is / exist / belongs / within
largest / 'maximum path' from leaf to root or
maximum distance from root to leaf == 'tree height' &
adjasted to (height / 2) node on this path
*/
  1                                               //> res5: Int(1) = 1

  abstract class kIntTree {
    /*in 'abstract' class methods allowed without bodies
  or exact method definitions*/
    def isEmpty: Boolean
    /*'value' (of some data type, possibly 'empty')*/
    //: Tree to Element
    //like head ?
    def value: Int
    def height: Int
    /*'list of references' to
      other nodes
      (list possibly 'empty', references possibly 'null')*/
    //:Tree to Forest
    //like tail
    def children: List[ kIntTree ]
    //: node ?
    //def    parent:
    //:Element with Forest to Tree
    def node: ( ( Int, Int ), List[ kIntTree ] )

    //def    nil: () → F
    /*add new root / head as value, then
  add rest / this as non empty child / tail*/
    def prePend[ Int ]( elem: Int ): kIntTree //= new Cons(elem, this)
    /*traverce to end / tail with 'empty child', then
  add one*/
    def apPend[ Int ]( elem: Int ): kIntTree //= new Cons(elem, this)

    //include (? more like 'insert' or 'add' as prefix ?) set in self
    def incl( x: Int ): kIntTree
    def contains( x: Int ): Boolean
    //exersise: implement
    def union( other: kIntTree ): kIntTree
  }

  //'cons' stands for construction operation
  /*class Cons[ Int ]( val value: Int,
                     val children: List[ kIntTree ] ) extends kIntTree {
    def isEmpty = false

    override def toString = {
      "{" + value + "}[" + children + "]"
    }
  }*/

  /** refactor 'Nil' as object
    * as it is a single instance of it needed
    */
  1 + 1                                           //> res6: Int(2) = 2
  def treeF( parent: Option[ Int ], /*exist or not*/
             /*must be refference to existing tree node
            as 'treeF' or 'None' or 'treeF.empty'*/
             value: Int, /*assume it is unique*/
             children: Option[ List[ Int ] ] /*empty or not*/ ): Map[ Int /*nodeValue*/ , ( Option[ Int ] /*nodeParent*/ , Option[ List[ Int ] ] /*nodeChildren*/ , Int /*nodeHeghit*/ ) ] = {
      /*'heght' changes when node created (initial value) &
      when parent changes*/
      def nodeHeight: Int = parent match {
        case Some( node ) => node + 1 /*parentHeight + 1*/
        case None         => 0
      }

    Map( value ->
      ( parent,
        children,
        nodeHeight ) )
  }                                               //> treeF: (parent: Option[Int], value: Int, children: Option[List[Int]])Map[In
                                                  //| t,(Option[Int], Option[List[Int]], Int)]

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
  }                                               //> treeF2: (parent: Option[Int], value: Int, children: Option[List[Int]])(Opti
                                                  //| on[Int], Int, Option[List[Int]], Int)
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

  /*in / at the monent / current step
  available / known only
  ?parent? + value or value + children
  not parent + value + children
  so,
  parent must be found / evaluated before &
  may be changed with futher import obtained
  when nessesery*/
  val node0 = treeF( Option.empty, 0, Option( List( 3 ) ) )
                                                  //> node0  : Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(0 -> (None,So
                                                  //| me(List(3)),0))
  val nodeT0 = treeF2( Option.empty, 0, Option( List( 3 ) ) )
                                                  //> nodeT0  : (Option[Int], Int, Option[List[Int]], Int) = (None,0,Some(List(3)
                                                  //| ),0)
  val node1 = treeF( Option( 0 ), 3, Option( List( 4 ) ) )
                                                  //> node1  : Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(3 -> (Some(0)
                                                  //| ,Some(List(4)),1))
  val nodeT1 = treeF2( Option( 0 ), 3, Option( List( 4 ) ) )
                                                  //> nodeT1  : (Option[Int], Int, Option[List[Int]], Int) = (Some(0),3,Some(List
                                                  //| (4)),1)
  val node2 = treeF( Option( 3 ), 4, Option( List( 9 ) ) )
                                                  //> node2  : Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(4 -> (Some(3)
                                                  //| ,Some(List(9)),4))
  val nodeT2 = treeF2( Option( 3 ), 4, Option( List( 9 ) ) )
                                                  //> nodeT2  : (Option[Int], Int, Option[List[Int]], Int) = (Some(3),4,Some(List
                                                  //| (9)),4)
  val node3 = treeF( Option( 4 ), 9, Option( List( 15 ) ) )
                                                  //> node3  : Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(9 -> (Some(4)
                                                  //| ,Some(List(15)),5))
  val nodeT3 = treeF2( Option( 4 ), 9, Option( List( 15 ) ) )
                                                  //> nodeT3  : (Option[Int], Int, Option[List[Int]], Int) = (Some(4),9,Some(List
                                                  //| (15)),5)
  /*1st fork*/
  val node4 = treeF( Option( 9 ), 15, Option( List( 1, 13 ) ) )
                                                  //> node4  : Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(15 -> (Some(9
                                                  //| ),Some(List(1, 13)),10))
  val nodeT4 = treeF2( Option( 9 ), 15, Option( List( 1, 13 ) ) )
                                                  //> nodeT4  : (Option[Int], Int, Option[List[Int]], Int) = (Some(9),15,Some(Lis
                                                  //| t(1, 13)),10)
  val sameASnodeT4 = nodeT4                       //> sameASnodeT4  : (Option[Int], Int, Option[List[Int]], Int) = (Some(9),15,So
                                                  //| me(List(1, 13)),10)
  sameASnodeT4 == nodeT4                          //> res7: Boolean = true
  sameASnodeT4 == nodeT3                          //> res8: Boolean = false
  sameASnodeT4.eq(nodeT4)                         //> res9: Boolean = true
  sameASnodeT4.eq(nodeT3)                         //> res10: Boolean = false
  sameASnodeT4.equals(nodeT4)                     //> res11: Boolean = true
  sameASnodeT4.equals(nodeT3)                     //> res12: Boolean = false
  sameASnodeT4.canEqual(nodeT4)                   //> res13: Boolean = true
  sameASnodeT4.canEqual(nodeT3)                   //> res14: Boolean = true
  //node1.nodeHeight
  node4.getOrElse( 9, -1 )                        //> res15: Any = -1
  node4.getOrElse( 15, -2 )                       //> res16: Any = (Some(9),Some(List(1, 13)),10)
  node4.keys.head                                 //> res17: Int = 15
  node4.values.head                               //> res18: (Option[Int], Option[List[Int]], Int) = (Some(9),Some(List(1, 13)),
                                                  //| 10)
  val ( parent0, nodeVal0, children0, heght0 ) = nodeT4
                                                  //> parent0  : Option[Int] = Some(9)
                                                  //| nodeVal0  : Int = 15
                                                  //| children0  : Option[List[Int]] = Some(List(1, 13))
                                                  //| heght0  : Int = 10
  parent0.getOrElse( -1 )                         //> res19: Int = 9
  val forest0 = Seq( node0, node1, node2, node3, node4, node0 )
                                                  //> forest0  : Seq[Map[Int,(Option[Int], Option[List[Int]], Int)]] = List(Map(
                                                  //| 0 -> (None,Some(List(3)),0)), Map(3 -> (Some(0),Some(List(4)),1)), Map(4 -
                                                  //| > (Some(3),Some(List(9)),4)), Map(9 -> (Some(4),Some(List(15)),5)), Map(15
                                                  //|  -> (Some(9),Some(List(1, 13)),10)), Map(0 -> (None,Some(List(3)),0)))
  val forest2 = Seq( nodeT0, nodeT1, nodeT2, nodeT3, nodeT4 , nodeT0)
                                                  //> forest2  : Seq[(Option[Int], Int, Option[List[Int]], Int)] = List((None,0,
                                                  //| Some(List(3)),0), (Some(0),3,Some(List(4)),1), (Some(3),4,Some(List(9)),4)
                                                  //| , (Some(4),9,Some(List(15)),5), (Some(9),15,Some(List(1, 13)),10), (None,0
                                                  //| ,Some(List(3)),0))
  
  forest0.head                                    //> res20: Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(0 -> (None,Som
                                                  //| e(List(3)),0))
  forest0.head.hashCode()                         //> res21: Int = -1555808269
  identity(forest0.head)                          //> res22: Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(0 -> (None,Som
                                                  //| e(List(3)),0))
  forest0.last                                    //> res23: Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(0 -> (None,Som
                                                  //| e(List(3)),0))
  forest0.last.hashCode()                         //> res24: Int = -1555808269
  identity(forest0.last)                          //> res25: Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(0 -> (None,Som
                                                  //| e(List(3)),0))
  /*from: scalatest.org
  Checking object identity
  If you need to
  check that
  two references refer to
  the exact same object,
  you can write:
  ref1 should be theSameInstanceAs ref2*/
  forest0.head == forest0.last                    //> res26: Boolean = true
  forest0.head eq forest0.last                    //> res27: Boolean = true
  forest0.head.canEqual(forest0.last)             //> res28: Boolean = true
  forest0.head.canEqual(forest0.drop(1).head)     //> res29: Boolean = true
  forest2.head                                    //> res30: (Option[Int], Int, Option[List[Int]], Int) = (None,0,Some(List(3)),
                                                  //| 0)
  //forest2.head.isInstanceOf
  forest2.last                                    //> res31: (Option[Int], Int, Option[List[Int]], Int) = (None,0,Some(List(3)),
                                                  //| 0)
  //forest2.last.mkString
  forest2.drop(1).head                            //> res32: (Option[Int], Int, Option[List[Int]], Int) = (Some(0),3,Some(List(4
                                                  //| )),1)
  //forest2.last.isInstanceOf
  forest2.head equals forest2.last                //> res33: Boolean = true
  forest2.head eq forest2.last                    //> res34: Boolean = true
  forest2.head == forest2.last                    //> res35: Boolean = true
  forest2.head.canEqual(forest2.last)             //> res36: Boolean = true
  forest2.head.canEqual(forest2.drop(1).head)     //> res37: Boolean = true
  treeF2( Option( 15 ), 1, Option( List( 14, 6 ) ) ) +: forest2
                                                  //> res38: Seq[(Option[Int], Int, Option[List[Int]], Int)] = List((Some(15),1,
                                                  //| Some(List(14, 6)),16), (None,0,Some(List(3)),0), (Some(0),3,Some(List(4)),
                                                  //| 1), (Some(3),4,Some(List(9)),4), (Some(4),9,Some(List(15)),5), (Some(9),15
                                                  //| ,Some(List(1, 13)),10), (None,0,Some(List(3)),0))
  treeF2FindNode( forest2, 15 )                   //> res39: Option[(Option[Int], Int, Option[List[Int]], Int)] = Some((Some(9),
                                                  //| 15,Some(List(1, 13)),10))
}