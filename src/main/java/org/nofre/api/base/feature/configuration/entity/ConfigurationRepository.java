package org.nofre.api.base.feature.configuration.entity;

import org.nofre.api.base.common.crud.repository.CommonRepository;
import org.nofre.api.base.observability.annotation.ObservedRepository;

import java.util.Optional;


@ObservedRepository
public interface ConfigurationRepository extends CommonRepository<ConfigurationEntity> {
    Optional<ConfigurationEntity> findByKey(String key);
}
