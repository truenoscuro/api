package org.nofre.api.base.feature.permission.entity;

import org.nofre.api.base.common.crud.repository.CommonRepository;
import org.nofre.api.base.observability.annotation.ObservedRepository;

@ObservedRepository
public interface PermissionRepository extends CommonRepository<PermissionEntity> {

}
