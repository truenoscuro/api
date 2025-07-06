package org.nofre.api.base.feature.localization.translation.entity;

import org.nofre.api.base.common.crud.repository.CommonRepository;
import org.nofre.api.base.observability.annotation.ObservedRepository;

import java.util.List;

@ObservedRepository
public interface TranslationRepository extends CommonRepository<TranslationEntity> {

    List<TranslationEntity> findAllByLanguage_Code(String languageCode);

    List<TranslationEntity> findAllByLanguage_CodeAndLabel_LabelGroup_Name(String languageCode, String groupName);
}
