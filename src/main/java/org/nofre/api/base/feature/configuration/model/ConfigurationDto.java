package org.nofre.api.base.feature.configuration.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.nofre.api.base.common.crud.model.CommonDto;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConfigurationDto extends CommonDto {
    private String key;
    private String value;
}
