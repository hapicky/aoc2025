package {{day}}

import scala.io.Source

object Part1 {

  def main(args: Array[String]): Unit = {
    val inputPath = args.headOption.getOrElse(
      sys.error("usage: 'sbt \"runMain {{day}}.Part1 input/{{day}}/sample.txt\"'")
    )

    val answer =
      Source
        .fromFile(inputPath)
        .getLines()
        .foldLeft(0)

    println(s"answer: $answer")
  }
}
