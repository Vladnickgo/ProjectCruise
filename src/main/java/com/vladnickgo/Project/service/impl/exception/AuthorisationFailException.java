package com.vladnickgo.Project.service.impl.exception;

public class AuthorisationFailException extends RuntimeException {
    public AuthorisationFailException(String message) {
        super(message);
    }
}
