package advertisingSpread

object Widest_path_problem {
/*From Wikipedia, the free encyclopedia
In 'graph algorithms',
the 'widest path problem',
also known as
the 'bottleneck shortest path problem' or
the 'maximum capacity path problem',
is the problem of
finding a 'path' between
two designated vertices in a weighted graph,
maximizing the weight of the 'minimum-weight edge' in the 'path'.
For instance,
if the graph represents
connections between routers in the Internet, and
the weight of an edge represents
the bandwidth of a connection between two routers,
the 'widest path problem' is
the problem of
finding an 'end-to-end path' between
two Internet nodes that has
the maximum possible bandwidth.
The weight of the 'minimum-weight edge' is known as
the 'capacity' or
'bandwidth' of the 'path'.
As well as its applications in network routing,
the widest path problem is also
an important component of
the 'Schulze method' for
deciding the winner of a multiway election, and
has been applied to
'digital compositing',
'metabolic analysis', and
the computation of 'maximum flows'.
It is possible to
adapt most 'shortest path' algorithms to
compute 'widest paths',
by modifying them to
use the 'bottleneck distance' instead of
'path length'.
However,
in many cases even faster algorithms are possible.
*/
/*A closely related problem,*/
/**the 'minimax path' problem,
asks for
the 'path' that
minimizes the 'maximum weight' of any of its 'edges'.
It has applications that include
'transportation planning'.*/
/*Any algorithm for the 'widest path' problem can
be transformed into
an algorithm for the 'minimax path' problem, or
vice versa,
by reversing
the sense of
all the 'weight comparisons' performed by the algorithm, or
equivalently by
replacing every 'edge weight' by its negation.*/
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
}