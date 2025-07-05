package org.nofre.api.base.feature.permission;

import org.mapstruct.Mapper;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.feature.permission.entity.PermissionEntity;
import org.nofre.api.base.feature.permission.model.PermissionDto;

@Mapper
public interface PermissionMapper extends CommonCrudMapper<PermissionEntity, PermissionDto> {
}
