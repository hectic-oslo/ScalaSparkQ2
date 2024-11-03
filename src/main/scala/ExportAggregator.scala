import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col, dense_rank, sum}
import org.apache.spark.sql.expressions.Window

object ExportAggregator {
  def aggregateData(df: DataFrame, year: Int): DataFrame = {
    // Filter by the specified year
    val filteredDF = df.filter(col("year") === year)

    // Aggregate export data by country and commodity, summing the value
    val aggregatedDF = filteredDF.groupBy("Commodity", "country")
      .agg(sum(col("value")))
      .withColumnRenamed("sum(value)", "Total_Value")

    // Rank countries within each commodity based on export value
    val windowSpec = Window.partitionBy("Commodity").orderBy(col("Total_Value").desc)
    aggregatedDF.withColumn("rank", dense_rank().over(windowSpec))
  }
}
