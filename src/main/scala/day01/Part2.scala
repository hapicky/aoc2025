package day01

import scala.io.Source

object Part2 {
  case class State(point: Int, password: Int)

  val SIZE = 100

  def countArrivedAtZero(pointFrom: Int, delta: Int): Int = {
    if (pointFrom == 0)
      return Math.abs(delta) / SIZE

    val pointTo = pointFrom + delta
    pointTo match {
      case 0                => 1
      case _ if pointTo > 0 => pointTo / SIZE
      case _ if pointTo < 0 => Math.abs(pointTo / SIZE) + 1
    }
  }

  def rotate(state: State, rotation: String): State = {
    val direction = rotation.head
    val distance = rotation.tail.toInt
    val delta = direction match {
      case 'L' => -distance
      case 'R' => distance
      case _   => sys.error(s"invalid direction: $direction")
    }

    val nextPoint =
      Math.floorMod(state.point + delta, SIZE)

    val nextPassword =
      state.password + countArrivedAtZero(state.point, delta)

    State(nextPoint, nextPassword)
  }

  def main(args: Array[String]): Unit = {
    val inputPath = args.headOption.getOrElse(
      sys.error("usage: 'sbt \"runMain day01.Part2 input/day01/sample.txt\"'")
    )

    val finalState =
      Source
        .fromFile(inputPath)
        .getLines()
        .foldLeft(State(50, 0))(rotate)

    println(s"answer: ${finalState.password}")
  }
}
