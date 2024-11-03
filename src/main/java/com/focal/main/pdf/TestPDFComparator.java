package com.focal.main.pdf;

import com.focal.main.PDFComparisonException;
import de.redsix.pdfcompare.CompareResult;
import de.redsix.pdfcompare.PdfComparator;
import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class TestPDFComparator {

    public CompareResult comparePdfs(String expectedPdfFilename, String actualPdfFilename) {
        final CompareResult result;
        try {
            result = new PdfComparator(expectedPdfFilename, actualPdfFilename).compare();
        } catch (IOException e) {
            throw new PDFComparisonException("Failed to compare PDFs", e);
        }
        return result;
    }
}
