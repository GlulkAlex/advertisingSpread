package advertisingSpread

object Dijkstra_algorithm {
/*From Wikipedia, the free encyclopedia
'Dijkstra's algorithm' is
an algorithm for finding
the 'shortest paths' between 'nodes' in a graph,
which may represent,
for example,
road networks.
It was conceived by
computer scientist Edsger W. Dijkstra in 1956 and
published in 1959.
The algorithm exists in many variants;
Dijkstra's original variant found
the shortest path between two nodes, but
a more common variant fixes a single node as
the "source" node and
finds shortest paths from the 'source' to
all other nodes in the graph,
producing a shortest path tree.*/

/*In the following algorithm,
the code 'u ← vertex in Q with min dist[u]',
searches for
the 'vertex' 'u' in the 'vertex set' 'Q' that has
the least 'dist[u]' value.
'length(u, v)' returns
the length of the edge joining
(i.e. the distance between)
the two 'neighbor-nodes' 'u' and 'v'.
The variable 'alt' on line 19 is
the 'length' of the 'path' from
the 'root node' to the neighbor node 'v' if
it were to go through 'u'.
If this path is shorter than
the 'current shortest path' recorded for 'v',
that 'current path' is replaced with this 'alt' path.
The 'prev' array is
populated with
a 'pointer' to the "next-hop" 'node' on the 'source graph' to get
the 'shortest route' to the 'source'.*/
/*
 1  function Dijkstra(Graph, source):
 2
 3      dist[source] ← 0                       // Distance from source to source
 4      prev[source] ← undefined               // Previous node in optimal path initialization
 5
 6      for each vertex v in Graph:  // Initialization
 7          if v ≠ source            // Where v has not yet been removed from Q (unvisited nodes)
 8              dist[v] ← infinity             // Unknown distance function from source to v
 9              prev[v] ← undefined            // Previous node in optimal path from source
10          end if
11          add v to Q                     // All nodes initially in Q (unvisited nodes)
12      end for
13
14      while Q is not empty:
15          u ← vertex in Q with min dist[u]  // Source node in first case
16          remove u from Q
17
18          for each neighbor v of u:           // where v is still in Q.
19              alt ← dist[u] + length(u, v)
20              if alt < dist[v]:               // A shorter path to v has been found
21                  dist[v] ← alt
22                  prev[v] ← u
23              end if
24          end for
25      end while
26
27      return dist[], prev[]
28
29  end function*/
/*If we are
only interested in
a 'shortest path' between
vertices 'source' and 'target',
we can
terminate the search after line 15 if 'u = target'.
Now
we can
read the 'shortest path' from
'source' to 'target' by 'reverse iteration':*/
/*
1  S ← empty sequence
2  u ← target
3  while prev[u] is defined:                 // Construct the shortest path with a stack S
4      insert u at the beginning of S          // Push the vertex onto the stack
5      u ← prev[u]                             // Traverse from target to source
6  end while*/

/*Using a priority queue
A 'min-priority queue' is
an abstract 'data structure' that provides
3 basic operations :
  'add_with_priority()',
  'decrease_priority()' and
  'extract_min()'.
As mentioned earlier,
using such a 'data structure' can lead to
faster computing times than
using a 'basic queue'.
Notably,
'Fibonacci heap' (Fredman & Tarjan 1984) or
'Brodal queue' offer
optimal implementations for those 3 operations.
As the algorithm is slightly different,
we mention it here,
in pseudo-code as well :*/
/*
1  function Dijkstra(Graph, source):
2      dist[source] ← 0                      // Initialization
3      for each vertex v in Graph:
4          if v ≠ source
5              dist[v] ← infinity            // Unknown distance from source to v
6              prev[v] ← undefined           // Predecessor of v
7          end if
8          Q.add_with_priority(v, dist[v])
9      end for
10
11     while Q is not empty:               // The main loop
12         u ← Q.extract_min()            // Remove and return best vertex
13         for each neighbor v of u:
14             alt = dist[u] + length(u, v)
15             if alt < dist[v]
16                 dist[v] ← alt
17                 prev[v] ← u
18                 Q.decrease_priority(v, alt)
19             end if
20         end for
21     end while
21     return prev[]*/
/*Instead of
filling the 'priority queue' with
all nodes in the 'initialization phase',
it is also possible to
initialize it to contain only 'source';
then,
inside the 'if alt < dist[v]' block,
the 'node' must be inserted if
not already in the 'queue'
(instead of performing a 'decrease_priority' operation).
Other 'data structures' can be used to
achieve even faster computing times in practice.*/

/*Dynamic programming perspective
From a 'dynamic programming' point of view,
Dijkstra's algorithm is
a 'successive approximation' 'scheme' that
solves the 'dynamic programming' 'functional equation' for
the 'shortest path' problem by
the 'Reaching method'.
In fact,
Dijkstra's explanation of
the logic behind the algorithm,
namely
Problem 2.
Find the path of
minimum 'total length' between two given 'nodes' 'P' and 'Q'.
We use the fact that,
if 'R' is a 'node' on the 'minimal path' from 'P' to 'Q',
knowledge of the latter implies
the knowledge of the 'minimal path' from 'P' to 'R'.
is a paraphrasing of
Bellman's famous 'Principle of Optimality' in
the context of the 'shortest path' problem.*/

  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
}