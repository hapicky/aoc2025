package day04

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.prop.TableDrivenPropertyChecks.*

class Part2Test extends AnyFunSuite:
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
      assert(Part2.countNeighbors(diagram, x, y) == expected)
    }

  test("removedDiagram"):
    val positions = Vector((2, 0), (0, 1))
    val removed = Part2.removedDiagram(diagram, positions)

    assert(removed(0)(2) == 'x')
    assert(removed(1)(0) == 'x')

  test("removeAllPapers"):
    val penultimate = """..........
                        |..........
                        |....x.....
                        |...@@@....
                        |...@@@@...
                        |...@@@@@..
                        |...@.@.@@.
                        |...@@.@@@.
                        |...@@@@@..
                        |....@@@...""".stripMargin

    val pDiagram = penultimate
      .split("\n")
      .map(_.toVector)
      .toVector

    assert(Part2.removeAllPapers(pDiagram) == 1)

  test("removeAllPapersRecursive"):
    assert(Part2.removeAllPapers(diagram) == 43)
