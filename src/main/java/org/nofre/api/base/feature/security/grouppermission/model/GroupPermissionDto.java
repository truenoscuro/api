package org.nofre.api.base.feature.security.grouppermission.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.nofre.api.base.common.crud.model.CommonDto;
import org.nofre.api.base.feature.security.permission.model.PermissionDto;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = "permissions")
@ToString(callSuper = true, exclude = "permissions")
public class GroupPermissionDto extends CommonDto {
    private String name;
    private Set<PermissionDto> permissions = new HashSet<>();
}
