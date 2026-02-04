package day01

import scala.io.Source

object Part1 {
  case class State(point: Int, password: Int)

  val SIZE = 100

  def rotate(state: State, rotation: String): State = {
    val direction = rotation.head
    val distance = rotation.tail.toInt
    val delta = direction match {
      case 'L' => -distance
      case 'R' => distance
      case _ => sys.error(s"invalid direction: $direction")
    }

    val nextPoint =
      Math.floorMod(state.point + delta, SIZE)

    val nextPassword =
      if (nextPoint == 0) state.password + 1 else state.password

    State(nextPoint, nextPassword)
  }

  def main(args: Array[String]): Unit = {
    val inputPath = args.headOption.getOrElse(
      sys.error("usage: 'sbt \"runMain day01.Part1 input/day01/sample.txt\"'")
    )

    val finalState =
      Source.fromFile(inputPath)
        .getLines()
        .foldLeft(State(50, 0))(rotate)

    println(s"answer: ${finalState.password}")
  }
}
