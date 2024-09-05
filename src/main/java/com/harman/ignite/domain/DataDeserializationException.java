package com.harman.ignite.domain;

/**
 * DataDeserializationException Class.
 */
public class DataDeserializationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataDeserializationException(String msg) {
        super(msg);
    }

    public DataDeserializationException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
