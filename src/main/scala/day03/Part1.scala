package day03

import scala.io.Source

object Part1 {

  val TURN_ON_COUNT = 2

  def largestJoltage(bank: String): Int = {
    val batteries = bank.map(_.asDigit)

    val (_, turnedOn) = (0 until TURN_ON_COUNT)
      .foldLeft((0, Vector.empty[Int])) { case ((start, selected), i) =>
        val end = batteries.length - TURN_ON_COUNT + i + 1
        val (battery, index) = batteries.slice(start, end).zipWithIndex.maxBy(_._1)
        (start + index + 1, selected :+ battery)
      }

    turnedOn.mkString.toInt
  }

  def main(args: Array[String]): Unit = {
    val inputPath = args.headOption.getOrElse(
      sys.error("usage: 'sbt \"runMain day03.Part1 input/day03/sample.txt\"'")
    )

    val answer =
      Source
        .fromFile(inputPath)
        .getLines()
        .map(largestJoltage)
        .sum

    println(s"answer: $answer")
  }
}
