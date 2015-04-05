package advertisingSpread

object Euclidean_minimumSpanningTree {
/*From Wikipedia, the free encyclopedia
The 'Euclidean minimum spanning' tree or 'EMST' is
a 'minimum spanning tree' of
a 'set' of 'n' points in the plane
(or more generally in Rd), where
the 'weight' of
the 'edge' between each 'pair of points' is
the 'distance' between those two points.
In simpler terms,
an 'EMST' connects
a set of dots using lines such that
the 'total length' of all the lines is
minimized and
any dot can be reached from any other by following the lines.

In the plane,
an EMST for
a given set of points may be found in
Θ(n log n) time using
O(n) space in
the *//**'algebraic decision tree' model of computation.*/
/*Faster 'randomized algorithms' of complexity
O(n log log n) are known in
more powerful 'models of computation' that
more accurately model the abilities of real computers.
*/
/*
Applications
An obvious application of 'Euclidean minimum spanning trees' is to
find the cheapest network of
wires or pipes to
connect a set of places,
assuming the 'links cost' a fixed amount per 'unit length'.
However,
while these give an 'absolute' 'lower bound' on
the 'amount of connection' needed,
most such networks prefer
a 'k-connected graph' to a 'tree', so
that
failure of an any individual link will
not split the network into parts.

Another application of 'EMST's is
a 'constant-factor' 'approximation' algorithm for
approximately solving the 'Euclidean traveling salesman problem',
the version of the 'traveling salesman' problem on
a 'set of points' in the plane with
'edges' labelled by their 'length'.
This realistic variation of the problem can be
solved within a factor of 2 by
computing the 'EMST',
doing a walk along its 'boundary' which
outlines the entire 'tree', and
then removing
all but one occurrence of each 'vertex' from this walk.
*/
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
}