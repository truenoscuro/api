package org.nofre.api.base.feature.configuration;

import org.nofre.api.base.common.controller.ApiRestController;
import org.nofre.api.base.common.controller.model.CommonRq;
import org.nofre.api.base.common.controller.model.CommonRs;
import org.nofre.api.base.common.crud.controller.CommonCrudControllerImp;
import org.nofre.api.base.common.crud.exception.CommonCrudException;
import org.nofre.api.base.feature.configuration.model.ConfigurationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@ApiRestController("configurations")
public class ConfigurationController extends CommonCrudControllerImp<ConfigurationDto, ConfigurationService> {

    public ConfigurationController(ConfigurationService service) {
        super(service, "CONF");
    }


    @PostMapping
    public ResponseEntity<CommonRs<ConfigurationDto>> saveItem(
            @ModelAttribute("authority") String authority,
            @RequestBody CommonRq<ConfigurationDto> rq) throws CommonCrudException {
        throw new CommonCrudException("No se pueden añadir configuraciones");
    }


    @DeleteMapping("{id}")
    public ResponseEntity<CommonRs<Void>> deleteItem(
            @ModelAttribute("authority") String authority,
            @PathVariable Long id) throws CommonCrudException {
        throw new CommonCrudException("No se pueden eliminar configuraciones");
    }


}
