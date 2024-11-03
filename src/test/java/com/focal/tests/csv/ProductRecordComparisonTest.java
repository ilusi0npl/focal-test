package com.focal.tests.csv;

import com.focal.main.csv.ProductListLoader;
import com.focal.main.csv.ProductRecord;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

public class ProductRecordComparisonTest {

    @Test
    public void testCompareProductRecordsFromTwoCsv() {
        String productionFilePath = System.getProperty("productionFilePath", "src/main/resources/prod/gap_report_grocery_focal_superstore_101_2024-10-28_2024-10-28.csv");
        String stagingFilePath = System.getProperty("stagingFilePath", "src/main/resources/staging/gap_report_grocery_focal_superstore_101_2024-10-28_2024-10-28.csv");

        List<ProductRecord> productionRecords = ProductListLoader.getProducts(productionFilePath);
        List<ProductRecord> stagingRecords = ProductListLoader.getProducts(stagingFilePath);

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(stagingRecords).hasSameSizeAs(productionRecords);
        for (int i = 0; i < stagingRecords.size(); i++) {
            ProductRecord stagingRecord = stagingRecords.get(i);
            ProductRecord productionRecord = productionRecords.get(i);
            softly.assertThat(stagingRecord)
                    .as("Record mismatch at index %d", i)
                    .usingRecursiveComparison()
                    .isEqualTo(productionRecord);
        }

        softly.assertAll();
    }
}
