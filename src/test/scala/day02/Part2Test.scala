package day02

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.prop.TableDrivenPropertyChecks.*

class Part2Test extends AnyFunSuite:
  test("isInvalidId"):
    val cases = Table(
      ("firstId", "lastId", "invalidIds"),
      (11, 22, List(11, 22)),
      (95, 115, List(99, 111)),
      (998, 1012, List(999, 1010)),
      (1188511880, 1188511890, List(1188511885)),
      (222220, 222224, List(222222)),
      (1698522, 1698528, List.empty),
      (446443, 446449, List(446446)),
      (38593856, 38593862, List(38593859)),
      (565653, 565659, List(565656)),
      (824824821, 824824827, List(824824824)),
      (2121212118, 2121212124, List(2121212121))
    )

    forAll(cases) { (firstId, lastId, invalidIds) =>
      assert((firstId to lastId).filter(Part2.isInvalidId).toList == invalidIds)
    }
