package org.nofre.api.base.feature.localization.language.entity;

import org.nofre.api.base.common.crud.repository.CommonRepository;
import org.nofre.api.base.observability.annotation.ObservedRepository;

@ObservedRepository
public interface LanguageRepository extends CommonRepository<LanguageEntity> {

    boolean existsByCode(String code);
}
