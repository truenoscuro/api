package org.nofre.api.base.feature.localization.language.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.nofre.api.base.common.crud.model.CommonDto;
import org.nofre.api.base.feature.localization.translation.model.TranslationDto;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString(callSuper = true, exclude = {"translations"})
@EqualsAndHashCode(callSuper = true, exclude = {"translations"})
public class LanguageDto extends CommonDto {
    private String code;
    private String name;
    private String locale;

    @JsonIgnoreProperties({"language"})
    private Set<TranslationDto> translations = new HashSet<>();
}
