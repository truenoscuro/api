package org.nofre.api.base.feature.authentication.service;

import lombok.RequiredArgsConstructor;
import org.nofre.api.base.common.service.BearerTokenService;
import org.nofre.api.base.feature.authentication.AuthenticationService;
import org.nofre.api.base.feature.authentication.LoginException;
import org.nofre.api.base.feature.authentication.model.AuthRs;
import org.nofre.api.base.feature.authentication.model.AuthUser;
import org.nofre.api.base.feature.user.UserService;
import org.nofre.api.base.feature.user.exception.EmailException;
import org.nofre.api.base.feature.user.model.UserDto;
import org.nofre.api.base.observability.annotation.ObservedService;
import org.springframework.security.crypto.password.PasswordEncoder;

@ObservedService
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    private final UserService userService;
    private final BearerTokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthRs getAuthRs(AuthUser user, String method) throws EmailException, LoginException {
        UserDto userDto = userService.getByEmail(user.email());
        if (user.password() != null && passwordEncoder.matches(user.password(), userDto.getPassword())) {
            return new AuthRs(tokenService.generateToken(userDto),
                    userDto.getRoles().stream()
                            .map(role -> role.getName().toUpperCase())
                            .toList()
            );
        }

        throw new LoginException();

    }


}
