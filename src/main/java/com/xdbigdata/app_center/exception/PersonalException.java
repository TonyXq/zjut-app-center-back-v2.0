package com.xdbigdata.app_center.exception;

/**
 * Created by staunch on 2016-07-08.
 * version：v1.0
 * instruction：初始版本
 */
public class PersonalException extends RuntimeException {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public PersonalException(String message) {
        super(message);
    }
}
