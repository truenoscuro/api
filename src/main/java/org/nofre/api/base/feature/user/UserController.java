package org.nofre.api.base.feature.user;

import org.nofre.api.base.common.controller.ApiRestController;
import org.nofre.api.base.common.crud.controller.CommonCrudControllerImp;
import org.nofre.api.base.feature.user.model.UserDto;

@ApiRestController("users")
public class UserController extends CommonCrudControllerImp<UserDto, UserService> {


    public UserController(UserService service) {
        super(service);
    }


}
