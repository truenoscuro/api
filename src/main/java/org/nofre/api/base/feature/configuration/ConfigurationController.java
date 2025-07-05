package org.nofre.api.base.feature.configuration;

import org.nofre.api.base.common.controller.ApiRestController;
import org.nofre.api.base.common.crud.controller.CommonCrudControllerImp;
import org.nofre.api.base.feature.configuration.model.ConfigurationDto;


@ApiRestController("configurations")
public class ConfigurationController extends CommonCrudControllerImp<ConfigurationDto, ConfigurationService> {

    public ConfigurationController(ConfigurationService service) {
        super(service);
    }

}
