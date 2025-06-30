package org.nofre.api.configuration;

import org.nofre.api.base.common.crud.ICommonCrudService;

public interface IConfigurationService extends ICommonCrudService<ConfigurationDto> {

    ConfigurationDto findByKey(String key);
}
