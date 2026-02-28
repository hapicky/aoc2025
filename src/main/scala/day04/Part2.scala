package day04

import scala.annotation.tailrec
import scala.io.Source

object Part2 {

  val offsets = for {
    dx <- -1 to 1
    dy <- -1 to 1
    if !(dx == 0 && dy == 0)
  } yield (dx, dy)

  val paper = '@'
  val removed = 'x'
  val threshold = 4

  def countNeighbors(diagram: Vector[Vector[Char]], x: Int, y: Int): Int = {
    offsets.count { case (dx, dy) =>
      diagram.lift(y + dy).flatMap(_.lift(x + dx)).contains(paper)
    }
  }

  def removedDiagram(
      diagram: Vector[Vector[Char]],
      positions: IndexedSeq[(Int, Int)]
  ): Vector[Vector[Char]] = {
    positions.foldLeft(diagram) { case (diagram, (x, y)) =>
      diagram.updated(y, diagram(y).updated(x, removed))
    }
  }

  @tailrec
  def removeAllPapers(diagram: Vector[Vector[Char]], totalRemoved: Int = 0): Int = {
    val positions = (for {
      y <- diagram.indices
      x <- diagram(y).indices
      cell = diagram(y)(x)
      if cell == paper && countNeighbors(diagram, x, y) < threshold
    } yield (x, y))

    if (positions.isEmpty) {
      totalRemoved
    } else {
      removeAllPapers(removedDiagram(diagram, positions), totalRemoved + positions.size)
    }
  }

  def main(args: Array[String]): Unit = {
    val inputPath = args.headOption.getOrElse(
      sys.error("usage: 'sbt \"runMain day04.Part2 input/day04/sample.txt\"'")
    )

    val diagram: Vector[Vector[Char]] =
      Source
        .fromFile(inputPath)
        .getLines()
        .map(_.toVector)
        .toVector

    val answer = removeAllPapers(diagram)

    println(s"answer: $answer")
  }
}
