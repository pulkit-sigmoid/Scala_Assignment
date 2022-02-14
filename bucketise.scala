package sigmoid

object bucketise {


  def bucket(num: Double): String = {

    var a = (num * 1000 / 100).toInt
    var b: String = ((((num * 1000 / 100).toInt).toDouble / 10)).toString
    var c = (num * 1000 / 100).round

    if (a == c) b + 0 + "-" + (b + 49)
    else (b + 5) + "-" + (b + 99)
  }

  def main(args: Array[String]): Unit = {

    var numbers: Array[Double] = Array(12.05, 12.03, 10.33, 11.45, 13.5)
    for (number <- numbers) {
      println(s"$number lies in bucket -> " + bucket(number))
    }
  }
}
