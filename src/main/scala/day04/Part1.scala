package day04

import scala.io.Source

object Part1 {

  val offsets = for {
    dx <- -1 to 1
    dy <- -1 to 1
    if !(dx == 0 && dy == 0)
  } yield (dx, dy)

  val paper = '@'
  val threshold = 4

  def countNeighbors(diagram: Vector[Vector[Char]], x: Int, y: Int): Int = {
    offsets.count { case (dx, dy) =>
      diagram.lift(y + dy).flatMap(_.lift(x + dx)).contains(paper)
    }
  }

  def main(args: Array[String]): Unit = {
    val inputPath = args.headOption.getOrElse(
      sys.error("usage: 'sbt \"runMain day04.Part1 input/day04/sample.txt\"'")
    )

    val diagram: Vector[Vector[Char]] =
      Source
        .fromFile(inputPath)
        .getLines()
        .map(_.toVector)
        .toVector

    val answer = (for {
      y <- diagram.indices
      x <- diagram(y).indices
      cell = diagram(y)(x)
      if cell == paper && countNeighbors(diagram, x, y) < threshold
    } yield 1).size

    println(s"answer: $answer")
  }
}
