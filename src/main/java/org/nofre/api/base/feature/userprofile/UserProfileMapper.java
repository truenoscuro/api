package org.nofre.api.base.feature.userprofile;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.nofre.api.base.common.crud.mapper.BasicMapping;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.common.crud.mapper.WithRelations;
import org.nofre.api.base.feature.userprofile.entity.UserProfileEntity;
import org.nofre.api.base.feature.userprofile.model.UserProfileDto;

@Mapper
public interface UserProfileMapper extends CommonCrudMapper<UserProfileEntity, UserProfileDto> {

    @BasicMapping
    @Mapping(target = "userId", source = "user.id")
    UserProfileDto toDto(UserProfileEntity entity);

    @WithRelations
    @InheritConfiguration(name = "toDto")
    UserProfileDto toDtoWithRelations(UserProfileEntity entity);
}
