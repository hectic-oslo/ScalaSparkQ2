import org.apache.spark.sql.{DataFrame, SparkSession}

object ExportExtractor {
  def readData(spark: SparkSession, path: String): DataFrame = {
    try {
      spark.read.option("header", "true").csv(path)
    } catch {
      case e: Exception =>
        // Log the exception (optional)
        println(s"Error reading file at $path: ${e.getMessage}")
        spark.emptyDataFrame // Return an empty DataFrame in case of an error
    }
  }
}
