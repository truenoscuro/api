package org.nofre.api.base.feature.role;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nofre.api.base.common.crud.mapper.BasicMapping;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.common.crud.mapper.WithRelations;
import org.nofre.api.base.feature.permission.PermissionMapper;
import org.nofre.api.base.feature.role.entity.RoleEntity;
import org.nofre.api.base.feature.role.model.RoleDto;

@Mapper(uses = {PermissionMapper.class})
public interface RoleMapper extends CommonCrudMapper<RoleEntity, RoleDto> {


    @BasicMapping
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "parentId", source = "parent.id")
    RoleDto toDto(RoleEntity entity);


    @WithRelations
    @Mapping(target = "permissions", qualifiedBy = WithRelations.class)
    @Mapping(target = "parent", qualifiedBy = BasicMapping.class)
    @Mapping(target = "children", qualifiedBy = WithRelations.class)
    @Mapping(target = "parentId", ignore = true)
    RoleDto toDtoWithRelations(RoleEntity entity);

}
