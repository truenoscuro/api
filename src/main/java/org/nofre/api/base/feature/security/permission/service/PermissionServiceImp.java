package org.nofre.api.base.feature.security.permission.service;

import org.nofre.api.base.common.crud.entity.specification.GenericSpecification;
import org.nofre.api.base.common.crud.service.CommonCrudServiceImp;
import org.nofre.api.base.common.crud.service.CrudService;
import org.nofre.api.base.feature.security.permission.PermissionMapper;
import org.nofre.api.base.feature.security.permission.PermissionService;
import org.nofre.api.base.feature.security.permission.entity.PermissionEntity;
import org.nofre.api.base.feature.security.permission.entity.PermissionRepository;
import org.nofre.api.base.feature.security.permission.model.PermissionDto;

@CrudService
public class PermissionServiceImp extends CommonCrudServiceImp<
        PermissionEntity,
        PermissionDto,
        PermissionMapper,
        PermissionRepository
        > implements PermissionService {

    public PermissionServiceImp(
            PermissionRepository repository,
            PermissionMapper mapper,
            GenericSpecification<PermissionEntity> specification) {
        super("permissions", repository, mapper, specification);
    }


}
