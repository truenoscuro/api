package org.nofre.api.base.feature.authentication.exception;

public class LoginException extends Exception {
    public LoginException() {
        super("Las contraseñas no coinciden");
    }
}
