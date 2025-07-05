package org.nofre.api.base.feature.role.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.nofre.api.base.common.crud.model.CommonDto;
import org.nofre.api.base.feature.permission.model.PermissionDto;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"permissions", "parent", "children"})
@ToString(callSuper = true, exclude = {"permissions", "parent", "children"})
public class RoleDto extends CommonDto {


    private String name;

    private String description;
    private Set<PermissionDto> permissions;
    private Long parentId;

    @JsonIgnoreProperties("children")
    private RoleDto parent;

    @JsonIgnoreProperties("parent")
    private Set<RoleDto> children;

}
