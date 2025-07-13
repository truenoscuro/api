package org.nofre.api.base.feature.localization.language;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nofre.api.base.common.crud.mapper.BasicMapping;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.common.crud.mapper.WithRelations;
import org.nofre.api.base.feature.localization.language.entity.LanguageEntity;
import org.nofre.api.base.feature.localization.language.model.LanguageDto;
import org.nofre.api.base.feature.localization.translation.TranslationMapper;

@Mapper(uses = {TranslationMapper.class})
public interface LanguageMapper extends CommonCrudMapper<LanguageEntity, LanguageDto> {


    @BasicMapping
    @Mapping(target = "translations", ignore = true)
    LanguageDto toDto(LanguageEntity entity);

    @WithRelations
    @Mapping(target = "translations", qualifiedBy = WithRelations.class)
    LanguageDto toDtoWithRelations(LanguageEntity entity);

}
