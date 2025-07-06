package org.nofre.api.base.feature.localization.language.service;

import org.nofre.api.base.common.crud.entity.specification.GenericSpecification;
import org.nofre.api.base.common.crud.service.CommonCrudServiceImp;
import org.nofre.api.base.common.crud.service.CrudService;
import org.nofre.api.base.feature.localization.language.LanguageMapper;
import org.nofre.api.base.feature.localization.language.LanguageService;
import org.nofre.api.base.feature.localization.language.entity.LanguageEntity;
import org.nofre.api.base.feature.localization.language.entity.LanguageRepository;
import org.nofre.api.base.feature.localization.language.model.LanguageDto;

@CrudService
public class LanguageServiceImp extends CommonCrudServiceImp<LanguageEntity, LanguageDto, LanguageMapper, LanguageRepository>
        implements LanguageService {

    public LanguageServiceImp(LanguageRepository repository,
                              LanguageMapper mapper,
                              GenericSpecification<LanguageEntity> specification) {
        super("languages", repository, mapper, specification);
    }

    @Override
    public boolean existsByCode(String code) {
        return repository.existsByCode(code);
    }
}
