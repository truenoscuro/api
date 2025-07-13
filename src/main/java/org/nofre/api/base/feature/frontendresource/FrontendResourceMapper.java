package org.nofre.api.base.feature.frontendresource;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nofre.api.base.common.crud.mapper.BasicMapping;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.common.crud.mapper.WithRelations;
import org.nofre.api.base.feature.frontendresource.entity.FrontendResourceEntity;
import org.nofre.api.base.feature.frontendresource.model.FrontendResourceDto;
import org.nofre.api.base.feature.security.permission.PermissionMapper;

@Mapper(uses = {PermissionMapper.class})
public interface FrontendResourceMapper extends CommonCrudMapper<FrontendResourceEntity, FrontendResourceDto> {

    @BasicMapping
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "parent.children", ignore = true)
    @Mapping(target = "parent.permissions", ignore = true)
    FrontendResourceDto toDto(FrontendResourceEntity entity);

    @WithRelations
    @Mapping(target = "parent", qualifiedBy = BasicMapping.class)
    @Mapping(target = "children", qualifiedBy = WithRelations.class)
    @Mapping(target = "permissions", qualifiedBy = WithRelations.class)
    FrontendResourceDto toDtoWithRelations(FrontendResourceEntity entity);

}
