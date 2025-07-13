package org.nofre.api.base.configuration.security;

import org.springframework.lang.Nullable;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;

import java.util.Set;

public interface RolePermissionEvaluator {
    static String roleKey(String roleName) {
        return "ROLE_" + roleName.toUpperCase().trim();
    }

    void loadRolePermissionsAndHierarchy();

    @Nullable
    Set<String> getPermissionsForRole(String roleName);

    RoleHierarchy getRoleHierarchy();
}
