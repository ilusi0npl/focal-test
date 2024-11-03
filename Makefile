# Default paths (you can change these as needed)
PRODUCTION_FILE_PATH="src/main/resources/prod/gap_report_grocery_focal_superstore_101_2024-10-28_2024-10-28.csv"
STAGING_FILE_PATH="src/main/resources/staging/gap_report_grocery_focal_superstore_101_2024-10-28_2024-10-28.csv"


build-test-project:
	mvn clean install -DskipTests

# Target to run all tests
run-all-tests:
	mvn clean test

# Target for running only csv tests
run-csv-tests-files:
	mvn clean test -Dtest=com.focal.tests.csv.ProductRecordComparisonTest -DproductionFilePath=$(PRODUCTION_FILE_PATH) -DstagingFilePath=$(STAGING_FILE_PATH)

# Target for running only pdf tests
run-pdf-comparison-tests:
	mvn clean test -Dtest=com.focal.tests.pdf.PDFCompareTest

# Run with production CSV file
run-validation-format-tests-production:
	mvn test -Dtest=com.focal.tests.csv.ProductRecordValidationFormatTest -DfileForFormatTest=$(PRODUCTION_FILE_PATH)

# Run with staging CSV file
run-validation-format-tests-staging:
	mvn test -Dtest=com.focal.tests.csv.ProductRecordValidationFormatTest -DfileForFormatTest=$(STAGING_FILE_PATH)

# Target to clean the project
clean:
	mvn clean

# Target to generate and serve Allure report
generate-test-report:
	mvn allure:serve
