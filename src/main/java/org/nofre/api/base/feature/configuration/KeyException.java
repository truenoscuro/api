package org.nofre.api.base.feature.configuration;

public class KeyException extends RuntimeException {

    public KeyException(String key) {
        super("No existe configuración con la key: " + key.toLowerCase());
    }
}
