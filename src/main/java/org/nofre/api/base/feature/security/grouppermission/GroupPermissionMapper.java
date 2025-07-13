package org.nofre.api.base.feature.security.grouppermission;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nofre.api.base.common.crud.mapper.BasicMapping;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.common.crud.mapper.WithRelations;
import org.nofre.api.base.feature.security.grouppermission.entity.GroupPermissionEntity;
import org.nofre.api.base.feature.security.grouppermission.model.GroupPermissionDto;
import org.nofre.api.base.feature.security.permission.PermissionMapper;

@Mapper(uses = {PermissionMapper.class})
public interface GroupPermissionMapper extends CommonCrudMapper<GroupPermissionEntity, GroupPermissionDto> {


    @BasicMapping
    @Mapping(target = "permissions", ignore = true)
    GroupPermissionDto toDto(GroupPermissionEntity entity);


    @WithRelations
    @Mapping(target = "permissions", qualifiedBy = WithRelations.class)
    GroupPermissionDto toDtoWithRelations(GroupPermissionEntity entity);
}
