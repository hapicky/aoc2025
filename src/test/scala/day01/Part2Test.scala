package day01

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.prop.TableDrivenPropertyChecks.*

class Part2Test extends AnyFunSuite:
  test("rotate sample"):
    val cases = Table(
      ("point", "password", "rotation", "expectedPoint", "expectedPassword"),
      (50, 0, "L68", 82, 1),
      (82, 1, "L30", 52, 1),
      (52, 1, "R48", 0, 2),
      (0, 2, "L5", 95, 2),
      (95, 2, "R60", 55, 3),
      (55, 3, "L55", 0, 4),
      (0, 4, "L1", 99, 4),
      (99, 4, "L99", 0, 5),
      (0, 5, "R14", 14, 5),
      (14, 5, "L82", 32, 6)
    )

    forAll(cases) { (pt, pswd, rotation, expectedPt, expectedPswd) =>
      assert(
        Part2.rotate(Part2.State(pt, pswd), rotation) == Part2.State(
          expectedPt,
          expectedPswd
        )
      )
    }

  test("rotate multiple times"):
    val cases = Table(
      ("point", "password", "rotation", "expectedPoint", "expectedPassword"),
      (50, 0, "R1000", 50, 10),
      (50, 0, "L1000", 50, 10)
    )

    forAll(cases) { (pt, pswd, rotation, expectedPt, expectedPswd) =>
      assert(
        Part2.rotate(Part2.State(pt, pswd), rotation) == Part2.State(
          expectedPt,
          expectedPswd
        )
      )
    }

  test("rotate step over 0"):
    val cases = Table(
      ("point", "password", "rotation", "expectedPoint", "expectedPassword"),
      (0, 0, "R1", 1, 0),
      (0, 0, "R99", 99, 0),
      (0, 0, "R100", 0, 1),
      (0, 0, "R101", 1, 1),
      (0, 0, "R199", 99, 1),
      (0, 0, "R200", 0, 2),
      (0, 0, "R201", 1, 2),
      (1, 0, "R1", 2, 0),
      (1, 0, "R98", 99, 0),
      (1, 0, "R99", 0, 1),
      (1, 0, "R100", 1, 1),
      (1, 0, "R198", 99, 1),
      (1, 0, "R199", 0, 2),
      (1, 0, "R200", 1, 2),
      (0, 0, "L1", 99, 0),
      (0, 0, "L99", 1, 0),
      (0, 0, "L100", 0, 1),
      (0, 0, "L101", 99, 1),
      (0, 0, "L199", 1, 1),
      (0, 0, "L200", 0, 2),
      (0, 0, "L201", 99, 2),
      (2, 0, "L1", 1, 0),
      (2, 0, "L2", 0, 1),
      (2, 0, "L3", 99, 1),
      (2, 0, "L101", 1, 1),
      (2, 0, "L102", 0, 2),
      (2, 0, "L103", 99, 2)
    )

    forAll(cases) { (pt, pswd, rotation, expectedPt, expectedPswd) =>
      assert(
        Part2.rotate(Part2.State(pt, pswd), rotation) == Part2.State(
          expectedPt,
          expectedPswd
        )
      )
    }
