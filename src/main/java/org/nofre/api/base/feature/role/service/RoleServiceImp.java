package org.nofre.api.base.feature.role.service;

import org.nofre.api.base.common.crud.repository.GenericSpecification;
import org.nofre.api.base.common.crud.service.CommonCrudServiceImp;
import org.nofre.api.base.common.crud.service.CrudService;
import org.nofre.api.base.feature.role.RoleMapper;
import org.nofre.api.base.feature.role.RoleService;
import org.nofre.api.base.feature.role.entity.RoleEntity;
import org.nofre.api.base.feature.role.entity.RoleRepository;
import org.nofre.api.base.feature.role.model.RoleDto;

@CrudService
public class RoleServiceImp extends CommonCrudServiceImp<
        RoleEntity,
        RoleDto,
        RoleMapper,
        RoleRepository> implements RoleService {
    public RoleServiceImp(
            RoleRepository repository,
            RoleMapper mapper,
            GenericSpecification<RoleEntity> specification) {
        super("roles", repository, mapper, specification);
    }

}
