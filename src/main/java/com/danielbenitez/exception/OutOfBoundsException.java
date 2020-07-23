package com.danielbenitez.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class OutOfBoundsException extends RuntimeException {
    public OutOfBoundsException() {
        super();
    }
    public OutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }
    public OutOfBoundsException(String message) {
        super(message);
    }
    public OutOfBoundsException(Throwable cause) {
        super(cause);
    }
}
