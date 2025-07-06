package org.nofre.api.base.feature.configuration.service;

import org.nofre.api.base.common.crud.entity.specification.GenericSpecification;
import org.nofre.api.base.common.crud.service.CommonCrudServiceImp;
import org.nofre.api.base.common.crud.service.CrudService;
import org.nofre.api.base.feature.configuration.ConfigurationMapper;
import org.nofre.api.base.feature.configuration.ConfigurationService;
import org.nofre.api.base.feature.configuration.KeyException;
import org.nofre.api.base.feature.configuration.entity.ConfigurationEntity;
import org.nofre.api.base.feature.configuration.entity.ConfigurationRepository;
import org.nofre.api.base.feature.configuration.model.ConfigurationDto;


@CrudService
public class ConfigurationServiceImp extends CommonCrudServiceImp<
        ConfigurationEntity,
        ConfigurationDto,
        ConfigurationMapper,
        ConfigurationRepository> implements ConfigurationService {

    public ConfigurationServiceImp(ConfigurationRepository repository,
                                   ConfigurationMapper mapper,
                                   GenericSpecification<ConfigurationEntity> specification
    ) {
        super("configuration", repository, mapper, specification);
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("No se pueden eliminar configuraciones");
    }


    @Override
    public ConfigurationDto findByKey(String key) {
        return repository.findByKey(key.toLowerCase())
                .map(mapper::toDto)
                .orElseThrow(() -> new KeyException(key));
    }
}
