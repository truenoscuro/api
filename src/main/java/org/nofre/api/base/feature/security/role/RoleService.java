package org.nofre.api.base.feature.security.role;

import org.nofre.api.base.common.crud.service.CommonCrudService;
import org.nofre.api.base.feature.security.role.model.RoleDto;

public interface RoleService extends CommonCrudService<RoleDto> {

    RoleDto findByName(String name);
}
