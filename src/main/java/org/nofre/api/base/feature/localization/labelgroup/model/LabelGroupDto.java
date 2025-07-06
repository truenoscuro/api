package org.nofre.api.base.feature.localization.labelgroup.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.nofre.api.base.common.crud.model.CommonDto;
import org.nofre.api.base.feature.localization.label.model.LabelDto;

import java.util.Set;

@Data
@ToString(callSuper = true, exclude = {"labels"})
@EqualsAndHashCode(callSuper = true, exclude = {"description", "labels"})
public class LabelGroupDto extends CommonDto {

    private String name;

    private String description;

    @JsonIgnoreProperties({"labelGroup"})
    private Set<LabelDto> labels;

}
