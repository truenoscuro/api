package org.nofre.api.configuration;

import org.nofre.api.base.common.crud.CommonRepository;
import org.nofre.api.base.observability.ObservedRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@ObservedRepository
public interface ConfigurationRepository extends CommonRepository<ConfigurationEntity> {
    Optional<ConfigurationEntity> findByKey(String key);
}
