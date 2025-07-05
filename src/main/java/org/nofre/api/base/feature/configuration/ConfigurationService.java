package org.nofre.api.base.feature.configuration;

import org.nofre.api.base.common.crud.service.CommonCrudService;
import org.nofre.api.base.feature.configuration.model.ConfigurationDto;

public interface ConfigurationService extends CommonCrudService<ConfigurationDto> {

    ConfigurationDto findByKey(String key);
}
