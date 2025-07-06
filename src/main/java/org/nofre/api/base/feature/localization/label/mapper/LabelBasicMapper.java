package org.nofre.api.base.feature.localization.label.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nofre.api.base.common.crud.mapper.BasicMapping;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.common.crud.mapper.WithRelations;
import org.nofre.api.base.feature.localization.label.entity.LabelEntity;
import org.nofre.api.base.feature.localization.label.model.LabelDto;

@Mapper
public interface LabelBasicMapper extends CommonCrudMapper<LabelEntity, LabelDto> {

    @BasicMapping
    @Mapping(target = "labelGroupId", source = "labelGroup.id")
    @Mapping(target = "translations", ignore = true)
    @Mapping(target = "labelGroup", ignore = true)
    LabelDto toDto(LabelEntity entity);

    @WithRelations
    @Mapping(target = "labelGroupId", source = "labelGroup.id")
    @Mapping(target = "translations", ignore = true)
    @Mapping(target = "labelGroup", ignore = true)
    LabelDto toDtoWithRelations(LabelEntity entity);
}
