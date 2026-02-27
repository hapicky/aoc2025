package day04

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.prop.TableDrivenPropertyChecks.*

class Part1Test extends AnyFunSuite:
  val input = """..@@.@@@@.
                |@@@.@.@.@@
                |@@@@@.@.@@
                |@.@@@@..@.
                |@@.@@@@.@@
                |.@@@@@@@.@
                |.@.@.@.@@@
                |@.@@@.@@@@
                |.@@@@@@@@.
                |@.@.@@@.@.""".stripMargin

  val diagram: Vector[Vector[Char]] = input
    .split("\n")
    .map(_.toVector)
    .toVector

  test("countNeighbors"):
    val cases = Table(
      ("x", "y", "expected"),
      (1, 1, 6),
      (0, 0, 2), // top left
      (9, 0, 3), // top right
      (0, 9, 1), // bottom left
      (9, 9, 2) // bottom right
    )

    forAll(cases) { (x, y, expected) =>
      assert(Part1.countNeighbors(diagram, x, y) == expected)
    }
