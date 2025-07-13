package org.nofre.api.base.configuration.security.service;

import lombok.Getter;
import org.nofre.api.base.configuration.security.RolePermissionEvaluator;
import org.nofre.api.base.feature.security.role.entity.RoleEntity;
import org.nofre.api.base.feature.security.role.entity.RoleRepository;
import org.springframework.lang.Nullable;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class RolePermissionEvaluatorImp implements PermissionEvaluator, RolePermissionEvaluator {

    private final RoleRepository roleRepository;


    private Map<String, Set<String>> ROLE_PERMISSIONS_CACHE = new HashMap<>();

    @Getter
    private RoleHierarchy roleHierarchy;


    public RolePermissionEvaluatorImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        loadRolePermissionsAndHierarchy();
    }

    @Override
    public void loadRolePermissionsAndHierarchy() {
        List<RoleEntity> roles = roleRepository.findAllEager();
        this.ROLE_PERMISSIONS_CACHE = loadRolePermissions(roles);
        this.roleHierarchy = loadRoleHierarchy(roles);
    }


    private Map<String, Set<String>> loadRolePermissions(List<RoleEntity> roles) {
        //Cargamos los nuevos rolePermissions
        Map<String, Set<String>> rolePermissions = new HashMap<>();
        for (RoleEntity role : roles) {
            loadChildRolePermissions(RolePermissionEvaluator.roleKey(role.getName()), role, rolePermissions);
            //añadimos todos los permisos de los hijos
        }
        //Los cambiamos para no perder los roles permissions antiguos hasta que se efectue el cambio
        return rolePermissions;
    }

    public RoleHierarchyImpl loadRoleHierarchy(List<RoleEntity> roles) {
        RoleHierarchyImpl.Builder builder = RoleHierarchyImpl.withDefaultRolePrefix();
        for (RoleEntity role : roles) {
            if (role.getChildren() != null) {
                loadChildHierarchy(role, builder);
            }
        }
        return builder.build();
    }

    private void loadChildHierarchy(RoleEntity role, RoleHierarchyImpl.Builder builder) {
        String roleName = RolePermissionEvaluator.roleKey(role.getName());
        for (RoleEntity childRole : role.getChildren()) {
            String childRoleName = childRole.getName().toUpperCase();
            builder.role(roleName).implies(childRoleName);
            loadChildHierarchy(childRole, builder);
        }
    }


    private void loadChildRolePermissions(String roleName, RoleEntity role, Map<String, Set<String>> rolePermissions) {
        Set<String> permissionsForRole = rolePermissions.computeIfAbsent(roleName, k -> new HashSet<>());

        //Añadimos sus permisos
        permissionsForRole.addAll(role.getPermissions().stream().map(permission -> permission.getName().toUpperCase()).collect(Collectors.toSet()));
        //Si tiene hijos añadimos los de sus hijos
        if (role.getChildren() != null) {
            for (RoleEntity childRole : role.getChildren()) {
                loadChildRolePermissions(roleName, childRole, rolePermissions);
            }
        }
    }


    @Nullable
    @Override
    public Set<String> getPermissionsForRole(String roleName) {
        roleName = roleName.toUpperCase();
        if (!roleName.startsWith("ROLE_")) {
            roleName = "ROLE_" + roleName;
        }
        return ROLE_PERMISSIONS_CACHE.get(roleName);
    }


    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null || !(permission instanceof String requiredPermission)) {
            return false;
        }
// Obtener los roles del usuario autenticado
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Verificar si alguno de los roles del usuario tiene el permiso requerido
        for (GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();
            // Asumiendo que solo los roles tienen el prefijo "ROLE_"
            if (roleName.startsWith("ROLE_")) {
                Set<String> permissionsForRole = getPermissionsForRole(roleName);
                if (permissionsForRole != null && permissionsForRole.contains(requiredPermission)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return hasPermission(authentication, null, permission);
    }
}
