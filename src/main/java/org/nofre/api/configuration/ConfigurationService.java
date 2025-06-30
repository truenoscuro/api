package org.nofre.api.configuration;

import jakarta.transaction.Transactional;
import org.nofre.api.base.common.crud.CommonCrudService;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ConfigurationService extends CommonCrudService<
        ConfigurationEntity,
        ConfigurationDto,
        ConfigurationMapper,
        ConfigurationRepository> implements IConfigurationService {

    public ConfigurationService(ConfigurationRepository repository,
                                ConfigurationMapper mapper) {
        super("configuration", repository, mapper);
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public ConfigurationDto findByKey(String key) {
        return repository.findByKey(key.toLowerCase())
                .map(mapper::toDto)
                .orElseThrow(() -> new KeyException(key));
    }
}
