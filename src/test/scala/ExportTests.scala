import ExportWriter.writeData
import org.scalatest.funsuite.AnyFunSuite
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.functions.col

class ExportTests extends AnyFunSuite {

  // Initialize SparkSession for the tests
  val spark: SparkSession = SparkSession.builder()
    .appName("ExportTests")
    .master("local[*]") // Running locally with all available cores
    .getOrCreate()

  import spark.implicits._

  // Helper method to clean up the output directory before running tests
  def cleanOutputDirectory(outputPath: String): Unit = {
    val fs = FileSystem.get(spark.sparkContext.hadoopConfiguration)
    val path = new Path(outputPath)
    if (fs.exists(path)) {
      fs.delete(path, true) // Recursively delete the directory if it exists
    }
  }

  // Test for ExportExtractor
  test("ExportExtractor should read valid data from CSV") {
    val df = ExportExtractor.readData(spark, "/Users/abhishekkumar/Downloads/Spark_Assignment_ques2a/src/main/resources/data/2018-2010_export.csv")
    assert(!df.isEmpty, "DataFrame should not be empty after reading valid data")
  }

  test("ExportExtractor should handle missing file gracefully") {
    val df = ExportExtractor.readData(spark, "/path/to/nonexistent/file.csv")
    assert(df.isEmpty, "DataFrame should be empty for a missing file")
  }


  // Test for ExportAggregator
  test("ExportAggregator should aggregate and rank data correctly") {
    val sampleDF = Seq(
      ("CommodityA", "Country1", 2018, 1000),
      ("CommodityA", "Country2", 2018, 2000),
      ("CommodityB", "Country1", 2018, 3000)
    ).toDF("Commodity", "country", "year", "value")

    val resultDF = ExportAggregator.aggregateData(sampleDF, 2018)
    assert(resultDF.filter($"rank" === 1).count() > 0, "There should be at least one rank 1 entry")
    assert(resultDF.count() > 0, "Output DataFrame should not be empty after aggregation")
  }

  test("ExportAggregator should handle no data for a given year") {
    val emptyDF = Seq.empty[(String, String, Int, Int)].toDF("Commodity", "country", "year", "value")
    val resultDF = ExportAggregator.aggregateData(emptyDF, 2018)
    assert(resultDF.isEmpty, "Output DataFrame should be empty when no data is provided")
  }

}
