package test

import collection.mutable.Stack
import org.scalatest._

/*from:
 * 'http://www.scalatest.org/user_guide/'
 * */
/*Using a different 'style' for 
 * 'unit' and 
 * 'acceptance' 'testing' can 
 * help developers "switch gears" between 
 * low-level 'unit testing' to 
 * high-level 'acceptance' testing.*/
/*we recommend you use 
 * 'FlatSpec' for 'unit' and 'integration testing' and 
 * 'FeatureSpec' for 'acceptance testing'. 
 * We recommend 'FlatSpec' as 
 * the 'default' choice, because 
 * it is flat (unnested) like 
 * the 'XUnit' 'tests' familiar to most developers, but 
 * guides you into 
 * writing 'focused tests' with 
 * descriptive, specification-style names.
 */
class ExampleSpec extends FlatSpec with Matchers {

  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new Stack[ Int ]
    stack.push( 1 )
    /*stack.push( 2 )*/
    stack.pop() should be( 2 )
    /*stack.pop() should be (1)*/
    /*or*/
    /*assert( stack.pop() === 1 )*/
    /*or*/
    assert( stack.pop() === 1, "'assert' with a clue")
  }

  /*If you have 'multiple tests' about 
   * the 'same subject', 
   * you can use 
   * 'it' to refer to the 'previous subject' (like "A Stack"):*/  
  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new Stack[ Int ]
    a[ NoSuchElementException ] should be thrownBy {
      emptyStack.pop()
    }
    /*or*/
    intercept[ NoSuchElementException ] {
      emptyStack.pop()
    }
    /*or*/
    withClue("'intercept' 'withClue'") {
    	intercept[ NoSuchElementException ] {
    		emptyStack.pop()
    	}
    }
  }
}