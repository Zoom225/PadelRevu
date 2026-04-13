package com.padel.exception;
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("Email deja utilise: " + email);
    }
}
