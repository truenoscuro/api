package org.nofre.api.base.feature.user.exception;

import org.nofre.api.base.common.crud.exception.CommonCrudException;

public class RegisterPasswordException extends CommonCrudException {
    public RegisterPasswordException() {
        super("No le ha añadido contraseña al usuario para registrarse");
    }
}
