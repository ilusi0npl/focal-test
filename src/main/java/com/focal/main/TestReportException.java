 package com.focal.main;

public class TestReportException extends RuntimeException {
    public TestReportException() {
    }

    public TestReportException(String message) {
        super(message);
    }

    public TestReportException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestReportException(Throwable cause) {
        super(cause);
    }

    public TestReportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
