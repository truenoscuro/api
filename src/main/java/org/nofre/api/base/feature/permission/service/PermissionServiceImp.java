package org.nofre.api.base.feature.permission.service;

import org.nofre.api.base.common.crud.repository.GenericSpecification;
import org.nofre.api.base.common.crud.service.CommonCrudServiceImp;
import org.nofre.api.base.common.crud.service.CrudService;
import org.nofre.api.base.feature.permission.PermissionMapper;
import org.nofre.api.base.feature.permission.PermissionService;
import org.nofre.api.base.feature.permission.entity.PermissionEntity;
import org.nofre.api.base.feature.permission.entity.PermissionRepository;
import org.nofre.api.base.feature.permission.model.PermissionDto;

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
