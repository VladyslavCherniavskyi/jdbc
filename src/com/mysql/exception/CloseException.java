package com.mysql.exception;

public class CloseException extends RuntimeException {
    public CloseException(Throwable cause) {
        super(cause);
    }
}
