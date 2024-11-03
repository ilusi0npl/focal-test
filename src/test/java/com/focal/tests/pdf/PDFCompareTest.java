package com.focal.tests.pdf;

import com.focal.main.pdf.TestPDFComparator;
import com.focal.main.testreport.TestReportSaver;
import de.redsix.pdfcompare.CompareResult;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PDFCompareTest {

    @Test
    public void comparePdfs() {
        String expectedPdfFilename = "src/main/resources/prod/gap_report_grocery_focal_superstore_101_2024-10-28_2024-10-28.pdf";
        String actualPdfFilename = "src/main/resources/staging/gap_report_grocery_focal_superstore_101_2024-10-28_2024-10-28.pdf";

        final CompareResult result;
        result = TestPDFComparator.comparePdfs(expectedPdfFilename, actualPdfFilename);

        if (result.isNotEqual()) {
            TestReportSaver.saveDifferenceReportToTestReport(result);
            throw new AssertionError("Provided PDFs are not equal! See Test Report for differences");
        }
        assertThat(result.isEqual()).as("PDF Results Comparison").isTrue();
    }


}
