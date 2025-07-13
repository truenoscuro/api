package org.nofre.api.base.feature.security.role.exception;

public class NoExistRoleException extends RuntimeException {
    public NoExistRoleException(String message) {
        super("No existe el rol: " + message);
    }
}
