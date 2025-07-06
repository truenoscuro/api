package org.nofre.api.base.feature.userprofile;

import org.nofre.api.base.common.crud.service.CommonCrudService;
import org.nofre.api.base.feature.user.exception.EmailException;
import org.nofre.api.base.feature.userprofile.model.UserProfileDto;

public interface UserProfileService extends CommonCrudService<UserProfileDto> {
    UserProfileDto getByEmail(String email) throws EmailException;
}
