package org.nofre.api.base.feature.localization.translation;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nofre.api.base.common.crud.mapper.BasicMapping;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.common.crud.mapper.WithRelations;
import org.nofre.api.base.feature.localization.translation.entity.TranslationEntity;
import org.nofre.api.base.feature.localization.translation.model.TranslationDto;

@Mapper
public interface TranslationMapper extends CommonCrudMapper<TranslationEntity, TranslationDto> {


    @BasicMapping
    @Mapping(target = "label.id", source = "label.id")
    @Mapping(target = "language.id", source = "language.id")
    TranslationDto toDto(TranslationEntity entity);

    @WithRelations
    @InheritConfiguration(name = "toDto")
    TranslationDto toDtoWithRelations(TranslationEntity entity);


}
