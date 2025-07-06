package org.nofre.api.base.feature.rolepermission.permission.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.nofre.api.base.common.crud.model.CommonDto;


@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionDto extends CommonDto {

    private String name;

    private String description;
}
