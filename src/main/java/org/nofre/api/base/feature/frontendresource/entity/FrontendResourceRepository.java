package org.nofre.api.base.feature.frontendresource.entity;

import org.nofre.api.base.common.crud.entity.CommonRepository;
import org.nofre.api.base.observability.annotation.ObservedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@ObservedRepository
public interface FrontendResourceRepository extends CommonRepository<FrontendResourceEntity> {
    //Filtrar por los permisos que tiene el usuario

    @Query("""
           SELECT fr FROM FrontendResourceEntity fr
           LEFT JOIN fr.permissions per
           LEFT JOIN fr.children
           WHERE (per IS NULL
               OR per.name IN (
                   SELECT per2.name FROM UserEntity us
                   JOIN us.roles rol
                   JOIN rol.permissions per2
                   WHERE us.email = :email
               ))
               AND fr.parent IS NULL
               AND fr.active IS TRUE
           ORDER BY fr.order ASC
           """)
    List<FrontendResourceEntity> findAllByMe(@Param("email") String email);
}
