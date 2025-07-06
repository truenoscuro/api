package org.nofre.api.base.feature.rolepermission.permission.entity;

import org.nofre.api.base.common.crud.entity.CommonRepository;
import org.nofre.api.base.observability.annotation.ObservedRepository;

@ObservedRepository
public interface PermissionRepository extends CommonRepository<PermissionEntity> {

}
