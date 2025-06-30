package org.nofre.api.configuration;

import org.mapstruct.Mapper;
import org.nofre.api.base.common.crud.CommonCrudMapper;

@Mapper
public interface ConfigurationMapper extends CommonCrudMapper<ConfigurationEntity, ConfigurationDto> {
}
