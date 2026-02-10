package day02

import scala.io.Source

object Part2 {

  def isInvalidId(id: Long): Boolean = {
    val strId = id.toString
    val idLength = strId.length

    (1 until idLength).exists { len =>
      idLength % len == 0 && {
        val prefix = strId.take(len)
        prefix.repeat(idLength / len) == strId
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val inputPath = args.headOption.getOrElse(
      sys.error("usage: 'sbt \"runMain day02.Part2 input/day02/sample.txt\"'")
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
