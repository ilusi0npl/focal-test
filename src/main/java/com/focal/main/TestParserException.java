package com.focal.main;

public class TestParserException extends RuntimeException {
    public TestParserException() {
    }

    public TestParserException(String message) {
        super(message);
    }

    public TestParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestParserException(Throwable cause) {
        super(cause);
    }

    public TestParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
