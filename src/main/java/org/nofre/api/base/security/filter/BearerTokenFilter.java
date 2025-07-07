package org.nofre.api.base.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.nofre.api.base.security.BearerTokenService;
import org.nofre.api.base.security.RolePermissionEvaluator;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class BearerTokenFilter extends OncePerRequestFilter {

    private final BearerTokenService bearerTokenService;
    private final RolePermissionEvaluator rolePermissionEvaluator;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null
                && authHeader.startsWith("Bearer ")
                && !authHeader.substring(7).isBlank()) {
            String accessToken = authHeader.substring(7);
            User user = bearerTokenService.getUserFromToken(accessToken);
            if (user != null) {

                Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                        user.getUsername(), null, getAuthorities(user));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }


        filterChain.doFilter(request, response);
    }

    /**
     * Carga los permisos del usuario por cada rol que le llega del token
     *
     * @param user
     * @return
     */
    private Collection<GrantedAuthority> getAuthorities(User user) {
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        Set<String> permissions = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();
            Set<String> newPermissions = rolePermissionEvaluator.getPermissionsForRole(roleName);
            if (newPermissions != null) {
                permissions.addAll(newPermissions);
            }
        }
        List<GrantedAuthority> fullAuthorities = new ArrayList<>(
                permissions.stream().map(SimpleGrantedAuthority::new)
                        .map(authority -> (GrantedAuthority) authority)
                        .toList());

        fullAuthorities.addAll(authorities);

        return fullAuthorities;
    }


}
