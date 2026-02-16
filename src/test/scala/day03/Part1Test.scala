package day03

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.prop.TableDrivenPropertyChecks.*

class Part1Test extends AnyFunSuite:
  test("largestJoltage"):
    val cases = Table(
      ("bank", "expected"),
      ("987654321111111", 98),
      ("811111111111119", 89),
      ("234234234234278", 78),
      ("818181911112111", 92)
    )

    forAll(cases) { (bank, expected) =>
      assert(Part1.largestJoltage(bank) == expected)
    }
