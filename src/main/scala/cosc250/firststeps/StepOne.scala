package cosc250.firststeps

import scala.annotation.tailrec

/**
  * Now we know about higher order functions, let's go back to the first tutorial and see if they can help us solve
  * the exercises. You are welcome to use the "for" notation, which is syntactic sugar for map and flatMap, and so
  * is calling higher order functions!
  */
object StepOne {

  /**
    * Double every element in an array.
    *
    * Start off by doing this iteratively. And then we'll discover how much shorter it is functionally.
    */
  def doubleArray(arr:Array[Int]):Array[Int] = arr.map(_ * 2)

  /**
    * Multiply every element in an array by its position in the array
    * eg, for [3, 4, 2, 6, 2] [3 * 0, 4 * 1, 2 * 2, 6 * 3, 2 * 4]
    *
    * You might need zipWithIndex here...
    */
  def timesPosition(arr:Array[Int]):Array[Int] = arr.zipWithIndex.map(_ * _)


  /**
    * Ok, we did that for arrays. Now, what if we want to do it for lists?
    * Hint: if you're working imperatively and mutably, you can start with an Array and then go .toList on it at the end
    */
  def doubleList(arr:List[Int]):List[Int] = arr.map(_ * 2)


  /**
    * Suppose we are compiling a crossword. Given two words, find all the pairs of positions where those
    * words have letters in commong. eg, for "frogs" and "eggs", we would return
    * List((3,1), (3,2), (4,3)
    */
  def matchingLetters(wordA:String, wordB:String):List[(Int, Int)] = 
    (for 
      (a, i) <- wordA.zipWithIndex
      (b, j) <- wordB.zipWithIndex if a == b
    yield (i, j)).toList

  /**
    * Ok, the Roman Numerals one is harder to do this way, but I'll leave it here for anyone who's keen. You can skip
    * it and head on to the Sudoku exercise if you'd prefer.
    */
  def roman(i:Int):String = 
    val numerals = List("M" -> 1000, "CM" -> 900, "D" -> 500, "CD" -> 400, "C" -> 100, "XC" -> 90, "L" -> 50, "XL" -> 40, "X" -> 10, "IX" -> 9, "V" -> 5, "IV" -> 4, "I" -> 1)
    
    // Let's fold across the numerals, passing the string we're building up and the remainder.
    // This gives us an odd-looking function in the middle, because we're given a tuple of tuples:
    // on the left is the (remainder, string) we're passing along
    // on the right is the (roman numeral, roman numeral value) we're looking at
    val (_, result) = numerals.foldLeft((i, ""))({ case ((remainder, string), (roman, value)) => 
      // How many times we need to append the roman numeral
      // Employs a slight trick that Int / Int has type Int, so it is already discarding any fractional part.
      val repeat = remainder / value

      // A little trick - if you "multiply" a string by a number, you get that string repeated that many times
      val append = roman * repeat
      val deduct = value * repeat
      (remainder - deduct, string + append)
    })

    result


  /*
   * Ok, now that's done, time to write a little Sudoku solver, and meet another higher order function: filter
   */

}
