package org.nofre.api.base.security;

import org.springframework.lang.Nullable;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;

import java.util.Set;

public interface RolePermissionEvaluator {
    void loadRolePermissionsAndHierarchy();

    @Nullable
    Set<String> getPermissionsForRole(String roleName);

    RoleHierarchy getRoleHierarchy();
}
