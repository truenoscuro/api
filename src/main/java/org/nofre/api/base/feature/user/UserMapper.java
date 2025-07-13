package org.nofre.api.base.feature.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nofre.api.base.common.crud.mapper.BasicMapping;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.common.crud.mapper.WithRelations;
import org.nofre.api.base.feature.security.role.RoleMapper;
import org.nofre.api.base.feature.user.entity.UserEntity;
import org.nofre.api.base.feature.user.model.UserDto;

@Mapper(uses = {RoleMapper.class})
public interface UserMapper extends CommonCrudMapper<UserEntity, UserDto> {


    @BasicMapping
    @Mapping(target = "roles", ignore = true)
    UserDto toDto(UserEntity entity);


    @WithRelations
    @Mapping(target = "roles", qualifiedBy = WithRelations.class)
    UserDto toDtoWithRelations(UserEntity entity);
}
