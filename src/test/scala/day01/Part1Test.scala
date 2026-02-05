package day01

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.prop.TableDrivenPropertyChecks.*

class Part1Test extends AnyFunSuite:
  test("rotate"):
    val cases = Table(
      ("point", "password", "rotation", "expectedPoint", "expectedPassword"),
      (50, 0, "L68", 82, 0),
      (82, 0, "L30", 52, 0),
      (52, 0, "R48", 0, 1),
      (0, 1, "L5", 95, 1),
      (95, 1, "R60", 55, 1),
      (55, 1, "L55", 0, 2),
      (0, 2, "L1", 99, 2),
      (99, 2, "L99", 0, 3),
      (0, 3, "R14", 14, 3),
      (14, 3, "L82", 32, 3)
    )

    forAll(cases) { (pt, pswd, rotation, expectedPt, expectedPswd) =>
      assert(
        Part1.rotate(Part1.State(pt, pswd), rotation) == Part1.State(
          expectedPt,
          expectedPswd
        )
      )
    }
