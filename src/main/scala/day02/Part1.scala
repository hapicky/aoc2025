package day02

import scala.io.Source

object Part1 {

  def isInvalidId(id: Long): Boolean = {
    val strId = id.toString
    val prefix = strId.take(strId.length() / 2)
    prefix.repeat(2) == strId
  }

  def main(args: Array[String]): Unit = {
    val inputPath = args.headOption.getOrElse(
      sys.error("usage: 'sbt \"runMain day02.Part1 input/day02/sample.txt\"'")
    )

    val ranges = Source.fromFile(inputPath).getLines().next().split(",")
    val total: Long =
      ranges.flatMap { range =>
        val Array(firstId, lastId) = range.split("-").map(_.toLong)
        (firstId to lastId).iterator.filter(isInvalidId)
      }.sum

    println(s"answer: $total")
  }
}
