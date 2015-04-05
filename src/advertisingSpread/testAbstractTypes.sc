package advertisingSpread

object testAbstractTypes {
  /*from:
  http://docs.scala-lang.org/tutorials/tour/abstract-types.html*/
  trait Buffer {
    type T
    val element: T
  }
  /*'Abstract types' are
	'types' whose identity is not precisely known.
	In the example above,
	we only know that
	each object of 'class' 'Buffer' has
	a 'type member' 'T', but
	the definition of 'class' 'Buffer' does not reveal to
	what 'concrete type' the 'member type' 'T' corresponds.
	Like 'value definitions',
	we can
	'override' 'type definitions' in 'subclasses'.
	This allows us to
	reveal more information about
	an 'abstract type' by
	tightening the 'type bound'
	(which describes possible 'concrete instantiations' of the 'abstract type').
	
	In the following program
	we derive a 'class' 'SeqBuffer' which
	allows us to
	store only sequences in the buffer by
	stating that
	'type' 'T' has to be
	a 'subtype' of 'Seq[U]' for
	a new 'abstract type' 'U':*/
  abstract class SeqBuffer extends Buffer {
    type U
    type T <: Seq[ U ]
    def length = element.length
  }
  /*'Traits' or 'classes' with
	'abstract type' 'members' are
	often used in combination with
	'anonymous class instantiations'.
	To illustrate this,
	we now look at
	a program which deals with
	a 'sequence buffer' that
	refers to a list of integers:*/
  abstract class IntSeqBuffer extends SeqBuffer {
    type U = Int
  }

  object AbstractTypeTest1 extends App {
    def newIntSeqBuf( elem1: Int,
                      elem2: Int ): IntSeqBuffer =
      new IntSeqBuffer {
        type T = List[ U ]
        val element = List( elem1, elem2 )
      }

    def testThis {
      val buf = newIntSeqBuf( 7, 8 )
      println( "length = " + buf.length )
      println( "content = " + buf.element )
    }
  }

  AbstractTypeTest1.testThis                      //> length = 2
                                                  //| content = List(7, 8)
  /*The 'return type' of method 'newIntSeqBuf' refers to
a 'specialization' of 'trait' 'Buffer' in which
'type' 'U' is now equivalent to 'Int'.
We have a similar 'type alias' in
the 'anonymous class instantiation' within
the body of method 'newIntSeqBuf'.
Here
we create a 'new instance' of 'IntSeqBuffer' in which
'type' 'T' refers to 'List[Int]'.

Please note that
 it is often possible to
 turn 'abstract type members' into
 'type parameters' of 'classes' and
 vice versa.
 Here is
 a version of the code above which
 only uses 'type parameters':*/

  abstract class Buffer2[ +T ] {
    val element: T
  }
  abstract class SeqBuffer2[ U, +T <: Seq[ U ] ] extends Buffer2[ T ] {
    def length = element.length
  }

  object AbstractTypeTest2 extends App {
    def newIntSeqBuf( e1: Int,
                      e2: Int ): SeqBuffer2[ Int, Seq[ Int ] ] =
      new SeqBuffer2[ Int, List[ Int ] ] {
        val element = List( e1, e2 )
      }
      
    def testThis {
      val buf = newIntSeqBuf( 7, 8 )
      println( "length = " + buf.length )
      println( "content = " + buf.element )
    }
  }

  /*Note that
	we have to
	use 'variance annotations' here;
	otherwise
	we would not be able to
	hide the 'concrete sequence implementation type' of
	the object returned from method 'newIntSeqBuf'.
	Furthermore,
	there are cases where
	it is not possible to
	replace 'abstract types' with 'type parameters'.*/
	AbstractTypeTest2.testThis                //> length = 2
                                                  //| content = List(7, 8)
}