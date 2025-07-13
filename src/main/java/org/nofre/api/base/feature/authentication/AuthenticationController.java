package org.nofre.api.base.feature.authentication;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.nofre.api.base.common.controller.ApiRestController;
import org.nofre.api.base.common.controller.CommonController;
import org.nofre.api.base.common.controller.model.CommonRq;
import org.nofre.api.base.common.controller.model.CommonRs;
import org.nofre.api.base.common.crud.exception.CommonCrudException;
import org.nofre.api.base.feature.authentication.exception.LoginException;
import org.nofre.api.base.feature.authentication.exception.RegisterException;
import org.nofre.api.base.feature.authentication.model.AuthRs;
import org.nofre.api.base.feature.authentication.model.AuthUser;
import org.nofre.api.base.feature.user.exception.EmailException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@ApiRestController("auth")
public class AuthenticationController extends CommonController {

    private final AuthenticationService authenticationService;

    @PermitAll
    @PostMapping("login")
    public ResponseEntity<CommonRs<AuthRs>> login(@RequestBody CommonRq<AuthUser> authUser, @RequestParam(required = false, defaultValue = "NONE") String method) throws EmailException, LoginException {
        return postResponse(authenticationService.getAuthRs(authUser.data(), method));
    }

    @PermitAll
    @PostMapping("register")
    public ResponseEntity<CommonRs<Void>> register(@RequestBody CommonRq<AuthUser> authUser) throws CommonCrudException, RegisterException, EmailException {
        authenticationService.register(authUser.data());
        return postResponse(null);
    }

}
