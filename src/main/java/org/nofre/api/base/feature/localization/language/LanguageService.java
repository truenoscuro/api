package org.nofre.api.base.feature.localization.language;

import org.nofre.api.base.common.crud.service.CommonCrudService;
import org.nofre.api.base.feature.localization.language.model.LanguageDto;

public interface LanguageService extends CommonCrudService<LanguageDto> {

    boolean existsByCode(String code);
}
