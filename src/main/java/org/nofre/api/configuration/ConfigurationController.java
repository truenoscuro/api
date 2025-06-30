package org.nofre.api.configuration;

import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import org.nofre.api.base.common.controller.CommonController;
import org.nofre.api.base.common.controller.CommonRq;
import org.nofre.api.base.common.controller.CommonRs;
import org.nofre.api.base.common.crud.ICommonCrudController;
import org.nofre.api.base.common.crud.TableContext;
import org.nofre.api.base.common.model.Paginated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Observed
@RestController
@AllArgsConstructor
@RequestMapping("api/configurations")
public class ConfigurationController extends CommonController implements ICommonCrudController<ConfigurationDto> {


    private final IConfigurationService configurationService;

    @GetMapping
    public ResponseEntity<CommonRs<ConfigurationDto>> findByKey(@RequestParam String key) {
        return getResponse(configurationService.findByKey(key));
    }


    @Override
    @GetMapping("{id}")
    public ResponseEntity<CommonRs<ConfigurationDto>> getItem(@PathVariable Long id) {
        return getResponse(configurationService.getById(id));
    }

    @Override
    @PostMapping("list")
    public ResponseEntity<CommonRs<Paginated<ConfigurationDto>>> getPaginated(@RequestBody CommonRq<TableContext> rq) {
        return paginatedResponse(configurationService.getPaginated(rq.data()));
    }

    @Override
    @PostMapping
    public ResponseEntity<CommonRs<ConfigurationDto>> saveItem(@RequestBody CommonRq<ConfigurationDto> rq) {
        throw new UnsupportedOperationException("No se puede guardar una configuracion");
    }

    @Override
    @PutMapping
    public ResponseEntity<CommonRs<ConfigurationDto>> updateItem(@RequestBody CommonRq<ConfigurationDto> rq) {
        return updatedResponse(configurationService.update(rq.data()));
    }


    @Override
    @DeleteMapping({"{id}"})
    public ResponseEntity<CommonRs<Void>> deleteItem(@PathVariable Long id) {
        throw new UnsupportedOperationException("No se puede borrar una configuracion");
    }

}
