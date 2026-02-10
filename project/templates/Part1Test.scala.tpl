package {{day}}

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.prop.TableDrivenPropertyChecks.*

class Part1Test extends AnyFunSuite:
  test("foobar"):
    val cases = Table(
      ("a", "b", "c"),
      (100, 200, 300)
    )

    forAll(cases) { (a, b, c) =>
      assert(Part1.foobar(a, b, c) == true)
    }
