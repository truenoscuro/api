package org.nofre.api.base.feature.authentication.service;

import lombok.RequiredArgsConstructor;
import org.nofre.api.base.common.crud.exception.CommonCrudException;
import org.nofre.api.base.configuration.security.BearerTokenService;
import org.nofre.api.base.configuration.security.RolePermissionEvaluator;
import org.nofre.api.base.feature.authentication.AuthenticationService;
import org.nofre.api.base.feature.authentication.exception.LoginException;
import org.nofre.api.base.feature.authentication.exception.RegisterException;
import org.nofre.api.base.feature.authentication.model.AuthRs;
import org.nofre.api.base.feature.authentication.model.AuthUser;
import org.nofre.api.base.feature.configuration.ConfigurationService;
import org.nofre.api.base.feature.security.role.RoleService;
import org.nofre.api.base.feature.security.role.model.RoleDto;
import org.nofre.api.base.feature.user.UserService;
import org.nofre.api.base.feature.user.exception.EmailException;
import org.nofre.api.base.feature.user.model.UserDto;
import org.nofre.api.base.observability.annotation.ObservedService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@ObservedService
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    private final UserService userService;
    private final BearerTokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final ConfigurationService configurationService;
    private final RoleService roleService;
    private final RolePermissionEvaluator rolePermissionEvaluator;


    @Value("${configuration.default.role}")
    private String roleDefault;

    @Override
    public AuthRs getAuthRs(AuthUser user, String method) throws EmailException, LoginException {
        UserDto userDto = userService.getByEmail(user.email());
        if (user.password() == null || !passwordEncoder.matches(user.password(), userDto.getPassword())) {
            throw new LoginException();
        }
        //añadimos el mapa de los roles con los permisos
        Set<String> authorities = new HashSet<>();
        for (RoleDto role : userDto.getRoles()) {
            //añades el rol
            authorities.add(RolePermissionEvaluator.roleKey(role.getName()));
            //añades los permisos
            Set<String> permissions = rolePermissionEvaluator.getPermissionsForRole(role.getName());
            if (permissions != null) {
                authorities.addAll(permissions);
            }
        }
        return new AuthRs(tokenService.generateToken(userDto), authorities);


    }

    @Override
    public void register(AuthUser user) throws RegisterException, CommonCrudException {
        boolean isFirstRegister = userService.isFirstRegister();

        if (!isFirstRegister && userService.existsByEmail(user.email())) {
            throw new RegisterException();
        }
        //creamos el usuario nuevo
        UserDto dto = new UserDto();
        //recogemos el role
        String roleName = "SUPER_ADMIN";

        if (!isFirstRegister) {
            roleName = configurationService.findByKey(roleDefault);
        }

        RoleDto role = roleService.findByName(roleName);

        dto.setEmail(user.email());
        dto.setPassword(user.password());
        dto.setRoles(Set.of(role));

        //Guardamos al usuario
        userService.save(dto);
    }


}
