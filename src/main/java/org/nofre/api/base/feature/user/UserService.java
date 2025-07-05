package org.nofre.api.base.feature.user;

import org.nofre.api.base.common.crud.service.CommonCrudService;
import org.nofre.api.base.feature.user.exception.EmailException;
import org.nofre.api.base.feature.user.model.UserDto;

public interface UserService extends CommonCrudService<UserDto> {

    UserDto getByEmail(String email) throws EmailException;
}
