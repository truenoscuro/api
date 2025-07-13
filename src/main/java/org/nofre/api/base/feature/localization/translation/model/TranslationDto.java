package org.nofre.api.base.feature.localization.translation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.nofre.api.base.common.crud.model.CommonDto;
import org.nofre.api.base.feature.localization.label.model.LabelDto;
import org.nofre.api.base.feature.localization.language.model.LanguageDto;

@Data
@ToString(callSuper = true, exclude = {"language", "label"})
@EqualsAndHashCode(callSuper = true, exclude = {"language", "label"})
public class TranslationDto extends CommonDto {

    private String value;

    @JsonIgnoreProperties({"translations"})
    private LanguageDto language;

    @JsonIgnoreProperties({"translations"})
    private LabelDto label;

}
