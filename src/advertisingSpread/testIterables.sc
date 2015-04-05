package advertisingSpread

object testIterables {
  /*'Iterables'
	The next 'trait' from the top in
	the 'collections hierarchy' is 'Iterable'.
	All 'methods' in this 'trait' are
	defined in terms of
	an 'abstract method', 'iterator', which
	'yields' the collection's 'elements' one by one.
	The 'foreach' method from 'trait' 'Traversable' is
	implemented in 'Iterable' in terms of 'iterator'.
	Here is the actual 'implementation':*/
  /*
  def foreach[ U ]( f: Elem => U ): Unit = {
    val it = iterator
    while ( it.hasNext ) f( it.next() )
  }
  */
  /*Quite a few 'subclasses' of 'Iterable' override
	this standard implementation of 'foreach' in 'Iterable', because
	they can provide a more efficient 'implementation'.
	Remember that
	'foreach' is
	the basis of the implementation of all operations in 'Traversable', so
	its 'performance matters'.
	
	Some known 'iterators' are
	'Sets',
	'Lists',
	'Vectors',
	'Stacks', and
	'Streams'.
	'Iterator' has two important 'methods':
	'hasNext', which
	answers whether the 'iterator' has
	another 'element' available.
	'next' which will
	return the 'next element' in the 'iterator'.*/
  val list = List( 3, 5, 9, 11, 15, 19, 21 )      //> list  : List[Int] = List(3, 5, 9, 11, 15, 19, 21)
  val it = list.iterator                          //> it  : Iterator[Int] = non-empty iterator
  if ( it.hasNext ) {
    it.next /*/*should be()*/*/
  }                                               //> res0: AnyVal = 3

  /*'grouped'
  will return an 'fixed sized' 'Iterable' 'chucks' of an 'Iterable'*/
  val list1 = List( 3, 5, 9, 11, 15, 19, 21, 24, 32 )
                                                  //> list1  : List[Int] = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
  val it1 = list grouped 3                        //> it1  : Iterator[List[Int]] = non-empty iterator
  it1.next() /*should be(List(, , ))*/            //> res1: List[Int] = List(3, 5, 9)
  it1.next() /*should be(List(, , ))*/            //> res2: List[Int] = List(11, 15, 19)
  it1.next() /*should be(List(, , ))*/            //> res3: List[Int] = List(21)

  /*'sliding'
  will return
  an 'Iterable' that
  shows a 'sliding window' of an 'Iterable'.*/
  val list2 = List( 3, 5, 9, 11, 15, 19, 21, 24, 32 )
                                                  //> list2  : List[Int] = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
  val it2 = list sliding 3                        //> it2  : Iterator[List[Int]] = non-empty iterator
  it2.next() /*should be(List(, , ))*/            //> res4: List[Int] = List(3, 5, 9)
  it2.next() /*should be(List(, , ))*/            //> res5: List[Int] = List(5, 9, 11)
  it2.next() /*should be(List(, , ))*/            //> res6: List[Int] = List(9, 11, 15)

  /*'sliding' can
  take the 'size' of the 'window' as well as
  the 'size' of the 'step' during each 'iteration'*/
  val list3 = List( 3, 5, 9, 11, 15, 19, 21, 24, 32 )
                                                  //> list3  : List[Int] = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
  val it3 = list sliding ( 3, 3 )                 //> it3  : Iterator[List[Int]] = non-empty iterator
  it3.next() /*should be(List(, , ))*/            //> res7: List[Int] = List(3, 5, 9)
  it3.next() /*should be(List(, , ))*/            //> res8: List[Int] = List(11, 15, 19)
  it3.next() /*should be(List(, , ))*/            //> res9: List[Int] = List(21)

  /*'takeRight' is
  the opposite of 'take' in 'Traversable'.
  It retrieves the 'last' elements of an 'Iterable'.*/
  val list4 = List( 3, 5, 9, 11, 15, 19, 21, 24, 32 )
                                                  //> list4  : List[Int] = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
  ( list4 takeRight 3 ) /*should be(List(, , ))*/ //> res10: List[Int] = List(21, 24, 32)

  /*'dropRight' will
  'drop' the number of 'elements' from the right.*/
  val list5 = List( 3, 5, 9, 11, 15, 19, 21, 24, 32 )
                                                  //> list5  : List[Int] = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
  ( list5 dropRight 3 ) /*should be(List(, , , , , ))*/
                                                  //> res11: List[Int] = List(3, 5, 9, 11, 15, 19)
  /*'zip' will
  stitch two 'iterables' into
  an 'iterable' of 'pairs' of
  corresponding elements from both 'iterables'.

  E.g.
  'Iterable(x1, x2, x3) zip Iterable(y1, y2, y3)' will return
  '((x1,y1), (x2, y2), (x3, y3))':*/
  val xs = List( 3, 5, 9 )                        //> xs  : List[Int] = List(3, 5, 9)
  val ys = List( "Bob", "Ann", "Stella" )         //> ys  : List[String] = List(Bob, Ann, Stella)
  ( xs zip ys ) /*should be(List((, ), (, ), (, )))*/
                                                  //> res12: List[(Int, String)] = List((3,Bob), (5,Ann), (9,Stella))
  ( xs zip ys ).toMap                             //> res13: scala.collection.immutable.Map[Int,String] = Map(3 -> Bob, 5 -> Ann,
                                                  //|  9 -> Stella)
  /*If
  two 'Iterables' aren't the 'same size', then
  'zip' will
  only 'zip' what can only be 'paired'.

  E.g.
  'Iterable(x1, x2, x3) zip Iterable(y1, y2)' will return
  '((x1,y1), (x2, y2))'*/
  val xs1 = List( 3, 5, 9 )                       //> xs1  : List[Int] = List(3, 5, 9)
  val ys1 = List( "Bob", "Ann" )                  //> ys1  : List[String] = List(Bob, Ann)
  ( xs1 zip ys1 ) /*should be(List((, ), (, )))*/ //> res14: List[(Int, String)] = List((3,Bob), (5,Ann))

  /*If
  two 'Iterables' aren't the 'same size', then
  'zipAll' can provide
  'fillers' for
  what it couldn't find a 'complement' for:

  E.g.
  'Iterable(x1, x2, x3) zipAll (Iterable(y1, y2), x, y)' will return
  '((x1,y1), (x2, y2, y))'*/
  val xs11 = List( 3, 5, 9 )                      //> xs11  : List[Int] = List(3, 5, 9)
  val ys11 = List( "Bob", "Ann" )                 //> ys11  : List[String] = List(Bob, Ann)
  ( xs11 zipAll ( ys11, -1, "?" ) ) /*should be(List((, ), (, ), (, "?")))*/
                                                  //> res15: List[(Int, String)] = List((3,Bob), (5,Ann), (9,?))
  
  val xs12 = List( 3, 5 )                         //> xs12  : List[Int] = List(3, 5)
  val ys12 = List( "Bob", "Ann", "Jack" )         //> ys12  : List[String] = List(Bob, Ann, Jack)
  ( xs12 zipAll ( ys12, -1, "?" ) ) /*should be(List((, ), (, ), (, "?")))*/
                                                  //> res16: List[(Int, String)] = List((3,Bob), (5,Ann), (-1,Jack))

  /*'zipWithIndex' will
  'zip' an 'Iterable' with
  it's integer 'index'*/
  val xs2 = List( "Manny", "Moe", "Jack" )        //> xs2  : List[String] = List(Manny, Moe, Jack)
  xs2.zipWithIndex /*should be(List((, 0), (, ), (, 2)))*/
                                                  //> res17: List[(String, Int)] = List((Manny,0), (Moe,1), (Jack,2))
  /*'sameElements'
  will return
  'true' if
  the two 'iterables' produce
  the 'same elements' in the 'same order':*/
  val xs3 = List( "Manny", "Moe", "Jack" )        //> xs3  : List[String] = List(Manny, Moe, Jack)
  val ys3 = List( "Manny", "Moe", "Jack" )        //> ys3  : List[String] = List(Manny, Moe, Jack)
  ( xs3 sameElements ys3 ) /*should be()*/        //> res18: Boolean = true

  val xs4 = List( "Manny", "Moe", "Jack" )        //> xs4  : List[String] = List(Manny, Moe, Jack)
  val ys4 = List( "Manny", "Jack", "Moe" )        //> ys4  : List[String] = List(Manny, Jack, Moe)
  ( xs4 sameElements ys4 ) /*should be()*/        //> res19: Boolean = false

  val xs5 = Set( 3, 2, 1, 4, 5, 6, 7 )            //> xs5  : scala.collection.immutable.Set[Int] = Set(5, 1, 6, 2, 7, 3, 4)
  val ys5 = Set( 7, 2, 1, 4, 5, 6, 3 )            //> ys5  : scala.collection.immutable.Set[Int] = Set(5, 1, 6, 2, 7, 3, 4)
  ( xs5 sameElements ys5 ) /*should be()*/        //> res20: Boolean = true

  val xs6 = Set( 1, 2, 3 )                        //> xs6  : scala.collection.immutable.Set[Int] = Set(1, 2, 3)
  val ys6 = Set( 3, 2, 1 )                        //> ys6  : scala.collection.immutable.Set[Int] = Set(3, 2, 1)
  ( xs6 sameElements ys6 ) /*should be()*/        //> res21: Boolean = false
  /* Caution - see below!
  Note:
  that very small 'Sets' (containing up to 4 elements) are
  'implemented' differently to larger 'Sets';
  as a result,
  their 'iterators' produce
  the 'elements' in the order that
  they were originally 'added'.
  This causes
  the surprising (and arguably incorrect) 'behaviour' in
  the final example above.*/

}