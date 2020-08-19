package com.mervetuncelkaya.exception;

public class SystemException extends RuntimeException {

    public SystemException() {
        super();
    }

    public SystemException(String messae) {
        super(messae);
    }

    public SystemException(Throwable throwable) {
        super(throwable);
    }

    public SystemException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
