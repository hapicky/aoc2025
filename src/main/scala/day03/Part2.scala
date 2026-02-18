package day03

import scala.io.Source

object Part2 {

  val TURN_ON_COUNT = 12

  def largestJoltage(bank: String): Long = {
    val batteries = bank.map(_.asDigit)

    val (_, turnedOn) = (0 until TURN_ON_COUNT)
      .foldLeft((0, Vector.empty[Int])) { case ((start, selected), i) =>
        val end = batteries.length - TURN_ON_COUNT + i
        val selectIndex = (start to end).maxBy(batteries)
        (selectIndex + 1, selected :+ batteries(selectIndex))
      }

    turnedOn.mkString.toLong
  }

  def main(args: Array[String]): Unit = {
    val inputPath = args.headOption.getOrElse(
      sys.error("usage: 'sbt \"runMain day03.Part2 input/day03/sample.txt\"'")
    )

    val answer: Long =
      Source
        .fromFile(inputPath)
        .getLines()
        .map(largestJoltage)
        .sum

    println(s"answer: $answer")
  }
}
