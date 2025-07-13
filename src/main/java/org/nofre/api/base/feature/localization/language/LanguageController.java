package org.nofre.api.base.feature.localization.language;

import org.nofre.api.base.common.controller.ApiRestController;
import org.nofre.api.base.common.crud.controller.CommonCrudControllerImp;
import org.nofre.api.base.feature.localization.language.model.LanguageDto;

@ApiRestController("languages")
public class LanguageController extends CommonCrudControllerImp<LanguageDto, LanguageService> {
    public LanguageController(LanguageService service) {
        super(service, "LANGUAGE");
    }
}
