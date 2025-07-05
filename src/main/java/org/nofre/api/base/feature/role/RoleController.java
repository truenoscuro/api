package org.nofre.api.base.feature.role;

import org.nofre.api.base.common.controller.ApiRestController;
import org.nofre.api.base.common.crud.controller.CommonCrudControllerImp;
import org.nofre.api.base.feature.role.model.RoleDto;

@ApiRestController("roles")
public class RoleController extends CommonCrudControllerImp<RoleDto, RoleService> {

    public RoleController(RoleService service) {
        super(service);
    }
}
