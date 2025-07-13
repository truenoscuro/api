package org.nofre.api.base.feature.user.entity;

import org.nofre.api.base.common.crud.entity.CommonRepository;
import org.nofre.api.base.observability.annotation.ObservedRepository;

import java.util.Optional;

@ObservedRepository
public interface UserRepository extends CommonRepository<UserEntity> {
    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
