package org.nofre.api.base.feature.security.role.service;

import org.nofre.api.base.common.crud.entity.specification.GenericSpecification;
import org.nofre.api.base.common.crud.service.CommonCrudServiceImp;
import org.nofre.api.base.common.crud.service.CrudService;
import org.nofre.api.base.feature.security.role.RoleMapper;
import org.nofre.api.base.feature.security.role.RoleService;
import org.nofre.api.base.feature.security.role.entity.RoleEntity;
import org.nofre.api.base.feature.security.role.entity.RoleRepository;
import org.nofre.api.base.feature.security.role.exception.NoExistRoleException;
import org.nofre.api.base.feature.security.role.model.RoleDto;

@CrudService
public class RoleServiceImp extends CommonCrudServiceImp<RoleEntity, RoleDto, RoleMapper, RoleRepository> implements RoleService {
    public RoleServiceImp(RoleRepository repository, RoleMapper mapper, GenericSpecification<RoleEntity> specification) {
        super("roles", repository, mapper, specification);
    }

    @Override
    public RoleDto findByName(String name) {
        return repository.findByName(name).map(mapper::toDto).orElseThrow(() -> new NoExistRoleException(name));
    }
}
