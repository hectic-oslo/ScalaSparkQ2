import com.fasterxml.jackson.databind.BeanProperty.Std
import org.apache.spark.sql.SparkSession

object ExportAggregatorDriver {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("ExportAggregator")
      .config("spark.master", "local")
      .config("spark.driver.bindAddress", "127.0.0.1")
      .config("spark.hadoop.hadoop.security.authentication", "simple")
      .config("spark.sql.warehouse.dir", "/tmp/spark-warehouse")
      .config("spark.hadoop.fs.file.impl", "org.apache.hadoop.fs.LocalFileSystem")
      .getOrCreate()

    // Input arguments
    val year = args(0).toInt
   // println("Enter Year")
    // val year=readInt()
    val filePath = "src/main/resources/data/2018-2010_export.csv"


    // Extract data
    val exportDF = ExportExtractor.readData(spark, filePath)

    // Aggregate and rank data
    val aggregatedDF = ExportAggregator.aggregateData(exportDF, year)

    // Write data to partitioned files
    ExportWriter.writeData(aggregatedDF, year)

    spark.stop()
  }
}
