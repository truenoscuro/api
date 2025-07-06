package org.nofre.api.base.feature.userprofile.entity;

import org.nofre.api.base.common.crud.repository.CommonRepository;
import org.nofre.api.base.observability.annotation.ObservedRepository;

import java.util.Optional;

@ObservedRepository
public interface UserProfileRepository extends CommonRepository<UserProfileEntity> {

    Optional<UserProfileEntity> findByUser_Email(String userEmail);

    Optional<UserProfileEntity> findByUser_Id(Long userId);

}
