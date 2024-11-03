import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col

object ExportWriter {
  def writeData(df: DataFrame, year: Int): Unit = {
    val commodities = df.select("Commodity").distinct().collect().map(_.getString(0))

    commodities.foreach { commodity =>
      df.filter(col("Commodity") === commodity)
        .coalesce(1) // Optional: to write a single file per commodity
        .write
        .option("header", "true").mode("overwrite")
        .csv(s"output/$year/$commodity/exportRanking.csv")
    }
  }






}