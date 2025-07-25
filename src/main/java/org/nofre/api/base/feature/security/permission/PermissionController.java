package org.nofre.api.base.feature.security.permission;

import org.nofre.api.base.common.controller.ApiRestController;
import org.nofre.api.base.common.crud.controller.CommonCrudControllerImp;
import org.nofre.api.base.feature.security.permission.model.PermissionDto;

@ApiRestController("permissions")
public class PermissionController extends CommonCrudControllerImp<PermissionDto, PermissionService> {

    public PermissionController(PermissionService service) {
        super(service, "PERMISSION");
    }

}
