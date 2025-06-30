package org.nofre.api.configuration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.nofre.api.base.common.crud.CommonDto;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConfigurationDto extends CommonDto {
    private String key;
    private String value;
}
