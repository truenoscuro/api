package org.nofre.api.base.feature.userprofile;

import org.nofre.api.base.common.controller.ApiRestController;
import org.nofre.api.base.common.controller.model.CommonRs;
import org.nofre.api.base.common.crud.controller.CommonCrudControllerImp;
import org.nofre.api.base.feature.user.exception.EmailException;
import org.nofre.api.base.feature.userprofile.model.UserProfileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

@ApiRestController("info-users")
public class UserProfileController extends CommonCrudControllerImp<UserProfileDto, UserProfileService> {
    public UserProfileController(UserProfileService service) {
        super(service);
    }

    @GetMapping("me")
    public ResponseEntity<CommonRs<UserProfileDto>> getMeInfoUser() throws EmailException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getResponse(service.getByEmail(email));
    }
}
