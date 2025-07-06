package org.nofre.api.base.feature.localization.translation;

import org.nofre.api.base.common.crud.service.CommonCrudService;
import org.nofre.api.base.feature.localization.language.exception.UnExistLanguageCode;
import org.nofre.api.base.feature.localization.translation.model.TranslationDto;

import java.util.Map;

public interface TranslationService extends CommonCrudService<TranslationDto> {

    Map<String, String> getTranslationsByLang(String language) throws UnExistLanguageCode;

}
