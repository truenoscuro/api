package org.nofre.api.base.feature.configuration;

import org.nofre.api.base.common.controller.ApiRestController;
import org.nofre.api.base.common.controller.model.CommonRq;
import org.nofre.api.base.common.controller.model.CommonRs;
import org.nofre.api.base.common.crud.controller.CommonCrudControllerImp;
import org.nofre.api.base.common.crud.exception.CommonCrudException;
import org.nofre.api.base.feature.configuration.model.ConfigurationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@ApiRestController("configurations")
public class ConfigurationController extends CommonCrudControllerImp<ConfigurationDto, ConfigurationService> {

    public ConfigurationController(ConfigurationService service) {
        super(service);
    }


    @PostMapping
    public ResponseEntity<CommonRs<ConfigurationDto>> saveItem(@RequestBody CommonRq<ConfigurationDto> rq) throws CommonCrudException {
        throw new CommonCrudException("No se pueden añadir configuraciones");
    }


    @DeleteMapping("{id}")
    public ResponseEntity<CommonRs<Void>> deleteItem(@PathVariable Long id) throws CommonCrudException {
        throw new CommonCrudException("No se pueden eliminar configuraciones");
    }


}
