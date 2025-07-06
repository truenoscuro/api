package org.nofre.api.base.feature.authentication;

import org.nofre.api.base.feature.authentication.exception.LoginException;
import org.nofre.api.base.feature.authentication.model.AuthRs;
import org.nofre.api.base.feature.authentication.model.AuthUser;
import org.nofre.api.base.feature.user.exception.EmailException;

public interface AuthenticationService {
    AuthRs getAuthRs(AuthUser user, String method) throws EmailException, LoginException;
}
