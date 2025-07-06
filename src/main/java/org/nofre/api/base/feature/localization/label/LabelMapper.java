package org.nofre.api.base.feature.localization.label;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nofre.api.base.common.crud.mapper.BasicMapping;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.common.crud.mapper.WithRelations;
import org.nofre.api.base.feature.localization.label.entity.LabelEntity;
import org.nofre.api.base.feature.localization.label.model.LabelDto;
import org.nofre.api.base.feature.localization.labelgroup.LabelGroupMapper;
import org.nofre.api.base.feature.localization.translation.mapper.TranslationBasicMapper;

@Mapper(uses = {LabelGroupMapper.class, TranslationBasicMapper.class})
public interface LabelMapper extends CommonCrudMapper<LabelEntity, LabelDto> {


    @BasicMapping
    @Mapping(target = "labelGroupId", source = "labelGroup.id")
    @Mapping(target = "translations", ignore = true)
    @Mapping(target = "labelGroup", ignore = true)
    LabelDto toDto(LabelEntity entity);


    @WithRelations
    @Mapping(target = "labelGroupId", ignore = true)
    @Mapping(target = "labelGroup", qualifiedBy = BasicMapping.class)
    @Mapping(target = "translations", qualifiedBy = WithRelations.class)
    LabelDto toDtoWithRelations(LabelEntity entity);

}
