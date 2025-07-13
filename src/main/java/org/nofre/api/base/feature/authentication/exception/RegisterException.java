package org.nofre.api.base.feature.authentication.exception;

public class RegisterException extends Exception {
    public RegisterException() {
        super("El usuario ya está registrado");
    }
}
