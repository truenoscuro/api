package org.nofre.api.base.feature.localization.language.exception;

import org.nofre.api.base.common.crud.exception.CommonCrudException;

public class UnExistLanguageCode extends CommonCrudException {
    public UnExistLanguageCode(String code) {
        super("no existe la lengua %s".formatted(code));
    }
}
