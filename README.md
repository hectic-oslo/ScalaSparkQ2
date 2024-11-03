#ScalaSparkQ2

# Yearly Export Data Aggregation and Country Ranking

This project is a Scala-based Spark job designed to process export data, aggregate it by year and commodity, and rank countries by export values. The output is structured into directories by year and commodity, providing clear insights into export performance.

## Table of Contents
- [Overview](#overview)
- [Project Structure](#project-structure)
- [Requirements](#requirements)
- [Setup](#setup)
- [Usage](#usage)
- [Class Descriptions](#class-descriptions)
- [Output Structure](#output-structure)
- [Sample Input and Output](#sample-input-and-output)
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
