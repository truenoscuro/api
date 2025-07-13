package org.nofre.api.base.feature.frontendresource.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.nofre.api.base.common.crud.model.CommonDto;
import org.nofre.api.base.feature.security.permission.model.PermissionDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true,
                   exclude = {"parent", "children", "permissions"})
@ToString(callSuper = true,
          exclude = {"parent", "children", "permissions"})
public class FrontendResourceDto extends CommonDto {
    private String label;
    private String icon;
    @JsonProperty("path")
    private String angularPath;
    @JsonProperty("redirectTo")
    private String angularRedirectTo;
    private int order = 99;
    private FrontendResourceDto parent;
    @JsonProperty("component")
    private String angularComponent;
    @JsonProperty("pathMatch")
    private String angularPathMatch = "prefix";
    @JsonProperty("isMenu")
    private boolean menu = false;
    private String titlePage;
    private List<FrontendResourceDto> children = new ArrayList<>();
    private Set<PermissionDto> permissions = new HashSet<>();
}
