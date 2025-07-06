package org.nofre.api.base.security;

import org.nofre.api.base.feature.user.model.UserDto;
import org.springframework.security.core.userdetails.User;

public interface BearerTokenService {
    User getUserFromToken(String token);

    String generateToken(UserDto user);
}
