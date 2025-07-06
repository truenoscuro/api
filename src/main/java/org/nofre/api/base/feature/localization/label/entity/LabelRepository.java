package org.nofre.api.base.feature.localization.label.entity;

import org.nofre.api.base.common.crud.entity.CommonRepository;
import org.nofre.api.base.observability.annotation.ObservedRepository;

@ObservedRepository
public interface LabelRepository extends CommonRepository<LabelEntity> {
}
