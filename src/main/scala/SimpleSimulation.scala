import scala.util.Random

object SimpleSimulation {
  def main(args: Array[String]): Unit = {
    val rand = new Random(42)
    val n = 200
    val p = 5000
    val k = 10
    
    // 生成β向量
    val beta = Array.fill(k)(2.0) ++ Array.fill(p - k)(0.0)
    
    // 生成X矩阵和y向量
    val y = Array.tabulate(n) { _ =>
      val x = Array.fill(p)(rand.nextGaussian())
      x.zip(beta).map { case (xi, bi) => xi * bi }.sum + rand.nextGaussian()
    }
    
    println(s"前10个系数值: ${beta.take(10).mkString(", ")}")
    println(s"前5个响应值: ${y.take(5).mkString(", ")}")
  }
}
