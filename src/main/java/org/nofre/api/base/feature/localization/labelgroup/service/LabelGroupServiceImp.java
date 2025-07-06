package org.nofre.api.base.feature.localization.labelgroup.service;

import org.nofre.api.base.common.crud.repository.specification.GenericSpecification;
import org.nofre.api.base.common.crud.service.CommonCrudServiceImp;
import org.nofre.api.base.common.crud.service.CrudService;
import org.nofre.api.base.feature.localization.labelgroup.LabelGroupMapper;
import org.nofre.api.base.feature.localization.labelgroup.LabelGroupService;
import org.nofre.api.base.feature.localization.labelgroup.entity.LabelGroupEntity;
import org.nofre.api.base.feature.localization.labelgroup.entity.LabelGroupRepository;
import org.nofre.api.base.feature.localization.labelgroup.model.LabelGroupDto;

@CrudService
public class LabelGroupServiceImp extends CommonCrudServiceImp<
        LabelGroupEntity,
        LabelGroupDto,
        LabelGroupMapper,
        LabelGroupRepository
        > implements LabelGroupService {
    public LabelGroupServiceImp(LabelGroupRepository repository,
                                LabelGroupMapper mapper,
                                GenericSpecification<LabelGroupEntity> specification) {
        super("label_groups", repository, mapper, specification);
    }

}
