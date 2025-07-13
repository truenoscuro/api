package org.nofre.api.base.feature.localization.labelgroup;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nofre.api.base.common.crud.mapper.BasicMapping;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.common.crud.mapper.WithRelations;
import org.nofre.api.base.feature.localization.label.LabelMapper;
import org.nofre.api.base.feature.localization.labelgroup.entity.LabelGroupEntity;
import org.nofre.api.base.feature.localization.labelgroup.model.LabelGroupDto;

@Mapper(uses = {LabelMapper.class})
public interface LabelGroupMapper extends CommonCrudMapper<LabelGroupEntity, LabelGroupDto> {

    @BasicMapping
    @Mapping(target = "labels", ignore = true)
    LabelGroupDto toDto(LabelGroupEntity entity);

    @WithRelations
    @Mapping(target = "labels", qualifiedBy = WithRelations.class)
    LabelGroupDto toDtoWithRelations(LabelGroupEntity entity);

}
