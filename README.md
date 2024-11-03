# ScalaSparkQ2



This project is a Scala-based Spark job designed to process export data, aggregate it by year and commodity, and rank countries by export values. The output is structured into directories by year and commodity, providing clear insights into export performance.

## Table of Contents
- [Overview](#overview)
- [Project Structure](#project-structure)
- [Requirements](#requirements)
- [Setup](#setup)
- [Class Descriptions](#class-descriptions)
- [Output Structure](#output-structure)
- [Sample Input](#sample-input)
- [Sample Output](#sample-Output)
- [Testing](#testing)

## Overview
This project reads export data from a CSV file, processes the data to aggregate total exports by commodity and country for a specified year, and ranks the countries by the value of exports for each commodity. The results are written to CSV files, partitioned by year and commodity.

## Project Structure
- **ExportExtractor**: Reads input CSV files and converts them into DataFrames.
- **ExportAggregator**: Filters and aggregates data by the specified year, grouping by commodity and calculating the total value per country.
- **ExportWriter**: Writes the aggregated DataFrame into CSV files partitioned by year and commodity.
- **ExportAggregatorDriver**: Main driver class that orchestrates the extraction, aggregation, and writing process.

## Requirements
- **Scala**: 2.12+
- **Apache Spark**: 3.0+
- **sbt**: 1.4+ (Scala Build Tool)

Ensure these dependencies are installed and configured in your environment.

## Setup
1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-repo/ExportDataAggregator.git
   cd ExportDataAggregator
## Class Descriptions
1. **ExportExtractor**
   Purpose: Reads data from CSV files into DataFrames.

   **Method:**
   ```text
   readData(spark: SparkSession, path: String): DataFrame

2. **ExportAggregator**
   Purpose: Aggregates data for a given year and commodity, and ranks countries by total export value.

   **Method:**
   ```text
   aggregateData(df: DataFrame, year: Int): DataFrame
   
3. **ExportWriter**
  Purpose: Writes the aggregated data to CSV files in an organized output structure.

   **Method:**
   ```text
   writeData(df: DataFrame, year: Int)
4. **ExportAggregatorDriver**
   Purpose: The main entry point that coordinates data extraction, aggregation, and writing.

## Output Structure

<img width="257" alt="image" src="https://github.com/user-attachments/assets/57d2102c-18b9-4242-aab7-928f20b86ba5">



## Sample Input 
1. **Command Line arguments**
   - **`<Year>`**
2. [2018-2010_export.csv](https://drive.google.com/file/d/1ev-30a1L0okJSGhlfqndSNr_QDqjS4-p/view?usp=sharing)

## Sample OutPut

<img width="335" alt="image" src="https://github.com/user-attachments/assets/184dcb91-0104-48f2-9950-78bd6ee77d9d">


<img width="503" alt="image" src="https://github.com/user-attachments/assets/00b421ab-7edc-4529-8556-4e3bf101f060">


Note :- This is just one sample data. we will receive data for all such comodities

## Testing
Unit tests are included to ensure that each component functions as expected
