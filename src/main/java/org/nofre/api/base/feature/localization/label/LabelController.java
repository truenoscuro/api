package org.nofre.api.base.feature.localization.label;

import org.nofre.api.base.common.controller.ApiRestController;
import org.nofre.api.base.common.crud.controller.CommonCrudControllerImp;
import org.nofre.api.base.feature.localization.label.model.LabelDto;

@ApiRestController("labels")
public class LabelController extends CommonCrudControllerImp<LabelDto, LabelService> {
    public LabelController(LabelService service) {
        super(service);
    }

}
