package com.focal.tests.csv;

import com.focal.main.csv.ProductListLoader;
import com.focal.main.csv.ProductRecord;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class ProductRecordValidationFormatTest {

    private List<ProductRecord> products;

    @BeforeClass
    public void setUp() {
        String filePath = System.getProperty("fileForFormatTest", "src/main/resources/prod/gap_report_grocery_focal_superstore_101_2024-10-28_2024-10-28.csv");

        products = ProductListLoader.getProducts(filePath);
    }

    @Test
    public void testStoreName() {
        SoftAssertions softly = new SoftAssertions();

        products.forEach(element -> {
            String approval = element.getStoreName();
            softly.assertThat(approval)
                    .as("Store name for product should be non-null and non-empty")
                    .isNotNull()
                    .isNotEmpty();
        });

        softly.assertAll();
    }

    @Test
    public void testBarCode() {
        SoftAssertions softly = new SoftAssertions();

        products.forEach(product -> {
            String element = product.getBarcode();
            softly.assertThat(element)
                    .as("Bar Code should be non-null and non-empty")
                    .isNotNull()
                    .isNotEmpty();

            softly.assertThat(element).as("Barcode should not contain a leading quote").contains("'").startsWith("'").containsPattern(Pattern.compile("^'\\d{14}$"));
            softly.assertThat(element).as("Barcode should have a length 15 signs").hasSize(15);
        });


        softly.assertAll();
    }

    @Test
    public void testArticleNumber() {
        SoftAssertions softly = new SoftAssertions();

        products.forEach(product -> {
            String element = product.getArticleNumber();
            softly.assertThat(element)
                    .as("ArticleNumber should be non-null and non-empty")
                    .isNotNull()
                    .isNotEmpty();

            softly.assertThat(element).as("Article number should not contain a leading quote").doesNotContain("'");
            softly.assertThat(element).as("Article number should have a length equals 9").hasSize(9);
            softly.assertThat(element).as("Article number should contain only digits").containsOnlyDigits();
        });

        softly.assertAll();
    }

    @Test
    public void testProductName() {
        SoftAssertions softly = new SoftAssertions();

        products.forEach(element -> {
            String approval = element.getProductName();
            softly.assertThat(approval)
                    .as("Product Name for product should be non-null and non-empty")
                    .isNotNull()
                    .isNotEmpty();
        });

        softly.assertAll();
    }

    @Test
    public void testBrand() {
        SoftAssertions softly = new SoftAssertions();

        products.forEach(element -> softly.assertThatCode(element::getBrand)
                .as("Get Brand does not throw any exception")
                .doesNotThrowAnyException());

        softly.assertAll();
    }


    @Test
    public void testAisle() {
        SoftAssertions softly = new SoftAssertions();

        products.forEach(product -> {
            String element = product.getAisle();
            softly.assertThat(element)
                    .as("Aisle should be non-null and non-empty")
                    .isNotNull()
                    .isNotEmpty();

            softly.assertThat(element).as("Aisle should follow pattern two letters two digits").containsPattern(Pattern.compile("^[A-Z]{2}\\d{2}$"));
            softly.assertThat(element).as("Aisle should have a length equals 4").hasSize(4);
        });

        softly.assertAll();
    }


    @Test
    public void testDepartment() {
        SoftAssertions softly = new SoftAssertions();

        products.forEach(element -> {
            String value = element.getDepartment();
            softly.assertThat(value)
                    .as("Department should be non-null and non-empty")
                    .isNotNull()
                    .isNotEmpty();
        });

        softly.assertAll();
    }

    @Test
    public void testCasePackSize() {
        SoftAssertions softly = new SoftAssertions();

        products.forEach(element -> {
            Integer value = element.getCasePackSize();
            softly.assertThat(value)
                    .as("Case Pack Size is not null or empty")
                    .isNotNull();

            softly.assertThatCode(() -> {
                        softly.assertThat(value)
                                .as("Case Pack Size should be a positive integer")
                                .isGreaterThan(0);
                        softly.assertThat(value % 2)
                                .as("Case Pack Size should be even")
                                .isEqualTo(0);
                    })
                    .doesNotThrowAnyException();
        });

        softly.assertAll();
    }


    // Date field test needs extensions, currently we only test if the date is in right format
    @Test
    public void testDates() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        products.forEach(product -> {
            assertThatCode(() -> LocalDateTime.parse(product.getMarkedAt().format(formatter), formatter))
                    .as("Marked At should be in yyyy-MM-dd HH:mm:ss format")
                    .doesNotThrowAnyException();
            assertThatCode(() -> LocalDateTime.parse(product.getLastReceivedDate().format(formatter), formatter))
                    .as("Last Received Date should be in yyyy-MM-dd HH:mm:ss format")
                    .doesNotThrowAnyException();
        });
    }

    // Additional Test Ideas:

    // 1. SR (Stock Ratio) Fields Validation
    //    - Validate `SR at Marked at Time` and `Current SR` fields are non-negative integers and within a reasonable range if they represent stock levels (e.g., 0-100).
    //    - Ensure consistency between `SR at Marked at Time` and `Current SR` (e.g., `Current SR` should not exceed `SR at Marked at Time` unless allowed by business rules).

    // 2. Marked By Field
    //    - Check that the `Marked By` field is a valid name (non-null, non-empty, and potentially matching a pattern like letters, spaces, and punctuation only).
    //    - Validate that there are no unexpected special characters in the `Marked By` field, if applicable.

    // 3. Marked As Field
    //    - Ensure the `Marked As` field only contains expected values (e.g., "No Stock", "In Stock") by defining a set of valid values and asserting each entry matches one of them.

    // 4. Case Pack Size Validity
    //    - Verify that `Case Pack Size` is a positive integer and, if required by business logic, is an even number.
    //    - Check if `Case Pack Size` is within a logical range based on the product type or category (e.g., 1–100).

    // 5. Approval Field
    //    - Validate the `Approval` field contains only expected values, like `AUTO`, `MANUAL`, and verify it is non-null and non-empty.

    // 6. Data Format Consistency
    //    - Confirm all date fields (e.g., `Marked At`, `Last Received Date`) conform to the correct format, currently `yyyy-MM-dd HH:mm:ss`.
    //    - Ensure `Last Received Date` is always on or before the `Marked At` date.

    // 7. Product Category and Department
    //    - Cross-validate `Department` with the product category (e.g., "Grocery" items should fall under specific department codes).
    //    - Ensure the `Department` field matches expected department values based on the `Aisle` or `Category` fields.

    // 8. Special Characters and Leading/Trailing Spaces
    //    - Verify that fields like `Product Name`, `Brand`, and `Store Name` have no leading or trailing spaces to ensure data cleanliness.
    //    - Ensure there are no unexpected special characters in key fields, especially `Product Name` and `Brand`.

    // 9. Error Conditions
    //    - Simulate missing or null values in required fields (e.g., `Store Name`, `Barcode`) and verify that the test fails as expected to handle missing data correctly.
    //    - Simulate malformed date or numeric values in the dataset and confirm the code throws an expected error or handles the case gracefully.

    // 10. Edge Cases for Barcode Length
    //    - Verify that the `Barcode` field consistently meets a predefined length (15 signs here) and fails if it does not.
    //    - Add checks for potential barcodes with varying lengths (e.g., 13 or 14 digits) and confirm that the test can handle or flag these cases as needed.


}