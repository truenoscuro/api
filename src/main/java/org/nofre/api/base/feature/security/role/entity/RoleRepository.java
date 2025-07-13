package org.nofre.api.base.feature.security.role.entity;

import org.nofre.api.base.common.crud.entity.CommonRepository;
import org.nofre.api.base.observability.annotation.ObservedRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@ObservedRepository
public interface RoleRepository extends CommonRepository<RoleEntity> {

    @Query("""
            SELECT DISTINCT roles FROM RoleEntity roles
            LEFT JOIN FETCH roles.permissions
            LEFT JOIN FETCH roles.parent
            LEFT JOIN FETCH roles.children
            """)
    List<RoleEntity> findAllEager();

    Optional<RoleEntity> findByName(String name);
}