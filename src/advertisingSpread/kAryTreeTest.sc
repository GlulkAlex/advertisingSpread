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

}