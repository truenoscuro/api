package org.nofre.api.base.feature.security.role.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.nofre.api.base.common.crud.model.CommonDto;
import org.nofre.api.base.feature.security.permission.model.PermissionDto;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true,
                   exclude = {"permissions", "parent", "children"})
@ToString(callSuper = true,
          exclude = {"permissions", "parent", "children"})
public class RoleDto extends CommonDto {


    private String name;
    private String description;
    private RoleDto parent;

    private Set<PermissionDto> permissions;
    private Set<RoleDto> children;

}
