package org.nofre.api.base.feature.localization.label;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nofre.api.base.common.crud.mapper.BasicMapping;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.common.crud.mapper.WithRelations;
import org.nofre.api.base.feature.localization.label.entity.LabelEntity;
import org.nofre.api.base.feature.localization.label.model.LabelDto;
import org.nofre.api.base.feature.localization.translation.TranslationMapper;

@Mapper(uses = {TranslationMapper.class})
public interface LabelMapper extends CommonCrudMapper<LabelEntity, LabelDto> {


    @BasicMapping
    @Mapping(target = "labelGroup.id", source = "labelGroup.id")
    @Mapping(target = "translations", ignore = true)
    LabelDto toDto(LabelEntity entity);


    @WithRelations
    @Mapping(target = "labelGroup.id", source = "labelGroup.id")
    @Mapping(target = "translations", qualifiedBy = WithRelations.class)
    LabelDto toDtoWithRelations(LabelEntity entity);

}
