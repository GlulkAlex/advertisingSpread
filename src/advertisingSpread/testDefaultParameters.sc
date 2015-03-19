package advertisingSpread

object testDefaultParameters {
  /*Default parameters can be functional too*/
  def reduce( a: Int,
              f: ( Int, Int ) => Int = _ + _ ): Int = f( a, a )
                                                  //> reduce: (a: Int, f: (Int, Int) => Int)Int

  reduce( 5 ) /*should equal()*/                  //> res0: Int = 10
  reduce( 5, _ * _ ) /*should equal()*/           //> res1: Int = 25
}