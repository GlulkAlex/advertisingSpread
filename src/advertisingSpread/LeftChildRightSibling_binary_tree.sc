package advertisingSpread

object LeftChildRightSibling_binary_tree {
/*From Wikipedia, the free encyclopedia
In computer science,
a 'left-child, right-sibling binary tree' is
a 'binary tree' representation of a 'k-ary tree'.
The process of
converting from
a 'k-ary tree' to
an 'LC-RS' 'binary tree' is
sometimes called a 'Knuth transform'.

To form a 'binary tree' from
an arbitrary 'k-ary tree' by this method,
the 'root' of the original tree is made
the 'root' of the 'binary tree'.
Then,
starting with the 'root',
each node's 'leftmost child' in
the original tree is
made its 'left child' in the 'binary tree', and
its 'nearest sibling' to the right in
the original tree is
made its 'right child' in the 'binary tree'.

If the original tree was 'sorted',
the new 'tree' will be
a 'binary search tree'.
*/
/*Example:
6-ary tree represented as a binary tree:
A
|   ||| ||
B   CDE FG
| ||  || |
H IG  KL M
||    ||
NO    PQ

[A]
  [G]
    [M]
  [F]
  [E]
    [L]
      [Q]
    [K]
      [P]
  [D]
  [C]
  [B]
    [J]
    [I]
    [H]
      [O]
      [N]
 
      A
     /
    B->C->D->E->F->G
   /        /     /
  H->I->J  K->L  M
 /        /  /
N->O     P  Q
*/
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
}