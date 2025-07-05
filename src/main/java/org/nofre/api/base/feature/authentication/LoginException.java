package org.nofre.api.base.feature.authentication;

public class LoginException extends Exception {
    public LoginException() {
        super("Las contraseñas no coinciden");
    }
}
