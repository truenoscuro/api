package org.nofre.api.base.feature.localization.labelgroup;

import org.nofre.api.base.common.controller.ApiRestController;
import org.nofre.api.base.common.crud.controller.CommonCrudControllerImp;
import org.nofre.api.base.feature.localization.labelgroup.model.LabelGroupDto;

@ApiRestController("label-groups")
public class LabelGroupController extends CommonCrudControllerImp<LabelGroupDto, LabelGroupService> {

    public LabelGroupController(LabelGroupService service) {
        super(service);
    }

}
