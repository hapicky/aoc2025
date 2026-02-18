import sbt.*

object GenPart2 {

  val genPart2 = Command.args("genPart2", "<Day>") { (state, args) =>
    val day = args.headOption.getOrElse {
      sys.error("usage: genPart2 day01")
    }

    val base = Project.extract(state).currentProject.base

    val mainDir = base / "src/main/scala" / day
    val testDir = base / "src/test/scala" / day

    def render(part1: File): String = {
      val raw = IO.read(part1)
      raw.replace("Part1", "Part2")
    }

    val mainOut = mainDir / "Part2.scala"
    val testOut = testDir / "Part2Test.scala"

    IO.write(mainOut, render(mainDir / "Part1.scala"))
    IO.write(testOut, render(testDir / "Part1Test.scala"))

    state
  }
}
