package com.danielbenitez.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class GameOverException extends RuntimeException {
    public GameOverException() {
        super();
    }
    public GameOverException(String message, Throwable cause) {
        super(message, cause);
    }
    public GameOverException(String message) {
        super(message);
    }
    public GameOverException(Throwable cause) {
        super(cause);
    }
}
