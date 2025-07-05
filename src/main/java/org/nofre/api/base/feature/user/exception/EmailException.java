package org.nofre.api.base.feature.user.exception;

public class EmailException extends Exception {
    public EmailException() {
        super("El ususario no está registrado en el sistema");
    }
}
