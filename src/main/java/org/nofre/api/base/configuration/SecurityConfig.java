package org.nofre.api.base.configuration;

import lombok.RequiredArgsConstructor;
import org.nofre.api.base.security.RolePermissionEvaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchy roleHierarchy(RolePermissionEvaluator permissionEvaluator) {
        return permissionEvaluator.getRoleHierarchy();
    }


    @Bean
    public DefaultWebSecurityExpressionHandler expressionHandler(
            PermissionEvaluator permissionEvaluator,
            RoleHierarchy roleHierarchy) {

        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(permissionEvaluator);
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }


}