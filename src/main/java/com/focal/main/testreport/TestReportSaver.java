package com.focal.main.testreport;

import com.focal.main.TestReportException;
import de.redsix.pdfcompare.CompareResult;
import io.qameta.allure.Allure;
import lombok.experimental.UtilityClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@UtilityClass
public class TestReportSaver {

    private static final String PDF = ".pdf";

    public void saveDifferenceReportToTestReport(CompareResult compareResult) {
        String savePath = "target/pdf-test-result";
        compareResult.writeTo(savePath);
        Path pdfPath = Path.of(savePath + PDF);
        if (Files.exists(pdfPath) && pdfPath.toString().endsWith(PDF)) {
            try (FileInputStream fis = new FileInputStream(pdfPath.toFile())) {
                Allure.addAttachment("PDF Report", "application/pdf", fis, PDF);
            } catch (IOException e) {
                throw new TestReportException("Failed to attach PDF file to Allure report", e);
            }
        } else {
            throw new TestReportException("PDF file not found in target directory: " + pdfPath);
        }
    }
}
