package org.nofre.api.base.feature.security.permission.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.nofre.api.base.common.crud.model.CommonDto;
import org.nofre.api.base.feature.security.grouppermission.model.GroupPermissionDto;


@Data
@EqualsAndHashCode(callSuper = true, exclude = "groupPermission")
@ToString(callSuper = true, exclude = "groupPermission")
public class PermissionDto extends CommonDto {

    private String name;
    private String description;
    private GroupPermissionDto groupPermission;
}
