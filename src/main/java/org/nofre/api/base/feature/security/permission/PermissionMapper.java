package org.nofre.api.base.feature.security.permission;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nofre.api.base.common.crud.mapper.BasicMapping;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.common.crud.mapper.WithRelations;
import org.nofre.api.base.feature.security.permission.entity.PermissionEntity;
import org.nofre.api.base.feature.security.permission.model.PermissionDto;

@Mapper
public interface PermissionMapper extends CommonCrudMapper<PermissionEntity, PermissionDto> {


    @BasicMapping
    @Mapping(target = "groupPermission.id", source = "groupPermission.id")
    PermissionDto toDto(PermissionEntity entity);


    @WithRelations
    @InheritConfiguration(name = "toDto")
    PermissionDto toDtoWithRelations(PermissionEntity entity);

}
