package org.nofre.api.base.feature.configuration;

import org.mapstruct.Mapper;
import org.nofre.api.base.common.crud.mapper.CommonCrudMapper;
import org.nofre.api.base.feature.configuration.entity.ConfigurationEntity;
import org.nofre.api.base.feature.configuration.model.ConfigurationDto;

@Mapper
public interface ConfigurationMapper extends CommonCrudMapper<ConfigurationEntity, ConfigurationDto> {
}
