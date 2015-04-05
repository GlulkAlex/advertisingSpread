package advertisingSpread

object kAryTreeTest {
	/*possible Graph Representations:
	1. The Adjacency Matrix
	2. Adjacency Lists
	
	Ingredients:
	>  array (or list) of vertices
	>  array (or list) of edges
	>  each edge points to its endpoints
	>  each vertex points to
	   edges incident on (resulting from) it
	
	Note:
	! edge-to-vertex 'one-to-one' correspondence !*/
 
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
/*Arborescence (graph theory)
From Wikipedia, the free encyclopedia

Tree (graph theory).
In graph theory,
an 'arborescence' is
a 'directed graph' in which,
for a 'vertex' 'u' called the 'root' and any other vertex 'v',
there is
exactly one 'directed path' from 'u' to 'v'.
An 'arborescence' is thus
the 'directed-graph' form of a 'rooted tree',
understood here as an undirected graph.*/

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
                                                  //> nodeT0  : (Option[Int], Int, Option[List[Int]], Int) = (None,0,Some(List(3
                                                  //| )),0)
  val node1 = treeF( Option( 0 ), 3, Option( List( 4 ) ) )
                                                  //> node1  : Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(3 -> (Some(0
                                                  //| ),Some(List(4)),1))
  val nodeT1 = treeF2( Option( 0 ), 3, Option( List( 4 ) ) )
                                                  //> nodeT1  : (Option[Int], Int, Option[List[Int]], Int) = (Some(0),3,Some(Lis
                                                  //| t(4)),1)
  val node2 = treeF( Option( 3 ), 4, Option( List( 9 ) ) )
                                                  //> node2  : Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(4 -> (Some(3
                                                  //| ),Some(List(9)),4))
  val nodeT2 = treeF2( Option( 3 ), 4, Option( List( 9 ) ) )
                                                  //> nodeT2  : (Option[Int], Int, Option[List[Int]], Int) = (Some(3),4,Some(Lis
                                                  //| t(9)),4)
  val node3 = treeF( Option( 4 ), 9, Option( List( 15 ) ) )
                                                  //> node3  : Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(9 -> (Some(4
                                                  //| ),Some(List(15)),5))
  val nodeT3 = treeF2( Option( 4 ), 9, Option( List( 15 ) ) )
                                                  //> nodeT3  : (Option[Int], Int, Option[List[Int]], Int) = (Some(4),9,Some(Lis
                                                  //| t(15)),5)
  /*1st fork*/
  val node4 = treeF( Option( 9 ), 15, Option( List( 1, 13 ) ) )
                                                  //> node4  : Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(15 -> (Some(
                                                  //| 9),Some(List(1, 13)),10))
  val nodeT4 = treeF2( Option( 9 ), 15, Option( List( 1, 13 ) ) )
                                                  //> nodeT4  : (Option[Int], Int, Option[List[Int]], Int) = (Some(9),15,Some(Li
                                                  //| st(1, 13)),10)
  val sameASnodeT4 = nodeT4                       //> sameASnodeT4  : (Option[Int], Int, Option[List[Int]], Int) = (Some(9),15,S
                                                  //| ome(List(1, 13)),10)
  sameASnodeT4 == nodeT4                          //> res5: Boolean = true
  sameASnodeT4 == nodeT3                          //> res6: Boolean = false
  sameASnodeT4.eq( nodeT4 )                       //> res7: Boolean = true
  sameASnodeT4.eq( nodeT3 )                       //> res8: Boolean = false
  sameASnodeT4.equals( nodeT4 )                   //> res9: Boolean = true
  sameASnodeT4.equals( nodeT3 )                   //> res10: Boolean = false
  sameASnodeT4.canEqual( nodeT4 )                 //> res11: Boolean = true
  sameASnodeT4.canEqual( nodeT3 )                 //> res12: Boolean = true
  //node1.nodeHeight
  node4.getOrElse( 9, -1 )                        //> res13: Any = -1
  node4.getOrElse( 15, -2 )                       //> res14: Any = (Some(9),Some(List(1, 13)),10)
  node4.keys.head                                 //> res15: Int = 15
  node4.values.head                               //> res16: (Option[Int], Option[List[Int]], Int) = (Some(9),Some(List(1, 13)),
                                                  //| 10)
  val ( parent0, nodeVal0, children0, heght0 ) = nodeT4
                                                  //> parent0  : Option[Int] = Some(9)
                                                  //| nodeVal0  : Int = 15
                                                  //| children0  : Option[List[Int]] = Some(List(1, 13))
                                                  //| heght0  : Int = 10
  parent0.getOrElse( -1 )                         //> res17: Int = 9
  val forest0 = Seq( node0, node1, node2, node3, node4, node0 )
                                                  //> forest0  : Seq[Map[Int,(Option[Int], Option[List[Int]], Int)]] = List(Map(
                                                  //| 0 -> (None,Some(List(3)),0)), Map(3 -> (Some(0),Some(List(4)),1)), Map(4 -
                                                  //| > (Some(3),Some(List(9)),4)), Map(9 -> (Some(4),Some(List(15)),5)), Map(15
                                                  //|  -> (Some(9),Some(List(1, 13)),10)), Map(0 -> (None,Some(List(3)),0)))
  val forest2 = Seq( nodeT0, nodeT1, nodeT2, nodeT3, nodeT4, nodeT0 )
                                                  //> forest2  : Seq[(Option[Int], Int, Option[List[Int]], Int)] = List((None,0,
                                                  //| Some(List(3)),0), (Some(0),3,Some(List(4)),1), (Some(3),4,Some(List(9)),4)
                                                  //| , (Some(4),9,Some(List(15)),5), (Some(9),15,Some(List(1, 13)),10), (None,0
                                                  //| ,Some(List(3)),0))

  forest0.head                                    //> res18: Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(0 -> (None,Som
                                                  //| e(List(3)),0))
  forest0.head.hashCode()                         //> res19: Int = -1555808269
  identity( forest0.head )                        //> res20: Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(0 -> (None,Som
                                                  //| e(List(3)),0))
  forest0.last                                    //> res21: Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(0 -> (None,Som
                                                  //| e(List(3)),0))
  forest0.last.hashCode()                         //> res22: Int = -1555808269
  identity( forest0.last )                        //> res23: Map[Int,(Option[Int], Option[List[Int]], Int)] = Map(0 -> (None,Som
                                                  //| e(List(3)),0))
  /*from: scalatest.org
  Checking object identity
  If you need to
  check that
  two references refer to
  the exact same object,
  you can write:
  ref1 should be theSameInstanceAs ref2*/
  forest0.head == forest0.last                    //> res24: Boolean = true
  forest0.head eq forest0.last                    //> res25: Boolean = true
  forest0.head.canEqual( forest0.last )           //> res26: Boolean = true
  forest0.head.canEqual( forest0.drop( 1 ).head ) //> res27: Boolean = true
  forest2.head                                    //> res28: (Option[Int], Int, Option[List[Int]], Int) = (None,0,Some(List(3)),
                                                  //| 0)
  //forest2.head.isInstanceOf
  forest2.last                                    //> res29: (Option[Int], Int, Option[List[Int]], Int) = (None,0,Some(List(3)),
                                                  //| 0)
  //forest2.last.mkString
  forest2.drop( 1 ).head                          //> res30: (Option[Int], Int, Option[List[Int]], Int) = (Some(0),3,Some(List(4
                                                  //| )),1)
  //forest2.last.isInstanceOf
  forest2.head equals forest2.last                //> res31: Boolean = true
  forest2.head eq forest2.last                    //> res32: Boolean = true
  forest2.head == forest2.last                    //> res33: Boolean = true
  forest2.head.canEqual( forest2.last )           //> res34: Boolean = true
  forest2.head.canEqual( forest2.drop( 1 ).head ) //> res35: Boolean = true
  treeF2( Option( 15 ), 1, Option( List( 14, 6 ) ) ) +: forest2
                                                  //> res36: Seq[(Option[Int], Int, Option[List[Int]], Int)] = List((Some(15),1,
                                                  //| Some(List(14, 6)),16), (None,0,Some(List(3)),0), (Some(0),3,Some(List(4)),
                                                  //| 1), (Some(3),4,Some(List(9)),4), (Some(4),9,Some(List(15)),5), (Some(9),15
                                                  //| ,Some(List(1, 13)),10), (None,0,Some(List(3)),0))
  treeF2FindNode( forest2, 15 )                   //> res37: Option[(Option[Int], Int, Option[List[Int]], Int)] = Some((Some(9),
                                                  //| 15,Some(List(1, 13)),10))
  /*? how about reccursion ?
  & How that can be possibly used ?*/
  case class SubTree( /*var*/ parent: Option[ SubTree ] = None,
                      value: Int,/*must be known in any case*/
                      /*all the elements of a list have the same type*/
                      /*var*/ /*val*/ children: List[ SubTree ] = Nil, /*List.empty or .isDefined*/
                      /*var*/ height: Int = -7/*evaluated value
                      so, must be method
                      as value expected to be updated
                      when three structure changes or
                      nodes be sorted / properly ordered*/ ) {
    /*calls the "primary constructor" of the class*/
    /*must be method body*/
    /** invoke auxiliary constructor
      * with 'new' keyword
      */
    def this( value: Int ) {
      /*must be something with 'this(param)'*/
      /*default*/
      this( None, value, Nil, 0 /*must be parent + 1 or 0*/ )
    }
    def this( value: Int,
              height: Int ) = this( None,
      value,
      Nil,
      height )
    /*for new elements in tree when
      known only node value & one child*/
    def this( value: Int,
              child: SubTree  ) = this( None,
      value,
      List(child),
      0 )
    def this( value: Int,
              children: List[ SubTree ] ) = this( None,
      value,
      children,
      0 )
    //add 'that' to 'itself' (class member - object / instance of class)
    def addChild( child: SubTree ) =
      /*SubTree( parent, value, child +: children, height )*//*woks*/
      SubTree( parent, value, child.changeHeight +: children, height )
      
    def changeHeight(  ) =
      /*SubTree( parent, value, child +: children, height )*//*woks*/
      SubTree( parent, value, children, height + 1 )
  }

  /*for childern manipulation?*/
  case class ForestOfTrees( val trees: List[ SubTree ] )

  /*________________([1])->6->8->7->2->5
  0->3->4->9->[15]->([1])->14->10
              [15]->13*/
  val fakeRoot = SubTree(
    Option.empty, /*None*/
    -1,
    List.empty, /*'Nil' ?use immutable?*/
    -1 )                                          //> fakeRoot  : advertisingSpread.kAryTreeTest.SubTree = SubTree(None,-1,List(
                                                  //| ),-1)
  val fakeRootCopy = fakeRoot.copy( height = -23 )//> fakeRootCopy  : advertisingSpread.kAryTreeTest.SubTree = SubTree(None,-1,L
                                                  //| ist(),-23)
  /*reassigment to 'val'
  fakeRoot.height = 0
  fakeRoot.children = fakeRoot.children :: SubTree( None, 0, Nil, 0 )*/
  /*add new in empty or reassign value, old value lost*/
  /*fakeRoot.children = List(SubTree(None, 0,Nil ,0))*/ /*works*/
  /*'+:' not a member of List
  fakeRoot.children +:= SubTree( None, 0, Nil, 0 )*/
  /** uses 'this' constructor if exist not 'default' values */
  fakeRoot.copy( children =  new SubTree( 0 ) +: fakeRoot.children  )
                                                  //> res38: advertisingSpread.kAryTreeTest.SubTree = SubTree(None,-1,List(SubTr
                                                  //| ee(None,0,List(),0)),-1)
  fakeRoot                                        //> res39: advertisingSpread.kAryTreeTest.SubTree = SubTree(None,-1,List(),-1)
                                                  //| 
  /*'assignment' operators to reassign a sequence*/
  /*add new to existing 'List', old value preserved*/
  /*not enough arguments
  fakeRoot.children +:= SubTree( None, 3, SubTree( 4 ), 0 )*/
  /*overloaded method constructor with alternatives*/
  fakeRoot.addChild( new SubTree( 3, new SubTree( 4 ) ) )
                                                  //> res40: advertisingSpread.kAryTreeTest.SubTree = SubTree(None,-1,List(SubTr
                                                  //| ee(None,3,List(SubTree(None,4,List(),0)),1)),-1)
  val nodeLeaf = new SubTree( 4 )                 //> nodeLeaf  : advertisingSpread.kAryTreeTest.SubTree = SubTree(None,4,List()
                                                  //| ,0)
  /*overloaded method constructor with alternatives
  new SubTree( 3, new SubTree( 4 ))
  new SubTree( 3, nodeLeaf)*/
  //fakeRoot.addChild( SubTree( 3, new SubTree( 4 ) ) )
  fakeRoot                                        //> res41: advertisingSpread.kAryTreeTest.SubTree = SubTree(None,-1,List(),-1)
                                                  //| 
  /*fakeRoot.children.scanLeft(z)(op)*/
  fakeRoot.children.tails.next()                  //> res42: List[advertisingSpread.kAryTreeTest.SubTree] = List()
  fakeRoot.children.tails.next()                  //> res43: List[advertisingSpread.kAryTreeTest.SubTree] = List()
  fakeRoot.children.headOption                    //> res44: Option[advertisingSpread.kAryTreeTest.SubTree] = None
  /*Option(fakeRoot.children/*.isEmpty*/.andThen { x => x.tail })*/
  fakeRoot.children.andThen { x => x }            //> res45: PartialFunction[Int,advertisingSpread.kAryTreeTest.SubTree] = <func
                                                  //| tion1>
  /*java.util.NoSuchElementException*/
  fakeRoot.children match {
    case Nil => Nil
    case nodes => nodes.tail
  }                                               //> res46: List[advertisingSpread.kAryTreeTest.SubTree] = List()
  fakeRoot.children match {
    case Nil => Nil
    case nodes => nodes.last
  }                                               //> res47: Product with Serializable = List()
  fakeRoot.children.drop( 1 )                     //> res48: List[advertisingSpread.kAryTreeTest.SubTree] = List()
  /*'.head' if '.isDefined' !'.isEmpty'*/
  fakeRoot.children.drop( 1 ).headOption          //> res49: Option[advertisingSpread.kAryTreeTest.SubTree] = None
  fakeRoot.children.find { x => x.value == -1 }   //> res50: Option[advertisingSpread.kAryTreeTest.SubTree] = None
  fakeRoot.children.find { x => x.value == 0 }    //> res51: Option[advertisingSpread.kAryTreeTest.SubTree] = None
  fakeRoot.children.find { x => x.value == 3 }    //> res52: Option[advertisingSpread.kAryTreeTest.SubTree] = None
  /*fakeRoot.children.copyToArray(xs, start, len) */
  /*to remove element from children list*/
  /*val (before, after) = fakeRoot.children.partition((x:SubTree) => x.value == 3)
    fakeRoot.children = before ++ after.tail*/ /*works*/
  fakeRoot                                        //> res53: advertisingSpread.kAryTreeTest.SubTree = SubTree(None,-1,List(),-1)
                                                  //| 

  /*subTree( Option( subTree() ), 3, Option( List(
        subTree( Option( subTree ), 4, Option.empty, 2 )
      ) ), 1 )
    ) ), 0 )*/ /*,
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
    TreeNode( Option( 2 ), 5, Option.empty, 10 )*/
  /*)*/
}