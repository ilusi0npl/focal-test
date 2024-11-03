package com.focal.main.csv;

import com.focal.main.TestParserException;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import lombok.experimental.UtilityClass;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@UtilityClass
public class ProductListLoader {

    private static final int COLUMN_ROW = 1;
    private static final String[] COLUMN_MAPPING = {
            "storeName",
            "barcode",
            "articleNumber",
            "productName",
            "brand",
            "aisle",
            "department",
            "casePackSize",
            "srAtMarkedAtTime",
            "currentSr",
            "markedBy",
            "markedAt",
            "markedAs",
            "lastReceivedDate",
            "approval"
    };

    public static List<ProductRecord> getProducts(String filename) {
        try (CSVReader csvReader = new CSVReader(new FileReader(filename))) {
            skipHeaderRow(csvReader);
            return parseCsv(csvReader);
        } catch (IOException e) {
            throw new TestParserException("Error processing CSV file: " + filename, e);
        }
    }

    private void skipHeaderRow(CSVReader csvReader) throws IOException {
        csvReader.skip(COLUMN_ROW);
    }

    private List<ProductRecord> parseCsv(CSVReader csvReader) {
        ColumnPositionMappingStrategy<ProductRecord> beanStrategy = new ColumnPositionMappingStrategy<>();
        beanStrategy.setType(ProductRecord.class);
        beanStrategy.setColumnMapping(COLUMN_MAPPING);

        CsvToBean<ProductRecord> csvToBean = new CsvToBean<>();
        csvToBean.setCsvReader(csvReader);
        csvToBean.setMappingStrategy(beanStrategy);
        return csvToBean.parse();
    }
}
