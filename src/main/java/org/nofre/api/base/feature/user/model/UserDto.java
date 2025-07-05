package org.nofre.api.base.feature.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.nofre.api.base.common.crud.model.CommonDto;
import org.nofre.api.base.feature.role.model.RoleDto;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString(callSuper = true, exclude = {"password", "roles"})
@EqualsAndHashCode(callSuper = true, exclude = {"password", "roles"})
public class UserDto extends CommonDto {

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    private Set<RoleDto> roles = new HashSet<>();

}
