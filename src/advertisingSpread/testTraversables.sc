package advertisingSpread

object testTraversables {
  /*'Traversables'
	At the top of the 'collection hierarchy' is
	'trait' 'Traversable'.
	Its only 'abstract' 'operation' is
	'foreach':
	
	'def foreach[U](f: Elem => U)'
	
	'Collection classes' that
	'implement' 'Traversable' just need to
	define this 'method';
	all other methods can be 'inherited' from 'Traversable'.
	
	The 'foreach' method is
	meant to
	traverse all 'elements' of the 'collection', and
	'apply' the given 'operation', 'f', to each element.
	The 'type' of the 'operation' is
	'Elem => U', where
	'Elem' is
	the 'type' of
	the 'collection’s elements' and
	'U' is
	an arbitrary 'result type'.
	The 'invocation' of 'f' is done for
	its 'side effect' only;
	in fact
	any 'function result' of 'f' is
	'discarded' by 'foreach'.
	
	'Traversables' are
	the 'superclass' of
	'Lists',
	'Arrays',
	'Maps',
	'Sets',
	'Streams', and more.
	The methods involved can be 'applied' to
	each other in
	a different type.
	'++' appends two 'Traversables' together.*/
  val set = Set( 1, 9, 10, 22 )                   //> set  : scala.collection.immutable.Set[Int] = Set(1, 9, 10, 22)
  val list = List( 3, 4, 5, 10 )                  //> list  : List[Int] = List(3, 4, 5, 10)
  val result = set ++ list                        //> result  : scala.collection.immutable.Set[Int] = Set(5, 10, 1, 9, 22, 3, 4)
                                                  //| 
  result.size /*should be()*/                     //> res0: Int = 7

  val result2 = list ++ set                       //> result2  : List[Int] = List(3, 4, 5, 10, 1, 9, 10, 22)
  result2.size /*should be()*/                    //> res1: Int = 8

  /*'map'
  will 'apply'
  the 'given function' on
  'all elements' of a 'Traversable' and
  return
  a 'new collection' of the result.*/
  val set1 = Set( 1, 3, 4, 6 )                    //> set1  : scala.collection.immutable.Set[Int] = Set(1, 3, 4, 6)
  val result1 = set1.map( _ * 4 )                 //> result1  : scala.collection.immutable.Set[Int] = Set(4, 12, 16, 24)
  result1.last /*should be()*/                    //> res2: Int = 24

  /*'flatten'
  will smash all child 'Traversables' within a 'Traversable'*/
  val list3 = List( List( 1 ), List( 2, 3, 4 ), List( 5, 6, 7 ), List( 8, 9, 10 ) )
                                                  //> list3  : List[List[Int]] = List(List(1), List(2, 3, 4), List(5, 6, 7), List
                                                  //| (8, 9, 10))
  list3.flatten /*should be(List(, , , , , , , , , ))*/
                                                  //> res3: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  /*'flatMap' will
  not only 'apply' the 'given function' on
  'all elements' of a 'Traversable', but
  'all elements' within
  the elements and
  flatten the results:*/
  val list4 = List( List( 1 ), List( 2, 3, 4 ), List( 5, 6, 7 ), List( 8, 9, 10 ) )
                                                  //> list4  : List[List[Int]] = List(List(1), List(2, 3, 4), List(5, 6, 7), List
                                                  //| (8, 9, 10))
  val result4 = list4.flatMap( _.map( _ * 4 ) )   //> result4  : List[Int] = List(4, 8, 12, 16, 20, 24, 28, 32, 36, 40)
  result4                                         //> res4: List[Int] = List(4, 8, 12, 16, 20, 24, 28, 32, 36, 40)
  /*should be(List(, , , , , , , , , ))*/
  /*'flatMap' of 'Options' will
  'filter' out all 'Nones' and
  Keep the 'Somes'*/
  val list5 = List( 1, 2, 3, 4, 5 )               //> list5  : List[Int] = List(1, 2, 3, 4, 5)
  val result5 = list5.flatMap( it => if ( it % 2 == 0 ) Some( it ) else None )
                                                  //> result5  : List[Int] = List(2, 4)
  list5.map( it => if ( it % 2 == 0 ) Some( it ) else None )
                                                  //> res5: List[Option[Int]] = List(None, Some(2), None, Some(4), None)
  result5 /*should be(List(, ))*/                 //> res6: List[Int] = List(2, 4)

  /*'collect' will
  'apply' a 'partial function' to
  'all elements' of a 'Traversable' and
  will 'return'
  a different 'collection'.
  In this exercise,
  a 'case fragment' is a 'partial function':*/
  val list6 = List( 4, 6, 7, 8, 9, 13, 14 )       //> list6  : List[Int] = List(4, 6, 7, 8, 9, 13, 14)
  val result6 = list6.collect {
    case x: Int if ( x % 2 == 0 ) => x * 3
  }                                               //> result6  : List[Int] = List(12, 18, 24, 42)
  
  list5.collect {
    case x: Int if ( x % 2 == 0 ) => x
    /*no need to include all possible cases*/
    case _                        => None
  }                                               //> res7: List[Any] = List(None, 2, None, 4, None)
  list5.collect {
    case x: Int if ( x % 2 == 0 ) => x
    //case _                        => None
  }                                               //> res8: List[Int] = List(2, 4)
  list5.map {
    case x: Int if ( x % 2 == 0 ) => x
    /*if no others match options present then
    'scala.MatchError:'*/
    case _                        => None
  }                                               //> res9: List[Any] = List(None, 2, None, 4, None)
  list6.map {
    case x: Int if ( x % 2 == 0 ) => x * 3
    case _                        => None
  }                                               //> res10: List[Any] = List(12, 18, None, 24, None, None, 42)
  result6 /*should be(List(, , , ))*/             //> res11: List[Int] = List(12, 18, 24, 42)

  /*'collect' will 'apply'
  a 'partial function' to
  'all elements' of a 'Traversable' and
  will return
  a different 'collection'.
  In this exercise,
  two 'case fragments' are
  'chained' to
  create a more robust result:*/
  val list7 = List( 4, 6, 7, 8, 9, 13, 14 )       //> list7  : List[Int] = List(4, 6, 7, 8, 9, 13, 14)
  val partialFunction1: PartialFunction[ Int, Int ] = {
    case x: Int if x % 2 == 0 => x * 3
  }                                               //> partialFunction1  : PartialFunction[Int,Int] = <function1>
  val partialFunction2: PartialFunction[ Int, Int ] = {
    case y: Int if y % 2 != 0 => y * 4
  }                                               //> partialFunction2  : PartialFunction[Int,Int] = <function1>
  val result7 = list7.collect( partialFunction1 orElse partialFunction2 )
                                                  //> result7  : List[Int] = List(12, 18, 28, 24, 36, 52, 42)
  result7 /*should be(List(, , , , , , ))*/       //> res12: List[Int] = List(12, 18, 28, 24, 36, 52, 42)

  /*'foreach' will 'apply'
  a function to
  'all elements' of a 'Traversable', but
  unlike the 'map' function,
  it will not return anything since
  the 'return type' is 'Unit', which is
  like a 'void' return type in Java, C++*/
  val list8 = List( 4, 6, 7, 8, 9, 13, 14 )       //> list8  : List[Int] = List(4, 6, 7, 8, 9, 13, 14)
  list8.foreach( num => println( num * 4 ) )      //> 16
                                                  //| 24
                                                  //| 28
                                                  //| 32
                                                  //| 36
                                                  //| 52
                                                  //| 56
  list8 /*should be(List(, , , , , , ))*/         //> res13: List[Int] = List(4, 6, 7, 8, 9, 13, 14)

  /*'toArray' will
  'convert' any 'Traversable' to
  an 'Array', which is
  a special 'wrapper' around
  a 'primitive Java array'.*/
  val set2 = Set( 4, 6, 7, 8, 9, 13, 14 )         //> set2  : scala.collection.immutable.Set[Int] = Set(14, 6, 9, 13, 7, 8, 4)
  val result8 = set2.toArray                      //> result8  : Array[Int] = Array(14, 6, 9, 13, 7, 8, 4)
  /*type check*/
  result8.isInstanceOf[ Array[ Int ] ] /*should be()*/
                                                  //> res14: Boolean = true
  /*'toList' will
  'convert' any 'Traversable' to a 'List'.*/
  val set3 = Set( 4, 6, 7, 8, 9, 13, 14 )         //> set3  : scala.collection.immutable.Set[Int] = Set(14, 6, 9, 13, 7, 8, 4)
  val result9 = set3.toList                       //> result9  : List[Int] = List(14, 6, 9, 13, 7, 8, 4)
  /*? plaseholder '_' for 'Any' ?*/
  result9.isInstanceOf[ List[ _ ] ] /*should be()*/
                                                  //> res15: Boolean = true
  /*'toList', as well as
  other 'conversion' methods like
  'toSet',
  'toArray',
  will not convert if
  the 'collection' 'type' is the same.*/
  val list10 = List( 5, 6, 7, 8, 9 )              //> list10  : List[Int] = List(5, 6, 7, 8, 9)
  val result10 = list10.toList                    //> result10  : List[Int] = List(5, 6, 7, 8, 9)
  result10 eq list10 /*should be()*/              //> res16: Boolean = true

  /*'toIterable' will
  'convert' any 'Traversable' to
  an 'Iterable'.
  This is
  a 'base trait' for all 'Scala collections' that
  define
  an 'iterator' method to
  step through one-by-one the collection's 'elements'.*/
  val set11 = Set( 4, 6, 7, 8, 9, 13, 14 )        //> set11  : scala.collection.immutable.Set[Int] = Set(14, 6, 9, 13, 7, 8, 4)
  val result11 = set11.toIterable                 //> result11  : Iterable[Int] = Set(14, 6, 9, 13, 7, 8, 4)
  result11.isInstanceOf[ Iterable[ _ ] ] /*should be()*/
                                                  //> res17: Boolean = true
  result11.iterator.hasNext                       //> res18: Boolean = true
  result11.iterator.next                          //> res19: Int = 14
  result11.headOption                             //> res20: Option[Int] = Some(14)

  /*'toSeq' will
  'convert' any 'Traversable' to
  a 'Seq' which is
  an 'ordered' 'Iterable' and
  is the 'superclass' to
  'List',
  'Queues', and
  'Vectors'.
  'Sequences' provide
  a method 'apply' for 'indexing'.
  'Indices' 'range' from
  '0' up the the 'length' of a 'sequence'.*/
  val set12 = Set( 4, 6, 7, 8, 9, 13, 14 )        //> set12  : scala.collection.immutable.Set[Int] = Set(14, 6, 9, 13, 7, 8, 4)
  val result12 = set12.toSeq                      //> result12  : Seq[Int] = ArrayBuffer(14, 6, 9, 13, 7, 8, 4)
  result12.isInstanceOf[ Seq[ _ ] ] /*should be()*/
                                                  //> res21: Boolean = true
  set12( 0 )                                      //> res22: Boolean = false
  set12( 14 )                                     //> res23: Boolean = true
  result12( 0 )                                   //> res24: Int = 14

  /*'toIndexedSeq' will
  'convert' any 'Traversable' to
  an 'IndexedSeq' which is
  an 'indexed' 'sequence' used in
  'Vectors' and 'Strings'*/
  val set13 = Set( 4, 6, 7, 8, 9, 13, 14 )        //> set13  : scala.collection.immutable.Set[Int] = Set(14, 6, 9, 13, 7, 8, 4)
  val result13 = set13.toIndexedSeq               //> result13  : scala.collection.immutable.IndexedSeq[Int] = Vector(14, 6, 9, 1
                                                  //| 3, 7, 8, 4)
  result13.isInstanceOf[ IndexedSeq[ _ ] ] /*should be()*/
                                                  //> res25: Boolean = true
  /*'toStream' will
  convert any 'Traversable' to
  a 'Stream' which is
  a 'lazy list' where
  'elements' are
  'evaluated' as they are needed.*/
  val list14 = List( 4, 6, 7, 8, 9, 13, 14 )      //> list14  : List[Int] = List(4, 6, 7, 8, 9, 13, 14)
  val result14 = list14.toStream                  //> result14  : scala.collection.immutable.Stream[Int] = Stream(4, ?)
  result14.isInstanceOf[ Stream[ _ ] ] /*should be()*/
                                                  //> res26: Boolean = true
  ( result14 take 3 ) /*should be(List(, , ))*/   //> res27: scala.collection.immutable.Stream[Int] = Stream(4, ?)
  ( result14 take 3 ).tail                        //> res28: scala.collection.immutable.Stream[Int] = Stream(6, ?)
  ( result14 take 3 ).head                        //> res29: Int = 4
  ( result14 take 1 ) /*should be(List(, , ))*/   //> res30: scala.collection.immutable.Stream[Int] = Stream(4, ?)
  ( result14 take 1 ).tail                        //> res31: scala.collection.immutable.Stream[Int] = Stream()
  ( result14 take 1 ).head                        //> res32: Int = 4
  ( result14 take 0 ) /*should be(List(, , ))*/   //> res33: scala.collection.immutable.Stream[Int] = Stream()

  /*'toSet' will
  'convert' any 'Traversable' to
  a 'Set' which is
  a 'collection' of
  'unordered',
  'unique values'.*/
  val list15 = List( 4, 6, 7, 8, 9, 13, 14, 4 )   //> list15  : List[Int] = List(4, 6, 7, 8, 9, 13, 14, 4)
  val result15 = list15.toSet                     //> result15  : scala.collection.immutable.Set[Int] = Set(14, 6, 9, 13, 7, 8, 4
                                                  //| )
  result15.isInstanceOf[ Set[ _ ] ] /*should be()*/
                                                  //> res34: Boolean = true
  /*'toMap'
  will convert
  any 'Traversable' to a 'Map'.
  How it's used depends on
  the original collection;
  if it's a 'List' or 'Seq',
  it should be of parameterized type 'Tuple2'.*/
  val list16 = List( "Phoenix" -> "Arizona", "Austin" -> "Texas" )
                                                  //> list16  : List[(String, String)] = List((Phoenix,Arizona), (Austin,Texas))
                                                  //| 
  val result16 = list16.toMap                     //> result16  : scala.collection.immutable.Map[String,String] = Map(Phoenix -> 
                                                  //| Arizona, Austin -> Texas)
  result16.isInstanceOf[ Map[ _, _ ] ] /*should be()*/
                                                  //> res35: Boolean = true
  /*'isEmpty' is pretty self evident*/
	val map17 = Map("Phoenix" -> "Arizona", "Austin" -> "Texas")
                                                  //> map17  : scala.collection.immutable.Map[String,String] = Map(Phoenix -> Ari
                                                  //| zona, Austin -> Texas)
	map17.isEmpty /*should be()*/             //> res36: Boolean = false
	
	val set17 = Set()                         //> set17  : scala.collection.immutable.Set[Nothing] = Set()
	set17.isEmpty /*should be()*/             //> res37: Boolean = true
	
  /*'nonEmpty' is pretty self evident too*/
	val map18 = Map("Phoenix" -> "Arizona", "Austin" -> "Texas")
                                                  //> map18  : scala.collection.immutable.Map[String,String] = Map(Phoenix -> Ari
                                                  //| zona, Austin -> Texas)
	map18.nonEmpty /*should be()*/            //> res38: Boolean = true
	
	val set18 = Set()                         //> set18  : scala.collection.immutable.Set[Nothing] = Set()
	set18.nonEmpty /*should be()*/            //> res39: Boolean = false
	
	/*'size' provides the 'size' of the 'traversable'*/
  val map19 = Map("Phoenix" -> "Arizona", "Austin" -> "Texas")
                                                  //> map19  : scala.collection.immutable.Map[String,String] = Map(Phoenix -> Ari
                                                  //| zona, Austin -> Texas)
  map19.size /*should be()*/                      //> res40: Int = 2
  
  /*'hasDefiniteSize'
  will return
  'true' if
  there is 'traversable' that
  has a 'finite' end,
  otherwise 'false'.*/
  val map20 = Map("Phoenix" -> "Arizona", "Austin" -> "Texas")
                                                  //> map20  : scala.collection.immutable.Map[String,String] = Map(Phoenix -> Ari
                                                  //| zona, Austin -> Texas)
  map20.hasDefiniteSize /*should be()*/           //> res41: Boolean = true

	import Stream.cons
	val stream20 = cons(0, cons(1, Stream.empty))
                                                  //> stream20  : Stream.Cons[Int] = Stream(0, ?)
	stream20.hasDefiniteSize /*should be()*/  //> res42: Boolean = false
	
	/*'head'
	will return
	the first element of
	an ordered collection, or
	some random element if
	order is
	not defined like in a 'Set' or 'Map'*/
  val list21 = List(10, 19, 45, 1, 22)            //> list21  : List[Int] = List(10, 19, 45, 1, 22)
  list21.head /*should be()*/                     //> res43: Int = 10
  
  /*'headOption'
  will return
  the first element as an 'Option' of
  an order collection, or
  some random element if
  order is not defined.
  If a first element is
  not available, then
  'None' is returned.*/
	val list22 = List(10, 19, 45, 1, 22)      //> list22  : List[Int] = List(10, 19, 45, 1, 22)
	list22.headOption /*should be(Some())*/   //> res44: Option[Int] = Some(10)
	
	val list23 = List()                       //> list23  : List[Nothing] = List()
	list23.headOption /*should be()*/         //> res45: Option[Nothing] = None
}