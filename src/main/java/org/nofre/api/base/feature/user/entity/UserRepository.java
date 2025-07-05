package org.nofre.api.base.feature.user.entity;

import org.nofre.api.base.common.crud.repository.CommonRepository;
import org.nofre.api.base.observability.annotation.ObservedRepository;

import java.util.Optional;

@ObservedRepository
public interface UserRepository extends CommonRepository<UserEntity> {
    Optional<UserEntity> findByEmail(String email);
}
