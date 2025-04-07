import scala.util.Random

object SmallSampleBootstrap {
  def main(args: Array[String]): Unit = {
    // 示例：估计伯努利分布的参数θ

    // 步骤1：从已知样本计算初始估计θ_hat
    val sample = Array(1, 0, 1, 1, 0, 1, 0, 1, 1, 1) // 1表示成功，0表示失败
    val thetaHat = sample.sum.toDouble / sample.length

    println(s"初始估计 θ_hat = $thetaHat")

    // 步骤2和3：执行Bootstrap
    val bootstrapResults = bootstrap(sample, thetaHat, G = 1000)

    // 输出结果
    println(s"Bootstrap估计均值: ${bootstrapResults.mean}")
    println(s"Bootstrap估计标准差: ${bootstrapResults.stdDev}")
    println(s"95%置信区间: (${bootstrapResults.confidenceInterval._1}, ${bootstrapResults.confidenceInterval._2})")
  }

  case class BootstrapResults(
    estimates: Array[Double],
    mean: Double,
    stdDev: Double,
    confidenceInterval: (Double, Double)
  )

  def bootstrap(
    originalSample: Array[Int],
    thetaHat: Double,
    G: Int = 1000
  ): BootstrapResults = {
    val random = new Random()
    val bootstrapEstimates = new Array[Double](G)

    for (g <- 0 until G) {
      // 生成Bootstrap样本
      val bootstrapSample = Array.fill(originalSample.length) {
        if (random.nextDouble() < thetaHat) 1 else 0
      }

      // 计算估计值
      bootstrapEstimates(g) = bootstrapSample.sum.toDouble / bootstrapSample.length
    }

    // 计算统计量
    val mean = bootstrapEstimates.sum / G
    val variance = bootstrapEstimates.map(e => math.pow(e - mean, 2)).sum / (G - 1)
    val stdDev = math.sqrt(variance)

    // 计算95%置信区间（使用百分位数法）
    val sortedEstimates = bootstrapEstimates.sorted
    val lower = sortedEstimates((G * 0.025).toInt)
    val upper = sortedEstimates((G * 0.975).toInt)

    BootstrapResults(bootstrapEstimates, mean, stdDev, (lower, upper))
  }
}
