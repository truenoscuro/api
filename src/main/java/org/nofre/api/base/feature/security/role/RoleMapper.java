package org.nofre.api.base.feature.security.role;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nofre.api.base.common.crud.mapper.BasicMapping;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.common.crud.mapper.WithRelations;
import org.nofre.api.base.feature.security.permission.PermissionMapper;
import org.nofre.api.base.feature.security.role.entity.RoleEntity;
import org.nofre.api.base.feature.security.role.model.RoleDto;

@Mapper(uses = {PermissionMapper.class})
public interface RoleMapper extends CommonCrudMapper<RoleEntity, RoleDto> {


    @BasicMapping
    @Mapping(target = "parent.parent", ignore = true)
    @Mapping(target = "parent.children", ignore = true)
    @Mapping(target = "parent.permissions", ignore = true)
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "children", ignore = true)
    RoleDto toDto(RoleEntity entity);


    @WithRelations
    @Mapping(target = "parent", qualifiedBy = BasicMapping.class)
    @Mapping(target = "permissions", qualifiedBy = WithRelations.class)
    @Mapping(target = "children", qualifiedBy = WithRelations.class)
    RoleDto toDtoWithRelations(RoleEntity entity);

}
