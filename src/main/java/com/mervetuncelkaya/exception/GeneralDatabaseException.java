package com.mervetuncelkaya.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class GeneralDatabaseException extends RuntimeException {

    public GeneralDatabaseException() {
        super();
    }

    public GeneralDatabaseException(String message) {
        super(message);
    }

    public GeneralDatabaseException(Throwable throwable) {
        super(throwable);
    }

    public GeneralDatabaseException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
