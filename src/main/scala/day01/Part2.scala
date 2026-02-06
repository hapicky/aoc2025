package day01

import scala.io.Source

object Part2 {
  case class State(point: Int, password: Int)

  val SIZE = 100

  def rotate(state: State, rotation: String): State = {
    val direction = rotation.head
    val distance = rotation.tail.toInt
    val delta = direction match {
      case 'L' => -distance
      case 'R' => distance
      case _   => sys.error(s"invalid direction: $direction")
    }

    val nextPoint = state.point + delta

    val count =
      if (nextPoint == 0) 1
      else if (nextPoint >= SIZE) nextPoint / SIZE
      else if (nextPoint < 0)
        if (state.point == 0) -nextPoint / SIZE else -nextPoint / SIZE + 1
      else 0

    val nextPassword = state.password + count

    State(Math.floorMod(nextPoint, SIZE), nextPassword)
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
