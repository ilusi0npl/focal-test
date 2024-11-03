  package com.focal.main;

public class PDFComparisonException extends RuntimeException {
    public PDFComparisonException() {
    }

    public PDFComparisonException(String message) {
        super(message);
    }

    public PDFComparisonException(String message, Throwable cause) {
        super(message, cause);
    }

    public PDFComparisonException(Throwable cause) {
        super(cause);
    }

    public PDFComparisonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
