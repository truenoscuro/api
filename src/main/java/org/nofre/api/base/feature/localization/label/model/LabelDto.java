package org.nofre.api.base.feature.localization.label.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.nofre.api.base.common.crud.model.CommonDto;
import org.nofre.api.base.feature.localization.labelgroup.model.LabelGroupDto;
import org.nofre.api.base.feature.localization.translation.model.TranslationDto;

import java.util.Set;

@Data
@ToString(callSuper = true, exclude = {"translations", "labelGroup"})
@EqualsAndHashCode(callSuper = true, exclude = {"translations", "labelGroup", "description"})
public class LabelDto extends CommonDto {

    private String name;
    private String description;
    private Long labelGroupId;

    @JsonIgnoreProperties({"labels"})
    private LabelGroupDto labelGroup;

    @JsonIgnoreProperties({"labels"})
    private Set<TranslationDto> translations;

}
