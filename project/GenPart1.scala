import sbt.*

object GenPart1 {

  val genPart1 = Command.args("genPart1", "<Day>") { (state, args) =>
    val day = args.headOption.getOrElse {
      sys.error("usage: genPart1 day01")
    }

    val base = Project.extract(state).currentProject.base

    val templateDir = base / "project" / "templates"
    val mainTpl = templateDir / "Part1.scala.tpl"
    val testTpl = templateDir / "Part1Test.scala.tpl"

    def render(tpl: File): String = {
      val raw = IO.read(tpl)
      raw.replace("{{day}}", day)
    }

    val mainOut = base / "src/main/scala" / day / "Part1.scala"
    val testOut = base / "src/test/scala" / day / "Part1Test.scala"

    IO.createDirectory(mainOut.getParentFile)
    IO.createDirectory(testOut.getParentFile)

    IO.write(mainOut, render(mainTpl))
    IO.write(testOut, render(testTpl))

    state
  }
}
