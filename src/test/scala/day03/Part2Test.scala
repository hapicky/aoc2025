package day03

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.prop.TableDrivenPropertyChecks.*

class Part2Test extends AnyFunSuite:
  test("largestJoltage"):
    val cases = Table(
      ("bank", "expected"),
      ("987654321111111", 987654321111L),
      ("811111111111119", 811111111119L),
      ("234234234234278", 434234234278L),
      ("818181911112111", 888911112111L)
    )

    forAll(cases) { (bank, expected) =>
      assert(Part2.largestJoltage(bank) == expected)
    }
