package com.focal.main.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor; // Use this for no-args constructor
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor // Add this to provide a no-args constructor
@Getter
@Setter
@ToString
public class ProductRecord {

    @CsvBindByName(column = "Store Name")
    private String storeName;

    @CsvBindByName(column = "Barcode")
    private String barcode;

    @CsvBindByName(column = "Article Number")
    private String articleNumber;

    @CsvBindByName(column = "Product Name")
    private String productName;

    @CsvBindByName(column = "Brand")
    private String brand;

    @CsvBindByName(column = "Aisle")
    private String aisle;

    @CsvBindByName(column = "Department")
    private String department;

    @CsvBindByName(column = "Case Pack Size")
    private Integer casePackSize; // Change to Integer to handle null values

    @CsvBindByName(column = "SR at Marked at Time")
    private Double srAtMarkedAtTime; // Change to Double to allow null

    @CsvBindByName(column = "Current SR")
    private Double currentSr; // Change to Double to allow null

    @CsvBindByName(column = "Marked by")
    private String markedBy;

    @CsvBindByName(column = "Marked At")
    @CsvDate("yyyy-MM-dd HH:mm:ss") // Specify date format
    private LocalDateTime markedAt;

    @CsvBindByName(column = "Marked As")
    private String markedAs;

    @CsvBindByName(column = "Last Received Date")
    @CsvDate("yyyy-MM-dd HH:mm:ss") // Specify date format
    private LocalDateTime lastReceivedDate;

    @CsvBindByName(column = "Approval")
    private String approval;
}
