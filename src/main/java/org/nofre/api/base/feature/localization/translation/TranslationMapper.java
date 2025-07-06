package org.nofre.api.base.feature.localization.translation;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nofre.api.base.common.crud.mapper.BasicMapping;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.common.crud.mapper.WithRelations;
import org.nofre.api.base.feature.localization.label.LabelMapper;
import org.nofre.api.base.feature.localization.language.LanguageMapper;
import org.nofre.api.base.feature.localization.translation.entity.TranslationEntity;
import org.nofre.api.base.feature.localization.translation.model.TranslationDto;

@Mapper(uses = {LanguageMapper.class, LabelMapper.class})
public interface TranslationMapper extends CommonCrudMapper<TranslationEntity, TranslationDto> {


    @BasicMapping
    @Mapping(target = "label", ignore = true)
    @Mapping(target = "language", ignore = true)
    @Mapping(target = "languageId", source = "language.id")
    @Mapping(target = "labelId", source = "label.id")
    TranslationDto toDto(TranslationEntity entity);

    @WithRelations
    @Mapping(target = "languageId", ignore = true)
    @Mapping(target = "labelId", ignore = true)
    @Mapping(target = "language", qualifiedBy = BasicMapping.class)
    @Mapping(target = "label", qualifiedBy = BasicMapping.class)
    TranslationDto toDtoWithRelations(TranslationEntity entity);


}
