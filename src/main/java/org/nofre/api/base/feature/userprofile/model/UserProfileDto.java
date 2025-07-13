package org.nofre.api.base.feature.userprofile.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.nofre.api.base.common.crud.model.CommonDto;
import org.nofre.api.base.feature.user.model.UserDto;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserProfileDto extends CommonDto {

    private String name;
    private String firstSurname;
    private String secondSurname;

    private UserDto user;

}
