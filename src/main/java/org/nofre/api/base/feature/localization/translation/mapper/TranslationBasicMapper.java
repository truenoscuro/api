package org.nofre.api.base.feature.localization.translation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nofre.api.base.common.crud.mapper.BasicMapping;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.common.crud.mapper.WithRelations;
import org.nofre.api.base.feature.localization.translation.entity.TranslationEntity;
import org.nofre.api.base.feature.localization.translation.model.TranslationDto;

@Mapper
public interface TranslationBasicMapper extends CommonCrudMapper<TranslationEntity, TranslationDto> {

    @BasicMapping
    @Mapping(target = "label", ignore = true)
    @Mapping(target = "language", ignore = true)
    @Mapping(target = "languageId", source = "language.id")
    @Mapping(target = "labelId", source = "label.id")
    TranslationDto toDto(TranslationEntity entity);

    @WithRelations
    @Mapping(target = "label", ignore = true)
    @Mapping(target = "language", ignore = true)
    @Mapping(target = "languageId", source = "language.id")
    @Mapping(target = "labelId", source = "label.id")
    TranslationDto toDtoWithRelations(TranslationEntity entity);
}
